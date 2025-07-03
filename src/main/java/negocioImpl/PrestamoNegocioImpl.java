package negocioImpl;

import dominio.Prestamo;
import negocio.PrestamoNegocio;

import java.util.List;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import negocioImpl.CuotaNegocioImpl;

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
	public List<Prestamo> listarPrestamosAprobados() {
		return dao.listarPrestamosAprobados();
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
	
	@Override
	public boolean aprobarPrestamo(int idPrestamo) {
		// Actualizar estado del préstamo a "aprobado"
		boolean estadoActualizado = dao.actualizarEstado(idPrestamo, "aprobado");
		
		if (estadoActualizado) {
			// Obtener el préstamo para generar las cuotas
			Prestamo prestamo = dao.obtenerPrestamoPorId(idPrestamo);
			if (prestamo != null) {
				// Generar las cuotas para el préstamo
				CuotaNegocioImpl cuotaNegocio = new CuotaNegocioImpl();
				return cuotaNegocio.generarCuotasParaPrestamo(
					prestamo.getIdPrestamo(), 
					prestamo.getCantidadCuotas(), 
					prestamo.getImporteCuota()
				);
			}
		}
		
		return false;
	}
	
	@Override
	public int obtenerCuotasPagadas(int idPrestamo) {
		CuotaNegocioImpl cuotaNegocio = new CuotaNegocioImpl();
		return cuotaNegocio.obtenerCuotasPagadasPorPrestamo(idPrestamo);
	}
	
	@Override
	public int obtenerCuotasPendientes(int idPrestamo) {
		CuotaNegocioImpl cuotaNegocio = new CuotaNegocioImpl();
		return cuotaNegocio.obtenerCuotasPendientesPorPrestamo(idPrestamo);
	}
}

