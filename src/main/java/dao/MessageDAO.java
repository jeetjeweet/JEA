package dao;

import model.Message;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MessageDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Message findById(long id){
        return entityManager.createNamedQuery("Message.findByID", Message.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
