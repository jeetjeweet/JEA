package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonDAO {
    @PersistenceContext
    private EntityManager entityManager;

}
