package src.yusov;

import java.io.IOException;
import java.util.logging.*;

public class Source {

    private static final Logger logger = Logger.getLogger(Source.class.getName());

    public static void main(String[] args) throws IOException {
        // Чтение конфигурации
        LogManager.getLogManager().readConfiguration();

        Handler fileHandler = new FileHandler();
        logger.setUseParentHandlers(false);     // отключает вывод в консоль
//        logger.addHandler(fileHandler);

        logger.info("info");


    }
}
