package org.ff.services;

import org.ff.config.BaseTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.HashMap;
import java.util.Map;

public class DataWriterTest extends BaseTestCase {

    @InjectMocks
    DataWriter dataWriter;

    @Test
    public void appendToFile() {
//        given
        Map<String, String> input = new HashMap<>();
        input.put("vishal", "10");
        input.put("sachin", "30");
        input.put("vaibhav", "20");
        String outputFileName = "src/test/resources/output/map-output.txt";
//        when
        dataWriter.appendToFile(  outputFileName, input);

    }
}
