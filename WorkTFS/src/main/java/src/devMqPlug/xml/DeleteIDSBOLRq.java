package src.devMqPlug.xml;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Класс отвечает за формирование основных тегов сообщения (корневой узел, rquid,
 * rqtm, status, statuscode)
 *
 * Места вызовов:
 * Processing - формирование сообщения ответа
***/
public class DeleteIDSBOLRq {

    public static Document gerDeleteIDSBOLRq(Document reqDoc) {
        Document respDoc = new Document(new Element("DeleteIDSBOLRs"));

        Element rootRs = respDoc.getRootElement();
        Element rootRq = reqDoc.getRootElement();

        rootRs.addContent(rootRq.getChild("RqUID").clone());
        rootRs.addContent(rootRq.getChild("RqTm").clone());

        Element Status = new Element("Status");

        Status.addContent(new Element("StatusCode").setText("-80"));
        Status.addContent(new Element("StatusDesc").setText("Officer"));
        rootRs.addContent(Status.clone());

        return respDoc;
    }

}