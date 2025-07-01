package negocio;

import java.util.List;

import dominio.Movimiento;

public interface MovimientoNegocio {
	List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta);
    List<Movimiento> obtenerMovimientosPorCuentaConFiltros(int idCuenta, String tipoMovimiento, String fechaDesde, String fechaHasta);
}
