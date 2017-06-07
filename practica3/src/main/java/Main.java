import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static spark.Spark.*;

/**
 * Created by eva_c on 6/3/2017.
 */
public class Main {
    private static final String SESSION_NAME = "username";
    private static final String COOKIE_NAME = "user_cookie";

    public static void main(String[] args) throws IOException {
        staticFileLocation("/");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
        port(4558);

        //region Subir Base de datos.
        try {
            H2Services.startDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseServices.getInstancia().testConexion();
        try {
            H2Services.crearTablas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //endregion

        get("/index", (req, res) -> {
            checkCookies(req);
            ArticulosServices articulosServices = new ArticulosServices();
            List<Articulo> articulos = articulosServices.listaArticulos();
            for (Articulo articulo : articulos){
                articulo.retrieveTags();
            }

            Map<String, Object> model = new HashMap<>();
            model.put("articulos", articulos);
            return new ModelAndView(model, "index.ftl");
        }, freeMarkerEngine);

        get("/crearArticulo", (req, res) -> {
            checkCookies(req);
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "crearArticulo.ftl");
        }, freeMarkerEngine);


        get("/post/:id", (req, res) -> {
            checkCookies(req);
            int id = Integer.parseInt(req.params("id"));
            ArticulosServices articulosServices = new ArticulosServices();
            Articulo articulo = articulosServices.getArticulo(id);
            articulo.retrieveComments();
            articulo.retrieveTags();
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss z");
            Date current = new Date();
            UsersServices usersServices = new UsersServices();
            Usuario user = usersServices.getUsuario(req.session().attribute(SESSION_NAME));
            Articulo a = new Articulo(
                    req.queryParams("titulo"),
                    req.queryParams("cuerpo"),
                    user,
                    dateFormat.format(current));
             ArticulosServices articulosServices = new ArticulosServices();
             articulosServices.crearArticulo(a);
             a = articulosServices.getUltimoArticulo();
            String [] eti = req.queryParams("etiquetas").split(" ");
            EtiquetaArticulo.crearRelaciones(eti,a);

            res.redirect("/index");
            return null;
        });

        post("/post1/:id", (req, res) -> {
            UsersServices usersServices = new UsersServices();
            Usuario user = usersServices.getUsuario(req.session().attribute(SESSION_NAME));
            Articulo a =  new ArticulosServices().getArticulo( Integer.parseInt(req.queryParams("id")));
            Comentario c = new Comentario(req.queryParams("comentario"),user,a);
            new ComentariosServices().crearComentario(c);

            res.redirect("/post/"+ req.queryParams("id"));
            return null;
        });

        get("/editar", (req, res) -> {
            checkCookies(req);
            Map<String, Object> model = new HashMap<>();
            Articulo a = new ArticulosServices().getArticulo(Long.parseLong(req.queryParams("id")));
            a.retrieveTags();

            model.put("articulo",a);
            return new ModelAndView(model, "editarArticulo.ftl");
        }, freeMarkerEngine);

        post("/editar", (req, res) -> {
            Articulo a = new ArticulosServices().getArticulo(Long.parseLong(req.queryParams("id")));
            a.retrieveTags();
            a.setTitulo(req.queryParams(("titulo")));
            a.setCuerpo(req.queryParams("cuerpo"));
            new ArticulosServices().actualizarArticulo(a);
            res.redirect("/index");
            return null;
        });

        post("/borrar", (req, res) -> {
            List<Comentario> comentarios = new ComentariosServices().getArticulosComments(Long.parseLong(req.queryParams("id")));
            Articulo a = new ArticulosServices().getArticulo(Long.parseLong(req.queryParams("id")));
            a.retrieveTags();
            for(Comentario c : comentarios){
                new ComentariosServices().borrarComentario(c.getId());
            }

            for(Etiqueta c : a.getEtiquetas()){
                new EtiquetaArticuloServices().borrarEtiquetaArticulo(c.getId(), a.getId());

            }
            new ArticulosServices().borrarArticulo(Long.parseLong(req.queryParams("id")));
            res.redirect("/index");
            return null;
        });

        post("/login", (req, res) -> {
            String username = req.queryParams("username");
            if(Usuario.autentificar(username,req.queryParams("password"))){
                res.cookie(COOKIE_NAME, username, 3600);
                req.session().attribute(SESSION_NAME, req.queryParams("username"));
                res.redirect("/index");
             }
            return null;
        });
    }

    private static void checkCookies(Request req) {
        if (req.session().attribute(SESSION_NAME) == null) {
            Map<String, String> cookies = req.cookies();
            if (cookies.containsKey(COOKIE_NAME)){
                System.out.println("Cookie found! -> " + cookies.get(COOKIE_NAME));
                req.session().attribute(SESSION_NAME, cookies.get(COOKIE_NAME));
            }
        }
    }
}
