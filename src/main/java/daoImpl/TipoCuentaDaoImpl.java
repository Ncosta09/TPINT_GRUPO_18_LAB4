package daoImpl;

import dao.TipoCuentaDao;
import dominio.TipoCuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoCuentaDaoImpl implements TipoCuentaDao {

    @Override
    public List<TipoCuenta> obtenerTodos() {
        List<TipoCuenta> tipos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "SELECT tipo_id, descripcion FROM Tipos_cuenta ORDER BY descripcion";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                TipoCuenta tipo = new TipoCuenta();
                tipo.setTipoId(rs.getInt("tipo_id"));
                tipo.setDescripcion(rs.getString("descripcion"));
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }
        return tipos;
    }

    @Override
    public TipoCuenta obtenerPorId(int tipoId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "SELECT tipo_id, descripcion FROM Tipos_cuenta WHERE tipo_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tipoId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                TipoCuenta tipo = new TipoCuenta();
                tipo.setTipoId(rs.getInt("tipo_id"));
                tipo.setDescripcion(rs.getString("descripcion"));
                return tipo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }
        return null;
    }

    @Override
    public TipoCuenta obtenerPorDescripcion(String descripcion) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "SELECT tipo_id, descripcion FROM Tipos_cuenta WHERE descripcion = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, descripcion);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                TipoCuenta tipo = new TipoCuenta();
                tipo.setTipoId(rs.getInt("tipo_id"));
                tipo.setDescripcion(rs.getString("descripcion"));
                return tipo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }
        return null;
    }
} 