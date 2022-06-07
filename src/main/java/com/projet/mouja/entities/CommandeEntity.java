package com.projet.mouja.entities;

import java.sql.Date;
import java.util.Objects;


public class CommandeEntity {

    private int idCmd;
    private String adresseLivraison;
    private Date date;
    private Integer qte;
    private Integer idp;
    private Integer idc;

    private String nomP;
    private String nomC;

    public CommandeEntity(String adresseLivraison, Date date, Integer qte, Integer idp, Integer idc) {
        this.adresseLivraison = adresseLivraison;
        this.date = date;
        this.qte = qte;
        this.idp = idp;
        this.idc = idc;
    }

    public CommandeEntity(String adresseLivraison, Date date, Integer qte, String nomP, String nomC) {
        this.adresseLivraison = adresseLivraison;
        this.date = date;
        this.qte = qte;
        this.nomC = nomC;
        this.nomP = nomP;
    }


    public CommandeEntity() {
    }


    public int getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(int idCmd) {
        this.idCmd = idCmd;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Integer getIdp() {
        return idp;
    }

    public void setIdp(Integer idp) {
        this.idp = idp;
    }

    public Integer getIdc() {
        return idc;
    }

    public void setIdc(Integer idc) {
        this.idc = idc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandeEntity that = (CommandeEntity) o;
        return idCmd == that.idCmd && Objects.equals(adresseLivraison, that.adresseLivraison) && Objects.equals(date, that.date) && Objects.equals(qte, that.qte) && Objects.equals(idp, that.idp) && Objects.equals(idc, that.idc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCmd, adresseLivraison, date, qte, idp, idc);
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }
}
