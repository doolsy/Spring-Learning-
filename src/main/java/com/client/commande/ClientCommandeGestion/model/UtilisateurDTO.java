package com.client.commande.ClientCommandeGestion.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by asy on 19/10/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtilisateurDTO extends ResourceSupport{

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long   identifiant;
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @NotNull
    @NotBlank
    @NotEmpty
    private String motDePasse;

    @NotNull
    @NotBlank
    @NotEmpty
    private String nom;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateInscription;

    public UtilisateurDTO()
    {

    }

    public UtilisateurDTO(Long identifiant, String email, String motDePasse, String nom, Date dateInscription) {
        this.identifiant = identifiant;
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.dateInscription = dateInscription;
    }

    public Long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Long id) {
        this.identifiant = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
}
