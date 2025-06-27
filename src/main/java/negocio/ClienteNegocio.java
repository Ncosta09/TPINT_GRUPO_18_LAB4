package negocio;

import java.util.List;
import dominio.Cliente;

public interface ClienteNegocio {
	List<Cliente> obtenerTodos();
	boolean darDeBaja(int idUsuario);
}
