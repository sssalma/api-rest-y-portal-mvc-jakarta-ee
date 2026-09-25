/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.Article;
import model.entities.Usuari;

/**
 *
 * @author salma
 */

@Stateless
@Path("customer")
public class UsuariFacadeREST extends AbstractFacade<Usuari>{ 

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    
    public UsuariFacadeREST() { 
        super(Usuari.class);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    //té path a nivell de classe, llisto a tots els usuaris
    public Response findAllUsuaris(){
        
        //obtinc tots els autors en una llista,for + select *
        List<Usuari> usuaris = em.createQuery("SELECT a FROM Usuari a", Usuari.class).getResultList();
   
            
            for(Usuari usuari : usuaris){
            //si té articles afegeixo l'enllaç a l'últim
                if(usuari.getArticles()!=null && !usuari.getArticles().isEmpty()){
                    Article mesRecent = em.createQuery(
                        "SELECT a FROM Article a WHERE a.autor.id =:autorId ORDER BY a.data DESC", Article.class)
                        .setParameter("autorId",usuari.getId()) 
                        .setMaxResults(1).getSingleResult();
                        System.out.println("article: " + mesRecent.getId());
                        
                        String linkHateoas = "/Homework1/rest/api/v1/article/" + mesRecent.getId();
                        usuari.setArticleRecentLink(linkHateoas);
                }
            }
        //retorno la llista amb el missatge http 200 OK
        return Response.ok(usuaris).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response UsuariPerId(@PathParam("id") long id){
        
        //busco l'usuari
        Usuari usuari = em.find(Usuari.class, id);
        
        if(usuari==null){ //no existeix l'autor amb id
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No existeix l'usuari amb id: "+ id )
                    .build();
        }
        if(usuari.getArticles()!=null && !usuari.getArticles().isEmpty()){
                    Article mesRecent = em.createQuery(
                        "SELECT a FROM Article a WHERE a.autor.id =:autorId ORDER BY a.data DESC", Article.class)
                        .setParameter("autorId",usuari.getId()) 
                        .setMaxResults(1).getSingleResult();
                        System.out.println("article: " + mesRecent.getId());
                        
                        String linkHateoas = "/Homework1/rest/api/v1/article/" + mesRecent.getId();
                        usuari.setArticleRecentLink(linkHateoas);
                }
        return Response.ok(usuari).build();
    }
    @Override
    protected EntityManager getEntityManager() {
        return em; //accedo a la instancia de em inyectada
    }
    
}

