import freemarker.template.Configuration;
import javafx.beans.property.LongPropertyBase;
import org.h2.engine.User;
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
    private static final String SESSION_NAME = "username";

    public static void main(String[] args) throws IOException {
        staticFileLocation("/");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
        port(4558);


        get("/index", (req, res) -> {
            ArticulosServices articulosServices = new ArticulosServices();
            List<Articulo> articulos = articulosServices.listaArticulos();
            Map<String, Object> model = new HashMap<>();
            model.put("articulos", articulos);
            return new ModelAndView(model, "index.ftl");
        }, freeMarkerEngine);



        get("/crearArticulo", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "crearArticulo.ftl");
        }, freeMarkerEngine);




        get("/post/:id", (req, res) -> {


            int id = Integer.parseInt(req.params("id"));
            ArticulosServices articulosServices = new ArticulosServices();
            Articulo articulo = articulosServices.getArticulo(id);
            Map<String, Object> model = new HashMap<>();
            model.put("articulo", articulo);
            return new ModelAndView(model, "post.ftl");
        }, freeMarkerEngine);

        get("/login", (req, res) -> {
            return new ModelAndView(null, "login.ftl");
        }, freeMarkerEngine);


        get("/registrar", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "registrar.ftl");
        }, freeMarkerEngine);

        post("/crearArticulo", (req, res) -> {
            Date current = new Date();
            UsersServices usersServices = new UsersServices();
            Usuario user = usersServices.getUsuario(req.session().attribute(SESSION_NAME));

            Articulo a = new Articulo(
                    Long.parseLong(req.queryParams("id")),
                    req.queryParams("titulo"),
                    req.queryParams("cuerpo"),
                    user,
                    current.toString());
            String [] eti = req.queryParams("etiquetas").split(" ");
            Articulo art = crearNuevaEtiqueta(eti,a);
            crearNuevoArticulo(art);
            res.redirect("/index");
            return null;
        });
        post("/cometar", (req, res) -> {
            req.queryParams("comentario");


            return null;
        });



        post("/login", (req, res) -> {
            if(autentificacion(req.queryParams("usuario"),req.queryParams("password"))){
                String usuario = req.queryParams("usuario");
                if (usuario == null){
                    req.session().attribute(SESSION_NAME, req.queryParams("usuario"));
                    res.redirect("/index");
                }
             }
            return null;
        });



    }
    private static  void crearNuevoComentario(Comentario c, Articulo a) {
        //TODO arreglar eso.
        List<Articulo> articulos = new ArrayList<>();
        for (Articulo ar : articulos) {
            if (ar.getId() == a.getId()) {
                ar.comentarios.add(c);
            }
        }

    }
    private static Articulo crearNuevaEtiqueta(String[] e, Articulo a){
        Articulo art = new Articulo();
        ArrayList<Etiqueta> etiquetas = new ArrayList<>();
        ArticulosServices articulosServices = new ArticulosServices();

        for (String s : e){
            etiquetas.add(new Etiqueta());
        }
        a.setEtiquetas(etiquetas);

        return a;
    }
    private static void crearNuevoArticulo(Articulo a){
        new ArticulosServices().crearArticulo(a);
    }

    private static Usuario buscarUsuario (String nombre){
        UsersServices usersServices = new UsersServices();
        List<Usuario> usuarios = usersServices.listaUsuarios();
        for(Usuario a : usuarios){
            if(a.getNombre().equals(nombre)){
                return a;
            }

        }
        return  null;
    }
    private static boolean autentificacion(String username, String password){
        List<Usuario> usuarios;
        UsersServices usersServices = new UsersServices();
        usuarios = usersServices.listaUsuarios();
        for(Usuario u : usuarios){
            if(username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                return true;
            }
        }
      return false;
    }

}
