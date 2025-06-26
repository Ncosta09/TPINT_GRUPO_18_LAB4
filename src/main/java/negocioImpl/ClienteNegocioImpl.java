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
	
}

