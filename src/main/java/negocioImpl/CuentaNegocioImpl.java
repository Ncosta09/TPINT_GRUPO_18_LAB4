package negocioImpl;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import dominio.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {
	

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
	    CuentaDao dao = new CuentaDaoImpl();
	    return dao.modificarCuenta(cuenta);
	}

	
}
