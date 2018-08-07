package ru.dmitartur.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.dmitartur.dao.abstraction.UserDAO;
import ru.dmitartur.model.Role;
import ru.dmitartur.model.User;
import ru.dmitartur.service.abstraction.UserService;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User get(String login) {
        return userDAO.get(login);
    }

    @Override
    public List getAll() {
        return userDAO.getAll();
    }

    @Override
    public void add(User o) {
        o.setActive(true);
        o.setRoles(Collections.singleton(Role.USER));
        userDAO.add(o);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }
}
