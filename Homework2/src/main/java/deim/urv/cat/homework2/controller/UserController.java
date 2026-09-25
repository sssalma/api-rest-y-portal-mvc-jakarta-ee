package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.AlertMessage;
import deim.urv.cat.homework2.model.Autor;
import deim.urv.cat.homework2.service.UserService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

/**
 * @author salma
 */
@Controller
@Path("UserProfile")
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private Models models;

    @Context
    private HttpServletRequest request;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String showUserProfile(@QueryParam("username") String username) {
        
        if (username == null || username.isEmpty()) {
            models.put("flashMessage", AlertMessage.danger("No se ha proporcionado un username."));
            return "alert.jsp"; }
        
        // uso el servei per llegir l'usuari
        Autor autor = userService.dadesUsuari(username);
        if (autor == null) {
            models.put("flashMessage", AlertMessage.danger("No se encuentra el usuario con el username: " + username));
            return "alert.jsp";
        }
        models.put("autor", autor);
        return "perfil-details.jsp";
    }

}
