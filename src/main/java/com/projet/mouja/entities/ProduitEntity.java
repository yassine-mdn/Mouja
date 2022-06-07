package com.projet.mouja.entities;

import java.util.Objects;


public class ProduitEntity {

    private int idp;
    private String nom;
    private String reference;
    private String category;
    private Double prixUnitaire;
    private Integer stock;
    private Integer qteVendue;
    private Integer idf;
    private String nomf;

    public ProduitEntity(String reference, String nom, String category, Double prixUnitaire, Integer stock, Integer qteVendue, Integer idf) {
        this.nom = nom;
        this.reference = reference;
        this.category = category;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
        this.qteVendue = qteVendue;
        this.idf = idf;
    }

    public ProduitEntity() {
    }


    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getQteVendue() {
        return qteVendue;
    }

    public void setQteVendue(Integer qteVendue) {
        this.qteVendue = qteVendue;
    }

    public Integer getIdf() {
        return idf;
    }

    public void setIdf(Integer idf) {
        this.idf = idf;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitEntity that = (ProduitEntity) o;
        return idp == that.idp && Objects.equals(reference, that.reference) && Objects.equals(category, that.category) && Objects.equals(prixUnitaire, that.prixUnitaire) && Objects.equals(stock, that.stock) && Objects.equals(qteVendue, that.qteVendue) && Objects.equals(idf, that.idf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idp, reference, category, prixUnitaire, stock, qteVendue, idf);
    }


    public String getNomf() {
        return nomf;
    }

    public void setNomf(String nomf) {
        this.nomf = nomf;
    }
}
