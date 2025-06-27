package dao;

import java.util.List;
import dominio.Cliente;

public interface ClienteDao {

	List<Cliente> obtenerTodos();
	boolean darDeBaja(int idUsuario);
<<<<<<< HEAD
	boolean altaCliente(Cliente cliente);	
	Cliente obtenerPorId(int idCliente);
	boolean modificarCliente(Cliente c);

=======
	boolean altaCliente(Cliente cliente);
>>>>>>> e65367dc35d83015c6bc750c0ecad6c38b47ac86
}
