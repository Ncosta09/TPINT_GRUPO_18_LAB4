package dao;

public interface UsuarioDao {
	boolean validarCredenciales(String nombreUsuario, String clave);

}
