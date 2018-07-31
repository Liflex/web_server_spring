package ru.dmitartur.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.ResourceBundle;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    private ResourceBundle rb = ResourceBundle.getBundle("application");

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDataSource());
        localSessionFactoryBean.setPackagesToScan("ru.dmitartur.model");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        return localSessionFactoryBean;
    }


    public DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(rb.getString("url"));
        basicDataSource.setUsername(rb.getString("username"));
        basicDataSource.setPassword(rb.getString("password"));
        basicDataSource.setDriverClassName(rb.getString("driverClassName"));
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


    Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
  //      hibernateProperties.setProperty(
//                "hibernate.hbm2ddl.auto", rb.getString("event"));
        hibernateProperties.setProperty(
                "hibernate.dialect", rb.getString("dialect"));
        hibernateProperties.setProperty(
                "show_sql" , rb.getString("show"));

        return hibernateProperties;
    }
}
