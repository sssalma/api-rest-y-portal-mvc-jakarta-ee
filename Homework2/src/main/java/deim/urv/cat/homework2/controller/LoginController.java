
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.AlertMessage;
import deim.urv.cat.homework2.service.UserService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.Base64;

/**
 *
 * @author salma
 */
@Controller
@Path("LoginDisplay")
public class LoginController {

    @Inject
    private UserService userService;

    @Inject
    private Models models;

    @Inject
    private AlertMessage flashMessage;  // Inyectar la clase de AlertMessage

    @GET
    public String showLoginForm() {
        return "login.jsp";
    }

    @Context
    private HttpServletRequest request;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response handleLoginForm() {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // verifico el resultat, recordem que null= login correcte == s'incia la sessió
        AlertMessage alert = userService.loginCorrecte(username, password);

        if (alert == null) {
            request.getSession().setAttribute("user", username);
            request.getSession().setAttribute("password", password);
            
            String articleId = request.getParameter("id");
            if (articleId != null) {
                return Response.seeOther(URI.create(request.getContextPath() + "/Web/ArticleDisplay/" + articleId)).build();
            }
            return Response.seeOther(URI.create(request.getContextPath() + "/Web/ArticleDisplay")).build();
        } else { //mostro el missatge d'error en la pàgina del login
            flashMessage.notify(alert.getType(), alert.getText());
            return Response.seeOther(URI.create(request.getContextPath() + "/Web/LoginDisplay")).build();
        }
    }    
}