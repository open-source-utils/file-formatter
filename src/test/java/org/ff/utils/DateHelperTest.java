package org.ff.utils;

import org.ff.config.BaseTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class DateHelperTest extends BaseTestCase {
    @InjectMocks
    DateHelper dateHelper;

    @Test
    public void isDateValid() {
        // given
        String date = "10-02-1994";
        // when
        SimpleDateFormat result = dateHelper.isDateValid(date);
        //then
        assertNotNull(result);
    }


    @Test
    public void isDateValid_null() {
        // given
        String date = "02/22/1994";
        // when
        SimpleDateFormat result = dateHelper.isDateValid(date);
        //then
        assertNull(result);
    }


    @Test(expected = RuntimeException.class)
    public void parse_exception() {
        // given
        String date = "02/22/1994";
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // when
        String result = dateHelper.parse(date, sdf);
        //then
        assertNull(result);
    }

    @Test
    public void parse() {
        // given
        String date = "10-02-1994";
        String format = "dd-MM-yyyy";
        String expected = "1994-02-10";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // when
        String result = dateHelper.parse(date, sdf);
        //then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    public void generateFileSuffix() {
        // given
        String expected = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // when
        String result = dateHelper.generateFileSuffix();
        //then
        assertNotNull(result);
        assertTrue(result.startsWith(expected));
    }


}
