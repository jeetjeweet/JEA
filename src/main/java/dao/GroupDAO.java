package dao;

import model.Groep;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GroupDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Groep groep){
        entityManager.persist(groep);
    }

}
