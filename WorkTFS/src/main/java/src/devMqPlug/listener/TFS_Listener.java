package src.devMqPlug.listener;

import src.devMqPlug.manager.MQ_Manager;
import src.devMqPlug.properties.MQ_Properties;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.servlet.ServletContextEvent;

/**
 * Класс системный листенер, инициирующий запуск ресивера с прослушиванием
 *
 * Места вызовов:
 * MQ_Properties - использование Logger
***/
public class TFS_Listener implements javax.servlet.ServletContextListener {

    final static Logger logger = Logger.getLogger(TFS_Listener.class.getName());
    public static MQ_Manager connectMQ;
    // Метод инициализации
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            // Инициализация идентификатора данных
            MQ_Properties prop = new MQ_Properties();
            // Инициализация соединения с MQ
            connectMQ = new MQ_Manager(prop);
            // Создание ресивера(получателя) с прослушивателем и сендером
            connectMQ.crReceiver();
            // Открытие коннекта (начало работы по прослушиванию, вычитке и отправки сообшений)
            connectMQ.start();
        } catch (JMSException | IOException e) {logger.log(Level.FINE, e.getMessage());}
    }
    // Метод постобработки
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            // Закрытие ресивера
            connectMQ.closeReciever();
            // Закрытие коннекта
            connectMQ.stop();
        } catch (JMSException e) {logger.log(Level.FINE, e.getMessage());}
    }
}