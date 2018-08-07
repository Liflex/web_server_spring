package ru.dmitartur.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitartur.dao.abstraction.UserDAO;
import ru.dmitartur.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOHibernateImpl  implements UserDAO {

    private final SessionFactory sessionFactory;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDAOHibernateImpl(SessionFactory sessionFactory, PasswordEncoder passwordEncoder) {
        this.sessionFactory = sessionFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @Override
    public User get(String username) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("From User u WHERE u.username = ?1")
                .setParameter(1, username)
                .uniqueResult();
    }



    @Transactional (propagation = Propagation.REQUIRED)
    @Cacheable(value = "users")
    @Override
    public List<User> getAll() {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("From User").list();
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @CacheEvict(value = "users", allEntries=true)
    @Override
    public void add(User t) {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @CacheEvict(value = "users", allEntries=true)
    @Override
    public void update(User t) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(t);
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @CacheEvict(value = "users", allEntries=true)
    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(new User(id));
    }

}
