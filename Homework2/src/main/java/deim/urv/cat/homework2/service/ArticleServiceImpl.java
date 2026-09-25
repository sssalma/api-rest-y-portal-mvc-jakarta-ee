/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.AlertMessage;
import deim.urv.cat.homework2.model.Article;
import deim.urv.cat.homework2.model.ArticleID;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import java.util.Base64;
import java.util.List;        

/**
 * @author salma
 * classe que consumeix els endpoints de la APIREST
 */
public class ArticleServiceImpl implements ArticleService {
    
    private final WebTarget webTarget;
    private final jakarta.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/Homework1/rest/api/v1/"; //url base on està la API REST
    
    public ArticleServiceImpl() {
    client = jakarta.ws.rs.client.ClientBuilder.newClient();
    webTarget = client.target(BASE_URI).path("article"); // URL de trabajo con los artículos
}

@Override
public List<Article> listArticles(String autor, String topic, AlertMessage alertMessage) {
    WebTarget target = webTarget;

    if (autor != null && !autor.isEmpty()) {
        target = target.queryParam("autor", autor);
    }

    if (topic != null && !topic.isEmpty()) {
        target = target.queryParam("topic", topic);
    }

    Response response = target.request(MediaType.APPLICATION_JSON).get();

    if (response.getStatus() == 204) {
        alertMessage.notify(AlertMessage.Type.danger, "No hi ha articles per l'autor o tòpic.");
        response.close();
        return null;  // O puedes devolver una lista vacía, dependiendo de tu lógica
    } else if (response.getStatus() != 200) {
        alertMessage.notify(AlertMessage.Type.danger, "Error: " + response.getStatus());
        response.close();
        return null;
    }

    List<Article> articles = response.readEntity(new jakarta.ws.rs.core.GenericType<List<Article>>() {});
    response.close();
    return articles;
}

    @Override
    public ArticleID getPerId(int id, String user, String password) {
       
        WebTarget target = webTarget.path(String.valueOf(id));
        Response response = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes()))
                .get();
        
        // Verifico la respuesta
        if (response.getStatus() != 200) {
            throw new RuntimeException("Error: " + response.getStatus());
        }
        ArticleID article = response.readEntity(ArticleID.class);
        response.close();
        return article;
}
}       
    

