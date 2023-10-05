package org.danil.logger4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {
        try {
            new Config();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Logger logger=Logger.getInstance();
        logger.setLoggerStrategy(logger.LOG_TO_FILE);
        logger.logInfo("try info!",null,"name:danil");

    }
}