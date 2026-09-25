package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.AlertMessage;
import deim.urv.cat.homework2.model.Autor;


public interface UserService {
    
    /**
     * @return true si las credencials son correctes, sinó false
     */
    public AlertMessage loginCorrecte(String username, String password);
    

    /**
     * @return usuari de la sessió
     */

     public Autor dadesUsuari(String username);
}