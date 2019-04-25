package jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dao.PersonDAO;
import model.Person;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/authentication")
public class AuthenticationController {
    @EJB
    PersonDAO personDAO;

    @Context
    private HttpServletResponse response;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response checkCredentials(@FormParam("name") String name, @FormParam("password") String password) {
        try{
            System.out.println(name + " " + password);
            Person person = personDAO.findOne(name,password);

            String token = generateToken(person);

            response.setHeader("token", token);

            return Response.ok().header("token", token).build();

        }catch (Exception e){
            e.printStackTrace();
            return Response.status(403).build();
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response){
//        try{
//            String name = request.getParameter("name");
//            String password = request.getParameter("password");
//
//            Person person = personDAO.findOne(name,password);
//
//            String token = generateToken(person);
//            response.setHeader("token", token);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    private String generateToken(Person person){
        try{
            Algorithm algorithm = Algorithm.HMAC256("DitIsGeenSecret");
            String token = JWT.create()
                    .withIssuer(person.getName())
                    .withClaim("username",person.getName())
                    .withClaim("ID",person.getId())
                    .withClaim("Roles" , String.valueOf(person.getRole()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + (5 * 60 * 1000)))
                    .sign(algorithm);
            return token;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
