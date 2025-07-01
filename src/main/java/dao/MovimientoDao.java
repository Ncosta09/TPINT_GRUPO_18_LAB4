package dao;

import java.util.List;
import dominio.Movimiento;

public interface MovimientoDao {
	List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta);
    List<Movimiento> obtenerMovimientosPorCuentaConFiltros(int idCuenta, String tipoMovimiento, String fechaDesde, String fechaHasta);
}
