package ru.dmitartur.dao.interf;

import ru.dmitartur.model.User;

import java.util.List;

public interface UserDAO {

    User get(String login, String password);
    List<User> getAll();
    void add(User t);
    void update(User t);
    void delete(long id);
}
