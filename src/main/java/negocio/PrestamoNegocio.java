package negocio;

import java.util.List;

import dominio.Prestamo;

public interface PrestamoNegocio {
	    boolean pedirPrestamo(Prestamo p);
	    List<Prestamo> listarPrestamosPorCliente(int idCliente);
	    List<Prestamo> listarPrestamosPendientes();
	    List<Prestamo> listarPrestamosAprobados();
	    boolean actualizarEstado(int idPrestamo, String nuevoEstado);
	    List<Prestamo> listarTodos();
		public Prestamo obtenerPrestamoPorId(int idPrestamo);
		boolean aprobarPrestamo(int idPrestamo);
		int obtenerCuotasPagadas(int idPrestamo);
		int obtenerCuotasPendientes(int idPrestamo);
}
