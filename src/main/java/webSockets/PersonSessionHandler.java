package webSockets;

import model.Person;

import javax.faces.bean.ApplicationScoped;
import javax.json.JsonObject;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class PersonSessionHandler {

    private Set<Session> sessions = new HashSet<>();
    private Set<Person> persons = new HashSet<>();

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    public List<Person> getPersons(){
        return new ArrayList<>(persons);
    }
    public void addPerson(Person person){

    }
    public void removePerson(){

    }
    public Person getPersonByName(String name){
        return null;
    }
    private JsonObject createAddMessage(Person person) {
        return null;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
    }

    private void sendToSession(Session session, JsonObject message) {
    }
}
