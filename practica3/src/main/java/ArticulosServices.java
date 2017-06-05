import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticulosServices {

    public List<Articulo> listaArticulos() {
        List<Articulo> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            String query = "select * from articulo";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Articulo articulo = new Articulo();
                articulo.setId(rs.getInt("ID"));
                articulo.setTitulo(rs.getString("TITULO"));
                articulo.setFecha(rs.getString("FECHA"));
                articulo.setCuerpo(rs.getString("CUERPO"));
                UsersServices usersServices = new UsersServices();
                Usuario autor = usersServices.getUsuario(rs.getString("AUTOR"));
                articulo.setAutor(autor);
                lista.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    /**
     *
     * @param id
     * @return
     */
    public Articulo getArticulo(long id) {
        Articulo articulo = null;
        Connection con = null;
        try {
            String query = "select * from articulo where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                articulo = new Articulo();
                articulo.setId(rs.getInt("ID"));
                articulo.setTitulo(rs.getString("TITULO"));
                articulo.setCuerpo(rs.getString("CUERPO"));
                articulo.setFecha(rs.getString("FECHA"));
                UsersServices usersServices = new UsersServices();
                Usuario autor = usersServices.getUsuario(rs.getString("AUTOR"));
                articulo.setAutor(autor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try { con.close(); }
            catch (SQLException ex) {
                Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return articulo;
    }

    public boolean crearArticulo(Articulo articulo){
        boolean ok =false;
        Connection con = null;
        try {
            String query = "INSERT INTO ARTICULO(titulo, cuerpo, autor, fecha) VALUES(?,?,?,?);";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, articulo.getTitulo());
            prepareStatement.setString(2, articulo.getCuerpo());
            prepareStatement.setString(3, articulo.getAutor().getUsername());
            prepareStatement.setString(4, articulo.getFecha());
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public boolean actualizarArticulo(Articulo articulo){
        boolean ok =false;
        Connection con = null;
        try {

            String query = "UPDATE articulo SET titulo=?, cuerpo=?, autor=?, fecha=? WHERE id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, articulo.getTitulo());
            prepareStatement.setString(2, articulo.getCuerpo());
            prepareStatement.setString(3, articulo.getAutor().getUsername());
            prepareStatement.setString(4, articulo.getFecha());
            //Indica el where...
            prepareStatement.setLong(5, articulo.getId());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public boolean borrarArticulo(long id){
        boolean ok =false;
        Connection con = null;
        try {
            String query = "DELETE FROM articulo WHERE id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Indica el where...
            prepareStatement.setLong(1, id);
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

}
