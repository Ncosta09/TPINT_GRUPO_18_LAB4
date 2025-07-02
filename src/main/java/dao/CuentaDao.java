package dao;

import dominio.Cliente;
import dominio.Cuenta;
import java.util.List;

public interface CuentaDao {
   boolean modificarCuenta(Cuenta var1);

   boolean crearCuenta(Cuenta var1);

   boolean bajaCuenta(int var1);

   List<Cliente> obtenerTodos();
   
   List<Cuenta> obtenerTodasLasCuentas();
   
   Cuenta obtenerPorId(int idCuenta);

   List<Cuenta> obtenerCuentasPorCliente(int idCliente);  

   boolean actualizarSaldo(int idCuenta, double nuevoSaldo);
}
