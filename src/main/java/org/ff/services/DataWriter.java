package org.ff.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class DataWriter {
    public static void appendToFile(String fileName,
                                    Map<String, String> data) {
        ObjectMapper mapper = new ObjectMapper();
        File file= new File(fileName);

        try {
            final File parentDirectory = file.getParentFile();
            if (parentDirectory != null)
            {
                parentDirectory.mkdirs();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.append(System.lineSeparator());
            writer.write(mapper.writeValueAsString(data));
            writer.close();
        } catch (IOException e) {
            System.out.println("exception occurred while writing to " + e);
        }
    }
}
