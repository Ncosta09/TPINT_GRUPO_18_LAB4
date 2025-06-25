package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CuentaDao;
import dominio.Cuenta;

public class CuentaDaoImpl implements CuentaDao {

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
	    String sql = "UPDATE cuentas SET saldo = ? WHERE id = ?";
	    try (Connection conn = Conexion.getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {
		
	    	ps.setDouble(0, cuenta.getSaldo());
	    	ps.setInt(1, cuenta.getId());

	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	

}
