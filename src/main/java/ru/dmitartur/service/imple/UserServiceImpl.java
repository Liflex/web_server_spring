package ru.dmitartur.service.imple;



import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitartur.dao.interf.UserDAO;
import ru.dmitartur.model.User;
import ru.dmitartur.service.interf.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User get(String login, String password) {
        return userDAO.get(login, password);
    }

    @Override
    public List getAll() {
        return userDAO.getAll();
    }

    @Override
    public void add(User o) {
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
