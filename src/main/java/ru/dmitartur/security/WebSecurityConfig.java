package ru.dmitartur.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.dmitartur.security.oauth2tools.CustomAuthoritiesExtractor;
import ru.dmitartur.security.oauth2tools.CustomPrincipalExtractor;
import ru.dmitartur.service.abstraction.UserService;
import ru.dmitartur.service.impl.UserDetailsServiceImpl;

import javax.servlet.Filter;


@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@EnableAuthorizationServer
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2ClientContext oauth2ClientContext;

    private final UserDetailsServiceImpl userDetailsService;

    private final UserService userService;

    private final AuthSuccessHandler authSuccessHandler;

    private final CustomAuthoritiesExtractor customAuthoritiesExtractor;

    private final CustomPrincipalExtractor customPrincipalExtractor;

    @Autowired
    public WebSecurityConfig(OAuth2ClientContext oauth2ClientContext, UserDetailsServiceImpl userDetailsService, UserService userService, AuthSuccessHandler authSuccessHandler, CustomAuthoritiesExtractor customAuthoritiesExtractor, CustomPrincipalExtractor customPrincipalExtractor) {
        this.oauth2ClientContext = oauth2ClientContext;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.authSuccessHandler = authSuccessHandler;
        this.customAuthoritiesExtractor = customAuthoritiesExtractor;
        this.customPrincipalExtractor = customPrincipalExtractor;
    }

    // конфигурация web based security для конкретных http-запросов
    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http
                .authorizeRequests()
//                    .antMatchers("/**").permitAll()
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
                    .permitAll()
                    .and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
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



    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oauth2ClientContext);
        googleFilter.setRestTemplate(googleTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(googleResourcess().getUserInfoUri(), google().getClientId());
        tokenServices.setPrincipalExtractor(customPrincipalExtractor);
        tokenServices.setAuthoritiesExtractor(customAuthoritiesExtractor);
        tokenServices.setRestTemplate(googleTemplate);
        googleFilter.setTokenServices(tokenServices);
        return googleFilter;
    }

    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails google() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResourcess() {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }
}
