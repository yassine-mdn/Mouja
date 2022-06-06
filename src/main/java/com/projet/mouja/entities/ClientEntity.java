package com.projet.mouja.entities;

import java.util.Objects;

public class ClientEntity {

    private int id;
    private String nom;
    private String prenom;
    private String adresseDeFacturation;
    private String ville;
    private String email;
    private int numTel;

    public ClientEntity(String nom, String prenom, String adresseDeFacturation, String ville, String email, int numTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresseDeFacturation = adresseDeFacturation;
        this.ville = ville;
        this.email = email;
        this.numTel = numTel;
    }

    public ClientEntity() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseDeFacturation() {
        return adresseDeFacturation;
    }

    public void setAdresseDeFacturation(String adresseDeFacturation) {
        this.adresseDeFacturation = adresseDeFacturation;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return id == that.id && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(adresseDeFacturation, that.adresseDeFacturation) && Objects.equals(ville, that.ville) && Objects.equals(email, that.email) && Objects.equals(numTel, that.numTel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, adresseDeFacturation, ville, email, numTel);
    }
}



