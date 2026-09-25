package deim.urv.cat.homework2.model;


public class Article {
    private String id;
    private String titol;
    private String autor;
    private String topic;
    private String resum;
    private String imatge; 
    private String data;    
    private String views;
    private boolean esPublic;
    

    public Article() { //constructor buit per la descerialització
    }

    public Article(String id, String titol, String autor, String topic, String resum, String imatge, String data, String views, boolean esPublic) {
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.topic = topic;
        this.resum = resum;
        this.imatge = imatge;
        this.data = data;
        this.views = views;
        this.esPublic = esPublic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    // Getters y setters

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public boolean getEsPublic() {
        return esPublic;
    }

    public void setEsPublic(boolean esPublic) {
        this.esPublic = esPublic;
    }
}
