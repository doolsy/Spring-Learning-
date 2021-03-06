package com.client.commande.ClientCommandeGestion.entity;
// Generated 4 juil. 2017 15:35:42 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Utilisateur generated by hbm2java
 */
@Entity
@Table( name = "utilisateur", catalog = "mybdd", uniqueConstraints = { @UniqueConstraint( columnNames = "email" ),
        @UniqueConstraint( columnNames = "nom" ) } )
public class Utilisateur implements java.io.Serializable {

    private Long   id;
    private String email;
    private String motDePasse;
    private String nom;
    private Date   dateInscription;

    public Utilisateur() {
    }

    public Utilisateur( String motDePasse, String nom ) {
        this.motDePasse = motDePasse;
        this.nom = nom;
    }

    public Utilisateur( String email, String motDePasse, String nom, Date dateInscription ) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.dateInscription = dateInscription;
    }

    @Id
    @GeneratedValue( strategy = IDENTITY )

    @Column( name = "id", unique = true, nullable = false )
    public Long getId() {
        return this.id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    @Column( name = "email", unique = true, length = 45 )
    public String getEmail() {
        return this.email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    @Column( name = "mot_de_passe", nullable = false, length = 56 )
    public String getMotDePasse() {
        return this.motDePasse;
    }

    public void setMotDePasse( String motDePasse ) {
        this.motDePasse = motDePasse;
    }

    @Column( name = "nom", unique = true, nullable = false, length = 45 )
    public String getNom() {
        return this.nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "date_inscription", length = 19 )
    public Date getDateInscription() {
        return this.dateInscription;
    }

    public void setDateInscription( Date dateInscription ) {
        this.dateInscription = dateInscription;
    }

}
