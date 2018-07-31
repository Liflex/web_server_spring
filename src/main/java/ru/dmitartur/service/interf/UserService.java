package ru.dmitartur.service.interf;



import ru.dmitartur.model.User;

import java.util.List;


public interface UserService {
    User get(String login, String password);
    List<User> getAll();
    void add(User t);
    void update(User t);
    void delete(long id);
}
