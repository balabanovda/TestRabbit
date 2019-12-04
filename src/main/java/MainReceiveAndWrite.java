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

public class MainReceiveAndWrite {
    public static void main(String[] args) throws Exception {

            recoveryXmlAndWriteToFile();
//            Thread.sleep(2000);
//            Person unmarshPerson = fromXmlToObject(outputFileName);         // восстанавливаем объект из XML файла
//            if (unmarshPerson != null) {
//                System.out.println(unmarshPerson.toString());
//            }
//
//            MyBatisUtil.getPersonMapper().save(unmarshPerson); //save to output

    }
}
