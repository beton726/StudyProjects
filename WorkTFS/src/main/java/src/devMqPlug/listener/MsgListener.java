package src.devMqPlug.listener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import org.jdom2.JDOMException;
import src.devMqPlug.xml.Processing;

/**
 * Класс отвечает за прослушивание очередей mq, получение сообщений и формирование/отправки
 * ответа
 *
 * Места вызовов:
 * ConnectionMQManager - создание объекта, поступление сообщения в очередь
***/
public class MsgListener implements MessageListener {

    private QueueSender sender;
    private QueueSession sessionSender;
    private QueueConnection con;
    private String queueResp;
    private String queueResp1;
//    private String queueResp2;

    public MsgListener(QueueConnection con, String queueResp) {
        this.con = con;
        this.queueResp = queueResp;
        this.queueResp1 = queueResp1;
//        this.queueResp2 = queueResp2;
    }
    // Метод описывающий действия при получении сообщения
    @Override
    public void onMessage(Message requestMsg) {
        try {
            // Объявление переменных
            TextMessage txtMessageRq = (TextMessage) requestMsg;
            // String respqueue = null;
            Queue queue_sender = null;
            // Получение сообщения ответа
            String responce = new String(Processing.getResponse(txtMessageRq.getText()).getBytes("Cp1251"), "UTF-8");
            // Получение очереди ответа
            // respqueue = new String(Processing.getRespQueue(txtMessageRq.getText()).getBytes("Cp1251"), "UTF-8");





            // Инициализация объектов sender.
            // Создание объекта сессии
            sessionSender = con.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            // Создание объекта очереди
            queue_sender = sessionSender.createQueue(queueResp);        // Куда отправляем сообщение




            TextMessage responseMsg = sessionSender.createTextMessage(responce);
            // Получение параметров из запроса rq
            String MessageId = requestMsg.getJMSMessageID();
            String GroupId = requestMsg.getStringProperty("GroupId");
            String MsgId = requestMsg.getStringProperty("MsgId");
            // Установка параметров сообщения ответа
            responseMsg.setJMSCorrelationID(MessageId);
            responseMsg.setStringProperty("GroupId", GroupId);
            responseMsg.setStringProperty("CorrelId", MsgId);
            // Отправка сообщения и закрытие
            sender.send(responseMsg);  // КУДА ОТПРАВЛЯЕТСЯ СООБЩЕНИЕ??? НА queueReq???
            sender.close();
            sessionSender.close();

            // Если запрос был tfs-..., то отправляем еще один запрос
//            if(responce.contains("Что то ищем")) {
//                // Получение сообщения ответа
//                responce = "123456";
//                // Создание объекта сессии
//                sessionSender = con.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
//                // Создание объекта очереди
//                queue_sender = sessionSender.createQueue(queueResp); // Очередь, куда кидаем второе сообщение
//                // Создание sender
//                sender = sessionSender.createSender(queue_sender);
//                // Создание объекта сообщения ответа
//                responseMsg = sessionSender.createTextMessage(responce);
//                sender.send(responseMsg);
//                sender.close();
//                sessionSender.close();
//            }
        } catch (JMSException | IOException | JDOMException e) {
            e.printStackTrace();
        }
    }
}