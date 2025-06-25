package dao;

import dominio.Usuario;

public interface UsuarioDao {
	boolean validarCredenciales(String nombreUsuario, String clave);
	Usuario obtenerUsuario(String nombreUsuario, String clave);
}
