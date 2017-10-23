package com.client.commande.ClientCommandeGestion.service.impl;

import com.client.commande.ClientCommandeGestion.dao.UserRepository;
import com.client.commande.ClientCommandeGestion.entity.Utilisateur;
import com.client.commande.ClientCommandeGestion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by asy on 19/10/2017.
 */
@Component("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur inscription( Utilisateur utilisateur) {

        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        userRepository.save(utilisateur);
        return utilisateur;
    }

    @Override
    public Utilisateur getUserConnected() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication
                    .getPrincipal();
            Utilisateur user = userRepository.findOneByEmail(principal.getUsername());
            return user;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findOneByEmail(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non existant");
        }

        return new User(userName,user.getMotDePasse(), AuthorityUtils.createAuthorityList("USER", "write"));
    }

    @Override
    public Boolean isEmailExists(String email) {
        return userRepository.findOneByEmail(email) != null;
    }
}
