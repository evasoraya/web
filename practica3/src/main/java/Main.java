import freemarker.template.Configuration;
import javafx.beans.property.LongPropertyBase;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by eva_c on 6/3/2017.
 */
public class Main {
    private static ArrayList<Articulo> articulos = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static Usuario loggedInUser = null;
    public static void main(String[] args) throws IOException {

        staticFileLocation("/");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
        port(4558);
        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("articulos", articulos);
            return new ModelAndView(model, "index.ftl");
        }, freeMarkerEngine);

        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
            Articulo a = new Articulo(
                                        req.queryParams("titulo"),
                                        req.queryParams("Cuerpo"),
                                        buscarUsuario(req.queryParams("titulo")),
                                        df.parse(req.queryParams("titulo"))


            );
            model.put("articulos", articulos);
            return new ModelAndView(model, "index.ftl");
        }, freeMarkerEngine);

        get("/about", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "about.ftl");
        }, freeMarkerEngine);
        get("/post", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "post.ftl");
        }, freeMarkerEngine);
        get("/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "login.ftl");
        }, freeMarkerEngine);


        get("/registrar", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "registrar.ftl");
        }, freeMarkerEngine);

        post("/crearArticulo", (req, res) -> {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH);
            Usuario u = buscarUsuario(req.queryParams("autor"));
            try {
                Articulo a = new Articulo(
                        req.queryParams("titulo"),
                        req.queryParams("cuerpo"),
                        u,
                        df.parse(req.queryParams("fecha"))
                );
                crearNuevaEtiqueta(req.queryParams("etiquetas").split(" "),a);
                crearNuevoArticulo(a);
            }catch (ParseException pe){

            }
            res.redirect("/home");
            return null;
        });
        post("/cometar", (req, res) -> {
            req.queryParams("comentario");

            return null;
        });


        post("/login", (req, res) -> {
           if(autentificacion(req.queryParams("usuario"),req.queryParams("password")))
               res.redirect("/home");

            return null;
        });



    }
    private static  void crearNuevoComentario(Comentario c, Articulo a) {
        for (Articulo ar : articulos) {
            if (ar.getId() == a.getId()) {
                ar.comentarios.add(c);

            }
        }

    }
    private static void crearNuevaEtiqueta(String[] e, Articulo a){

        for (Articulo ar : articulos) {
            if (ar.getId() == a.getId()) {
                for(String s : e){
                    ar.etiquetas.add(new Etiqueta(s));
                }

            }
        }
    }
    private static void crearNuevoArticulo(Articulo a){
        articulos.add(a);
    }

    private static Usuario buscarUsuario (String nombre){
        for(Articulo a : articulos){
            if(a.getAutor().getNombre().equals(nombre)){
                return a.getAutor();
            }

        }
        return  null;
    }
    private static boolean autentificacion(String username, String password){
        for(Usuario u : usuarios){
            if(username.equals(u.getUsername()) && password.equals(u.getPassword())) {
               loggedInUser = u;
                return true;
            }

        }
      return false;
    }

}
