package Rabbit;
import JAXBwoker.JaxbWoker;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class Send {
    private final static String QUEUE_NAME = "hello";
    static String contents = "";
    public static void sentXMLtoRabbit(String fileName) throws Exception {

        contents = readUsingScanner(fileName);
        System.out.println(contents);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, contents.getBytes());
            System.out.println(" [x] Sent '" + contents + "'");

        }
    }

    public static String readUsingScanner(String fileName) throws IOException {
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
        String data = scanner.useDelimiter("\\A").next();
        //data.replaceAll("\\n", "");
        scanner.close();
        return data;
    }
}