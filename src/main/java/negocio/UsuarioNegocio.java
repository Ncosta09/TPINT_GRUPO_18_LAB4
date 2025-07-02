package negocio;

import dominio.Usuario;
import dominio.exceptions.UsuarioExistenteException;

public interface UsuarioNegocio {
	boolean validar(String nombreUsuario, String clave);
	Usuario obtenerUsuario(String nombreUsuario, String clave);
	boolean agregarUsuario(Usuario usuario) throws UsuarioExistenteException;
	
	Usuario obtenerPorId(int idUsuario);
    boolean actualizarUsername(int idUsuario, String nuevoUsername);
    boolean actualizarContrasena(int idUsuario, String nuevaPass);
}
