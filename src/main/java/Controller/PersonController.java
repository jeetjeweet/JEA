package Controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import dao.PersonDAO;
import jwt.Role;
import model.Person;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Path("/Person")
public class PersonController {
    @EJB
    private PersonDAO personDAO;

    @GET
    @Path("/getSelf")
    public Person getSelf(@Context HttpHeaders headers) throws UnsupportedEncodingException {
        long id;
        DecodedJWT decodedJWT = personDAO.verifyJWT(headers.getRequestHeader("token").get(0));
        id= decodedJWT.getClaim("ID").asLong();
        Person p = personDAO.findByID(id);
        if(p.getAuthenticationKey() == null){
            p.setAuthenticationKey("");
        }
        return p;
    }

    @POST
    public void addPerson(@FormParam("name") String name, @FormParam("password") String password){
        Person newPerson = new Person(name, password);
        newPerson.setRole(Role.User);
        System.out.println("addPerson " + name + " pw: " + password);
        personDAO.save(newPerson);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPerson(){
        return personDAO.getAll();
    }

    @GET
    @Path("/authKey")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String generateGoogleAuthKey(@Context HttpHeaders headers) throws UnsupportedEncodingException {
        return personDAO.generateAuthKey(headers.getRequestHeader("token").get(0));
    }


}
