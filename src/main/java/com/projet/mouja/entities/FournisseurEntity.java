package com.projet.mouja.entities;

public class FournisseurEntity {

    private int idf;
    private String nom;
    private String adresse;
    private String pays;
    private String ville;
    private String email;
    private String numTel;

    public FournisseurEntity(String nom,String adresse, String pays, String ville, String email, String numTel) {
        this.nom = nom;
        this.adresse = adresse;
        this.pays = pays;
        this.ville = ville;
        this.email = email;
        this.numTel = numTel;
    }

    public FournisseurEntity(int idF, String nom,String adresse, String pays, String ville, String email, String numTel) {
        this.idf = idF;
        this.nom = nom;
        this.adresse = adresse;
        this.pays = pays;
        this.ville = ville;
        this.email = email;
        this.numTel = numTel;
    }

    public FournisseurEntity() {
    }

    public int getIdf() { return idf; }

    public void setIdf(int idf) { this.idf = idf; }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
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

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }


}
