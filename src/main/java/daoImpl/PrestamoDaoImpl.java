package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import dao.PrestamoDao;
import dominio.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao {

    @Override
    public boolean insertarPrestamo(Prestamo p) {
        Connection conn = null;
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "INSERT INTO Prestamos (id_cliente, id_cuenta, fecha_alta, importe_pedido, plazo_meses, cantidad_cuotas, importe_cuota) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, p.getIdCliente());
            ps.setInt(2, p.getIdCuenta());
            ps.setDate(3, new java.sql.Date(p.getFechaAlta().getTime()));
            ps.setDouble(4, p.getImportePedido());
            ps.setInt(5, p.getPlazoMeses());
            ps.setInt(6, p.getCantidadCuotas());
            ps.setDouble(7, p.getImporteCuota());

            int rows = ps.executeUpdate();
            conn.commit();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public List<Prestamo> listarPrestamosPorCliente(int idCliente) {
        List<Prestamo> prestamos = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM Prestamos WHERE id_cliente = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();

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
            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prestamos;
    }
    
    
    

	@Override
	public List<Prestamo> listarPrestamosPendientes() {
	    List<Prestamo> prestamos = new ArrayList<>();
	    Connection conn = null;

	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT * FROM Prestamos WHERE estado = 'pendiente'";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

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

	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return prestamos;
	}
	
	
	
	
	
	
	@Override
	public List<Prestamo> listarTodos() {
	    List<Prestamo> prestamos = new ArrayList<>();
	    Connection conn = null;

	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT * FROM Prestamos";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

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

	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return prestamos;
	}
	
	
	
	
	
	
	

	@Override
	public boolean actualizarEstado(int idPrestamo, String nuevoEstado) {
	    Connection conn = null;

	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        String query = "UPDATE Prestamos SET estado = ? WHERE id_prestamo = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, nuevoEstado);
	        ps.setInt(2, idPrestamo);

	        int filas = ps.executeUpdate();
	        conn.commit();
	        ps.close();

	        return filas > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return false;
	}

}