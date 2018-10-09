package ru.dmitartur.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitartur.dao.abstraction.RoleDAO;
import ru.dmitartur.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class RoleDAOHibernateImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional (propagation = Propagation.REQUIRED)
    public Role get(Long id) {
        Role role = null;
        try {
            role = (Role) entityManager.createQuery("From Role u WHERE u.id = ?1")
                    .setParameter(1, id).getSingleResult();
        } catch (NoResultException ignored) {}
        return role;
    }

    @Override
    @Transactional (propagation = Propagation.REQUIRED)
    public void add(Role t) {
        entityManager.merge(t);
    }
}
