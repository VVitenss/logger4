package org.danil.logger4;

public class Logger {
    private static Logger logger;//Это у нас синглтон
    public int LOG_TO_FILE=1;//это просто для конфигурации более понятной
    public int LOG_TO_CONSOLE=2;

    private static LoggerStrategy loggerStrategy;//Включаем стратегию для записи в файл ну или в консоль

    private Logger(){
        loggerStrategy=new LogToConsole();//По умолчанию пишем в консоль
    }

    public static Logger getInstance(){//Это у нас тоже для синглтона чтобы создавать только 1 раз
        if(logger==null){
            logger=new Logger();
        }

        return logger;
    }

    public void setLoggerStrategy(int type){ //Метод для переключения стратегии
        if(type==Logger.logger.LOG_TO_FILE){
            loggerStrategy=new LogToFile();
        }else if(type==Logger.logger.LOG_TO_CONSOLE){
            loggerStrategy=new LogToConsole();
        }else{
            throw new IllegalArgumentException("Неподдерживаемая стратегия");
        }
    }


    public void logError(String message) { //Метод создания лога ошибок
        Log log=LogFactory.createLog(LogFactory.LOG_ERROR,message); //Через фабрику собираем сам объект лога
        loggerStrategy.createLog(log);//и пишем уже его туда куда нужно (в зависимости от выбранной стратегии)
    }

    public void logError(String message,String sessionId) { //Метод создания лога ошибок
        Log log=LogFactory.createLog(LogFactory.LOG_ERROR,message,sessionId); //Через фабрику собираем сам объект лога
        loggerStrategy.createLog(log);//и пишем уже его туда куда нужно (в зависимости от выбранной стратегии)
    }

    public void logError(String message,String sessionId,String userData) { //Метод создания лога ошибок
        Log log=LogFactory.createLog(LogFactory.LOG_ERROR,message,sessionId,userData); //Через фабрику собираем сам объект лога
        loggerStrategy.createLog(log);//и пишем уже его туда куда нужно (в зависимости от выбранной стратегии)
    }



    public void logInfo(String message) { //Метод создания лога инфо
        Log log=LogFactory.createLog(LogFactory.LOG_INFO,message);
        loggerStrategy.createLog(log);
    }

    public void logInfo(String message,String sessionId) { //Метод создания лога инфо
        Log log=LogFactory.createLog(LogFactory.LOG_INFO,message,sessionId);
        loggerStrategy.createLog(log);
    }

    public void logInfo(String message,String sessionId,String userData) { //Метод создания лога инфо
        Log log=LogFactory.createLog(LogFactory.LOG_INFO,message,sessionId,userData);
        loggerStrategy.createLog(log);
    }


    public void logWarning(String message) { //Метод создания лога предупреждений
        Log log=LogFactory.createLog(LogFactory.LOG_WARNING,message);
        loggerStrategy.createLog(log);
    }

    public void logWarning(String message,String sessionId) { //Перегружам на 2 аргумента
        Log log=LogFactory.createLog(LogFactory.LOG_WARNING,message,sessionId);
        loggerStrategy.createLog(log);
    }

    public void logWarning(String message,String sessionId,String userData) { //Перегружам на 3 аргумента
        Log log=LogFactory.createLog(LogFactory.LOG_WARNING,message,sessionId,userData);
        loggerStrategy.createLog(log);
    }
}
