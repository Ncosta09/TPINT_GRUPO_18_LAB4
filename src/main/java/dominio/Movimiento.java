package dominio;

import java.util.Date;

public class Movimiento {

	private int idMovimiento;
    private int idCuenta;
    private int idTipoMovimiento;
    private Date fecha;
    private double importe;
    private double saldo;
    private String tipoMovimientoDescripcion;
    
    public Movimiento() {}
    
    public int getIdMovimiento() {
        return idMovimiento;
    }
    
    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }
    
    public int getIdCuenta() {
        return idCuenta;
    }
    
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
    
    public int getIdTipoMovimiento() {
        return idTipoMovimiento;
    }
    
    public void setIdTipoMovimiento(int idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public double getImporte() {
        return importe;
    }
    
    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public String getTipoMovimientoDescripcion() {
        return tipoMovimientoDescripcion;
    }
    
    public void setTipoMovimientoDescripcion(String tipoMovimientoDescripcion) {
        this.tipoMovimientoDescripcion = tipoMovimientoDescripcion;
    }
	
}
