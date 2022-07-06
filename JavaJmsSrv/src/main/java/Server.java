import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Server implements MessageListener {

    private QueueSession writeSession;
    private QueueSession readSession;
    private QueueSender writer;
    private QueueReceiver reader;

    public Server(String host, String requestQ, String responseQ) throws Exception {
        System.out.println("Java Server opened at " + host + ":61616\nRequest: " + requestQ + "\nResponse: " + responseQ);
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.setProperty("java.naming.provider.url", "tcp://" + host + ":61616");
        properties.setProperty("queue." + requestQ, requestQ);
        properties.setProperty("queue." + responseQ, responseQ);

        Context ctx = new InitialContext(properties);
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
        javax.jms.Queue queueReq = (javax.jms.Queue) ctx.lookup(requestQ);
        javax.jms.Queue queueRes = (javax.jms.Queue) ctx.lookup(responseQ);
        QueueConnection connection = connectionFactory.createQueueConnection();
        writeSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        readSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        writer = writeSession.createSender(queueRes);
        reader = readSession.createReceiver(queueReq);
        reader.setMessageListener(this);
        connection.start();
    }

    @Override
    public void onMessage(Message message) {
        String req = "";
        String res = "";
        try {
            req = ((TextMessage) message).getText();
        } catch (Exception e) {
            try {
                BytesMessage bytesMessage = (BytesMessage) message;
                byte[] bytes = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(bytes);
                req = new String(bytes);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            Controller controller = new Controller();

            String[] msg = (req + "|0|0|0").split("[|]");
            switch (msg[0]) {
                case "ceva":
                    res += "am trimis ceva";
                    break;
                case "addStudent":
                    controller.addStudent(msg[1], msg[2], msg[3]);
                    res += "student adaugat";
                    break;
                case "editStudent":
                    controller.editStudent(Integer.parseInt(msg[1]), msg[2], msg[3], msg[4]);
                    res += "modificat student";
                    break;
                case "remStudent":
                    controller.remStudent(Integer.parseInt(msg[1]));
                    res += "sters student";
                    break;
                case "addMaterie":
                    controller.addMaterie(msg[1], msg[2]);
                    res += "adaugat materie";
                    break;
                case "editMaterie":
                    controller.editMaterie(Integer.parseInt(msg[1]), msg[2], msg[3]);
                    res += "modificat materie";
                    break;
                case "remMaterie":
                    controller.remMaterie(Integer.parseInt(msg[1]));
                    res += "sters materie";
                    break;
                case "addNota":
                    controller.addNota(Integer.parseInt(msg[1]), msg[2], Integer.parseInt(msg[3]), Integer.parseInt(msg[4]));
                    res += "adaugat nota";
                    break;
                case "editNota":
                    controller.editNota(Integer.parseInt(msg[1]), Integer.parseInt(msg[2]), msg[3]);
                    res += "modificat nota";
                    break;
                case "exit":
                    res += "s-a deconectat clientul";
                    break;
                case "listCatalog":
                    res += controller.listCatalog();
                    break;
                default:
                    res += "Apel eronat.";
                    break;
            }
            TextMessage textMessage = writeSession.createTextMessage();
            textMessage.setText(req + ": " + res);
            System.out.println(textMessage.getText());
            writer.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
