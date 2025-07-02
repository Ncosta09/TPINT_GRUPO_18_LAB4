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
        try (Connection conn = Conexion.obtenerConexionDirecta()) {
            String sql = "SELECT tipo_id, descripcion FROM Tipos_cuenta ORDER BY descripcion";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                TipoCuenta tipo = new TipoCuenta();
                tipo.setTipoId(rs.getInt("tipo_id"));
                tipo.setDescripcion(rs.getString("descripcion"));
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
    }

    @Override
    public TipoCuenta obtenerPorId(int tipoId) {
        try (Connection conn = Conexion.obtenerConexionDirecta()) {
            String sql = "SELECT tipo_id, descripcion FROM Tipos_cuenta WHERE tipo_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tipoId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                TipoCuenta tipo = new TipoCuenta();
                tipo.setTipoId(rs.getInt("tipo_id"));
                tipo.setDescripcion(rs.getString("descripcion"));
                return tipo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TipoCuenta obtenerPorDescripcion(String descripcion) {
        try (Connection conn = Conexion.obtenerConexionDirecta()) {
            String sql = "SELECT tipo_id, descripcion FROM Tipos_cuenta WHERE descripcion = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, descripcion);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                TipoCuenta tipo = new TipoCuenta();
                tipo.setTipoId(rs.getInt("tipo_id"));
                tipo.setDescripcion(rs.getString("descripcion"));
                return tipo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} 