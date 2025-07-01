package negocioImpl;

import dominio.Prestamo;
import negocio.PrestamoNegocio;
import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;

public class PrestamoNegocioImpl implements PrestamoNegocio {
    private PrestamoDao dao = new PrestamoDaoImpl();

   
	@Override
	public boolean pedirPrestamo(Prestamo p) {
		return dao.insertarPrestamo(p);
	}
}

