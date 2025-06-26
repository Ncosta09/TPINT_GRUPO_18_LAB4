package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LocalidadDao;
import dao.SexoDao;
import dominio.Localidad;

public class LocalidadDaoImpl implements LocalidadDao{
	public List<Localidad> obtenerTodosLocalidad() {
	    List<Localidad> localidad = new ArrayList<>();
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        PreparedStatement st = cn.prepareStatement("SELECT id_localidad, nombre, id_provincia FROM Localidad");

	        ResultSet rs = st.executeQuery();
	        
	            while (rs.next()) {
	                
	            	Localidad l = new Localidad();
	                
	            	l.setIdLocalidad(rs.getInt("id_localidad"));
	            	l.setNombre(rs.getString("nombre"));
	            	l.setIdProvincia(rs.getInt("id_provincia"));
	                
	            	localidad.add(l);
	            }
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return localidad;
	}

}
