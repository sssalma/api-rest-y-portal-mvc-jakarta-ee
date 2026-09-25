/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import authn.Credentials;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.List;



/**
 *
 * @author salma
 */

@Entity
@Table(name= "Usuari")
public class Usuari implements Serializable{
    
    private static final long serialVersionUID = 2L; //serialitzacio pel format a canviar
    
    
    @Id
    @SequenceGenerator(name = "Usuari_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuari_gen")
    private Long id;
    private String nom;
    
    @OneToOne
    @JsonbTransient
    @JoinColumn(name = "credentials_id", referencedColumnName = "id")
    private Credentials credencials;
      
    @OneToMany (mappedBy = "autor")
    @JsonbTransient
    private List<Article> articles;
    
    @Transient //per no tenirho a la bd
    private String articleRecentLink; 
   

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Credentials getCredencials() {
        return credencials;
    }

    public void setCredencials(Credentials credencials) {
        this.credencials = credencials;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    public String getArticleRecentLink() {
        return articleRecentLink;
    }
    public void setArticleRecentLink(String articleRecentLink) {
        this.articleRecentLink = articleRecentLink;
    }
    
        // hashCode, equals y toString
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Nota: este método no funcionará si los campos id no están configurados
        if (!(object instanceof Usuari)) {
            return false;
        }
        Usuari other = (Usuari) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.User[ id=" + id + ", username=" + nom + " ]";
    }
    
    
    
}
