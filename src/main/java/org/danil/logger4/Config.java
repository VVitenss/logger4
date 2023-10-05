package org.danil.logger4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static String DEFAUTL_PATH;
    private static Double DEFAULT_FILE_SIZE_MB;
    private static String DEFAULT_DATE_FORMAT_LOG;
    private static String DEFAULT_DATE_FORMAT_FILE;

    public Config() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(new File("aplication.properties")));

        DEFAUTL_PATH = props.getProperty("DEFAUTL_PATH", "logs/");
        DEFAULT_FILE_SIZE_MB = Double.valueOf(props.getProperty("DEFAULT_FILE_SIZE_MB", "100.0"));
        DEFAULT_DATE_FORMAT_LOG = props.getProperty("DEFAULT_DATE_FORMAT_LOG","yyyy-MM-dd HH:mm:ss SSS");
        DEFAULT_DATE_FORMAT_FILE = props.getProperty("DEFAULT_DATE_FORMAT_FILE","yyyy-MM-dd");
    }

    public static String getDefautlPath() {
        return DEFAUTL_PATH;
    }

    public static Double getDefaultFileSizeMb() {
        return DEFAULT_FILE_SIZE_MB;
    }

    public static String getDefaultDateFormatLog() {
        return DEFAULT_DATE_FORMAT_LOG;
    }

    public static String getDefaultDateFormatFile() {
        return DEFAULT_DATE_FORMAT_FILE;
    }
}
