package Controller;

import dao.PersonDAO;
import jwt.Role;
import model.Person;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/Person")
public class PersonController {
    @Inject
    private PersonDAO personDAO;

    @POST
    public void addPerson(@FormParam("name") String name, @FormParam("password") String password){
        Person newPerson = new Person(name, password);
        newPerson.setRole(Role.User);
        System.out.println("addPerson " + name + " pw: " + password);
        personDAO.save(newPerson);
    }

    @GET
    @Path("/authKey")
    @Produces("application/json")
    public void generateGoogleAuthKey(){

    }
}
