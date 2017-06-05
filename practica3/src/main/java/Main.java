import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.sql.SQLException;
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
            System.out.println(req.params("id"));
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss z");
            Date current = new Date();
            UsersServices usersServices = new UsersServices();
            Usuario user = usersServices.getUsuario(req.session().attribute(SESSION_NAME));
            Articulo a = new Articulo(
                    req.queryParams("titulo"),
                    req.queryParams("cuerpo"),
                    user,
                    dateFormat.format(current));
            String [] eti = req.queryParams("etiquetas").split(" ");
            a.setEtiquetasList(eti);
            new ArticulosServices().crearArticulo(a);
            res.redirect("/index");
            return null;
        });

        post("/cometar", (req, res) -> {
            req.queryParams("comentario");
            return null;
        });

        post("/login", (req, res) -> {
            if(Usuario.autentificar(req.queryParams("username"),req.queryParams("password"))){
                req.session().attribute(SESSION_NAME, req.queryParams("username"));
                res.redirect("/index");
             }
            return null;
        });
    }
}
