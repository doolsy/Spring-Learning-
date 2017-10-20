package com.client.commande.ClientCommandeGestion.model;

/**
 * Created by asy on 20/10/2017.
 */
public class TokenDTO {
    private String idToken;

    public TokenDTO(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return this.idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
