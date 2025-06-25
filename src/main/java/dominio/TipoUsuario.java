package dominio;

public class TipoUsuario {
    private int tipoId;
    private String descripcionTipo;

    public TipoUsuario() { }

    public TipoUsuario(int tipoId, String descripcionTipo) {
        this.tipoId = tipoId;
        this.descripcionTipo = descripcionTipo;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" +
               "tipoId=" + tipoId +
               ", descripcionTipo='" + descripcionTipo + '\'' +
               '}';
    }
}