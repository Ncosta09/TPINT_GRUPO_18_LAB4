package dao;

import java.util.List;
import dominio.Cliente;

public interface ClienteDao {

	boolean altaCliente(Cliente cliente);
	List<Cliente> obtenerTodos();	
}
