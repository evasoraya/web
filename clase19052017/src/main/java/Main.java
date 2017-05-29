import org.apache.commons.validator.UrlValidator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by eva_c on 5/19/2017.
 */
public class Main
{
   public static void main(String[]args) throws IOException {

       String url ;//= "https://www.pucmm.edu.do";
       System.out.println("Escriba una url valida: ");
       Scanner in = new Scanner(System.in);
       url = in.next();

       String[] schemes = {"http","https"};
       UrlValidator urlValidator = new UrlValidator(schemes);
       if (!urlValidator.isValid(url)) {
           System.out.println("Escriba una url valida: ");
            in = new Scanner(System.in);
           url = in.next();
       }


        Document doc = Jsoup.connect(url).get();
        Elements formulariosPOST = doc.select("form[method= POST]");
        Elements formulariosGET = doc.select("form[method= GET]");
        Elements parrafos = doc.select("p");

      // org.jsoup.nodes.Document dom = Jsoup.parse(html);
     //  String text = dom.text();

    //   System.out.println("load from jsoup connect= " + es.size());

        System.out.println("Hay " + doc.html().split("\\n").length +" lineas" );
        System.out.println("Hay " + parrafos.select("img").size() +" imagenes en los parrafos");
        System.out.println("Hay " + parrafos.size() +" parrafos");

        System.out.println("Hay " + formulariosGET.size() +" formulariosGET");
        
        int i = 0;
        for(Element form : formulariosGET){

            i++;
            System.out.println("Form "+ i );
            for(Element e : form.getElementsByAttribute("type").select("input"))
                System.out.println( " " +e.attr("type"));
        }

        System.out.println("Hay " + formulariosPOST.size() +" formulariosPOST");
        i=0;
        for(Element form : formulariosPOST){

            i++;
            System.out.println("Form "+ i );
            for(Element e : form.getElementsByAttribute("type").select("input"))
                System.out.println( " " +e.attr("type"));

        }

       for(Element f : formulariosPOST){
           int j= f.attr("action").length();
           String action = f.attr("action");
          // f.absUrl("action");
           //System.out.println(f.absUrl("action"));
           if(Character.toString(f.attr("action").charAt(0)).matches("."))
               action = f.attr("action").toString().substring(1,j);

          Document d = Jsoup.connect(f.absUrl("action")).data("asignatura","practica1").post();
          //Connection.Response d = Jsoup.connect(url + action).data("asignatura","practica1").method(Connection.Method.POST).execute();
           System.out.println(d);
       }


   }

}

