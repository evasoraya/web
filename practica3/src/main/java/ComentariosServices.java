import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComentariosServices {

    public List<Comentario> listaComentarios() {
        List<Comentario> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            String query = "select * from comentario";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            lista = getCommentsList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    /**
     *
     * @param id
     * @return comentario
     */
    public Comentario getComentario(int id) {
        Comentario comentario = null;
        Connection con = null;
        try {
            String query = "select * from comentario where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                comentario = new Comentario();
                comentario.setId(rs.getInt("ID"));
                comentario.setComentario(rs.getString("COMENTARIO"));
                UsersServices usersServices = new UsersServices();
                Usuario autor = usersServices.getUsuario(rs.getString("AUTOR"));
                comentario.setAutor(autor);
                ArticulosServices articulosServices = new ArticulosServices();
                Articulo articulo = articulosServices.getArticulo(rs.getInt("ARTICULO"));
                comentario.setArticulo(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try { con.close(); }
            catch (SQLException ex) {
                Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return comentario;
    }

    public boolean crearComentario(Comentario comentario){
        boolean ok =false;
        Connection con = null;
        try {
            String query = "INSERT INTO COMENTARIO(comentario, autor, articulo) VALUES(?,?,?);";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, comentario.getComentario());
            prepareStatement.setString(2, comentario.getAutor().getUsername());
            prepareStatement.setLong(3, comentario.getArticulo().getId());
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public boolean actualizarComentario(Comentario comentario){
        boolean ok =false;
        Connection con = null;
        try {

            String query = "UPDATE comentario SET comentario=?, autor=?, articulo=? WHERE id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, comentario.getComentario());
            prepareStatement.setString(2, comentario.getAutor().getUsername());
            prepareStatement.setLong(3, comentario.getArticulo().getId());
            //Indica el where...
            prepareStatement.setLong(4, comentario.getId());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public boolean borrarComentario(long id){
        boolean ok =false;

        Connection con = null;
        try {
            String query = "DELETE FROM comentario WHERE id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Indica el where...
            prepareStatement.setLong(1, id);
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }


    public List<Comentario> getArticulosComments(long artID){
        List<Comentario> comentarios = null;
        Connection con = null;
        try {
            String query = "select * from comentario where articulo = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, artID);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            comentarios = getCommentsList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try { con.close(); }
            catch (SQLException ex) {
                Logger.getLogger(ComentariosServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return comentarios;
    }

    private List<Comentario> getCommentsList(ResultSet rs) {
        List<Comentario> comentarios = new ArrayList<>();
        try {
            while(rs.next()){
                Comentario com = new Comentario();
                com.setId(rs.getInt("ID"));
                com.setComentario(rs.getString("COMENTARIO"));
                UsersServices usersServices = new UsersServices();
                Usuario autor = usersServices.getUsuario(rs.getString("AUTOR"));
                com.setAutor(autor);
                ArticulosServices articulosServices = new ArticulosServices();
                Articulo articulo = articulosServices.getArticulo(rs.getInt("ARTICULO"));
                com.setArticulo(articulo);
                comentarios.add(com);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }

}
