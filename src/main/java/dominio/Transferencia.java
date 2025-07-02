package dominio;

import java.util.Date;

public class Transferencia {
    private int idTransferencia;
    private int idCuentaOrigen;
    private int idCuentaDestino;
    private double importe;
    private Date fecha;
    private String detalle;

    // Constructores
    public Transferencia() {
    }
    
    public Transferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }
    
    public Transferencia(int idCuentaOrigen, int idCuentaDestino, double importe, String detalle) {
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDestino = idCuentaDestino;
        this.importe = importe;
        this.detalle = detalle;
        this.fecha = new Date();
    }

    // Getters y Setters
    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public int getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public void setIdCuentaOrigen(int idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
