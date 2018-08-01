package ru.dmitartur.dao.abstraction;

import ru.dmitartur.model.User;

import java.util.List;

public interface UserDAO {

    User get(String login, String password);
    List<User> getAll();
    void add(User t);
    void update(User t);
    void delete(long id);
}
