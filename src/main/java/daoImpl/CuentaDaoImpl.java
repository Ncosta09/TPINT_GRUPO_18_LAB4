package daoImpl;

import dominio.Cliente;
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
        try {
            cn = Conexion.getConexion().getSQLConexion();
            PreparedStatement st = cn.prepareStatement("SELECT c.id_cuenta, c.numero_cuenta, c.cbu, c.saldo, c.estado, tc.descripcion as tipo_cuenta_desc, cl.nombre, cl.apellido, cl.id_cliente FROM Cuentas c INNER JOIN Tipos_cuenta tc ON c.tipo_cuenta = tc.tipo_id INNER JOIN Clientes cl ON c.id_cliente = cl.id_cliente ORDER BY cl.apellido, cl.nombre");

            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id_cuenta"));
                cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
                cuenta.setCbu(rs.getString("cbu"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuenta.setEstado(rs.getBoolean("estado"));
                cuenta.setTipoCuenta(rs.getString("tipo_cuenta_desc"));
                
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
        }

        return cuentas;
    }

    @Override
    public Cuenta obtenerPorId(int idCuenta) {
        Connection cn = null;
        try {
            cn = Conexion.getConexion().getSQLConexion();
            PreparedStatement st = cn.prepareStatement("SELECT c.id_cuenta, c.numero_cuenta, c.cbu, c.saldo, c.estado, tc.descripcion as tipo_cuenta_desc, cl.nombre, cl.apellido, cl.id_cliente FROM Cuentas c INNER JOIN Tipos_cuenta tc ON c.tipo_cuenta = tc.tipo_id INNER JOIN Clientes cl ON c.id_cliente = cl.id_cliente WHERE c.id_cuenta = ?");
            st.setInt(1, idCuenta);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Cuenta c = new Cuenta();
                    c.setId(rs.getInt("id_cuenta"));
                    c.setNumeroCuenta(rs.getString("numero_cuenta"));
                    c.setCbu(rs.getString("cbu"));
                    c.setSaldo(rs.getDouble("saldo"));
                    c.setEstado(rs.getBoolean("estado"));
                    c.setTipoCuenta(rs.getString("tipo_cuenta_desc"));
                    
                    dominio.Cliente cli = new dominio.Cliente();
                    cli.setIdCliente(rs.getInt("id_cliente"));
                    cli.setNombre(rs.getString("nombre"));
                    cli.setApellido(rs.getString("apellido"));
                    c.setCliente(cli);
                    return c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean modificarCuenta(Cuenta cuenta) {
        Connection conn;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            PreparedStatement ps = conn.prepareStatement("UPDATE Cuentas SET saldo = ?, estado = ? WHERE id_cuenta = ?");
            conn.setAutoCommit(false);

            ps.setDouble(1, cuenta.getSaldo());
            ps.setBoolean(2, cuenta.isEstado());
            ps.setInt(3, cuenta.getId());
            
            int filas = ps.executeUpdate();
            conn.commit();
            return filas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean crearCuenta(Cuenta cuenta) {
        try (Connection conn = Conexion.obtenerConexionDirecta()) {
            String sqlCheck = "SELECT COUNT(*) FROM Cuentas WHERE id_cliente = ? AND estado = 1";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setInt(1, cuenta.getCliente().getIdCliente());
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) >= 3) return false;

            String numeroCuentaGenerado = generarNumeroCuenta();
            String cbuGenerado = generarCBU();

            String insertCuenta = "INSERT INTO Cuentas (numero_cuenta, cbu, tipo_cuenta, fecha_creacion, saldo, id_cliente) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertCuenta, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, numeroCuentaGenerado);
            ps.setString(2, cbuGenerado);
            ps.setInt(3, obtenerIdTipoCuenta(conn, cuenta.getTipoCuenta()));
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            ps.setDouble(5, 10000.00);
            ps.setInt(6, cuenta.getCliente().getIdCliente());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int idCuenta = keys.getInt(1);
                    agregarMovimientoInicial(conn, idCuenta, 10000.00);
                }
                conn.commit();
                return true;
            }else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        try (Connection conn = Conexion.obtenerConexionDirecta()) {
            String sql = "UPDATE Cuentas SET estado = 0 WHERE id_cuenta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCuenta);
            int rows = ps.executeUpdate();
            conn.commit();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, nombre, apellido, dni FROM Clientes WHERE id_cliente > 0";
        Connection conn = null;
        try {
        	 conn = Conexion.obtenerConexionDirecta();
        	 conn.setAutoCommit(true);
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDni(rs.getString("dni"));
                lista.add(c);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private int obtenerIdTipoCuenta(Connection conn, String descripcion) throws SQLException {
        String query = "SELECT tipo_id FROM Tipos_cuenta WHERE descripcion = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, descripcion);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt(1);
        else throw new SQLException("Tipo de cuenta no encontrado");
    }

    private void agregarMovimientoInicial(Connection conn, int idCuenta, double importe) throws SQLException {
        String sql = "INSERT INTO Movimientos (id_cuenta, id_tipo_movimiento, importe, saldo) VALUES (?, 1, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idCuenta);
        ps.setDouble(2, importe);
        ps.setDouble(3, importe);
        ps.executeUpdate();
    }
    
    @Override
    public List<Cuenta> obtenerCuentasPorCliente(int idCliente) {
        List<Cuenta> cuentas = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.cbu, c.saldo, c.estado, tc.descripcion as tipo_cuenta_desc, cl.id_cliente, cl.nombre, cl.apellido FROM Cuentas c INNER JOIN Tipos_cuenta tc ON c.tipo_cuenta = tc.tipo_id INNER JOIN Clientes cl ON c.id_cliente = cl.id_cliente WHERE c.id_cliente = ? AND c.estado = 1 ORDER BY c.numero_cuenta";

            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id_cuenta"));
                cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
                cuenta.setCbu(rs.getString("cbu"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuenta.setEstado(rs.getBoolean("estado"));
                cuenta.setTipoCuenta(rs.getString("tipo_cuenta_desc"));
                
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cuenta.setCliente(cliente);
                
                cuentas.add(cuenta);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
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
                conn.commit(); // Confirmar la transacción
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}