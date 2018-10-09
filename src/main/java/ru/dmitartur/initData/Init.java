package ru.dmitartur.initData;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitartur.dao.abstraction.RoleDAO;
import ru.dmitartur.dao.abstraction.UserDAO;
import ru.dmitartur.model.Role;
import ru.dmitartur.model.User;

import java.util.Collections;

public class Init {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private UserDAO userDAO;

    public void init() {
        Role test;
        User test1;
        if((test = roleDAO.get(1L)) == null) {
            roleDAO.add(new Role(1, "USER"));
        }
        if((test = roleDAO.get(2L)) == null) {
            roleDAO.add(new Role(2, "ADMIN"));
        }
        if((test1 = userDAO.get("Admin"))  == null) {
            userDAO.update(new User("Admin", "Admin", true, Collections.singletonList(new Role(1))));
        }
    }
}
