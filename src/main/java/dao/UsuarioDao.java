package dao;

import dominio.Usuario;

public interface UsuarioDao {
	boolean validarCredenciales(String nombreUsuario, String clave);
	Usuario obtenerUsuario(String nombreUsuario, String clave);
	boolean insert(Usuario usuario);
	boolean existeUsuarioPorNombre(String nombreUsuario);
	
	Usuario obtenerPorId(int idUsuario);
    boolean updateUsername(int idUsuario, String nuevoUsername);
    boolean updatePassword(int idUsuario, String nuevaPass);
	
}
