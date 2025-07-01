package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UsuarioDao;
import dominio.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	
	@Override
    public Usuario obtenerPorId(int idUsuario) {
		Connection conn;
		
        try {	
        	conn = Conexion.getConexion().getSQLConexion();
        	PreparedStatement ps = conn.prepareStatement("SELECT id_usuario, username, password, estado, tipo_usuario FROM Usuarios WHERE id_usuario = ?");
        	
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("username"));
                u.setPassUsuario(rs.getString("password"));
                u.setEstado(rs.getInt("estado"));
                u.setTipoUsuario(rs.getInt("tipo_usuario"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
    @Override
    public boolean updateUsername(int idUsuario, String nuevoUsername) {
    	Connection conn;
    	
        try {	
        	conn = Conexion.getConexion().getSQLConexion();
        	PreparedStatement ps = conn.prepareStatement("UPDATE Usuarios SET nombre_usuario = ? WHERE id_usuario = ?");        	
        	
            ps.setString(1, nuevoUsername);
            ps.setInt(2, idUsuario);
            int rows = ps.executeUpdate();
            conn.commit();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePassword(int idUsuario, String nuevaPass) {
    	Connection conn;
    	
        try {
        	conn = Conexion.getConexion().getSQLConexion();
        	PreparedStatement ps = conn.prepareStatement("UPDATE Usuarios SET pass_usuario = ? WHERE id_usuario = ?");     
        	
            ps.setString(1, nuevaPass);
            ps.setInt(2, idUsuario);
            int rows = ps.executeUpdate();
            conn.commit();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	@Override
	public boolean validarCredenciales(String nombreUsuario, String clave) {
        Connection conn;
				
		try{
			
			conn = Conexion.getConexion().getSQLConexion();
        	PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM Usuarios WHERE nombre_usuario LIKE ? AND pass_usuario LIKE ?");

			
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
	
	public Usuario obtenerUsuario(String nombreUsuario, String clave) {
        Connection conn;

        try {
        	
        	conn = Conexion.getConexion().getSQLConexion();
        	PreparedStatement ps = conn.prepareStatement("SELECT id_usuario, nombre_usuario, pass_usuario, tipo_usuario, estado FROM Usuarios WHERE nombre_usuario LIKE ? AND pass_usuario LIKE ?");

            ps.setString(1, nombreUsuario);
            ps.setString(2, clave);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setNombreUsuario (rs.getString ("nombre_usuario"));
                    u.setPassUsuario (rs.getString ("pass_usuario"));
                    u.setTipoUsuario (rs.getInt ("tipo_usuario"));
                    u.setEstado (rs.getInt ("estado"));
                    
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public boolean insert(Usuario usuario) {
		Connection conn;
	    PreparedStatement ps;
	    
	    String query = "INSERT INTO Usuarios (nombre_usuario, pass_usuario, tipo_usuario) VALUES (?, ?, ?)";

	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        ps = conn.prepareStatement(query);
	        
	        ps.setString(1, usuario.getNombreUsuario());
	        ps.setString(2, usuario.getPassUsuario());
	        ps.setInt(3, usuario.getTipoUsuario());
	        

	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            conn.commit(); 
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
}