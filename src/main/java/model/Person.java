package model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.findOne", query = "select p from model.Person p where p.name = :name and p.password = :password"),
        @NamedQuery(name = "Person.getAll", query = "select p from model.Person p")
})
public class Person {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    private String password;

    @Email(message = "Enter valid email")
    private String email;

    @ManyToMany(mappedBy = "friendslist")
    private List<Groep> groepList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person(String name, String password) {
        setName(name);
        setPassword(password);
    }

    public void send(Message messageToSend){

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Groep> getGroepList() {
        return groepList;
    }

    public void setGroepList(List<Groep> groepList) {
        this.groepList = groepList;
    }
}
