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
	public boolean modificarCuenta(Cuenta cuenta) {
		Connection conn;
	    
	    try {
	    	
	    	conn = Conexion.getConexion().getSQLConexion();
        	PreparedStatement ps = conn.prepareStatement("UPDATE cuentas SET saldo = ? WHERE id = ?");
		
	    	ps.setDouble(0, cuenta.getSaldo());
	    	ps.setInt(1, cuenta.getId());

	        return ps.executeUpdate() > 0;
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

	        // Generar numeroCuenta y cbu
	        String numeroCuentaGenerado = generarNumeroCuenta();
	        String cbuGenerado = generarCBU();

	        String insertCuenta = "INSERT INTO Cuentas (numero_cuenta, cbu, tipo_cuenta, fecha_creacion, saldo, estado, id_cliente) VALUES (?, ?, ?, ?, ?, 1, ?)";
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
	    // 22 digitos numericos aleatorios
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
            conn.commit();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, nombre, apellido, dni FROM Clientes WHERE id_cliente > 0";

        try (Connection conn = Conexion.obtenerConexionDirecta();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDni(rs.getString("dni"));
                lista.add(c);
            }
            
            System.out.println("Clientes obtenidos: " + lista.size()); // <- esto sirve para confirmar que pasa por el servlet.
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
        String sql = "INSERT INTO Movimientos (id_cuenta, id_tipo_movimiento, fecha, importe, detalle, saldo) VALUES (?, 1, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idCuenta);
        ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        ps.setDouble(3, importe);
        ps.setString(4, "Apertura de cuenta");
        ps.setDouble(5, importe);
        ps.executeUpdate();
    }
    
    
    
    
    
	
	
	

}
