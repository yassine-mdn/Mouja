package com.projet.mouja.entities;

import java.util.Objects;


public class CompteEntity {

    private int id;
    private String identifiant;
    private String motDePasse;
    private Boolean statut;

    public CompteEntity(String identifiant, String motDePasse, Boolean statut) {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.statut = statut;
    }

    public CompteEntity() {
    }


    public int getId() {
        return id;
    }

    private void setId(int id){this.id = id;}

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompteEntity that = (CompteEntity) o;
        return id == that.id && Objects.equals(identifiant, that.identifiant) && Objects.equals(motDePasse, that.motDePasse) && Objects.equals(statut, that.statut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifiant, motDePasse, statut);
    }

}
