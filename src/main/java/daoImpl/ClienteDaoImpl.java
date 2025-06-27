package daoImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import dominio.Cliente;
import dominio.Usuario;
import dao.ClienteDao;

public class ClienteDaoImpl implements ClienteDao {

  

	@Override
	public List<Cliente> obtenerTodos() {
	    List<Cliente> clientes = new ArrayList<>();
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        PreparedStatement st = cn.prepareStatement("SELECT c.DNI, c.nombre, c.apellido, c.email, c.telefono, u.estado, u.tipo_usuario, t.descripcion_tipo FROM Clientes c JOIN Usuarios u ON c.id_usuario = u.id_usuario JOIN Tipos_usuario t ON u.tipo_usuario = t.tipo_id WHERE u.estado = 1");

	        ResultSet rs = st.executeQuery();
	        
	            while (rs.next()) {
	                
	            	Cliente cliente = new Cliente();
	                Usuario usuario = new Usuario();
	                
	                cliente.setDni(rs.getString("DNI"));
	                cliente.setNombre(rs.getString("nombre"));
	                cliente.setApellido(rs.getString("apellido"));
	                cliente.setEmail(rs.getString("email"));
	                cliente.setTelefono(rs.getString("telefono"));
	                usuario.setEstado(rs.getInt("estado"));
	                usuario.setTipoUsuario(rs.getInt("tipo_usuario"));
	                cliente.setUsuario(usuario);
	                
	                clientes.add(cliente);
	            }
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return clientes;
	}
	
	public boolean darDeBaja(int idUsuario) {
	    Connection conn = null;
	    try {
	        conn = Conexion.obtenerConexionDirecta();
	        String sql = "UPDATE Usuarios SET estado = 0 WHERE id_usuario = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, idUsuario);
	        int rows = ps.executeUpdate();
	        conn.commit();
	        return rows > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        try { if (conn != null) conn.rollback(); } catch (Exception ex) {}
	    }
	    return false;
	}
}
