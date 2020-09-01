package src.devMqPlug.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Класс отвечает за формирование основных тегов сообщения (корневой узел, rquid,
 * rqtm, status, statuscode)
 *
 * Места вызовов:
 * MsgListener - получение сообщения овтета и очереди
***/
public class Processing {

    private static String response = null;
    private static String respqueue = null;
    // Получение сообщения ответа
    public static String getResponse(String request) throws JDOMException, IOException {
        // Объявление переменных и объектов для работы с xml
        XMLOutputter xmlOut = new XMLOutputter(Format.getCompactFormat().setOmitDeclaration(true));
        // Чтение переданного текста сообщения
        SAXBuilder builder = new SAXBuilder();
        Document reqDoc = builder.build(new ByteArrayInputStream(request.getBytes("UTF-8")));
        // Ветвление при формировании сообщения ответа (формируются основные теги сообщения (корневой узел, rquid, rqtm, status, statuscode))
        switch (reqDoc.getRootElement().toString()) {
            // tfs-uploadfile-out-adapter
            case "1":
                response = xmlOut.outputString(DeleteIDSBOLRq.gerDeleteIDSBOLRq(reqDoc));
                break;
            // tfs-downloadfile-out-adapter
            case "2":
                response = xmlOut.outputString(DeleteIDSBOLRq.gerDeleteIDSBOLRq(reqDoc));
                break;
            // tfs-srvfiletransfermgmtecm-out-adapter
            case "3":
                response = xmlOut.outputString(DeleteIDSBOLRq.gerDeleteIDSBOLRq(reqDoc));
                break;
            // tfs-srvfiletransfermgm-out-adapter
            case "4":
                response = xmlOut.outputString(DeleteIDSBOLRq.gerDeleteIDSBOLRq(reqDoc));
                break;
            default:
                response = "";
                break;
        }
        return response;
    }
    // Получение очереди сообщения ответа
    public static String getRespQueue(String request) throws JDOMException, IOException {
        // Чтение переданного текста сообщения
        SAXBuilder builder = new SAXBuilder();
        Document reqDoc = builder.build(new ByteArrayInputStream(request.getBytes("UTF-8")));
        // Ветвление при формировании очереди сообщения ответа
        switch (reqDoc.getRootElement().toString()) {
            case "1":
                respqueue = "queue1";
                break;
            default:
                respqueue = "";
                break;
        }
        return respqueue;
    }
}