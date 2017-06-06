import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by eva_c on 6/3/2017.
 */
public class Articulo {
    private long id;
    private String titulo;
    private String cuerpo;
    private Usuario autor;
    private String fecha;
    public ArrayList<Comentario> comentarios = new ArrayList<>();
    public ArrayList<Etiqueta> etiquetas= new ArrayList<>();

    public Articulo(){}

    public Articulo( String titulo, String cuerpo, Usuario autor, String fecha) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    private void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    private void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public void setEtiquetasList(String[] etiquetas){
        EtiquetasServices etiquetasServices = new EtiquetasServices();
        for (String s : etiquetas){
            etiquetasServices.crearEtiqueta(new Etiqueta(s, this));
        }
        List<Etiqueta> nuevas_etiquetas = etiquetasServices.listaEtiquetas();
        this.setEtiquetas(new ArrayList<>(nuevas_etiquetas));
    }

    public void retrieveComments(){
        List<Comentario> comentarios = new ComentariosServices().getArticulosComments(this.getId());
        this.setComentarios(new ArrayList<>(comentarios));
    }

    public void retrieveTags(){
        this.setEtiquetas(new ArrayList<>(new EtiquetasServices().getArticlesTags(this.getId())));
    }
}
