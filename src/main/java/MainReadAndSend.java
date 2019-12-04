import Rabbit.Send;
import mapper.PersonMapper;
import model.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import utils.MyBatisUtil;

import java.io.IOException;
import java.io.Reader;

import static JAXBwoker.JaxbWoker.*;
import static Rabbit.Recv.recoveryXmlAndWriteToFile;
import static Rabbit.Send.sentXMLtoRabbit;

public class MainReadAndSend {
    public static void main(String[] args) throws Exception {

        PersonMapper personMapper = MyBatisUtil.getPersonMapper();
        Person person = personMapper.getPersonById(1);
        System.out.println(person);
        convertObjectToXml(person, fileName);       // сохраняем объект в XML файл
        sentXMLtoRabbit(fileName);
    }
}
