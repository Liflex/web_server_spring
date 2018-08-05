package ru.dmitartur.service.abstraction;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
