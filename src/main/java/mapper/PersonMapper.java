package mapper;

import model.Person;
import utils.MyBatisUtil;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.Map;

public interface PersonMapper {

    //@Insert("INSERT INTO public.\"Output\"(\"ID\", \"FirstName\", \"LastName\")VALUES (#{id}, #{firstName}, #{lastName});")
    @Insert("INSERT INTO output(first_name, last_name)VALUES(#{firstName}, #{lastName})")
    public void save(Person person);

    @Update("Update person set first_name = 'bolintiago' where id=1")
    public void updatePerson(Person person);

    @Delete("Delete from Person where personId=#{personId}")
    public void deletePersonById(Integer personId);

    @Select("SELECT * FROM public.\"Person\"")
    Person getPerson(Integer personId);

    @Select("SELECT id, first_name, last_name FROM public.person where id =#{id}")
    @Results(value = {  @Result(property = "id", column = "id"),
                        @Result(property = "firstName", column = "first_name"),
                        @Result(property = "lastName", column = "last_name")


    })
    public Person getPersonById(Integer id);

//    @Select("select addressId,streetAddress,personId from address where personId=#{personId}")
//    public Address getAddresses(Integer personId);

    @Select("select * from Person ")
    @MapKey("personId")
    Map<Integer, Person> getAllPerson();

    @SelectProvider(type = MyBatisUtil.class, method = "getPersonByName")
    public Person getPersonByName(String name);

    @Select(value = "{ CALL getPersonByProc( #{personId, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public Person getPersonByProc(Integer personId);

}