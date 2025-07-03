package negocio;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.exceptions.TipoCuentaExistenteException;
import java.util.List;

public interface CuentaNegocio {
   boolean modificarCuenta(Cuenta var1);

   int crearCuenta(Cuenta var1) throws TipoCuentaExistenteException;

   boolean bajaCuenta(int var1);

   boolean reactivarCuenta(int idCuenta);
   
   List<Cliente> obtenerTodos();
   
   List<Cuenta> obtenerTodasLasCuentas();
   
   Cuenta obtenerPorId(int idCuenta);
   
   List<Cuenta> obtenerCuentasPorCliente(int idCliente);
   
   boolean actualizarSaldo(int idCuenta, double nuevoSaldo);
}
