package ru.dmitartur.config;


import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.dmitartur.service.abstraction.SecurityService;
import ru.dmitartur.service.impl.SecurityServiceImpl;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;

@Configuration
@ComponentScan(basePackages="ru.dmitartur")
@EnableWebMvc
@EnableWebSecurity
@EnableCaching
@EnableTransactionManagement
public class AppConfig extends AbstractSecurityWebApplicationInitializer implements WebMvcConfigurer {
    private ResourceBundle rb = ResourceBundle.getBundle("application");



    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setOrder(1);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        // использование JstlView позволяет делать JSTL-инъекции в динамические страницы или фрагменты страниц
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        ApplicationContext context = new AnnotationConfigApplicationContext();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("users")));
        return cacheManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDataSource());
        localSessionFactoryBean.setPackagesToScan("ru.dmitartur.model");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        return localSessionFactoryBean;
    }


    private DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(rb.getString("database.url"));
        basicDataSource.setUsername(rb.getString("database.user"));
        basicDataSource.setPassword(rb.getString("database.password"));
        basicDataSource.setDriverClassName(rb.getString("database.driver"));
        basicDataSource.setMaxActive(10);
        return basicDataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }


    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
              //hibernateProperties.setProperty(
                //"hibernate.hbm2ddl.auto", rb.getString("hibernate.event"));
        hibernateProperties.setProperty(
                "hibernate.dialect", rb.getString("hibernate.dialect"));
        hibernateProperties.setProperty(
                "show_sql" , rb.getString("hibernate.show"));

        return hibernateProperties;
    }

//    @Bean
//    public SecurityService getSecurityService () {
//        return new SecurityServiceImpl();
//    }
}
