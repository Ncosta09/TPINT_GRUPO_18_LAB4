package dao;

import java.util.List;

import dominio.Cuota;

public interface CuotaDao {
	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);
    boolean generarCuotasParaPrestamo(int idPrestamo, int cantidadCuotas, double montoCuota);
}
