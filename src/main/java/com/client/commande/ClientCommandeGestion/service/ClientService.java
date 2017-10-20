package com.client.commande.ClientCommandeGestion.service;

import com.client.commande.ClientCommandeGestion.entity.Client;

import java.util.List;

/**
 * Created by asy on 19/10/2017.
 */
public interface ClientService {
    Client trouver(Long id );

    void creer( Client client );

    List<Client> lister();

    void supprimer( Client client );
}
