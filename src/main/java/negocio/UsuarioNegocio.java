package negocio;

import dominio.Usuario;

public interface UsuarioNegocio {
	boolean validar(String nombreUsuario, String clave);
	Usuario obtenerUsuario(String nombreUsuario, String clave);
}
