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
		Connection conn = null;
		PreparedStatement ps = null;

        try  {
        	conn = Conexion.getConexion().getSQLConexion();
        	ps = conn.prepareStatement("INSERT INTO Clientes (id_usuario, DNI, CUIL, nombre, apellido, id_sexo, id_nacionalidad, fecha_nacimiento, direccion, id_localidad, email, telefono) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
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
                resultado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(ps, conn);
        }

        return resultado;
	}


	@Override
	public List<Cliente> obtenerTodos() {
	    List<Cliente> clientes = new ArrayList<>();
	    Connection cn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        st = cn.prepareStatement("SELECT c.id_cliente, c.DNI, c.nombre, c.apellido, c.email, c.telefono, u.estado, u.tipo_usuario, t.descripcion_tipo, u.id_usuario FROM Clientes c JOIN Usuarios u ON c.id_usuario = u.id_usuario JOIN Tipos_usuario t ON u.tipo_usuario = t.tipo_id");

	        rs = st.executeQuery();
	        
	            while (rs.next()) {
	                
	            	Cliente cliente = new Cliente();
	                Usuario usuario = new Usuario();
	                
	                cliente.setIdCliente(rs.getInt("id_cliente"));
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
	    } finally {
            Utils.closeResources(rs, st, cn);
        }
	    return clientes;
	}
	
	public boolean darDeBaja(int idUsuario) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    
	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String sql = "UPDATE Usuarios SET estado = 0 WHERE id_usuario = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, idUsuario);
	        int rows = ps.executeUpdate();
	        return rows > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(ps, conn);
        }
	    return false;
	}
	
	@Override
    public boolean reactivarCliente(int idUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "UPDATE Usuarios SET estado = 1 WHERE id_usuario = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(ps, conn);
        }
        return false;
    }
	
	@Override
	public Cliente obtenerPorId(int idCliente) {
	    Cliente cliente = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT c.id_cliente, c.id_usuario, c.DNI, c.CUIL, c.nombre, c.apellido, c.id_sexo, c.id_nacionalidad, c.fecha_nacimiento, c.direccion, c.id_localidad, c.email, c.telefono FROM Clientes c WHERE c.id_cliente = ?";
	        
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, idCliente);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	        	
	        	Cliente c = new Cliente();
                
	        	c.setIdCliente(rs.getInt("id_cliente"));
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setDni(rs.getString("DNI"));
                c.setCuil(rs.getString("CUIL"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setIdSexo(rs.getInt("id_sexo"));
                c.setIdNacionalidad(rs.getInt("id_nacionalidad"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setDireccion(rs.getString("direccion"));
                c.setIdLocalidad(rs.getInt("id_localidad"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                
                return c;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(rs, ps, conn);
        }
	    return null;
	}
	
	@Override
	public Cliente obtenerPorIdUsuario(int idUsuario) {
	    Cliente cliente = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT c.id_cliente, c.id_usuario, c.DNI, c.CUIL, c.nombre, c.apellido, c.id_sexo, c.id_nacionalidad, c.fecha_nacimiento, c.direccion, c.id_localidad, c.email, c.telefono FROM Clientes c WHERE c.id_usuario = ?";
	        
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, idUsuario);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	        	
	        	Cliente c = new Cliente();
                
	        	c.setIdCliente(rs.getInt("id_cliente"));
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setDni(rs.getString("DNI"));
                c.setCuil(rs.getString("CUIL"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setIdSexo(rs.getInt("id_sexo"));
                c.setIdNacionalidad(rs.getInt("id_nacionalidad"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setDireccion(rs.getString("direccion"));
                c.setIdLocalidad(rs.getInt("id_localidad"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                
                return c;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(rs, ps, conn);
        }
	    return null;
	}
	
	@Override
	public boolean modificarCliente(Cliente c) {
	    String sql = "UPDATE Clientes SET DNI = ?, CUIL = ?, nombre = ?, apellido = ?, id_sexo = ?, id_nacionalidad = ?, fecha_nacimiento= ?, direccion = ?, id_localidad = ?, email = ?, telefono = ? WHERE id_cliente = ?";
	    Connection conn = null;
	    PreparedStatement ps = null;
	    
	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        ps = conn.prepareStatement(sql);

	        ps.setString (1, c.getDni());
	        ps.setString (2, c.getCuil());
	        ps.setString (3, c.getNombre());
	        ps.setString (4, c.getApellido());
	        ps.setInt    (5, c.getIdSexo());
	        ps.setInt    (6, c.getIdNacionalidad());
	        ps.setDate   (7, new java.sql.Date(c.getFechaNacimiento().getTime()));
	        ps.setString (8, c.getDireccion());
	        ps.setInt    (9, c.getIdLocalidad());
	        ps.setString(10, c.getEmail());
	        ps.setString(11, c.getTelefono());
	        ps.setInt   (12, c.getIdCliente());
	        
	        int rows = ps.executeUpdate();
	        return rows > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
            Utils.closeResources(ps, conn);
        }
	}


	@Override
	public int contarClientes(boolean dateFilter) {
		 int total = 0;
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String filtroMes = "";
		 
		 if(dateFilter) {
			 filtroMes= " WHERE MONTH(fecha_creacion) = MONTH(CURRENT_DATE()) AND YEAR(fecha_creacion) = YEAR(CURRENT_DATE())";
		 }
		 
		    try {
		    	conn = Conexion.getConexion().getSQLConexion();
		        ps = conn.prepareStatement("SELECT COUNT(*) AS 'total' FROM Clientes"+filtroMes);
		        rs = ps.executeQuery();
		        if (rs.next()) {
		            total = rs.getInt("total");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
                Utils.closeResources(rs, ps, conn);
            }
		    return total;
	}
	
	@Override
	public Cliente obtenerClientePorUsuario(int idUsuario) {
	    Cliente cliente = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try  {
	    	conn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT c.id_cliente, c.id_usuario, c.DNI, c.CUIL, c.nombre, c.apellido, c.id_sexo, c.id_nacionalidad, c.fecha_nacimiento, c.direccion, c.id_localidad, c.email, c.telefono FROM Clientes c WHERE c.id_usuario = ?";
	        
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, idUsuario);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("id_cliente"));
	            cliente.setIdUsuario(rs.getInt("id_usuario"));
	            cliente.setDni(rs.getString("DNI"));
	            cliente.setCuil(rs.getString("CUIL"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            cliente.setIdSexo(rs.getInt("id_sexo"));
	            cliente.setIdNacionalidad(rs.getInt("id_nacionalidad"));
	            cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
	            cliente.setDireccion(rs.getString("direccion"));
	            cliente.setIdLocalidad(rs.getInt("id_localidad"));
	            cliente.setEmail(rs.getString("email"));
	            cliente.setTelefono(rs.getString("telefono"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(rs, ps, conn);
        }
	    return cliente;
	}
	
}
