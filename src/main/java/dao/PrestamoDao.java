package dao;

import java.util.List;

import dominio.Prestamo;

public interface PrestamoDao {
	boolean insertarPrestamo(Prestamo prestamo);
    List<Prestamo> listarPrestamosPendientes();
    boolean actualizarEstado(int idPrestamo, String nuevoEstado);
    List<Prestamo> listarPrestamosPorCliente(int idCliente);
	List<Prestamo> listarTodos();
	public Prestamo obtenerPrestamoPorId(int idPrestamo);
}
