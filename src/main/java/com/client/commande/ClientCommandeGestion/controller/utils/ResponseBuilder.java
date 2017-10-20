package com.client.commande.ClientCommandeGestion.controller.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by asy on 19/10/2017.
 */
public class ResponseBuilder<T> {

    public ResponseEntity<ServerResponse<T>> error(String message) {
        return ResponseEntity.ok(new ServerResponse<T>(null, message));
    }

    public ResponseEntity<ServerResponse<T>> success(T data) {
        return ResponseEntity.ok(new ServerResponse<T>(data, "SUCCES"));
    }
}

