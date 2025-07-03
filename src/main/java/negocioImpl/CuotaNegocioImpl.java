package negocioImpl;

import java.util.List;


import daoImpl.CuotaDaoImpl;
import dominio.Cuota;
import negocio.CuotaNegocio;

public class CuotaNegocioImpl implements CuotaNegocio {
	CuotaDaoImpl dao=new CuotaDaoImpl();
	@Override
	public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
		
		return dao.obtenerCuotasPorPrestamo(idPrestamo);
	}

	@Override
	public boolean generarCuotasParaPrestamo(int idPrestamo, int cantidadCuotas, double montoCuota) {
		
		return dao.generarCuotasParaPrestamo(idPrestamo, cantidadCuotas, montoCuota);
	}
	
	@Override
	public boolean pagarCuota(int idCuota) {
		return dao.pagarCuota(idCuota);
	}

	@Override
	public Cuota obtenerCuotaPorId(int idCuota) {
		return dao.obtenerCuotaPorId(idCuota);
	}
	
	@Override
	public int obtenerCuotasPagadasPorPrestamo(int idPrestamo) {
		return dao.obtenerCuotasPagadasPorPrestamo(idPrestamo);
	}
	
	@Override
	public int obtenerCuotasPendientesPorPrestamo(int idPrestamo) {
		return dao.obtenerCuotasPendientesPorPrestamo(idPrestamo);
	}

}
