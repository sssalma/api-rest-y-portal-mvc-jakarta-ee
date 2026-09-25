
package deim.urv.cat.homework2.model;


import java.util.List;

/**
 *
 * @author salma
 */
public class ArticleID {
    private String id;
    private String titol;
    
    private String resum;
    private String imatge; 
    private String data;    
    private String views2;
    private boolean esPublic;
    private String text;
    private List<Topic> topics;
    private Autor autor;

    public ArticleID() { //constructor buit per la descerialització
    }

    public ArticleID(String id, String titol, Autor autor, List<Topic>topics, String text,String resum, String imatge, String data, String views2, boolean esPublic) {
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.topics = topics;
        this.resum = resum;
        this.imatge = imatge;
        this.data = data;
        this.views2 = views2;
        this.esPublic = esPublic;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getResum() {
        return resum;
    }

    public void setResum(String resum) {
        this.resum = resum;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getViews2() {
        return views2;
    }

    public void setViews2(String views2) {
        this.views2 = views2;
    }

    public boolean isEsPublic() {
        return esPublic;
    }

    public void setEsPublic(boolean esPublic) {
        this.esPublic = esPublic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }







}

   
    

