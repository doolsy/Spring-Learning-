package com.client.commande.ClientCommandeGestion.controller.utils;

/**
 * Created by asy on 19/10/2017.
 */
public class ServerResponse<T> {
    T data;
    String status;

    public ServerResponse(T data, String message) {
        this.data = data;
        this.status = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
