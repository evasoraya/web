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
    public ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
    public ArrayList<Etiqueta> etiquetas= new ArrayList<Etiqueta>();

    public Articulo(){}

    public Articulo( String titulo, String cuerpo, Usuario autor, String fecha /*ArrayList<Etiqueta> etiquetas*/) {

        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
       // this.etiquetas = etiquetas;
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

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

}
