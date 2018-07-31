package ru.dmitartur.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dmitartur.dao.imple.UserDAOHibernateImpl;
import ru.dmitartur.dao.interf.UserDAO;
import ru.dmitartur.service.imple.UserServiceImpl;
import ru.dmitartur.service.interf.UserService;


@Configuration
public class SpringBeanFactory {

    @Bean
    public UserDAO getUserDAO() {
        return new UserDAOHibernateImpl();
    }

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }


}
