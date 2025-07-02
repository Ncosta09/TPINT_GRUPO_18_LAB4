package negocioImpl;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import dominio.Cliente;
import dominio.Cuenta;
import java.util.List;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {
   private CuentaDao dao = new CuentaDaoImpl();

   public CuentaNegocioImpl() {
   }

   public boolean crearCuenta(Cuenta cuenta) {
      return this.dao.crearCuenta(cuenta);
   }

   public boolean bajaCuenta(int idCuenta) {
      return this.dao.bajaCuenta(idCuenta);
   }

   public boolean modificarCuenta(Cuenta cuenta) {
      return this.dao.modificarCuenta(cuenta);
   }

   public List<Cliente> obtenerTodos() {
      return this.dao.obtenerTodos();
   }
   
   @Override
   public List<Cuenta> obtenerTodasLasCuentas() {
       return dao.obtenerTodasLasCuentas();
   }
   
   public Cuenta obtenerPorId(int idCuenta) {
		 return dao.obtenerPorId(idCuenta);
	 }
   
   @Override
   public List<Cuenta> obtenerCuentasPorCliente(int idCliente) {
       return dao.obtenerCuentasPorCliente(idCliente);
   }
   
   @Override
   public boolean actualizarSaldo(int idCuenta, double nuevoSaldo) {
       return dao.actualizarSaldo(idCuenta, nuevoSaldo);
   }
   
}
