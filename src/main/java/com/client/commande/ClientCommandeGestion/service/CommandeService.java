package com.client.commande.ClientCommandeGestion.service;

import com.client.commande.ClientCommandeGestion.entity.Commande;

import java.util.List;

/**
 * Created by asy on 19/10/2017.
 */
public interface CommandeService {
    Commande trouver(Long id );

    void creer( Commande commande );

    List<Commande> lister();

    void supprimer( Commande commande );
}
