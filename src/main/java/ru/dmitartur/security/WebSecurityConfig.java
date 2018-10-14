package ru.dmitartur.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dmitartur.service.abstraction.UserService;
import ru.dmitartur.service.impl.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final UserService userService;

    private final AuthSuccessHandler authSuccessHandler;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserService userService, AuthSuccessHandler authSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.authSuccessHandler = authSuccessHandler;
    }

    // конфигурация web based security для конкретных http-запросов
    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http
                .authorizeRequests()
//                    .antMatchers("/**").permitAll()
                    .antMatchers("/test").permitAll()
                    .antMatchers("/registration").permitAll()
//                .antMatchers("/user").permitAll()
                    .antMatchers("/user").access("hasAnyAuthority('USER', 'ADMIN')")
//                .antMatchers("/admin/**").permitAll()
                    .antMatchers("/admin/**").access("hasAnyAuthority('ADMIN')")
                    .and()
                .formLogin()
                    .loginPage("/")
                    .successHandler(authSuccessHandler)
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return authProvider;
    }

}
