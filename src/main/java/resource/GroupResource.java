package resource;

import dao.PersonDAO;
import model.Groep;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("Groups")
public class GroupResource {

    @Inject
    private PersonDAO personDao;

    @GET
    @Produces("application/json")
    public List<Groep> all() {
        return null;
    }

    @POST
    @Consumes("application/json")
    public void save(Groep groep) {
    }

    @PUT
    @Consumes("application/json")
    public void update(Groep groep) {


    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id) {

    }
}
