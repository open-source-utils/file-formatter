package org.ff;

import org.ff.services.DataReader;
import org.ff.utils.CommandLineParser;
import org.ff.utils.DirUtils;

import static org.ff.utils.Constants.*;
import static org.ff.utils.DateHelper.generateFileSuffix;

public class Main {

    public static void main(String[] args) {
        CommandLineParser clp = new CommandLineParser(args);
        initOperation(clp);
        System.out.println("Bingo!!!!");
    }

    private static void initOperation(CommandLineParser clp) {
        String path = clp.getArgumentValue(PATH)[0];
        if (path == null || path.isEmpty()) {
            path = DirUtils.getJarDirectory() + FILE_DIR;
        }
        String names[] = clp.getArgumentValue(FILE_NAMES);
        String delimiters[] = clp.getArgumentValue(DELIMITERS);

        if (delimiters.length == names.length) {
            for (int i = 0; i < names.length; i++) {
                char delimiter = delimiters[i].charAt(0);
                String outputPath = path + OUTPUT + names[i]+generateFileSuffix()+".json";
                String filePath = path + names[i];
                DataReader.readFiles(filePath, delimiter, outputPath);
            }
        } else {
            System.out.println("Please provide delimiters for all input files");
        }
    }


}