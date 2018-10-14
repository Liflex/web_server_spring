package ru.dmitartur.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitartur.dao.abstraction.UserDAO;
import ru.dmitartur.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Transactional
@Path("/test")
public class TestRestController {

    @Autowired
    private UserDAO userDAO;

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional (propagation = Propagation.REQUIRED)
    public Object getUserById(@PathParam("name") String name) {
        User user = userDAO.get(name);
        System.out.println(user);
        if(user == null) {
            return null;
        }
        return user;
    }
}
