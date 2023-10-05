package org.danil.logger4;

public class LogFactory {
    public static int LOG_INFO=1;
    public static int LOG_ERROR=1;
    public static int LOG_WARNING=3;

    public static Log createLog(int logLevel,String message){
        if(logLevel==LogFactory.LOG_INFO){
            return new LogInfo(message);
        }else if(logLevel==LogFactory.LOG_ERROR){
            return new LogError(message);
        }else if(logLevel==LogFactory.LOG_WARNING){
            return new LogWarning(message);
        }else{
            throw new IllegalArgumentException("Неподдерживаемый тип");
        }
    }

    public static Log createLog(int logLevel,String message,String sessionId){
        Log log;

        if(logLevel==LogFactory.LOG_INFO){
            log= new LogInfo(message);
        }else if(logLevel==LogFactory.LOG_ERROR){
            log= new LogError(message);
        }else if(logLevel==LogFactory.LOG_WARNING){
            log= new LogWarning(message);
        }else{
            throw new IllegalArgumentException("Неподдерживаемый тип");
        }
        if(sessionId!=null){
            log=new LogWithSessionId(log,sessionId);
        }
        return log;
    }

    public static Log createLog(int logLevel,String message,String sessionId,String userData){
        Log log;

        try {
            if (logLevel == LogFactory.LOG_INFO) {
                log = new LogInfo(message);
            } else if (logLevel == LogFactory.LOG_ERROR) {
                log = new LogError(message);
            } else if (logLevel == LogFactory.LOG_WARNING) {
                log = new LogWarning(message);
            } else {
                throw new IllegalArgumentException("Неподдерживаемый тип");
            }
            if (sessionId != null) {
                log = new LogWithSessionId(log, sessionId);
            }

            if (userData != null) {
                log = new LogWithUserData(log, userData);
            }
            return log;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
