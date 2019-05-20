package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@NamedQueries({@NamedQuery(name = "Message.getByID", query = "select m from model.Message m where m.id = :id")})
public class Message {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    private String creator;
    private Date datum;

    private ArrayList<Link> links = new ArrayList<>();

    public String getContent() {
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public Message(){

    }
    public Message(String content, String creator) {
        setContent(content);
        setCreator(creator);
        datum = new Date();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel){
        Link l = new Link();
        l.setLink(url);
        l.setRel(rel);
        links.add(l);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
