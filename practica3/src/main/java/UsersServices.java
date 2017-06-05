import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersServices {

    public List<Usuario> listaUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {

            String query = "select * from usuario";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Usuario user = new Usuario();
                user.setUsername(rs.getString("USERNAME"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setAdministrator(rs.getBoolean("ADMINISTRADOR"));
                user.setAutor(rs.getBoolean("AUTOR"));
                user.setPassword(rs.getString("PASSWORD"));
                lista.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    /**
     *
     * @param username
     * @return
     */
    public Usuario getUsuario(String username) {
        Usuario user = null;
        Connection con = null;
        try {

            String query = "select * from usuario where username = ?";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, username);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                user = new Usuario();
                user.setUsername(rs.getString("USERNAME"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setAdministrator(rs.getBoolean("ADMINISTRADOR"));
                user.setAutor(rs.getBoolean("AUTOR"));
                user.setPassword(rs.getString("PASSWORD"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try { con.close(); }
            catch (SQLException ex) {
                Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public boolean crearUsuario(Usuario user){
        boolean ok =false;

        Connection con = null;
        try {
            String query = "INSERT INTO USUARIO(username, nombre, password, administrador, autor) VALUES(?,?,?,?,?)";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, user.getUsername());
            prepareStatement.setString(2, user.getNombre());
            prepareStatement.setString(3, user.getPassword());
            prepareStatement.setBoolean(4, user.isAdministrator());
            prepareStatement.setBoolean(5, user.isAutor());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public boolean actualizarUsuario(Usuario user){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "UPDATE usuario SET nombre=?, password=?, administrador=?, autor=? WHERE username = ?";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, user.getNombre());
            prepareStatement.setString(2, user.getPassword());
            prepareStatement.setBoolean(4, user.isAdministrator());
            prepareStatement.setBoolean(3, user.isAutor());
            //Indica el where...
            prepareStatement.setString(5, user.getUsername());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public boolean borrarUsuario(String username){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "DELETE FROM usuario WHERE username = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Indica el where...
            prepareStatement.setString(1, username);
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

}
