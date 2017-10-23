package com.client.commande.ClientCommandeGestion.controller.utils;

import com.client.commande.ClientCommandeGestion.exception.BusinessException;
import com.client.commande.ClientCommandeGestion.model.TokenDTO;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by asy on 19/10/2017.
 */
@ControllerAdvice
public class ClientCommandeControllerAdvice {

        @ResponseBody
        @ExceptionHandler(UsernameNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        VndErrors userNotFoundExceptionHandler(UsernameNotFoundException ex) {
                return new VndErrors("error", ex.getMessage());
        }
        @ResponseBody
        @ExceptionHandler(AuthenticationException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        VndErrors AuthenticationExceptionHandler(AuthenticationException ex) {
                return new VndErrors("error", ex.getMessage());
               //return new ResponseBuilder<TokenDTO>().error(Constants.BusinessMessages.AUTHENTICATION_ERROR);
        }


        @ResponseBody
        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        VndErrors ExceptionHandler(Exception ex) {
                return new VndErrors("error", ex.getMessage());
        }
}
