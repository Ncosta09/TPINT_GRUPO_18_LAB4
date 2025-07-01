package negocio;

import java.util.List;

import dominio.Prestamo;

public interface PrestamoNegocio {
	    boolean pedirPrestamo(Prestamo p);
	    List<Prestamo> listarPrestamosPorCliente(int idCliente);
	    List<Prestamo> listarPrestamosPendientes();
	    boolean actualizarEstado(int idPrestamo, String nuevoEstado);
	    List<Prestamo> listarTodos();
}
