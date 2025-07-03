package dominio;

import java.math.BigDecimal;
import java.sql.Date;

public class Reporte {
    private Date fecha;
    private String tipo;
    private String categoria;
    private String descripcion;
    private BigDecimal monto;

    public Reporte() {}
    
    public Reporte(Date fecha, String tipo, String categoria, String descripcion, BigDecimal monto) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.monto = monto;
    }

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
}
