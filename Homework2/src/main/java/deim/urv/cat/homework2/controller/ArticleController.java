/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.AlertMessage;
import deim.urv.cat.homework2.model.Article;
import deim.urv.cat.homework2.model.ArticleID;
import deim.urv.cat.homework2.service.ArticleService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author salma
 */
@Controller
@Path("ArticleDisplay")
public class ArticleController {

    @Inject
    ArticleService articleService;

    @Inject
    @Named("flashMessage")
    private AlertMessage alertMessage;

    @Inject
    Models models;

    @Context
    HttpServletRequest request;

    // maneja la visualización de la página JSP
        @GET
    public String showArticles(
            @jakarta.ws.rs.QueryParam("autor") String autor,
            @jakarta.ws.rs.QueryParam("topic") String topic
    ) {
        // Si no se pasan los parámetros, se asignan como null
        if (autor == null || autor.isEmpty()) {
            autor = null;
        }

        if (topic == null || topic.isEmpty()) {
            topic = null;
        }

        try {
            // Llamada al servicio para obtener los artículos
            List<Article> articles = articleService.listArticles(autor, topic, alertMessage);

            // Verificación de si se obtuvieron artículos o si ocurrió un error
            if (articles == null || articles.isEmpty()) {
                // Si no se encuentran artículos, establezco un mensaje de advertencia
                alertMessage.notify(AlertMessage.Type.warning, "No hi ha articles per l'autor o tòpic.");
                models.put("flashMessage", alertMessage);
            } else {
                // Si se encuentran artículos, los paso al modelo para mostrar en la vista
                models.put("articles", articles);
                models.put("autorSeleccionat", autor);
                models.put("topicSeleccionat", topic);
            }

        } catch (RuntimeException e) {
            // En caso de excepción, establecer un mensaje de error
            alertMessage.notify(AlertMessage.Type.danger, e.getMessage());
            models.put("flashMessage", alertMessage);
        }

        // Siempre devuelve la misma vista "articles-display.jsp"
        return "articles-display.jsp";
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String showArticleDetails(@jakarta.ws.rs.PathParam("id") int id) {

        String user = (String) request.getSession().getAttribute("user");
        String password = (String) request.getSession().getAttribute("password");

        // Obtener el artículo por ID
        ArticleID article = articleService.getPerId(id, user, password);

        if (article == null) {
            return "alert.jsp";
        }

        // Pasar los datos del artículo al modelo
        models.put("article", article);

        // Retornar el nombre de la página JSP que mostrará los detalles del artículo
        return "article-details.jsp";
    }
}
