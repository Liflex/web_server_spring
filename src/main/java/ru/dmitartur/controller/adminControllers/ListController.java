package ru.dmitartur.controller.adminControllers;

import ru.arthur.webserver.service.imple.UserServiceImpl;
import ru.arthur.webserver.service.interf.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet( urlPatterns = {"/adminControllers/list"} )
public class ListController extends HttpServlet {

    private final static Logger log = Logger.getLogger(ListController.class.getName());

    private final UserService userService = new UserServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("getAll");
        req.setAttribute("Users", userService.getAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(req, resp);
    }

}
