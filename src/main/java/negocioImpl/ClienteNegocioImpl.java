package negocioImpl;

import java.util.List;
import dominio.Cliente;
import negocio.ClienteNegocio;
import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;

public class ClienteNegocioImpl implements ClienteNegocio {

    private ClienteDao clienteDao = new ClienteDaoImpl();

	@Override
	public boolean altaCliente(Cliente cliente) {
		return clienteDao.altaCliente(cliente);
	}
    
    @Override
    public List<Cliente> obtenerTodos() {
        return clienteDao.obtenerTodos();
    }
    @Override
    public boolean darDeBaja(int idUsuario) {
        return clienteDao.darDeBaja(idUsuario);
    }
    
    @Override
    public Cliente obtenerPorId(int idCliente) {
        return clienteDao.obtenerPorId(idCliente);
    }
    
    @Override
    public boolean modificarCliente(Cliente c) {
        return clienteDao.modificarCliente(c);
    }

	@Override
	public int contarClientes(boolean dateFilter) {
		return clienteDao.contarClientes(dateFilter);
	}

	@Override
	public Cliente obtenerClientePorUsuario(int idUsuario) {
	    return clienteDao.obtenerClientePorUsuario(idUsuario);
	}

}

