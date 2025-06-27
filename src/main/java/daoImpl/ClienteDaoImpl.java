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
	public boolean altaCliente(Cliente cliente) {
		boolean resultado = false;
		Connection conn;

        try  {
        	conn = Conexion.getConexion().getSQLConexion();
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO Clientes (id_usuario, DNI, CUIL, nombre, apellido, id_sexo, id_nacionalidad, fecha_nacimiento, direccion, id_localidad, email, telefono) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");

        	
        	ps.setInt(1,cliente.getIdUsuario());
            ps.setString (2, cliente.getDni());
            ps.setString (3, cliente.getCuil());
            ps.setString (4, cliente.getNombre());
            ps.setString (5, cliente.getApellido());
            ps.setInt (6, cliente.getIdSexo());
            ps.setInt (7, cliente.getIdNacionalidad());
            ps.setDate   (8, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setString (9, cliente.getDireccion());
            ps.setInt (10, cliente.getIdLocalidad());
            ps.setString (11, cliente.getEmail());
            ps.setString (12, cliente.getTelefono());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                conn.commit();
                resultado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
	}


	@Override
	public List<Cliente> obtenerTodos() {
	    List<Cliente> clientes = new ArrayList<>();
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        PreparedStatement st = cn.prepareStatement("SELECT c.DNI, c.nombre, c.apellido, c.email, c.telefono, u.estado, u.tipo_usuario, t.descripcion_tipo, u.id_usuario FROM Clientes c JOIN Usuarios u ON c.id_usuario = u.id_usuario JOIN Tipos_usuario t ON u.tipo_usuario = t.tipo_id");

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
	                usuario.setIdUsuario(rs.getInt("id_usuario"));
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
	
	@Override
	public Cliente obtenerPorId(int idCliente) {
	    Cliente cliente = null;
	    try (Connection conn = Conexion.obtenerConexionDirecta()) {
	        String query = "SELECT * FROM Clientes WHERE id_cliente = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, idCliente);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("id_cliente"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            cliente.setDni(rs.getString("dni"));
	            cliente.setEmail(rs.getString("email"));
	            cliente.setTelefono(rs.getString("telefono"));
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cliente;
	}
	
	@Override
	public boolean modificarCliente(Cliente c) {
	    String sql = "UPDATE Clientes SET dni=?, nombre=?, apellido=?, email=?, telefono=? WHERE id_cliente=?";
	    try (Connection conn = Conexion.obtenerConexionDirecta();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, c.getDni());
	        ps.setString(2, c.getNombre());
	        ps.setString(3, c.getApellido());
	        ps.setString(4, c.getEmail());
	        ps.setString(5, c.getTelefono());
	        ps.setInt(6, c.getIdCliente());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
