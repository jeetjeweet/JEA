package dao;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import model.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Stateless
public class PersonDAO {
    @PersistenceContext
    private EntityManager entityManager;

    // persoonsobject opslaan
    public void save(Person person){
        entityManager.persist(person);
    }

    // alle personen ophalen
    public List<Person> getAll(){
        return entityManager.createNamedQuery("Person.getAll", Person.class).getResultList();
    }

    // find person by name en password
    public Person findOne(String name, String password){
        return entityManager.createNamedQuery("Person.findOne", Person.class)
                .setParameter("name" , name)
                .setParameter("password",password)
                .getSingleResult();
    }

    // find person by id
    public Person findByID(long id){
     return entityManager.createNamedQuery("Person.getByID", Person.class)
             .setParameter("id", id)
             .getSingleResult();
    }

    public void update(Person person) {
        entityManager.merge(person);
    }

    public void delete(Person person) {
        entityManager.remove(person);
    }

    //de 2Factor authentication key genereren
    public String generateAuthKey(String token) throws UnsupportedEncodingException {
        DecodedJWT decodedjwt = verifyJWT(token);
        Long id = decodedjwt.getClaim("ID").asLong();
        Person p = findByID(id);

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();

        p.setAuthenticationKey(key.getKey());
        update(p);
        return key.getKey();
    }

    // verify JWT
    public DecodedJWT verifyJWT(String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("DitIsGeenSecret"))
                .build();
        return verifier.verify(token);
    }
}
