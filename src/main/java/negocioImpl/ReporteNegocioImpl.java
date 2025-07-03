package negocioImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import dominio.Reporte;
import negocio.ReporteNegocio;
import dao.ReporteDao;
import daoImpl.ReporteDaoImpl;

public class ReporteNegocioImpl implements ReporteNegocio{
    private ReporteDao reporteDAO = new ReporteDaoImpl();

    @Override
    public BigDecimal totalIngresos(int idCliente, Date desde, Date hasta) {
        return reporteDAO.totalIngresos(idCliente, desde, hasta);
    }

    @Override
    public BigDecimal totalEgresos(int idCliente, Date desde, Date hasta) {
        return reporteDAO.totalEgresos(idCliente, desde, hasta);
    }

    @Override
    public List<Reporte> detalleMovimientos(int idCliente, Date desde, Date hasta) {
        return reporteDAO.detalleMovimientos(idCliente, desde, hasta);
    }
}
