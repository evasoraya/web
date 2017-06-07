import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EtiquetaArticuloServices {

    public List<EtiquetaArticulo> listaEtiquetaArticulos() {
        List<EtiquetaArticulo> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {

            String query = "select * from etiqueta_articulo";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            lista = getArtTagList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    private List<EtiquetaArticulo> getArtTagList(ResultSet rs) {
        List<EtiquetaArticulo> inter = new ArrayList<>();
        try {
            while(rs.next()){
                EtiquetaArticulo ea = new EtiquetaArticulo();
                ea.setId(rs.getInt("ID"));
                ea.setIdEtiqueta(rs.getLong("ID_ETIQUETA"));
                ea.setIdArticulo(rs.getLong("ID_ARTICULO"));
                inter.add(ea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inter;
    }

    /**
     *
     * @param id
     * @return etiqueta
     */
    public EtiquetaArticulo getEtiquetaArticulo(long id) {
        EtiquetaArticulo ea = null;
        Connection con = null;
        try {
            String query = "select * from etiqueta_articulo where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                ea = new EtiquetaArticulo();
                ea.setId(rs.getInt("ID"));
                ea.setIdEtiqueta(rs.getLong("ID_ETIQUETA"));
                ea.setIdArticulo(rs.getLong("ID_ARTICULO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try { con.close(); }
            catch (SQLException ex) {
                Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ea;
    }

    public boolean crearEtiquetaArticulo(EtiquetaArticulo ea){
        boolean ok =false;
        Connection con = null;
        try {
            String query = "INSERT INTO etiqueta_articulo (id_articulo, id_etiqueta ) VALUES (?,?);";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, ea.getIdArticulo());
            prepareStatement.setLong(2, ea.getIdEtiqueta());
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public boolean actualizarEtiquetaArticulo(EtiquetaArticulo ea){
        boolean ok =false;
        Connection con = null;
        try {
            String query = "UPDATE etiqueta_articulo SET id_articulo=?, id_etiqueta=? WHERE id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, ea.getIdArticulo());
            prepareStatement.setLong(2, ea.getIdEtiqueta());
            //Indica el where...
            prepareStatement.setLong(3, ea.getId());
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public boolean borrarEtiquetaArticulo(long id){
        boolean ok =false;
        Connection con = null;
        try {
            String query = "DELETE FROM etiqueta_articulo WHERE id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Indica el where...
            prepareStatement.setLong(1, id);
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public ArrayList<Long> getSelectedTags(long idArticulo) {
        List<Long> etiquetasID = new ArrayList<>();
        EtiquetaArticulo ea = null;
        Connection con = null;
        try {
            String query = "select * from etiqueta_articulo where id_articulo = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, idArticulo);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                /*ea = new EtiquetaArticulo();
                ea.setId(rs.getInt("ID"));
                ea.setIdEtiqueta(rs.getLong("ID_ETIQUETA"));
                ea.setIdArticulo(rs.getLong("ID_ARTICULO"));*/
                etiquetasID.add(rs.getLong("ID_ETIQUETA"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try { con.close(); }
            catch (SQLException ex) {
                Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ArrayList<>(etiquetasID);
    }


    /*public List<Etiqueta> getArticlesTags(long id){
        List<Etiqueta> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            String query = "select * from etiqueta_ where ARTICULO=?";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //where...
            prepareStatement.setLong(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            lista = getTagList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }*/
}
