package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
// определяем корневой элемент
@XmlRootElement(name = "Person")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"id", "firstName", "lastName"})
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
//    private List<Address> addresses;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person() {
    }
    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    @Override
    public String toString() {
        return "Person{" +
                "Id=" + id +
                ", FirstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                '}';
    }


}
