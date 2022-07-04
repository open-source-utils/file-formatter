package org.ff.utils;

import org.ff.config.BaseTestCase;
import org.ff.connectors.ProcessConnector;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DirUtilsTest extends BaseTestCase {

    @InjectMocks
    DirUtils dirUtils;

    @Test
    public void getJarDirectory() {
        // when
        String directoryPath = dirUtils.getJarDirectory();
        //then
        assertTrue(directoryPath.length()>0);
    }

}
