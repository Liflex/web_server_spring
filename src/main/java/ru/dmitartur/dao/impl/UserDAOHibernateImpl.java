package ru.dmitartur.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.support.ClasspathScanningPersistenceUnitPostProcessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitartur.dao.abstraction.UserDAO;
import ru.dmitartur.model.User;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOHibernateImpl  implements UserDAO {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDAOHibernateImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @Override
    public User get(String username) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        return (User) session.createQuery("From User u WHERE u.username = ?1")
                .setParameter(1, username)
                .uniqueResult();
    }



    @Transactional (propagation = Propagation.REQUIRED)
    @Override
    public List<User> getAll() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        return (List<User>) session.createQuery("From User").list();
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @Override
    public void add(User t) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.save(t);
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @Override
    public void update(User t) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.saveOrUpdate(t);
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @Override
    public void delete(long id) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.delete(new User(id));
    }

}
