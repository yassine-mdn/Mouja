package com.projet.mouja.entities;


public class CartEntity {

    private int idC;
    private String refProd;
    private float prix;
    private int quantite;

    private String nomProd;

    public CartEntity(int idC, String refProd, float prix, int quantite) {
        this.idC = idC;
        this.refProd = refProd;
        this.prix = prix;
        this.quantite = quantite;
    }
    public CartEntity(int idC, String refProd, float prix, int quantite, String nomProd) {
        this.idC = idC;
        this.refProd = refProd;
        this.prix = prix;
        this.quantite = quantite;
        this.nomProd = nomProd;
    }

    public String getRefProd() {
        return refProd;
    }

    public void setRefProd(String refProd) {
        this.refProd = refProd;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

}
