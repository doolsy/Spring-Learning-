package com.client.commande.ClientCommandeGestion.dao;

import com.client.commande.ClientCommandeGestion.entity.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by asy on 19/10/2017.
 */
public interface ClientRepository extends CrudRepository<Client,Integer>{
}
