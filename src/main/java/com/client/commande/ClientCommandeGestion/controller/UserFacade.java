package com.client.commande.ClientCommandeGestion.controller;

import com.client.commande.ClientCommandeGestion.config.security.TokenProvider;
import com.client.commande.ClientCommandeGestion.controller.utils.Constants;
import com.client.commande.ClientCommandeGestion.controller.utils.RESTServices;
import com.client.commande.ClientCommandeGestion.controller.utils.ResponseBuilder;
import com.client.commande.ClientCommandeGestion.controller.utils.ServerResponse;
import com.client.commande.ClientCommandeGestion.entity.Utilisateur;
import com.client.commande.ClientCommandeGestion.exception.BusinessException;
import com.client.commande.ClientCommandeGestion.model.DTOFactory;
import com.client.commande.ClientCommandeGestion.model.LoginDTO;
import com.client.commande.ClientCommandeGestion.model.TokenDTO;
import com.client.commande.ClientCommandeGestion.model.UtilisateurDTO;
import com.client.commande.ClientCommandeGestion.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by asy on 19/10/2017.
 */
@RestController
@RequestMapping(RESTServices.ROOT.PATH_ROOT)
public class UserFacade {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;


    private final Logger log = LoggerFactory.getLogger(UserFacade.class);

    @PostMapping(RESTServices.ROOT.USERS.REGISTER)
    public ResponseEntity<ServerResponse<UtilisateurDTO>> inscription(@Valid @RequestBody UtilisateurDTO utilisateur) throws BusinessException {
        boolean exists = userService.isEmailExists(utilisateur.getEmail());
        if (exists) {
            throw new BusinessException(Constants.BusinessMessages.EMAIL_EXISTS);
        }


        Utilisateur created = userService.inscription(DTOFactory.utilisateurDTOToUtilisateur(utilisateur));

        return new ResponseBuilder<UtilisateurDTO>().success(DTOFactory.utilisateurToUtilisateurDTO(created));
    }

    @PostMapping(RESTServices.ROOT.AUTHENTICATE)
    public ResponseEntity<ServerResponse<TokenDTO>> authenticate(@Valid @RequestBody LoginDTO loginDTO) throws BusinessException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword());

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);
            return new ResponseBuilder<TokenDTO>().success(new TokenDTO(jwt));
        } catch (AuthenticationException exception) {
            log.error("AuthenticationException",exception);
            throw new AuthenticationServiceException("Erreur d'authentification");

           // throw new BusinessException(Constants.BusinessMessages.AUTHENTICATION_ERROR);
        }

    }
    @GetMapping(RESTServices.ROOT.USERS.GET_CONNECTED)
    public ResponseEntity<ServerResponse<UtilisateurDTO>> getConnected() throws BusinessException {

        Utilisateur utilisateur= userService.getUserConnected();
        if(utilisateur == null) {
            throw new BusinessException("Token incorrect");
        }
        else {
            UtilisateurDTO utilisateurDTO = DTOFactory.utilisateurToUtilisateurDTO(utilisateur);
            return new ResponseBuilder<UtilisateurDTO>().success(utilisateurDTO);
        }
    }
}
