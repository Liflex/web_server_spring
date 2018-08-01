package ru.dmitartur.dao.impl;

import org.springframework.stereotype.Repository;
import ru.dmitartur.dao.abstraction.UserDAO;
import ru.dmitartur.model.User;

import java.util.List;

@Repository
public class UserDAOCopie implements UserDAO {
    @Override
    public User get(String login, String password) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void add(User t) {

    }

    @Override
    public void update(User t) {

    }

    @Override
    public void delete(long id) {

    }
}
