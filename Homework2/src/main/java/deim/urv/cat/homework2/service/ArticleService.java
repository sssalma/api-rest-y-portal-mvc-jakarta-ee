/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.AlertMessage;
import java.util.List;
import deim.urv.cat.homework2.model.Article;
import deim.urv.cat.homework2.model.ArticleID;
/**
 *
 * @author salma
 */
public interface ArticleService {
    List<Article> listArticles(String autor, String topic,AlertMessage alertMessage);
    ArticleID getPerId(int id, String user,String password);
}
