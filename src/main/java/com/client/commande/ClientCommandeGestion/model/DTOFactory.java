package com.client.commande.ClientCommandeGestion.model;

import com.client.commande.ClientCommandeGestion.entity.Utilisateur;

/**
 * Created by asy on 19/10/2017.
 */
public class DTOFactory {

    public static UtilisateurDTO utilisateurToUtilisateurDTO(Utilisateur utilisateur)
    {
        return new UtilisateurDTO(utilisateur.getId(),utilisateur.getEmail(),"*********",utilisateur.getNom(),utilisateur.getDateInscription());
    }
    public static Utilisateur utilisateurDTOToUtilisateur(UtilisateurDTO utilisateurDTO)
    {
        Utilisateur utilisateur = new Utilisateur(utilisateurDTO.getEmail(),"***********",utilisateurDTO.getNom(),utilisateurDTO.getDateInscription());
        utilisateur.setId(utilisateurDTO.getIdentifiant());
        return utilisateur;
    }
}
