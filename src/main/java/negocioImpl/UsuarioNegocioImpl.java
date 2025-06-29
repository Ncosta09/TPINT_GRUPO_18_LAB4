package negocioImpl;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import dominio.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{
	
	private UsuarioDao UsuarioDao = new UsuarioDaoImpl();

	@Override
	public boolean validar(String nombreUsuario, String clave) {
		return UsuarioDao.validarCredenciales(nombreUsuario, clave);
	}
	
	@Override
    public Usuario obtenerUsuario(String nombreUsuario, String clave) {
        return UsuarioDao.obtenerUsuario(nombreUsuario, clave);
    }

	@Override
	public boolean agregarUsuario(Usuario usuario) {
		return UsuarioDao.insert(usuario);
	}
}
