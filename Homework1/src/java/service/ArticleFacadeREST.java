/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Credentials;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entities.Article;
//import authn.Secured;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.core.SecurityContext;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import model.entities.Usuari;
import model.entities.Topic;
import model.entities.ArticleDTO;

/**
 *
 * @author salma
 */

@Stateless
@Path("article")
public class ArticleFacadeREST extends AbstractFacade<Article> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public ArticleFacadeREST() {
        super(Article.class);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getArticles(
            @jakarta.ws.rs.QueryParam("topic") List<String> topics,
            @jakarta.ws.rs.QueryParam("autor") String autor
    ) {
        //Consulta base amb StringBuilder --construcció dinàmica
        StringBuilder consulta = new StringBuilder("SELECT a FROM Article a WHERE 1=1 ");

        //per autor
        if (autor != null && !autor.isEmpty()) {
            consulta.append("AND a.autor.nom = :autor ");
        }

        //per topic
        if (topics != null && !topics.isEmpty()) {
            consulta.append("AND EXISTS (SELECT t FROM a.topics t WHERE t.nom IN :topics) ");
        }

        consulta.append("ORDER BY a.views DESC");

        // Creo la consulta i passo els paràmetres
        TypedQuery<Article> query = em.createQuery(consulta.toString(), Article.class);

        if (autor != null && !autor.isEmpty()) {
            query.setParameter("autor", autor);
        }
        if (topics != null && !topics.isEmpty()) {
            query.setParameter("topics", topics);
        }

        // executo, i obtinc resultats amb .getResultList()
        List<Article> articles = query.getResultList();

        if (articles.isEmpty()) { //si no hi ha articles que compleixin ambdues condicions d'autor i topic, retorna buit. ==Filtre per autor 
            //consulta estàtica en string
            String consulta2 = "SELECT a FROM Article a WHERE a.autor.nom = :autor ORDER BY a.views DESC ";

            TypedQuery<Article> query2 = em.createQuery(consulta2, Article.class);
            query2.setParameter("autor", autor);
            articles = query2.getResultList();
        }

        List<ArticleDTO> articlesDTO = passarLlistaADTO(articles);
        
        if (articlesDTO.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(articlesDTO).build();
    }

    public ArticleDTO passarADTO(Article article) {
        //Metode per convertir un article en articleDTO
        long id = article.getId();
        String autor = article.getAutor().getNom();
        String views = article.getViews2();
        Boolean esPublic = article.isEsPublic();

        return new ArticleDTO(article.getId(), article.getImatge(), autor, article.getTitol(), article.getResum(), article.getData(), views, esPublic);
    }

    public List<ArticleDTO> passarLlistaADTO(List<Article> articles) {
        return articles.stream()
                .map(this::passarADTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    // @Secured deixo d'usarlo i passo a fer la comprovació amb la capçalera.
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getArticleById(@PathParam("id") int id, @Context HttpHeaders headers) {

        //obtencio de l'article
        Article article = super.find(id);

        if (article == null) {
            System.out.println("No es troba l'article");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No es troba l'article")
                    .build();
        }

        //------Correcció: es fa la comprovació pels privats aquí amb validarHeader()
        if (!article.isEsPublic()) {

            if (!validarHeader(headers)) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Article privat, comprova les credencials")
                        .build();
            }
        }

        //el visito == views ++
        article.setViews(article.getViews() + 1);
        super.edit(article);

        return Response.ok(article).build();

    }

    @POST
    // @Secured deixo d'usarlo i passo a fer la comprovació amb la capçalera. 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createArticle(Article article, @Context HttpHeaders headers) {

        //--Validació autentització:
        if (!validarHeader(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("No estàs autenticat")
                    .build();
        }

        //1. verifico que l'autor existeix 
        Usuari autor = em.find(Usuari.class, article.getAutor().getId());
        if (autor == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        //2. verifico que l'id és corresponent al nom
        if (!autor.getNom().equals(article.getAutor().getNom())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("L'usuari (id-nom) no existeix").build();
        }
        //valido els topics
        //1. Verifico que es proporcionen, com a màxim 2 tòpics
        if (article.getTopics() == null || article.getTopics().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("T'has deixat els topics!").build();
        }
        if (article.getTopics().size() > 2) {
            return Response.status(Response.Status.BAD_REQUEST).entity("El màxim son dos topics.").build();
        }

        //2. miro que els procporcionats siguin vàlids (id existent)
        for (Topic topic : article.getTopics()) {
            Topic existent = em.find(Topic.class, topic.getId());
            if (existent == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("El topic amb ID " + topic.getId() + " no existeix.").build();
            }

            //2.1 miro que l'id i el nom del topic coincideixin
            if (!existent.getName().equals(topic.getName())) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Els topics no són valids, comprova'n el nom o l'id").build();
            }
        }
        //fico la data actual
        article.setData(new Date());

        //actualitzo la bd, afegintlo
        em.persist(article);

        //retornar codi 201 created i id
        return Response.status(Response.Status.CREATED)
                .entity("Artcle creat amb ID: " + article.getId())
                .build();

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;

    }

    private boolean validarHeader(HttpHeaders headers) {
        List<String> headersAuth = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);

        if (headersAuth == null || headersAuth.isEmpty()) {
            return false;
        } else {
            try {
                // Decodificar y extraer usuario y contraseña
                String auth = headersAuth.get(0).replace("Basic ", "");
                String decode = new String(Base64.getDecoder().decode(auth), StandardCharsets.UTF_8);
                StringTokenizer tokenizer = new StringTokenizer(decode, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                // Valido els credencials amb la namedquery findUsers de Credentials.
                TypedQuery<Credentials> query = em.createNamedQuery("Credentials.findUser", Credentials.class);
                Credentials c = query.setParameter("username", username).getSingleResult();

                // Si trobo l'username, en comprovo el password.
                return c.getPassword().equals(password);
            } catch (Exception e) {
                return false;
            }
        }
    }

}
