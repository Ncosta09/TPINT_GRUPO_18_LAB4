package dao;

import java.util.List;
import dominio.Cliente;

public interface ClienteDao {

	List<Cliente> obtenerTodos();
	boolean darDeBaja(int idUsuario);
	boolean altaCliente(Cliente cliente);	
	Cliente obtenerPorId(int idCliente);
	Cliente obtenerPorIdUsuario(int idUsuario);
	boolean modificarCliente(Cliente c);
	int contarClientes(boolean dateFilter);

}
