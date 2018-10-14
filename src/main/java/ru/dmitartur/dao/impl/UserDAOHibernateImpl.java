package ru.dmitartur.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitartur.dao.abstraction.UserDAO;
import ru.dmitartur.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class UserDAOHibernateImpl  implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public User get(String username) {
        User user = null;
        try {
            user = (User) entityManager.createQuery("From User u WHERE u.username = ?1")
                    .setParameter(1, username).getSingleResult();
        } catch (NoResultException ignored) {}
        return user;
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll() {
        return (List<User>) entityManager.createQuery("From User").getResultList();
    }
    @Override
    public void add(User t) {
        entityManager.persist(t);
    }
    @Override
    public void update(User t) {
        entityManager.merge(t);
    }
    @Override
    public void delete(long id) {
        User user = new User(id);
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

}
