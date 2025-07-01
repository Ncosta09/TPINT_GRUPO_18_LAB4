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

        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM Cuotas WHERE id_prestamo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idPrestamo);

            ResultSet rs = ps.executeQuery();

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

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cuotas;
    }

    
    
    public boolean generarCuotasParaPrestamo(int idPrestamo, int cantidadCuotas, double montoCuota) {
        Connection conn = null;

        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "INSERT INTO Cuotas (id_prestamo, numero_cuota, monto, pagada) VALUES (?, ?, ?, 0)";
            PreparedStatement ps = conn.prepareStatement(query);

            for (int i = 1; i <= cantidadCuotas; i++) {
                ps.setInt(1, idPrestamo);
                ps.setInt(2, i);
                ps.setDouble(3, montoCuota);
                ps.addBatch();//Lote de inserts.
            }

            ps.executeBatch();
            conn.commit();
            ps.close();
            return true;

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