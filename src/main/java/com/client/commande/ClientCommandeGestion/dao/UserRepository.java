package com.client.commande.ClientCommandeGestion.dao;

import com.client.commande.ClientCommandeGestion.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by asy on 19/10/2017.
 */
public interface UserRepository extends CrudRepository <Utilisateur, Long>{
    Utilisateur findOneByEmail(String username);
}
