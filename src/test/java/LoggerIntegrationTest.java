import org.danil.logger4.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

public class LoggerIntegrationTest {

    @Test
    public void testLogToFileStrategy() {
        Logger logger = Logger.getInstance();
        logger.setLoggerStrategy(Logger.LOG_TO_FILE);

        // Создаем временный файл для записи логов
        File tempLogFile;
        try {
            tempLogFile = File.createTempFile("test_log", ".log");
        } catch (IOException e) {
            e.printStackTrace();
            fail("Не удалось создать временный файл для записи логов");
            return;
        }

        // Путь к временному файлу
        String tempLogFilePath = tempLogFile.getAbsolutePath();

        // Логируем сообщение
        logger.logInfo("try info!", null, "name:danil");

        // Проверяем, что файл существует и не пустой
        File logFile = new File(tempLogFilePath);
        assertTrue(logFile.exists());
        assertTrue(logFile.length() > 0);

        // Удаляем временный файл после выполнения теста
        if (tempLogFile.exists()) {
            tempLogFile.delete();
        }
    }
}