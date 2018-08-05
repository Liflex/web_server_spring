package ru.dmitartur.service.abstraction;



import ru.dmitartur.model.User;

import java.util.List;


public interface UserService {
    User get(String login);
    List<User> getAll();
    void add(User t);
    void update(User t);
    void delete(long id);
}
