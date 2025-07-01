package dominio;

import java.util.Date;

public class Prestamo {
	
	    private int idPrestamo;
	    private int idCliente;
	    private int idCuenta;
	    private Date fechaAlta;
	    private double importePedido;
	    private int plazoMeses;
	    private int cantidadCuotas;
	    private double importeCuota;
	    private String estado;
		
		
	    
	    public int getIdPrestamo() {
			return idPrestamo;
		}
		public void setIdPrestamo(int idPrestamo) {
			this.idPrestamo = idPrestamo;
		}
		public int getIdCliente() {
			return idCliente;
		}
		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}
		public int getIdCuenta() {
			return idCuenta;
		}
		public void setIdCuenta(int idCuenta) {
			this.idCuenta = idCuenta;
		}
		public Date getFechaAlta() {
			return fechaAlta;
		}
		public void setFechaAlta(Date fechaAlta) {
			this.fechaAlta = fechaAlta;
		}
		public double getImportePedido() {
			return importePedido;
		}
		public void setImportePedido(double importePedido) {
			this.importePedido = importePedido;
		}
		public int getPlazoMeses() {
			return plazoMeses;
		}
		public void setPlazoMeses(int plazoMeses) {
			this.plazoMeses = plazoMeses;
		}
		public int getCantidadCuotas() {
			return cantidadCuotas;
		}
		public void setCantidadCuotas(int cantidadCuotas) {
			this.cantidadCuotas = cantidadCuotas;
		}
		public double getImporteCuota() {
			return importeCuota;
		}
		public void setImporteCuota(double importeCuota) {
			this.importeCuota = importeCuota;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}

	  
	

}
