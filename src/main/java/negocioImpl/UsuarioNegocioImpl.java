package negocioImpl;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{
	
	private UsuarioDao UsuarioDao = new UsuarioDaoImpl();

	@Override
	public boolean validar(String nombreUsuario, String clave) {
		return UsuarioDao.validarCredenciales(nombreUsuario, clave);
	}
}
