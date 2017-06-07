import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by vacax on 27/05/16.
 */
public class H2Services {

    /**
     * @throws SQLException
     */
    public static void startDb() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    /**
     * @throws SQLException
     */
    public static void stopDb() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }

    /**
     * Metodo para recrear las tablas necesarias
     *
     * @throws SQLException
     */
    public static void crearTablas() throws SQLException {
        String sqlUsuario = "CREATE TABLE IF NOT EXISTS USUARIO\n" +
                "(USERNAME VARCHAR(25) PRIMARY KEY NOT NULL,\n" +
                "  NOMBRE VARCHAR(100) NOT NULL,\n" +
                "  PASSWORD VARCHAR(100) NOT NULL,\n" +
                "  ADMINISTRADOR BOOLEAN NOT NULL,\n" +
                "  AUTOR BOOLEAN NOT NULL);";

        String sqlArticulo = "CREATE TABLE IF NOT EXISTS ARTICULO" +
                "(ID INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "TITULO VARCHAR(100) NOT NULL," +
                "CUERPO VARCHAR(2000) NOT NULL," +
                "AUTOR VARCHAR(25) NOT NULL, FOREIGN KEY (AUTOR) REFERENCES USUARIO(USERNAME)," +
                "FECHA VARCHAR(30) NOT NULL);";

        String sqlComentario = "CREATE TABLE IF NOT EXISTS COMENTARIO\n" +
                "(ID INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,COMENTARIO VARCHAR(1000) NOT NULL," +
                "AUTOR VARCHAR(25) NOT NULL, FOREIGN KEY (AUTOR) REFERENCES USUARIO(USERNAME)," +
                "ARTICULO INTEGER NOT NULL, FOREIGN KEY (ARTICULO) REFERENCES ARTICULO(ID));";

        String sqlEtiqueta =
                "CREATE TABLE IF NOT EXISTS ETIQUETA" +
                        "(ID INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                        "NOMBRE VARCHAR(100) NOT NULL, " +
                        "ARTICULO INTEGER NOT NULL, FOREIGN KEY (ARTICULO) REFERENCES ARTICULO(ID));";

        String sqlAdmin = "INSERT INTO USUARIO (USERNAME, NOMBRE, PASSWORD, ADMINISTRADOR, AUTOR)" +
                " VALUES ('admin', 'Administrador', 'admin', TRUE, TRUE);";

        Connection con = DataBaseServices.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sqlUsuario);
        statement.execute(sqlArticulo);
        statement.execute(sqlEtiqueta);
        statement.execute(sqlComentario);
        statement.execute(sqlAdmin);
        statement.close();
        con.close();
    }

}
