package negocio;

import java.util.List;

import dominio.Cuota;

public interface CuotaNegocio {
	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);
    boolean generarCuotasParaPrestamo(int idPrestamo, int cantidadCuotas, double montoCuota);
    boolean pagarCuota(int idCuota);
    Cuota obtenerCuotaPorId(int idCuota);
    int obtenerCuotasPagadasPorPrestamo(int idPrestamo);
    int obtenerCuotasPendientesPorPrestamo(int idPrestamo);
}
