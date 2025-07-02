package daoImpl;

import dao.TransferenciaDao;
import dominio.Transferencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferenciaDaoImpl implements TransferenciaDao {
    
    @Override
    public boolean realizarTransferencia(Transferencia transferencia) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "INSERT INTO Transferencias (id_cuenta_origen, id_cuenta_destino, importe, detalle) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, transferencia.getIdCuentaOrigen());
            ps.setInt(2, transferencia.getIdCuentaDestino());
            ps.setDouble(3, transferencia.getImporte());
            ps.setString(4, transferencia.getDetalle());

            int result = ps.executeUpdate();
            
            if (result > 0) {
                conn.commit(); 
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public int contarTransferenciasRealizadasPorClienteEnMes(int idCliente, int mes, int anio) {
        int cantidad = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "SELECT COUNT(*) FROM Transferencias t " +
                         "INNER JOIN Cuentas c ON t.id_cuenta_origen = c.id_cuenta " +
                         "WHERE c.id_cliente = ? AND MONTH(t.fecha) = ? AND YEAR(t.fecha) = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.setInt(2, mes);
            ps.setInt(3, anio);
            rs = ps.executeQuery();
            if (rs.next()) {
                cantidad = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidad;
    }
} 