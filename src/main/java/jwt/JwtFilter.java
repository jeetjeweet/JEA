package jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import dao.PersonDAO;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

@JWT
@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer";
    @EJB
    private PersonDAO personDAO;
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        System.out.println("in filter!!!");
        String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        // Validate the Authorization header
        if (!isTokenBasedAuthentication(header)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        String token = header.substring(AUTHENTICATION_SCHEME.length()).trim();

        try{
            rolecheck(personDAO.verifyJWT(token));
        }catch (Exception e){
            System.out.println(e.getMessage());
            abortWithUnauthorized(requestContext);
        }
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .build());
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }
    private JWT GetAnnotation(AnnotatedElement annotatedElement){
        return  annotatedElement.getAnnotation(JWT.class);
    }

    private void rolecheck(DecodedJWT decodedJWT) throws Exception {

        JWT JWTContext = null;

        JWTContext = GetAnnotation(resourceInfo.getResourceMethod());
        if (JWTContext == null) {
            JWTContext = GetAnnotation(resourceInfo.getResourceClass());
        }

        if (JWTContext != null) {
            model.Role[] permission = JWTContext.Permissions();

            if (!Arrays.asList(permission).contains(Role.User)) {
                String roles = decodedJWT.getClaim("Roles").asString();

                boolean check = false;
                for (model.Role r : Arrays.asList(permission)) {
                    if (roles.toUpperCase().contains(r.toString().toUpperCase())) {
                        check = true;
                    }

                }
                if (!check) {
                    throw new Exception("no roles");
                }

//                if (!Arrays.asList(permission).contains(rolelist)) {
//                    throw new Exception("no roles");
//
//                }

            }

        }
    }
}
