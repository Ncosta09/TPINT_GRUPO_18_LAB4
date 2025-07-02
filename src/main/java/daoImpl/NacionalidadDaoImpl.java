package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.NacionalidadDao;
import dao.SexoDao;
import dominio.Nacionalidad;

public class NacionalidadDaoImpl implements NacionalidadDao{

	public List<Nacionalidad> obtenerTodosNacionalidad() {
	    List<Nacionalidad> nacionalidad = new ArrayList<>();
	    Connection cn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        st = cn.prepareStatement("SELECT id_nacionalidad, nombre FROM Nacionalidad");

	        rs = st.executeQuery();
	        
	            while (rs.next()) {
	                
	            	Nacionalidad n = new Nacionalidad();
	                
	            	n.setIdNacionalidad(rs.getInt("id_nacionalidad"));
	            	n.setNombre(rs.getString("nombre"));
	                
	            	nacionalidad.add(n);
	            }
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(rs, st, cn);
        }
	    return nacionalidad;
	}
}
