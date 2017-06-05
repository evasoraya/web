import freemarker.template.Configuration;
import javafx.beans.property.LongPropertyBase;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static spark.Spark.*;

/**
 * Created by eva_c on 6/3/2017.
 */
public class Main {
    private static ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

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



        get("/crearArticulo", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "crearArticulo.ftl");
        }, freeMarkerEngine);

        get("/post", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Articulo a = new Articulo(
                    0,
                    req.queryParams("titulo"),
                    req.queryParams("cuerpo"),
                   buscarUsuario( req.queryParams("usuario")),
                    req.queryParams("titulo")

            );
            model.put("articulo",a);
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
           Date current = new Date();

                Articulo a = new Articulo(
                        Long.parseLong(req.queryParams("id")),
                        req.queryParams("titulo"),
                        req.queryParams("cuerpo"),
                        loggedInUser,
                        current.toString()

                );
                String [] eti = req.queryParams("etiquetas").split(" ");
                System.out.println(eti.length);
                Articulo art = crearNuevaEtiqueta(eti,a);
                crearNuevoArticulo(art);

            //System.out.println(a.getEtiquetas().size());
            //System.out.println(articulos.get(0).getEtiquetas().get(0).getEtiqueta());

            res.redirect("/index");
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
    private static Articulo crearNuevaEtiqueta(String[] e, Articulo a){
        Articulo art = new Articulo();
        ArrayList<Etiqueta> etiquetas = new ArrayList<>();
        for (String s : e){
            etiquetas.add(new Etiqueta(s));
        }
        a.setEtiquetas(etiquetas);

        return a;
    }
    private static void crearNuevoArticulo(Articulo a){
        articulos.add(a);
    }

    private static Usuario buscarUsuario (String nombre){
        for(Usuario a : usuarios){
            if(a.getNombre().equals(nombre)){
                return a;
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
