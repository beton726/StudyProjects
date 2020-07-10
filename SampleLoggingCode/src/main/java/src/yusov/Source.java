package src.yusov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Source {

    private static final Logger logger = LoggerFactory.getLogger(Source.class.getName());

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 2; i++) {
            logger.info("Message for info");
            logger.debug("Message for debug");
            try {
                logger.warn("Внимание!");
                System.out.println(12/0);
            } catch (ArithmeticException x) {
                logger.error("Произошла арифметическая ошибка!", x);
            }
        }


    }
}