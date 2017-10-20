package com.client.commande.ClientCommandeGestion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by asy on 19/10/2017.
 */
public class LoginDTO  {
    @Email
    @NotNull
    @Size(
            min = 1,
            max = 50
    )
    private String email;
    @NotNull
    @Size(
            min = 4,
            max = 100
    )
    private String password;
    @JsonIgnore
    private Boolean rememberMe;

    public LoginDTO() {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isRememberMe() {
        return this.rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String toString() {
        return "LoginVM{password='*****', email='" + this.email + '\'' + ", rememberMe=" + this.rememberMe + '}';
    }
}

