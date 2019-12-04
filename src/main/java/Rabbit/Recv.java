package Rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import model.Person;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import utils.MyBatisUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import static JAXBwoker.JaxbWoker.fromXmlToObject;

public class Recv {
    private static String outputFileName = "C:\\Users\\rusdbb\\IdeaProjects\\output.xml";
    private final static String QUEUE_NAME = "hello";
    static String message = "";

    public static void recoveryXmlAndWriteToFile() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            if (message != "") {
                Document document = stringToDocument(message);
                try {
                    docToXml(document, outputFileName); // записываем xml в файл
                } catch (TransformerException e) {
                    e.printStackTrace();

                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Person unmarshPerson = fromXmlToObject(outputFileName);         // восстанавливаем объект из XML файла
                if (unmarshPerson != null) {
                    System.out.println(unmarshPerson.toString());
                }

                MyBatisUtil.getPersonMapper().save(unmarshPerson); //save to output

            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });

    }

    private static Document stringToDocument(String xmlStr) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = builderFactory.newDocumentBuilder();
            // парсим переданную на вход строку с XML разметкой
            return docBuilder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void docToXml(Document document, String outputFileName) throws IOException, TransformerException {
        DOMSource source = new DOMSource(document);
        FileWriter writer = new FileWriter(new File(outputFileName));
        StreamResult result = new StreamResult(writer);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);

    }
}
