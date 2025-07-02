package dominio;

public class TipoCuenta {
    private int tipoId;
    private String descripcion;
    
    public TipoCuenta() {}
    
    public TipoCuenta(int tipoId, String descripcion) {
        this.tipoId = tipoId;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    public int getTipoId() {
        return tipoId;
    }
    
    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TipoCuenta that = (TipoCuenta) obj;
        return tipoId == that.tipoId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(tipoId);
    }
} 