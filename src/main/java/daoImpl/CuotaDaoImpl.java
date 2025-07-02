package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CuotaDao;
import dominio.Cuota;

public class CuotaDaoImpl implements CuotaDao {

    @Override
    public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
        List<Cuota> cuotas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM Cuotas WHERE id_prestamo = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPrestamo);

            rs = ps.executeQuery();

            while (rs.next()) {
                Cuota c = new Cuota();
                c.setIdCuota(rs.getInt("id_cuota"));
                c.setIdPrestamo(rs.getInt("id_prestamo"));
                c.setNumeroCuota(rs.getInt("numero_cuota"));
                c.setMonto(rs.getDouble("monto"));
                c.setFechaPago(rs.getDate("fecha_pago"));
                c.setPagada(rs.getBoolean("pagada"));
                cuotas.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }

        return cuotas;
    }

    
    
    public boolean generarCuotasParaPrestamo(int idPrestamo, int cantidadCuotas, double montoCuota) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "INSERT INTO Cuotas (id_prestamo, numero_cuota, monto, pagada) VALUES (?, ?, ?, 0)";
            ps = conn.prepareStatement(query);

            for (int i = 1; i <= cantidadCuotas; i++) {
                ps.setInt(1, idPrestamo);
                ps.setInt(2, i);
                ps.setDouble(3, montoCuota);
                ps.addBatch();//Lote de inserts.
            }

            ps.executeBatch();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(ps, conn);
        }

        return false;
    
    }
    
    
    
    
    
    
}