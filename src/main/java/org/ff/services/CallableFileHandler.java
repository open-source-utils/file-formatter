package org.ff.services;

import java.util.concurrent.Callable;

public class CallableFileHandler implements Callable<Long> {

    private char delimiter;
    private String outputPath;
    private String filePath;
     

    public CallableFileHandler(char delimiter, String outputPath, String filePath) {
        this.delimiter = delimiter;
        this.outputPath = outputPath;
        this.filePath = filePath;
    }
 
 
    @Override
    public Long call() throws Exception {
        DataReader.processFiles(filePath, delimiter, outputPath);
        return 1l;
    }
 
}
