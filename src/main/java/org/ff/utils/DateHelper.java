package org.ff.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.ff.utils.Constants.ALLOWED_FORMATS;
import static org.ff.utils.Constants.TARGET_SDF;

public class DateHelper {

    public static SimpleDateFormat isDateValid(String dateValue) {
        SimpleDateFormat returnVal = null;
        if (dateValue == null || dateValue.isEmpty()) {
            return null;
        }
        for (int i = 0; i < ALLOWED_FORMATS.length; i++) {
            try {
                returnVal = null;
                SimpleDateFormat sdfObj = new SimpleDateFormat(ALLOWED_FORMATS[i]);
                sdfObj.setLenient(false);
                sdfObj.parse(dateValue);
                returnVal = sdfObj;
                break;
            } catch (ParseException e) {
            }
        }
        return returnVal;
    }


    public static String parse(String dateString, SimpleDateFormat dateFormatter) {
        String formattedDate;
        try {
            Date date = dateFormatter.parse(dateString);
            formattedDate = TARGET_SDF.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return formattedDate;
    }

    public static String generateFileSuffix(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}