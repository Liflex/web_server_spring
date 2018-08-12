package ru.dmitartur.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ComponentScan(basePackages="ru.dmitartur")
@EntityScan( basePackages = {"ru.dmitartur"} )
@EnableWebSecurity
@EnableTransactionManagement
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
