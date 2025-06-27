package dominio;

public class Cuenta {
    private int idCuenta;
    private Cliente cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private String cbu;
    private double saldo;
    private boolean estado; // Para baja lógica
	


    // Getters y Setters
    


	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public int getId() {
		return idCuenta;
	}
	public void setId(int id) {
		this.idCuenta = id;
	}

	public String getCbu() {
		return cbu;
	}
	
	public void setCbu(String cbu) {
	    this.cbu = cbu;
	}
    
}
