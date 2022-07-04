package org.ff.utils;

import org.ff.Application;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class DirUtils {
    public static String getJarDirectory() {
        CodeSource codeSource = Application.class.getProtectionDomain().getCodeSource();
        String directoryPath = null;
        try {
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            directoryPath = jarFile.getParentFile().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return directoryPath;
    }
}
