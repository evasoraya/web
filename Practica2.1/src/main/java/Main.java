import spark.ModelAndView;
import freemarker.template.Configuration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
//import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.*;


/**
 * Created by eva_c on 5/31/2017.
 */
public class Main {
    private static ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

    public static void main(String[] args) throws IOException {
        staticFileLocation("templates");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
        ;
        port(4568);
        get("/home", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("estudiantes", estudiantes);
            return new ModelAndView(model, "home.ftl");
        }, freeMarkerEngine);

        get("/crear", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "crear.ftl");
        }, freeMarkerEngine);

        get("/editar", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            System.out.println(req.queryParams("matricula").replace(",", ""));
            Estudiante es = buscarEstudiante(Integer.parseInt(req.queryParams("matricula").replace(",", "")));
            model.put("estudiante", es);
            return new ModelAndView(model, "editar.ftl");
        }, freeMarkerEngine);



        get("/ver", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            Estudiante es = buscarEstudiante(Integer.parseInt(req.queryParams("matricula").replace(",", "")));
            model.put("estudiante", es);
            return  new ModelAndView(model,"ver.ftl");
        }, freeMarkerEngine);

        post("/crear", (req, res) -> {
            Estudiante es = new Estudiante(req.queryParams("nombre"), req.queryParams("apellido"),
                    req.queryParams("telefono"), Integer.parseInt(req.queryParams("matricula")));
            crearEstudiante(es);
            res.redirect("/home");
            return null;
        });

        post("/editar", (req, res) -> {
            System.out.println((req.queryParams("nombre") + req.queryParams("apellido") +
                    req.queryParams("telefono")));
            System.out.println(Integer.parseInt(req.queryParams("matricula").replace(",", "")));

            Estudiante es = new Estudiante(req.queryParams("nombre"), req.queryParams("apellido"),
                    req.queryParams("telefono"), Integer.parseInt(req.queryParams("matricula").replace(",", "")));

            editarEstudiante(es);
            res.redirect("/home");
            return null;
        });
        get("/borrar", (req, res) -> {
            int es =  Integer.parseInt(req.queryParams("matricula").replace(",", ""));
            borrarEstudiante(es);
            res.redirect("/home");
            return null;
        });





    }

    private static void crearEstudiante(Estudiante es) {
        estudiantes.add(es);
    }

    private static Estudiante buscarEstudiante(int matricula) {

        for (Estudiante e : estudiantes) {
            if (matricula == e.getMatricula()) return e;
        }
        return null;
    }

    private static void editarEstudiante(Estudiante es) {

        for (Estudiante e : estudiantes) {
            if (e.getMatricula() == es.getMatricula()) {
                e.setNombre(es.getNombre());
                e.setApellido(es.getApellido());
                e.setTelefono(es.getTelefono());
                // e.setMatricula(es.getMatricula());
                //return "se ha hecho su cambio";

            }

        }
        //return "Error";
    }

    private static void borrarEstudiante(int es) {

        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println(estudiantes.get(i).getMatricula() + "  " + estudiantes.get(i));

            if (estudiantes.get(i).getMatricula() == es) {
                estudiantes.remove(estudiantes.get(i));
            }

        }

    }
}
