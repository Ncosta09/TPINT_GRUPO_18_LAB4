package negocioImpl;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.exceptions.TipoCuentaExistenteException;
import java.util.Date;
import java.util.List;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {
   private CuentaDao dao = new CuentaDaoImpl();
   private MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();

   public CuentaNegocioImpl() {
   }

   public int crearCuenta(Cuenta cuenta) throws TipoCuentaExistenteException {
      try {
          if (dao.existeTipoCuentaCliente(cuenta.getCliente().getIdCliente(), cuenta.getTipoCuenta().getTipoId())) {
              throw new TipoCuentaExistenteException(cuenta.getTipoCuenta().getDescripcion());
          }
          
          int idCuentaCreada = dao.crearCuentaYRetornarId(cuenta);
          
          if (idCuentaCreada > 0) {
              Movimiento movimientoInicial = new Movimiento();
              movimientoInicial.setIdCuenta(idCuentaCreada);
              movimientoInicial.setIdTipoMovimiento(1); // ID 1 = Depósito inicial
              movimientoInicial.setFecha(new Date());
              movimientoInicial.setImporte(10000.00);
              movimientoInicial.setSaldo(10000.00);
              
              try {
                  movimientoNegocio.insertarMovimiento(movimientoInicial);
              } catch (Exception e) {
                  System.err.println("Error al crear movimiento inicial: " + e.getMessage());
              }
              
              return idCuentaCreada;
          } else {
              throw new RuntimeException("No se pudo crear la cuenta en la base de datos.");
          }
      } catch (TipoCuentaExistenteException e) {
          throw e;
      } catch (Exception e) {
          throw new RuntimeException("Error del sistema: " + e.getMessage(), e);
      }
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
