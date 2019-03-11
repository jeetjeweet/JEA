package resource;

import dao.PersonDAO;
import model.Person;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("Person")
public class PersonResource {

    @Inject
    private PersonDAO personDao;

    @GET
    @Produces("application/json")
    public List<Person> all() {
        return null;
    }

    @POST
    @Consumes("application/json")
    public void save(Person person) {
    }

    @PUT
    @Consumes("application/json")
    public void update(Person person) {


    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id) {

    }
}