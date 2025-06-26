package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import dao.SexoDao;
import dominio.Sexo;


public class SexoDaoImpl implements SexoDao{

	public List<Sexo> obtenerTodosSexo() {
	    List<Sexo> sexo = new ArrayList<>();
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        PreparedStatement st = cn.prepareStatement("SELECT id_sexo, descripcion FROM Sexo");

	        ResultSet rs = st.executeQuery();
	        
	            while (rs.next()) {
	                
	            	Sexo s = new Sexo();
	                
	            	s.setIdSexo(rs.getInt("id_sexo"));
	            	s.setDescripcion(rs.getString("descripcion"));
	                
	                sexo.add(s);
	            }
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return sexo;
	}
}
