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
	
	

}
