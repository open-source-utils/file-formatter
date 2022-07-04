package org.ff.connectors;


import org.ff.config.BaseTestCase;
import org.ff.utils.CommandLineParser;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessConnectorTest extends BaseTestCase {

    String arguments[] = {"JCG", "JCG"};

    @Mock
    private CommandLineParser commandLineParser = mock(CommandLineParser.class);
    @InjectMocks
    ProcessConnector processConnector;

    @Test
    public void initOperation() {
        // given
        when(commandLineParser.getArgumentValue("path")).thenReturn(arguments);
        when(commandLineParser.getArgumentValue("names")).thenReturn(arguments);
        when(commandLineParser.getArgumentValue("delimiters")).thenReturn(arguments);
        // when
        processConnector.initOperation(commandLineParser);
        //then
        ArgumentCaptor<CommandLineParser> captor = ArgumentCaptor.forClass(CommandLineParser.class);
        List<CommandLineParser> params = captor.getAllValues();
        assertEquals(params.size(), 0);
    }

}