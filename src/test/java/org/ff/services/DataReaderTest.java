package org.ff.services;

import org.ff.config.BaseTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;

public class DataReaderTest extends BaseTestCase {

    @InjectMocks
    DataReader dataReader;

    @Test
    public void processFiles() {
        String inputPath = "src/test//resources/input/DSV-input-1.txt";
        char delimiter = ',';
        String outputPath = "src/test/resources/output/DSV-output-1.txt";
        dataReader.processFiles(inputPath, delimiter, outputPath);

    }
}
