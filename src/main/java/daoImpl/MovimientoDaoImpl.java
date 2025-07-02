package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientoDao;
import dominio.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao{

	@Override
    public List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta) {
        List<Movimiento> movimientos = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "SELECT m.id_movimiento, m.id_cuenta, m.id_tipo_movimiento, m.fecha, m.importe, m.saldo, tm.nombre as tipo_descripcion FROM Movimientos m INNER JOIN Tipo_movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento WHERE m.id_cuenta = ? ORDER BY m.fecha DESC";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCuenta);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
                movimiento.setIdCuenta(rs.getInt("id_cuenta"));
                movimiento.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
                movimiento.setFecha(rs.getTimestamp("fecha"));
                movimiento.setImporte(rs.getDouble("importe"));
                movimiento.setSaldo(rs.getDouble("saldo"));
                movimiento.setTipoMovimientoDescripcion(rs.getString("tipo_descripcion"));
                
                movimientos.add(movimiento);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return movimientos;
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuentaConFiltros(int idCuenta, String tipoMovimiento, 
                                                                 String fechaDesde, String fechaHasta) {
        List<Movimiento> movimientos = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT m.id_movimiento, m.id_cuenta, m.id_tipo_movimiento, m.fecha, m.importe, m.saldo, tm.nombre as tipo_descripcion FROM Movimientos m INNER JOIN Tipo_movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento WHERE m.id_cuenta = ? ");
            
            if (tipoMovimiento != null && !tipoMovimiento.trim().isEmpty()) {
                sql.append("AND tm.nombre LIKE ? ");
            }
            if (fechaDesde != null && !fechaDesde.trim().isEmpty()) {
                sql.append("AND DATE(m.fecha) >= ? ");
            }
            if (fechaHasta != null && !fechaHasta.trim().isEmpty()) {
                sql.append("AND DATE(m.fecha) <= ? ");
            }
            
            sql.append("ORDER BY m.fecha DESC");
            
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            int paramIndex = 1;
            ps.setInt(paramIndex++, idCuenta);
            
            if (tipoMovimiento != null && !tipoMovimiento.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + tipoMovimiento + "%");
            }
            if (fechaDesde != null && !fechaDesde.trim().isEmpty()) {
                ps.setString(paramIndex++, fechaDesde);
            }
            if (fechaHasta != null && !fechaHasta.trim().isEmpty()) {
                ps.setString(paramIndex++, fechaHasta);
            }
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
                movimiento.setIdCuenta(rs.getInt("id_cuenta"));
                movimiento.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
                movimiento.setFecha(rs.getTimestamp("fecha"));
                movimiento.setImporte(rs.getDouble("importe"));
                movimiento.setSaldo(rs.getDouble("saldo"));
                movimiento.setTipoMovimientoDescripcion(rs.getString("tipo_descripcion"));
                
                movimientos.add(movimiento);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return movimientos;
    }
    
    @Override
    public boolean insertarMovimiento(Movimiento movimiento) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "INSERT INTO Movimientos (id_cuenta, id_tipo_movimiento, importe, saldo) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, movimiento.getIdCuenta());
            ps.setInt(2, movimiento.getIdTipoMovimiento());
            ps.setDouble(3, movimiento.getImporte());
            ps.setDouble(4, movimiento.getSaldo());
            
            int result = ps.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
}
