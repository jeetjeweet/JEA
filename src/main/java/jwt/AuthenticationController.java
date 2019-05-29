package jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.warrenstrange.googleauth.GoogleAuthenticator;
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
    public Response checkCredentials(@FormParam("name") String name,
                                     @FormParam("password") String password,
                                     @FormParam("authCode") String authCode) {
        try{
            System.out.println(name + " " + password);
            Person person = personDAO.findOne(name,password);
            if((person.getAuthenticationKey() == null) || decodeAuthenticationKey(authCode, person)){
                String token = generateToken(person);
                String responsestring = "token:" + token + "| user:" + name;
                return Response.ok(responsestring).build();
            }
            else{
                return Response.status(403).build();
            }

        }catch (Exception e){
            e.printStackTrace();
            return Response.status(403).build();
        }
    }
    //valideert of de ingevoerde code hoort bij de key
    private boolean decodeAuthenticationKey(String code, Person person){
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        return gAuth.authorize(person.getAuthenticationKey(),Integer.parseInt(code));
    }

    private String generateToken(Person person){
        try{
            Algorithm algorithm = Algorithm.HMAC256("DitIsGeenSecret");
            String authkey = person.getAuthenticationKey();
            if(authkey == null){
                authkey = "";
            }
            String token = JWT.create()
                    .withIssuer("Chatturbait")
                    .withClaim("username",person.getName())
                    .withClaim("ID",person.getId())
                    .withClaim("Roles" , String.valueOf(person.getRole()))
                    .withClaim("authenticationKey", authkey)
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + (5 * 60 * 1000)))
                    .sign(algorithm);
            return token;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
