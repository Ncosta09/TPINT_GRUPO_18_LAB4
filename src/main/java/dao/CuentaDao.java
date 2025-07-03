package dao;

import dominio.Cliente;
import dominio.Cuenta;
import java.util.List;

public interface CuentaDao {
    List<Cuenta> obtenerTodasLasCuentas();
    Cuenta obtenerPorId(int idCuenta);
    boolean modificarCuenta(Cuenta cuenta);
    boolean crearCuenta(Cuenta cuenta);
    int crearCuentaYRetornarId(Cuenta cuenta);
    boolean existeTipoCuentaCliente(int idCliente, int tipoCuentaId);
    boolean bajaCuenta(int idCuenta);
    boolean reactivarCuenta(int idCuenta);
    List<Cliente> obtenerTodos();
    List<Cuenta> obtenerCuentasPorCliente(int idCliente);
    boolean actualizarSaldo(int idCuenta, double nuevoSaldo);
}
