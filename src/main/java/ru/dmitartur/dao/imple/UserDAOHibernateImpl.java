package ru.dmitartur.dao.imple;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitartur.dao.interf.UserDAO;
import ru.dmitartur.model.User;


import java.util.List;

public class UserDAOHibernateImpl  implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDAOHibernateImpl() {

    }

    @Transactional (propagation = Propagation.REQUIRED)
    @Override
    public User get(String login, String password) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("From User u WHERE u.login = ?1 AND u.password = ?2")
                .setParameter(1, login)
                .setParameter(2, password)
                .uniqueResult();
    }


    @Transactional (propagation = Propagation.REQUIRED)
    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("From User").list();
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @CacheEvict("users")
    @Override
    public void add(User t) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @CacheEvict("users")
    @Override
    public void update(User t) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(t);
        transaction.commit();
        session.close();
    }

    @Transactional (propagation = Propagation.REQUIRED)
    @CacheEvict("users")
    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(new User(id));
        transaction.commit();
        session.close();
    }

}
