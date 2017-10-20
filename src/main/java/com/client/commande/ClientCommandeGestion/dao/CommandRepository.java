package com.client.commande.ClientCommandeGestion.dao;

import com.client.commande.ClientCommandeGestion.entity.Commande;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by asy on 19/10/2017.
 */
public interface CommandRepository extends CrudRepository<Commande,Integer>{
}
