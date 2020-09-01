package src.devMqPlug.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Класс отвечает за инициализацию идентификационных данных
 *
 * Места вызовов:
 * TFS_Listener - создание объекта (прокидывается в ConnectionMQManager)
 * MQ_Manager - получение параметров
***/
public class MQ_Properties {

    private String HOST_NAME = null;
    private int PORT = 0;
    private String QUEUE_MANAGER = null;
    private String CHANNEL = null;
    private String QUEUE_REQ = null;
    private String QUEUE_RESP = null;
    private String QUEUE_RESP2 = null;

    public MQ_Properties() throws IOException {
        Properties props = new Properties();
        props.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("mq_properties.ini")));

        HOST_NAME = props.getProperty("HOST_NAME");
        PORT = Integer.parseInt(props.getProperty("PORT"));
        QUEUE_MANAGER = props.getProperty("QUEUE_MANAGER");
        CHANNEL = props.getProperty("CHANNEL");
        QUEUE_REQ = props.getProperty("QUEUE_REQ");
        QUEUE_RESP = props.getProperty("QUEUE_RESP");
        QUEUE_RESP2 = props.getProperty("QUEUE_RESP2");
    }

    public void setHOST_NAME(String HOST_NAME) {
        this.HOST_NAME = HOST_NAME;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public void setQUEUE_MANAGER(String QUEUE_MANAGER) {
        this.QUEUE_MANAGER = QUEUE_MANAGER;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public void setQUEUE_REQ(String QUEUE_REQ) {
        this.QUEUE_REQ = QUEUE_REQ;
    }

    public void setQUEUE_RESP(String QUEUE_RESP) {
        this.QUEUE_RESP = QUEUE_RESP;
    }

    public void setQUEUE_RESP2(String QUEUE_RESP2) {
        this.QUEUE_RESP2 = QUEUE_RESP2;
    }

    public String getHOST_NAME() {
        return HOST_NAME;
    }

    public int getPORT() {
        return PORT;
    }

    public String getQUEUE_MANAGER() {
        return QUEUE_MANAGER;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public String getQUEUE_REQ() {
        return QUEUE_REQ;
    }

    public String getQUEUE_RESP() {
        return QUEUE_RESP;
    }

    public String getQUEUE_RESP2() {
        return QUEUE_RESP2;
    }
}