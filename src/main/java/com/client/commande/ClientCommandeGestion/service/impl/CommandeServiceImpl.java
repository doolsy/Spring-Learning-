package com.client.commande.ClientCommandeGestion.service.impl;

import com.client.commande.ClientCommandeGestion.dao.CommandRepository;
import com.client.commande.ClientCommandeGestion.entity.Commande;
import com.client.commande.ClientCommandeGestion.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asy on 19/10/2017.
 */
@Component()
@Transactional
public class CommandeServiceImpl implements CommandeService{

    @Autowired
    CommandRepository commandRepository;

    @Override
    public Commande trouver(Long id) {
        return commandRepository.findOne(Math.toIntExact(id));
    }

    @Override
    public void creer(Commande commande) {
        commandRepository.save(commande);
    }

    @Override
    public List<Commande> lister() {
        return (List<Commande>) commandRepository.findAll();
    }

    @Override
    public void supprimer(Commande commande) {
        commandRepository.delete(commande);
    }
}
