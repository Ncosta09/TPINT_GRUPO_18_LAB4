package dao;

import java.util.List;
import dominio.Cliente;

public interface ClienteDao {

<<<<<<< HEAD
	List<Cliente> obtenerTodos();
	boolean darDeBaja(int idUsuario);
	
=======
	boolean altaCliente(Cliente cliente);
	List<Cliente> obtenerTodos();	
>>>>>>> 75bcb2ae5945837531de9bfe2fcd64d2c42584af
}
