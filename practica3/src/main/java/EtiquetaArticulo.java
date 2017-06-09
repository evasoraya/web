import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dewyn on 06/06/17.
 */
public class EtiquetaArticulo {
    private long id;
    private long idArticulo;
    private long idEtiqueta;

    public EtiquetaArticulo() {}

    public EtiquetaArticulo(long idArticulo, long idEtiqueta) {
        this.idArticulo = idArticulo;
        this.idEtiqueta = idEtiqueta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public long getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(long idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public static void crearRelaciones(String[] eti, Articulo articulo) {
        List<String> tags = new LinkedList<>(Arrays.asList(eti));
        EtiquetasServices etiquetasServices =  new EtiquetasServices();
        List<Etiqueta> etiquetas = etiquetasServices.listaEtiquetas();

        ArrayList<String> toRemove = new ArrayList<>();

        if (articulo.getEtiquetas().size() != 0){
            for (Etiqueta e : articulo.getEtiquetas()){
                for (String s : tags){
                    if (e.getEtiqueta().equals(s)){
                        toRemove.add(s);
                        //tags.remove(s);
                    }
                }
            }
        }

        tags.removeAll(toRemove);

        for (String s : tags){
            boolean there = false;
            if (etiquetas.size() != 0){
                for (Etiqueta e : etiquetas){
                    if (s.equals(e.getEtiqueta())){
                        there = true;
                        break;
                    }
                }
                if (!there){
                    new EtiquetasServices().crearEtiqueta(new Etiqueta(s));
                    etiquetas = etiquetasServices.listaEtiquetas();
                }
            } else {
                new EtiquetasServices().crearEtiqueta(new Etiqueta(s));
                etiquetas = etiquetasServices.listaEtiquetas();
            }
        }

       //etiquetas = new EtiquetasServices().listaEtiquetas();
        for (String s : tags){
            for(Etiqueta e : etiquetas){
                if (s.equals(e.getEtiqueta())){
                    new EtiquetaArticuloServices().crearEtiquetaArticulo(new EtiquetaArticulo(articulo.getId(), e.getId()));
                }
            }
        }

    }

    public static ArrayList<Etiqueta> getArticlesTags(long id) {
        ArrayList<Long> etiquetasID = new EtiquetaArticuloServices().getSelectedTags(id);
        List<Etiqueta> allTags = new EtiquetasServices().listaEtiquetas();
        ArrayList<Etiqueta> etiquetas = new ArrayList<>();

        for (long tagId : etiquetasID){
            for (Etiqueta e : allTags){
                if (tagId == e.getId()){
                    etiquetas.add(e);
                }
            }
        }

        return etiquetas;
    }
}
