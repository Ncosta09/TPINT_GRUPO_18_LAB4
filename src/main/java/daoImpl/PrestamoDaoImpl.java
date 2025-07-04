package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import dao.PrestamoDao;
import dominio.Cliente;
import dominio.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao {

    @Override
    public boolean insertarPrestamo(Prestamo p) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "INSERT INTO Prestamos (id_cliente, id_cuenta, fecha_alta, importe_pedido, plazo_meses, cantidad_cuotas, importe_cuota) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);

            ps.setInt(1, p.getIdCliente());
            ps.setInt(2, p.getIdCuenta());
            ps.setDate(3, new java.sql.Date(p.getFechaAlta().getTime()));
            ps.setDouble(4, p.getImportePedido());
            ps.setInt(5, p.getPlazoMeses());
            ps.setInt(6, p.getCantidadCuotas());
            ps.setDouble(7, p.getImporteCuota());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Utils.closeResources(ps, conn);
        }
    }

    @Override
    public List<Prestamo> listarPrestamosPorCliente(int idCliente) {
        List<Prestamo> prestamos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM Prestamos WHERE id_cliente = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idCliente);

            rs = ps.executeQuery();

            while (rs.next()) {
                Prestamo p = new Prestamo();
                p.setIdPrestamo(rs.getInt("id_prestamo"));
                p.setIdCliente(rs.getInt("id_cliente"));
                p.setIdCuenta(rs.getInt("id_cuenta"));
                p.setFechaAlta(rs.getDate("fecha_alta"));
                p.setImportePedido(rs.getDouble("importe_pedido"));
                p.setPlazoMeses(rs.getInt("plazo_meses"));
                p.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
                p.setImporteCuota(rs.getDouble("importe_cuota"));
                p.setEstado(rs.getString("estado"));

                prestamos.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }
        return prestamos;
    }
    
    
    

	@Override
	public List<Prestamo> listarPrestamosPendientes() {
	    List<Prestamo> prestamos = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT * FROM Prestamos WHERE estado = 'pendiente'";
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Prestamo p = new Prestamo();
	            p.setIdPrestamo(rs.getInt("id_prestamo"));
	            p.setIdCliente(rs.getInt("id_cliente"));
	            p.setIdCuenta(rs.getInt("id_cuenta"));
	            p.setFechaAlta(rs.getDate("fecha_alta"));
	            p.setImportePedido(rs.getDouble("importe_pedido"));
	            p.setPlazoMeses(rs.getInt("plazo_meses"));
	            p.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
	            p.setImporteCuota(rs.getDouble("importe_cuota"));
	            p.setEstado(rs.getString("estado"));
	            prestamos.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(rs, ps, conn);
        }

	    return prestamos;
	}
	
	
	
	
	
	
	@Override
	public List<Prestamo> listarTodos() {
	    List<Prestamo> prestamos = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT p.*, c.nombre, c.apellido FROM Prestamos p " +
	                      "INNER JOIN Clientes c ON p.id_cliente = c.id_cliente " +
	                      "ORDER BY p.fecha_alta DESC";
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Prestamo p = new Prestamo();
	            p.setIdPrestamo(rs.getInt("id_prestamo"));
	            p.setIdCliente(rs.getInt("id_cliente"));
	            p.setIdCuenta(rs.getInt("id_cuenta"));
	            p.setFechaAlta(rs.getDate("fecha_alta"));
	            p.setImportePedido(rs.getDouble("importe_pedido"));
	            p.setPlazoMeses(rs.getInt("plazo_meses"));
	            p.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
	            p.setImporteCuota(rs.getDouble("importe_cuota"));
	            p.setEstado(rs.getString("estado"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("id_cliente"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            p.setCliente(cliente);
	            
	            prestamos.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(rs, ps, conn);
        }

	    return prestamos;
	}
	
	
	
	
	
	
	

	@Override
	public boolean actualizarEstado(int idPrestamo, String nuevoEstado) {
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "UPDATE Prestamos SET estado = ? WHERE id_prestamo = ?";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, nuevoEstado);
	        ps.setInt(2, idPrestamo);

	        int filas = ps.executeUpdate();

	        return filas > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(ps, conn);
        }

	    return false;
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) {
		Prestamo prestamo = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT * FROM Prestamos WHERE id_prestamo = ?";
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, idPrestamo);

	        rs = ps.executeQuery();

	        if (rs.next()) {
	            prestamo = new Prestamo();
	            prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
	            prestamo.setIdCliente(rs.getInt("id_cliente"));
	            prestamo.setIdCuenta(rs.getInt("id_cuenta"));
	            prestamo.setFechaAlta(rs.getDate("fecha_alta"));
	            prestamo.setImportePedido(rs.getDouble("importe_pedido"));
	            prestamo.setPlazoMeses(rs.getInt("plazo_meses"));
	            prestamo.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
	            prestamo.setImporteCuota(rs.getDouble("importe_cuota"));
	            prestamo.setEstado(rs.getString("estado"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(rs, ps, conn);
        }

	    return prestamo;
	}
	
	@Override
	public List<Prestamo> listarPrestamosPorEstado(String estado) {
	    List<Prestamo> prestamos = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT p.*, c.nombre, c.apellido FROM Prestamos p " +
	                      "INNER JOIN Clientes c ON p.id_cliente = c.id_cliente " +
	                      "WHERE p.estado = ? " +
	                      "ORDER BY p.fecha_alta DESC";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, estado);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Prestamo p = new Prestamo();
	            p.setIdPrestamo(rs.getInt("id_prestamo"));
	            p.setIdCliente(rs.getInt("id_cliente"));
	            p.setIdCuenta(rs.getInt("id_cuenta"));
	            p.setFechaAlta(rs.getDate("fecha_alta"));
	            p.setImportePedido(rs.getDouble("importe_pedido"));
	            p.setPlazoMeses(rs.getInt("plazo_meses"));
	            p.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
	            p.setImporteCuota(rs.getDouble("importe_cuota"));
	            p.setEstado(rs.getString("estado"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("id_cliente"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            p.setCliente(cliente);
	            
	            prestamos.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
            Utils.closeResources(rs, ps, conn);
        }

	    return prestamos;
	}
	
	@Override
	public List<Prestamo> listarPrestamosAprobados() {
	    return listarPrestamosPorEstado("aprobado");
	}

}