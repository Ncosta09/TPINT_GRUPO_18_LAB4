package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProvinciaDao;
import dao.SexoDao;
import dominio.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao{
	public List<Provincia> obtenerTodosProvincia() {
	    List<Provincia> provincia = new ArrayList<>();
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        PreparedStatement st = cn.prepareStatement("SELECT id_provincia, nombre FROM Provincia");

	        ResultSet rs = st.executeQuery();
	        
	            while (rs.next()) {
	                
	            	Provincia p = new Provincia();
	                
	            	p.setIdProvincia(rs.getInt("id_provincia"));
	            	p.setNombre(rs.getString("nombre"));
	                
	            	provincia.add(p);
	            }
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return provincia;
	}

}
