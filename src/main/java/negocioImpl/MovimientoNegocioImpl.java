package negocioImpl;

import java.util.List;

import dao.MovimientoDao;
import daoImpl.MovimientoDaoImpl;
import dominio.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio{

	private MovimientoDao movimientoDao = new MovimientoDaoImpl();

    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta) {
        return movimientoDao.obtenerMovimientosPorCuenta(idCuenta);
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuentaConFiltros(int idCuenta, String tipoMovimiento, 
                                                                 String fechaDesde, String fechaHasta) {
        return movimientoDao.obtenerMovimientosPorCuentaConFiltros(idCuenta, tipoMovimiento, fechaDesde, fechaHasta);
    }
    
    @Override
    public boolean insertarMovimiento(Movimiento movimiento) {
        return movimientoDao.insertarMovimiento(movimiento);
    }
	
}
