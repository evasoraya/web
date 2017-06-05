/**
 * Created by eva_c on 6/3/2017.
 */
public class Etiqueta {
    private long id = -1;
    private String etiqueta;
    private Articulo articulo;

    public Etiqueta(){}

    public Etiqueta(String etiqueta, Articulo articulo) {
        this.etiqueta = etiqueta;
        this.articulo = articulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
