import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EtiquetasServices {

    public List<Etiqueta> listaEtiquetas() {
        List<Etiqueta> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {

            String query = "select * from etiqueta";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            lista = getTagList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    private List<Etiqueta> getTagList(ResultSet rs) {
        List<Etiqueta> lista = new ArrayList<>();
        try {
            while(rs.next()){
                Etiqueta etiqueta = new Etiqueta();
                etiqueta.setId(rs.getInt("ID"));
                etiqueta.setEtiqueta(rs.getString("NOMBRE"));
                ArticulosServices articulosServices = new ArticulosServices();
                Articulo articulo = articulosServices.getArticulo(rs.getInt("ARTICULO"));
                etiqueta.setArticulo(articulo);
                lista.add(etiqueta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     *
     * @param id
     * @return etiqueta
     */
    public Etiqueta getEtiqueta(int id) {
        Etiqueta etiqueta = null;
        Connection con = null;
        try {
            String query = "select * from etiqueta where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                etiqueta = new Etiqueta();
                etiqueta.setId(rs.getInt("ID"));
                etiqueta.setEtiqueta(rs.getString("NOMBRE"));
                ArticulosServices articulosServices = new ArticulosServices();
                Articulo articulo = articulosServices.getArticulo(rs.getInt("ARTICULO"));
                etiqueta.setArticulo(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try { con.close(); }
            catch (SQLException ex) {
                Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return etiqueta;
    }

    public boolean crearEtiqueta(Etiqueta etiqueta){
        boolean ok =false;
        Connection con = null;
        try {
            String query = "INSERT INTO COMENTARIO(nombre, articulo) VALUES (?,?);";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, etiqueta.getEtiqueta());
            prepareStatement.setLong(2, etiqueta.getArticulo().getId());
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public boolean actualizarEtiqueta(Etiqueta etiqueta){
        boolean ok =false;
        Connection con = null;
        try {
            String query = "UPDATE etiqueta SET nombre=?, articulo=? WHERE id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, etiqueta.getEtiqueta());
            prepareStatement.setLong(2, etiqueta.getArticulo().getId());
            //Indica el where...
            prepareStatement.setLong(3, etiqueta.getId());
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public boolean borrarEtiqueta(long id){
        boolean ok =false;
        Connection con = null;
        try {
            String query = "DELETE FROM etiqueta WHERE id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Indica el where...
            prepareStatement.setLong(1, id);
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }


    public List<Etiqueta> getArticlesTags(long id){
        List<Etiqueta> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            String query = "select * from etiqueta where ARTICULO=?";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //where...
            prepareStatement.setLong(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            lista = getTagList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetasServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
}
