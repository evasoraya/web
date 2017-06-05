import freemarker.template.Configuration;
import javafx.beans.property.LongPropertyBase;
import org.h2.engine.User;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
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

        get("/index", (req, res) -> {
            ArticulosServices articulosServices = new ArticulosServices();
            List<Articulo> articulos = articulosServices.listaArticulos();
            Map<String, Object> model = new HashMap<>();

            for (Articulo a : articulos){
                System.out.println("......................"
                        +a.getTitulo()+" "+ a.getId() + " "+ a.getCuerpo()+ " "+ a.getAutor().getNombre() + " "+ a.getFecha());
            }

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
            Date current = new Date();
            UsersServices usersServices = new UsersServices();
            Usuario user = usersServices.getUsuario(req.session().attribute(SESSION_NAME));
            Articulo a = new Articulo(

                    req.queryParams("titulo"),
                    req.queryParams("cuerpo"),
                    user,
                    current.toString());
            //System.out.println(user.getNombre());
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
            if(autentificacion(req.queryParams("username"),req.queryParams("password"))){

                String usuario = req.queryParams("username");

                req.session().attribute(SESSION_NAME, req.queryParams("username"));
                res.redirect("/index");

             }
            return null;
        });
    }
    private static void crearNuevoComentario(Comentario c, Articulo a) {
        c.setArticulo(a);
        ComentariosServices comentariosServices = new ComentariosServices();
        comentariosServices.crearComentario(c);
        List<Comentario> comentarios = comentariosServices.getArticulosComments(a.getId());
        a.setComentarios(new ArrayList<>(comentarios));
    }
    private static Articulo crearNuevaEtiqueta(String[] e, Articulo a){
        EtiquetasServices etiquetasServices = new EtiquetasServices();
        for (String s : e){
            etiquetasServices.crearEtiqueta(new Etiqueta(s, a));
        }
        List<Etiqueta> etiquetas = etiquetasServices.listaEtiquetas();
        a.setEtiquetas(new ArrayList<>(etiquetas));
        return a;
    }
    private static void crearNuevoArticulo(Articulo a){
        new ArticulosServices().crearArticulo(a);
    }

    private static boolean autentificacion(String username, String password){
        List<Usuario> usuarios;
        UsersServices usersServices = new UsersServices();
        usuarios = usersServices.listaUsuarios();
        System.out.println(usuarios.size());
        for(Usuario u : usuarios){
            if(username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                return true;
            }
        }
      return false;
    }

}
