package JAXBwoker;

import mapper.PersonMapper;
import model.Person;
import org.apache.ibatis.session.SqlSessionFactory;
import utils.MyBatisUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbWoker {
    public static String fileName = "C:\\Users\\rusdbb\\IdeaProjects\\person.xml";
    public static String outputFileName = "C:\\Users\\rusdbb\\IdeaProjects\\output.xml";

//    public static void main(String[] args) {
//        // определяем название файла, куда будем сохранять
//
//        SqlSessionFactory sqlSessionFactory = MyBatisUtil.buildSqlSessionFactory();
//        PersonMapper personMapper = sqlSessionFactory.openSession(true).getMapper(PersonMapper.class);
//        Person person = personMapper.getPersonById(1);
//        System.out.println(person);
//        convertObjectToXml(person, fileName);           // сохраняем объект в XML файл
//
//        Person unmarshPerson = fromXmlToObject(outputFileName);         // восстанавливаем объект из XML файла
//        if (unmarshPerson != null) {
//            System.out.println(unmarshPerson.toString());
//        }
//
//        //personMapper.save(unmarshPerson); //save to output
//
//
//    }


    // восстанавливаем объект из XML файла
    public static Person fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Person) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // сохраняем объект в XML файл
    public static void convertObjectToXml(Person person, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(person, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
