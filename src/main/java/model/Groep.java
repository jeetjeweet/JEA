package model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Groep {
    @Id
    @GeneratedValue
    private long id;
    @Size(min = 5, max = 100, message = "veld mag niet langer zijn dan 100")
    private String name;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Person> friendslist = new ArrayList<>();

    public List<Person> getFriendslist() {
        return friendslist;
    }

    public void setFriendslist(List<Person> friendslist) {
        this.friendslist = friendslist;
    }

    public Groep(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
