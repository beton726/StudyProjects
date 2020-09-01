package src.devMqPlug.manager;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.common.CommonConstants;
import src.devMqPlug.listener.MsgListener;
import src.devMqPlug.properties.MQ_Properties;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Queue;

/**
 * Класс отвечает за инициализацию параметров подключения, за открытие и закрытие коннекта
 *
 * Места вызовов:
 * TFS_Listener - создание объекта, открытие и закрытие коннекта
***/
public class MQ_Manager {

    private QueueConnection con;
    private String queueReq;
    private String queueResp;
//    private String queueResp2;
    private QueueReceiver receiver;

    public MQ_Manager(MQ_Properties prop) throws JMSException {

        MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
        factory.setIntProperty(CommonConstants.WMQ_CONNECTION_MODE, CommonConstants.WMQ_CM_CLIENT);
        factory.setCCSID(1251);
        factory.setChannel(prop.getCHANNEL());
        factory.setHostName(prop.getHOST_NAME());
        factory.setPort(prop.getPORT());
        factory.setQueueManager(prop.getQUEUE_MANAGER());
        con = factory.createQueueConnection();
        queueReq = prop.getQUEUE_REQ();
        queueResp = prop.getQUEUE_RESP();   // Получаем очередь для прослушки
//        queueResp2 = prop.getQUEUE_RESP2(); // Получает две очереди

    }
    // Открытие коннекта
    public void start() throws JMSException {
        con.start();
    }
    // Закрытие коннекта
    public void stop() throws JMSException {
        con.stop();
    }
    // Создание ресивера(приёмника)
    public void crReceiver() throws JMSException {
        // Создание объекта слушателя (что прослушиваем)
        MessageListener listener = new MsgListener(con, queueResp); // Указываем очереди, куда будем отправлять сообщения
        // Создание сессии
        QueueSession session = con.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        // Создание объекта очереди
        Queue queue = session.createQueue(queueReq); // слушаем очередь эту
        // Создание ресивера
        receiver = session.createReceiver(queue);
        // Установка ресивера
        receiver.setMessageListener(listener);
    }
    // Закрытие ресивера
    public void closeReciever() throws JMSException {
        receiver.close();
    }

}