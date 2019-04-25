import Controller.PersonController;
import filter.CorsFilter;
import jwt.AuthenticationController;

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(CorsFilter.class);
        resources.add(PersonController.class);
        resources.add(AuthenticationController.class);
    }
}