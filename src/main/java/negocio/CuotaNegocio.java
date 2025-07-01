package negocio;

import java.util.List;

import dominio.Cuota;

public interface CuotaNegocio {
	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);
    boolean generarCuotasParaPrestamo(int idPrestamo, int cantidadCuotas, double montoCuota);
}
