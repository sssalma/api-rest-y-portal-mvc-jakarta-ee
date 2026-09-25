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
import authn.Credentials;
import authn.Secured;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.POST;
import java.util.List;
import model.entities.Usuari;

/**
 *
 * @author salma
 */
@Stateless
@Path("credentials")
public class CredentialsFacade extends AbstractFacade<Credentials> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public CredentialsFacade() {
        super(Credentials.class);
    }

    @GET
    @Path("/{username}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCredentialByUsername(@PathParam("username") String username) {
        // Buscar credencial por username
        TypedQuery<Credentials> query = em.createNamedQuery("Credentials.findUser", Credentials.class);
        query.setParameter("username", username);

         try {
        Credentials credential = query.getSingleResult();
        
        // Busco la relació one to one per trobar l'usuari propietari de les credencials de la sesió
        TypedQuery<Usuari> userQuery = em.createQuery(
            "SELECT u FROM Usuari u WHERE u.credencials = :credential", Usuari.class
        );
        userQuery.setParameter("credential", credential);
        
        Usuari user = userQuery.getSingleResult();
        return Response.ok(user).build();

    } catch (jakarta.persistence.NoResultException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("No se encontró el usuario con username: " + username)
                .build();
    }
    }
    
    @POST
    @Path("validate")
    @Secured
    public Response validate(){  //si s'hi pot accedir es que la capçalera és vàlida. 
        return Response.ok().build();
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
