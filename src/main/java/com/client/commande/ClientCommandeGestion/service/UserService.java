package com.client.commande.ClientCommandeGestion.service;

import com.client.commande.ClientCommandeGestion.entity.Utilisateur;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;

/**
 * Created by asy on 19/10/2017.
 */
public interface UserService extends UserDetailsService{
    Utilisateur inscription(Utilisateur utilisateur );
    Boolean isEmailExists(String email);
    Utilisateur getUserConnected();
}
