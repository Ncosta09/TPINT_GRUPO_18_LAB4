package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UsuarioDao;
import dominio.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	
	@Override
	public boolean validarCredenciales(String nombreUsuario, String clave) {
		
		String sql = "SELECT 1 FROM Usuarios WHERE nombre_usuario LIKE ? AND pass_usuario LIKE ?";
		
		try (Connection conn = Conexion.getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, nombreUsuario);
			ps.setString(2, clave);
			
			try (ResultSet rs = ps.executeQuery()) {
	            return rs.next();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}	
}