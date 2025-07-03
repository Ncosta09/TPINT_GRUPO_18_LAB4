package daoImpl;

import dominio.Cliente;
import dominio.TipoCuenta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import dominio.Cuenta;

public class CuentaDaoImpl implements CuentaDao {

    @Override
    public List<Cuenta> obtenerTodasLasCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            cn = Conexion.getConexion().getSQLConexion();
            st = cn.prepareStatement("SELECT c.id_cuenta, c.numero_cuenta, c.cbu, c.saldo, c.estado, tc.tipo_id, tc.descripcion as tipo_cuenta_desc, cl.nombre, cl.apellido, cl.id_cliente FROM Cuentas c INNER JOIN Tipos_cuenta tc ON c.tipo_cuenta = tc.tipo_id INNER JOIN Clientes cl ON c.id_cliente = cl.id_cliente ORDER BY cl.apellido, cl.nombre");

            rs = st.executeQuery();
            
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id_cuenta"));
                cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
                cuenta.setCbu(rs.getString("cbu"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuenta.setEstado(rs.getBoolean("estado"));
                
                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setTipoId(rs.getInt("tipo_id"));
                tipoCuenta.setDescripcion(rs.getString("tipo_cuenta_desc"));
                cuenta.setTipoCuenta(tipoCuenta);
                
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cuenta.setCliente(cliente);
                
                cuentas.add(cuenta);
            }
            
            System.out.println("Cuentas obtenidas: " + cuentas.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, st, cn);
        }

        return cuentas;
    }

    @Override
    public Cuenta obtenerPorId(int idCuenta) {
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            cn = Conexion.getConexion().getSQLConexion();
            st = cn.prepareStatement("SELECT c.id_cuenta, c.numero_cuenta, c.cbu, c.saldo, c.estado, tc.tipo_id, tc.descripcion as tipo_cuenta_desc, cl.nombre, cl.apellido, cl.id_cliente FROM Cuentas c INNER JOIN Tipos_cuenta tc ON c.tipo_cuenta = tc.tipo_id INNER JOIN Clientes cl ON c.id_cliente = cl.id_cliente WHERE c.id_cuenta = ?");
            st.setInt(1, idCuenta);
            
            rs = st.executeQuery();
            if (rs.next()) {
                Cuenta c = new Cuenta();
                c.setId(rs.getInt("id_cuenta"));
                c.setNumeroCuenta(rs.getString("numero_cuenta"));
                c.setCbu(rs.getString("cbu"));
                c.setSaldo(rs.getDouble("saldo"));
                c.setEstado(rs.getBoolean("estado"));
                
                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setTipoId(rs.getInt("tipo_id"));
                tipoCuenta.setDescripcion(rs.getString("tipo_cuenta_desc"));
                c.setTipoCuenta(tipoCuenta);
                
                dominio.Cliente cli = new dominio.Cliente();
                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                c.setCliente(cli);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, st, cn);
        }
        return null;
    }

    @Override
    public boolean modificarCuenta(Cuenta cuenta) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            ps = conn.prepareStatement("UPDATE Cuentas SET saldo = ?, estado = ? WHERE id_cuenta = ?");

            ps.setDouble(1, cuenta.getSaldo());
            ps.setBoolean(2, cuenta.isEstado());
            ps.setInt(3, cuenta.getId());
            
            int filas = ps.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(ps, conn);
        }
        return false;
    }

    @Override
    public int crearCuentaYRetornarId(Cuenta cuenta) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet keys = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String numeroCuentaGenerado = generarNumeroCuenta();
            String cbuGenerado = generarCBU();

            String insertCuenta = "INSERT INTO Cuentas (numero_cuenta, cbu, tipo_cuenta, fecha_creacion, saldo, id_cliente) VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(insertCuenta, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, numeroCuentaGenerado);
            ps.setString(2, cbuGenerado);
            ps.setInt(3, cuenta.getTipoCuenta().getTipoId());
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            ps.setDouble(5, 10000.00);
            ps.setInt(6, cuenta.getCliente().getIdCliente());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int idCuenta = keys.getInt(1);
                    return idCuenta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(keys, ps, conn);
        }
        return -1;
    }

    @Override
    public boolean crearCuenta(Cuenta cuenta) {
        int resultado = crearCuentaYRetornarId(cuenta);
        return resultado > 0;
    }

    private String generarNumeroCuenta() {
        return "CA-" + System.currentTimeMillis();
    }

    private String generarCBU() {
        StringBuilder cbu = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            cbu.append((int)(Math.random() * 10));
        }
        return cbu.toString();
    }

    @Override
    public boolean bajaCuenta(int idCuenta) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "UPDATE Cuentas SET estado = 0 WHERE id_cuenta = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCuenta);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(ps, conn);
        }
        return false;
    }
    
    @Override
    public boolean reactivarCuenta(int idCuenta) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "UPDATE Cuentas SET estado = 1 WHERE id_cuenta = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCuenta);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(ps, conn);
        }
        return false;
    }

    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, nombre, apellido, dni FROM Clientes WHERE id_cliente > 0";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDni(rs.getString("dni"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }
        return lista;
    }

    @Override
    public List<Cuenta> obtenerCuentasPorCliente(int idCliente) {
        List<Cuenta> cuentas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.cbu, c.saldo, c.estado, tc.tipo_id, tc.descripcion as tipo_cuenta_desc, cl.id_cliente, cl.nombre, cl.apellido FROM Cuentas c INNER JOIN Tipos_cuenta tc ON c.tipo_cuenta = tc.tipo_id INNER JOIN Clientes cl ON c.id_cliente = cl.id_cliente WHERE c.id_cliente = ? AND c.estado = 1 ORDER BY c.numero_cuenta";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id_cuenta"));
                cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
                cuenta.setCbu(rs.getString("cbu"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuenta.setEstado(rs.getBoolean("estado"));
                
                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setTipoId(rs.getInt("tipo_id"));
                tipoCuenta.setDescripcion(rs.getString("tipo_cuenta_desc"));
                cuenta.setTipoCuenta(tipoCuenta);
                
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cuenta.setCliente(cliente);
                
                cuentas.add(cuenta);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, ps, conn);
        }
        
        return cuentas;
    }
    
    @Override
    public boolean actualizarSaldo(int idCuenta, double nuevoSaldo) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "UPDATE Cuentas SET saldo = ? WHERE id_cuenta = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, idCuenta);
            
            int result = ps.executeUpdate();
            
            if (result > 0) {
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Utils.closeResources(ps, conn);
        }
    }
    


    public boolean existeTipoCuentaCliente(int idCliente, int tipoCuentaId) {
       Connection conn = null;
       PreparedStatement psCheck = null;
       ResultSet rs = null;
       
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sqlCheck = "SELECT COUNT(*) FROM Cuentas WHERE id_cliente = ? AND estado = 1 AND tipo_cuenta = ?";
            psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setInt(1, idCliente);
            psCheck.setInt(2, tipoCuentaId);
            rs = psCheck.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.closeResources(rs, psCheck, conn);
        }
        return false;
    }
}