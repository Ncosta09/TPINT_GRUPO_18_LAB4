package negocio;

import java.util.List;
import dominio.Cliente;

public interface ClienteNegocio {
	boolean altaCliente(Cliente cliente);
	List<Cliente> obtenerTodos();
	boolean darDeBaja(int idUsuario);
	Cliente obtenerPorId(int idCliente);
	Cliente obtenerPorIdUsuario(int idUsuario);
	boolean modificarCliente(Cliente c);
	int contarClientes(boolean dateFilter);
}
