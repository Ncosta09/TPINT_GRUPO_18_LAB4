package dominio;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String passUsuario;
    private int tipoUsuario;
    private int estado;

    public Usuario() { }

    public Usuario(int idUsuario, String nombreUsuario, String passUsuario, int tipoUsuario, int estado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.passUsuario = passUsuario;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "idUsuario=" + idUsuario +
               ", nombreUsuario='" + nombreUsuario + '\'' +
               ", tipoUsuario=" + tipoUsuario +
               ", estado=" + estado +
               '}';
    }
}
