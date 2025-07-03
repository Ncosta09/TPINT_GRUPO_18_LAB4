package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ReporteDao;
import dominio.Reporte;

public class ReporteDaoImpl implements ReporteDao{
	
	 @Override
	    public BigDecimal totalIngresos(int idCliente, Date desde, Date hasta) {
		 	Connection conn = null;
	        PreparedStatement ps = null;

	        BigDecimal total = BigDecimal.ZERO;

	        try {
	            conn = Conexion.getConexion().getSQLConexion();
		        String sql =
			            "SELECT ("
			          + "  SELECT COALESCE(SUM(t.importe),0) FROM Transferencias t "
			          + "   WHERE t.id_cuenta_destino IN (SELECT id_cuenta FROM Cuentas WHERE id_cliente = ?) "
			          + "     AND t.fecha BETWEEN ? AND ?"
			          + ") + ("
			          + "  SELECT COALESCE(SUM(p.importe_pedido),0) FROM Prestamos p "
			          + "   WHERE p.id_cliente = ? AND p.estado = 'aprobado' "
			          + "     AND p.fecha_alta BETWEEN ? AND ?"
			          + ") AS total";
		        
	            ps = conn.prepareStatement(sql);
	        	
	            ps.setInt(1, idCliente);
	            ps.setDate(2, new java.sql.Date(desde.getTime()));
	            ps.setDate(3, new java.sql.Date(hasta.getTime()));
	            ps.setInt(4, idCliente);
	            ps.setDate(5, new java.sql.Date(desde.getTime()));
	            ps.setDate(6, new java.sql.Date(hasta.getTime()));
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    total = rs.getBigDecimal("total");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return total;
	    }
	 
	 @Override
	    public BigDecimal totalEgresos(int idCliente, Date desde, Date hasta) {
		 	Connection conn = null;
	        PreparedStatement ps = null;
	        
	        BigDecimal total = BigDecimal.ZERO;

	        try{
	            conn = Conexion.getConexion().getSQLConexion();
		        String sql =
			            "SELECT ("
			          + "  SELECT COALESCE(SUM(q.monto),0) FROM Cuotas q "
			          + "   WHERE q.id_prestamo IN (SELECT id_prestamo FROM Prestamos WHERE id_cliente = ? AND estado = 'aprobado') "
			          + "     AND q.fecha_pago BETWEEN ? AND ?"
			          + ") + ("
			          + "  SELECT COALESCE(SUM(t.importe),0) FROM Transferencias t "
			          + "   WHERE t.id_cuenta_origen IN (SELECT id_cuenta FROM Cuentas WHERE id_cliente = ?) "
			          + "     AND t.fecha BETWEEN ? AND ?"
			          + ") AS total";
		        
	            ps = conn.prepareStatement(sql);
	        	
	            ps.setInt(1, idCliente);
	            ps.setDate(2, new java.sql.Date(desde.getTime()));
	            ps.setDate(3, new java.sql.Date(hasta.getTime()));
	            ps.setInt(4, idCliente);
	            ps.setDate(5, new java.sql.Date(desde.getTime()));
	            ps.setDate(6, new java.sql.Date(hasta.getTime()));
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    total = rs.getBigDecimal("total");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return total;
	    }
	 
	 @Override
	    public List<Reporte> detalleMovimientos(int idCliente, Date desde, Date hasta) {
		 	Connection conn = null;
	        PreparedStatement ps = null;
	        List<Reporte> list = new ArrayList<>();
	        
	        try {
	            conn = Conexion.getConexion().getSQLConexion();
	            String sql =
	    	            "SELECT fecha, tipo, categoria, descripcion, monto FROM ("
	    	          + "  SELECT t.fecha AS fecha, 'Transferencia entrante' AS tipo, 'Transferencia' AS categoria, "
	    	          + "         t.detalle AS descripcion, t.importe AS monto "
	    	          + "    FROM Transferencias t "
	    	          + "   WHERE t.id_cuenta_destino IN (SELECT id_cuenta FROM Cuentas WHERE id_cliente = ?) "
	    	          + "     AND t.fecha BETWEEN ? AND ? "
	    	          + "  UNION ALL "
	    	          + "  SELECT p.fecha_alta AS fecha, 'Préstamo aprobado' AS tipo, 'Préstamo' AS categoria, '' AS descripcion, p.importe_pedido AS monto "
	    	          + "    FROM Prestamos p "
	    	          + "   WHERE p.id_cliente = ? AND p.estado = 'aprobado' "
	    	          + "     AND p.fecha_alta BETWEEN ? AND ? "
	    	          + "  UNION ALL "
	    	          + "  SELECT q.fecha_pago AS fecha, 'Pago de cuota' AS tipo, 'Cuota' AS categoria, "
	    	          + "         CONCAT('Cuota ', q.numero_cuota) AS descripcion, q.monto AS monto "
	    	          + "    FROM Cuotas q "
	    	          + "   WHERE q.id_prestamo IN (SELECT id_prestamo FROM Prestamos WHERE id_cliente = ? AND estado = 'aprobado') "
	    	          + "     AND q.fecha_pago BETWEEN ? AND ? "
	    	          + "  UNION ALL "
	    	          + "  SELECT t2.fecha AS fecha, 'Transferencia saliente' AS tipo, 'Transferencia' AS categoria, t2.detalle AS descripcion, t2.importe AS monto "
	    	          + "    FROM Transferencias t2 "
	    	          + "   WHERE t2.id_cuenta_origen IN (SELECT id_cuenta FROM Cuentas WHERE id_cliente = ?) "
	    	          + "     AND t2.fecha BETWEEN ? AND ? "
	    	          + ") AS sub ORDER BY fecha";
		        
	            ps = conn.prepareStatement(sql);
	        	
	            int i = 1;
	            ps.setInt(i++, idCliente);
	            ps.setDate(i++, new java.sql.Date(desde.getTime()));
	            ps.setDate(i++, new java.sql.Date(hasta.getTime()));
	            ps.setInt(i++, idCliente);
	            ps.setDate(i++, new java.sql.Date(desde.getTime()));
	            ps.setDate(i++, new java.sql.Date(hasta.getTime()));
	            ps.setInt(i++, idCliente);
	            ps.setDate(i++, new java.sql.Date(desde.getTime()));
	            ps.setDate(i++, new java.sql.Date(hasta.getTime()));
	            ps.setInt(i++, idCliente);
	            ps.setDate(i++, new java.sql.Date(desde.getTime()));
	            ps.setDate(i++, new java.sql.Date(hasta.getTime()));

	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Reporte item = new Reporte(
	                        rs.getDate("fecha"),
	                        rs.getString("tipo"),
	                        rs.getString("categoria"),
	                        rs.getString("descripcion"),
	                        rs.getBigDecimal("monto")
	                    );
	                    list.add(item);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
}
