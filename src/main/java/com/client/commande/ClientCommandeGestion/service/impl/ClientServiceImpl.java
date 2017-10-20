package com.client.commande.ClientCommandeGestion.service.impl;

import com.client.commande.ClientCommandeGestion.dao.ClientRepository;
import com.client.commande.ClientCommandeGestion.entity.Client;
import com.client.commande.ClientCommandeGestion.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asy on 19/10/2017.
 */
@Component()
@Transactional
public class ClientServiceImpl implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client trouver(Long id) {
        return clientRepository.findOne(Math.toIntExact(id));
    }

    @Override
    public void creer(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> lister() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public void supprimer(Client client) {
        clientRepository.delete(client);
    }
}
