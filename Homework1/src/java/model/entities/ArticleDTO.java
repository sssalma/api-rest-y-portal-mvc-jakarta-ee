package model.entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Date;

/**
 *
 * @author salma
 */
public class ArticleDTO {
    private long id;
    private String imatge;
    private String autor;
    private String titol;
    private String resum;
    private Date data;
    private String views;
    private Boolean esPublic;
    
    
    public ArticleDTO(long id, String imatge, String autor, String titol, String resum, Date data, String views, Boolean esPublic) {
        this.id = id;
        this.imatge = imatge;
        this.autor = autor;
        this.titol = titol;
        this.resum = resum;
        this.data = data;
        this.views = views;
        this.esPublic=esPublic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getEsPublic() {
        return esPublic;
    }

    public void setEsPublic(Boolean esPublic) {
        this.esPublic = esPublic;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getResum() {
        return resum;
    }

    public void setResum(String resum) {
        this.resum = resum;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
    
}
