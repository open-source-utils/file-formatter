package org.ff.services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.ff.utils.DateHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
public class DataReader {
    @Async("threadPoolTaskExecutor")
    private static void readDataWithCustomSeparatorAndWrite(String content, char delimiter, List<String> fieldNames, String outputPath) {

        CSVParser parser = new CSVParserBuilder().withSeparator(delimiter)
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .build();
        try(CSVReader csvReader = new CSVReaderBuilder(new StringReader(content))
                        .withCSVParser(parser)
                        .build()) {
            List<String[]> allData = csvReader.readAll();
            Map<String, String> outputMap = new HashMap<>();
            mergeHeaderAndData(fieldNames, allData, outputMap);
            DataWriter.appendToFile(outputPath, outputMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mergeHeaderAndData(List<String> fieldNames, List<String[]> allData, Map<String, String> outputMap) {
        for (String[] row : allData) {
            if (row.length == 1 && row[0].isEmpty()) {
                continue;
            }
            Map<String, String> obj = new HashMap();
            for (int i = 0; i < fieldNames.size(); i++) {
                SimpleDateFormat simpleDateFormat = DateHelper.isDateValid(row[i]);
                String formattedDate = null;
                if (simpleDateFormat != null) {
                    formattedDate = DateHelper.parse(row[i], simpleDateFormat);
                }
                obj.put(fieldNames.get(i), formattedDate == null ? row[i] : formattedDate);
            }
            outputMap.putAll(obj);
        }
    }

    private static List<String> readHeader(String content, char delimiter) {

        List<String> fieldNames = null;
        CSVParser parser = new CSVParserBuilder().withSeparator(delimiter)
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .build();
        try(CSVReader csvReader = new CSVReaderBuilder(new StringReader(content))
                .withCSVParser(parser)
                .build()) {
            fieldNames = getFieldNames(csvReader.readAll());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldNames;
    }

    private static List<String> getFieldNames(List<String[]> allData) {
        boolean headerEncountered = false;
        List<String> fieldNames = null;
        for (String[] row : allData) {
            if (row.length == 1 && row[0].isEmpty()) {
                continue;
            }
            if (!headerEncountered && fieldNames == null) {
                fieldNames = new ArrayList(Arrays.asList(row));
                headerEncountered = true;
                break;
            }
        }
        return fieldNames;
    }

    public static void processFiles(String path, char delimiter, String outputPath) {
        long startTime = System.nanoTime();
        Path file = Paths.get(path);
        try(Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
            List<String> header = null;
            boolean headerEncountered = false;
            for (String line : (Iterable<String>) lines::iterator) {
                if (!headerEncountered) {
                    header = readHeader(line, delimiter);
                    headerEncountered = true;
                    continue;
                }
                readDataWithCustomSeparatorAndWrite(line, delimiter, header, outputPath);
            }

        } catch (IOException ioe) {
            System.out.println("Exception in readFiles " + ioe.getMessage());
        }

        long endTime = System.nanoTime();
        long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
        System.out.println("Total elapsed time: " + elapsedTimeInMillis + " ms in processing " + path);
    }


}
