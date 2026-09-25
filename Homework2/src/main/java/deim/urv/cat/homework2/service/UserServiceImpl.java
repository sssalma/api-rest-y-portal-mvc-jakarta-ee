package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.AlertMessage;
import deim.urv.cat.homework2.model.Autor;
import deim.urv.cat.homework2.model.Credential;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private final WebTarget webTarget;
    private final jakarta.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/Homework1/rest/api/v1/";

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    public UserServiceImpl() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("credentials"); 
    }

    @Override
    public AlertMessage loginCorrecte(String username, String password) {               //retorno null si el login es correcte.

        //Faig el post al endpoint /credentials/validate amb passant els credencials al .header()
        Response response = webTarget.path("validate").request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()))
                .post(null);

        //retorno .warning quan la crida no dona codi ok 200
        if (response.getStatus() != 200) {
            return AlertMessage.warning("Credenciales incorrectas para el usuario: " + username);
        }
        return null;
    }

    @Override
    public Autor dadesUsuari(String username) {
        Response response = webTarget.path(username).request(MediaType.APPLICATION_JSON).get();

        switch (response.getStatus()) {
            case 200:
                Autor autor = response.readEntity(Autor.class);
                return autor;
            case 404:
                logger.log(Level.WARNING, "No es troba l'usuari amb username: ", username);
                return null;
            default:
                logger.log(Level.SEVERE, "Error al obtener los datos del usuario. Codigo de estado: ", response.getStatus());
                return null;
        }
    }

}
