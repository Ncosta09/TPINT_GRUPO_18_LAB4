package negocioImpl;

import dominio.Prestamo;
import negocio.PrestamoNegocio;

import java.util.List;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;

public class PrestamoNegocioImpl implements PrestamoNegocio {
    private PrestamoDao dao = new PrestamoDaoImpl();

   
	@Override
	public boolean pedirPrestamo(Prestamo p) {
		return dao.insertarPrestamo(p);
	}
	
	
	@Override
	
	public List<Prestamo> listarPrestamosPorCliente(int idCliente){
		return dao.listarPrestamosPorCliente(idCliente);
	}


	@Override
	public List<Prestamo> listarPrestamosPendientes() {
		return dao.listarPrestamosPendientes();
		
	}


	@Override
	public boolean actualizarEstado(int idPrestamo, String nuevoEstado) {
		return dao.actualizarEstado(idPrestamo, nuevoEstado);
	}


	@Override
	public List<Prestamo> listarTodos() {
		
		return dao.listarTodos();
	}


	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) {
		return dao.obtenerPrestamoPorId(idPrestamo);
	}
	
	
	
	
}

