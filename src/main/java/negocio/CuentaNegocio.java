package negocio;

import dominio.Cliente;
import dominio.Cuenta;
import java.util.List;

public interface CuentaNegocio {
   boolean modificarCuenta(Cuenta var1);

   boolean crearCuenta(Cuenta var1);

   boolean bajaCuenta(int var1);

   List<Cliente> obtenerTodos();
}
