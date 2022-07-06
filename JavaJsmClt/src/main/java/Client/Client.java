package Client;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;
import java.util.Scanner;

public class Client {

    public Client(String host, String requestQ, String responseQ) throws Exception {
        System.out.println("Client Java sent to " + host + ":61616\nRequest: " + requestQ + "\nResponse: " + responseQ);
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.setProperty("java.naming.provider.url", "tcp://" + host + ":61616");
        properties.setProperty("queue." + requestQ, requestQ);
        properties.setProperty("queue." + responseQ, responseQ);
        Context ctx = new InitialContext(properties);
        QueueConnectionFactory connFac = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
        javax.jms.Queue queueReq = (javax.jms.Queue) ctx.lookup(requestQ);
        javax.jms.Queue queueRes = (javax.jms.Queue) ctx.lookup(responseQ);
        QueueConnection conn = connFac.createQueueConnection();
        QueueSession writeSession = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        QueueSession readSession = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        QueueSender writer = writeSession.createSender(queueReq);
        QueueReceiver reader = readSession.createReceiver(queueRes);

        conn.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("> ");
            String stringInput = scanner.nextLine();
            TextMessage msg = writeSession.createTextMessage();
            msg.setText(stringInput);
            writer.send(msg);
            System.out.println(convertToText(reader.receive()));
            if (stringInput.equals("exit")) {
                break;
            }

        }

        conn.close();
    }


    public String convertToText(Message message) {
        String res = "";
        try {
            res = ((TextMessage) message).getText();
        } catch (Exception e) {
            try {
                BytesMessage bMsg = (BytesMessage) message;
                byte[] bytes = new byte[(int)bMsg.getBodyLength()];
                bMsg.readBytes(bytes);
                res = new String(bytes);
            } catch (Exception e2) {
                System.out.printf("Error for converting message: %s%n", e2);
            }
        }
        return res;
    }
}

