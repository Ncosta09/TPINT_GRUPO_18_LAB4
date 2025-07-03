package negocio;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import dominio.Reporte;

public interface ReporteNegocio {
    BigDecimal totalIngresos(int idCliente, Date desde, Date hasta);
    BigDecimal totalEgresos(int idCliente, Date desde, Date hasta);
    List<Reporte> detalleMovimientos(int idCliente, Date desde, Date hasta);
}
