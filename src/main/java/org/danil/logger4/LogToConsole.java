package org.danil.logger4;

public class LogToConsole implements LoggerStrategy{
    @Override
    public void createLog(Log log) {
        System.out.println(log.getCompleteMessage());
    }
}
