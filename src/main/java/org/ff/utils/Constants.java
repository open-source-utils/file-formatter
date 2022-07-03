package org.ff.utils;

import java.text.SimpleDateFormat;

public class Constants {
    public static final String FILE_DIR = "/DSVS/";
    public static final String OUTPUT = "/output/";
    public static final String PATH = "path";
    public static final String FILE_NAMES = "names";
    public static final String DELIMITERS = "delimiters";

    private static final String TARGET_FORMAT = "yyyy-MM-dd";

    public static final SimpleDateFormat TARGET_SDF = new SimpleDateFormat(TARGET_FORMAT);

    public static final String[] ALLOWED_FORMATS = new String[]{"MM/dd/yyyy hh:mm:ss.sss", "MM/dd/yyyy hh:mm:ss", "yyyy-MM-dd hh:mm:ss.sss", "yyyy-MM-dd hh:mm", "dd/MM/yyyy", "dd-MM-yyyy", "MM-dd-yyyy", "ddMMyyyy", "yyyy/MM/dd", TARGET_FORMAT};
}
