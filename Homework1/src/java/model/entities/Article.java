/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
//import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author salma
 */

@Entity
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Article_gen")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="autor_id", nullable = false)
    private Usuari autor;
    
    @Column(nullable=false)
    private String titol;
    
    @Column(nullable=false, length = 300)
    private String resum;
    
    @Temporal(TemporalType.DATE)
    private Date data; 
    private String imatge; //serà un URL
    
    @JsonbTransient
    private int views;
    private boolean esPublic;
 
    @Column(nullable = false, length = 3500)
    private String text;
    
    @ManyToMany
    @JoinTable(
            name = "topic_article",
            joinColumns = @JoinColumn(name = "idArticle"),
            inverseJoinColumns= @JoinColumn(name="idTopic"))
    private Set<Topic> topics = new HashSet<>();
    
    //2 topics per article
    @PrePersist
    public void ntopics(){
        if (this.topics.size() != 2){
            throw new IllegalArgumentException("Son dos topics per article");
        }
    }
    
    public Article(){} //contructor buit pel JPA

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuari getAutor() {
        return autor;
    }

    public void setAutor(Usuari autor) {
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

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }
    
    
    public int getViews() {
       return views;
    }
    
    public String getViews2(){
        if (views >=1000){
            return String.format("%.1fk", views/1000.0);
        }
        return String.valueOf(views);
    }

    public void setViews(int views) {
        this.views = views;
    }
    
    public boolean isEsPublic(){
        return esPublic;
    }
    
    public void setEsPublic(boolean esPublic) {
        this.esPublic = esPublic;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
}

