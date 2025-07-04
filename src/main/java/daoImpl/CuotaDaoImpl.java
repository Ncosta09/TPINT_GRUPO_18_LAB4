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
                c.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
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
            String query = "INSERT INTO Cuotas (id_prestamo, numero_cuota, monto, fecha_vencimiento, pagada) VALUES (?, ?, ?, ?, 0)";
            ps = conn.prepareStatement(query);

            java.util.Calendar cal = java.util.Calendar.getInstance();
            
            for (int i = 1; i <= cantidadCuotas; i++) {
                cal.add(java.util.Calendar.MONTH, 1);
                java.sql.Date fechaVencimiento = new java.sql.Date(cal.getTimeInMillis());
                
                ps.setInt(1, idPrestamo);
                ps.setInt(2, i);
                ps.setDouble(3, montoCuota);
                ps.setDate(4, fechaVencimiento);
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
    
    @Override
    public boolean pagarCuota(int idCuota) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "UPDATE Cuotas SET pagada = 1, fecha_pago = NOW() WHERE id_cuota = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idCuota);
            
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
    public Cuota obtenerCuotaPorId(int idCuota) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM Cuotas WHERE id_cuota = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idCuota);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                Cuota c = new Cuota();
                c.setIdCuota(rs.getInt("id_cuota"));
                c.setIdPrestamo(rs.getInt("id_prestamo"));
                c.setNumeroCuota(rs.getInt("numero_cuota"));
                c.setMonto(rs.getDouble("monto"));
                c.setFechaPago(rs.getDate("fecha_pago"));
                c.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                c.setPagada(rs.getBoolean("pagada"));
                return c;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }
        
        return null;
    }
    
    @Override
    public int obtenerCuotasPagadasPorPrestamo(int idPrestamo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT COUNT(*) as cuotas_pagadas FROM Cuotas WHERE id_prestamo = ? AND pagada = 1";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPrestamo);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("cuotas_pagadas");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }
        
        return 0;
    }
    
    @Override
    public int obtenerCuotasPendientesPorPrestamo(int idPrestamo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT COUNT(*) as cuotas_pendientes FROM Cuotas WHERE id_prestamo = ? AND pagada = 0";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPrestamo);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("cuotas_pendientes");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }
        
        return 0;
    }
}