package com.client.commande.ClientCommandeGestion.config;

import com.client.commande.ClientCommandeGestion.config.security.*;
import com.client.commande.ClientCommandeGestion.controller.utils.RESTServices;
import com.client.commande.ClientCommandeGestion.service.UserService;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

/**
 * Created by asy on 20/10/2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder() ;
    }

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Bean
    public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }

    @PostConstruct
    public void init() {
     /*   try {
            facebookConnectionSignup
                    .setPasswordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }*/
        try {
            authenticationManagerBuilder
                    .userDetailsService(userService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf()
                .disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .passwordParameter("password")
                .usernameParameter("email")
                .successHandler(ajaxAuthenticationSuccessHandler())
                .failureHandler(ajaxAuthenticationFailureHandler())
                .permitAll()
                .and()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/signin/*","/authenticate/*","/signup/*").permitAll()
                .antMatchers(RESTServices.ROOT.PATH_ROOT+RESTServices.ROOT.AUTHENTICATE).permitAll()
                .antMatchers(RESTServices.ROOT.PATH_ROOT+RESTServices.ROOT.USERS.REGISTER).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JWTConfigurer(tokenProvider));
    }
}
