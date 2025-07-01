package negocio;

import dominio.Usuario;

public interface UsuarioNegocio {
	boolean validar(String nombreUsuario, String clave);
	Usuario obtenerUsuario(String nombreUsuario, String clave);
	boolean agregarUsuario(Usuario usuario);
	
	Usuario obtenerPorId(int idUsuario);
    boolean actualizarUsername(int idUsuario, String nuevoUsername);
    boolean actualizarContrasena(int idUsuario, String nuevaPass);
}
