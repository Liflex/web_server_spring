package ru.dmitartur.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.dmitartur.initData.Init;


@SpringBootApplication
@ComponentScan(basePackages="ru.dmitartur")
@EntityScan( basePackages = "ru.dmitartur" )
@EnableTransactionManagement
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

    @Bean(initMethod = "init")
    public Init initData() {
        return new Init();
    }
}
