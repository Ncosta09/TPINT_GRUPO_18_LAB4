package negocioImpl;

import dao.TransferenciaDao;
import daoImpl.TransferenciaDaoImpl;
import negocio.TransferenciaNegocio;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import dominio.Transferencia;
import dominio.Cuenta;
import dominio.Movimiento;
import java.util.List;
import java.util.Date;

public class TransferenciaNegocioImpl implements TransferenciaNegocio {
    
    private TransferenciaDao transferenciaDao = new TransferenciaDaoImpl();
    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
    private MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
    
    @Override
    public boolean realizarTransferencia(Transferencia transferencia) {
        if (!validarTransferencia(transferencia.getIdCuentaOrigen(), transferencia.getImporte())) {
            return false;
        }
        
        Cuenta cuentaDestino = cuentaNegocio.obtenerPorId(transferencia.getIdCuentaDestino());
        if (cuentaDestino == null) {
            return false;
        }
        
        if (transferencia.getIdCuentaOrigen() == transferencia.getIdCuentaDestino()) {
            return false;
        }
        
        if (transferencia.getImporte() <= 0) {
            return false;
        }

        Cuenta cuentaOrigen = cuentaNegocio.obtenerPorId(transferencia.getIdCuentaOrigen());
        if (cuentaOrigen == null) {
            return false;
        }
        
        double nuevoSaldoOrigen = cuentaOrigen.getSaldo() - transferencia.getImporte();
        double nuevoSaldoDestino = cuentaDestino.getSaldo() + transferencia.getImporte();
        
        Movimiento movimientoOrigen = new Movimiento();
        movimientoOrigen.setIdCuenta(transferencia.getIdCuentaOrigen());
        movimientoOrigen.setIdTipoMovimiento(2);
        movimientoOrigen.setImporte(-transferencia.getImporte());
        movimientoOrigen.setSaldo(nuevoSaldoOrigen);
        
        Movimiento movimientoDestino = new Movimiento();
        movimientoDestino.setIdCuenta(transferencia.getIdCuentaDestino());
        movimientoDestino.setIdTipoMovimiento(2);
        movimientoDestino.setImporte(transferencia.getImporte());
        movimientoDestino.setSaldo(nuevoSaldoDestino);
        

        try {
            if (!transferenciaDao.realizarTransferencia(transferencia)) {
                return false;
            }
            
            if (!movimientoNegocio.insertarMovimiento(movimientoOrigen)) {
                return false;
            }
            
            if (!movimientoNegocio.insertarMovimiento(movimientoDestino)) {
                return false;
            }
            
            if (!cuentaNegocio.actualizarSaldo(transferencia.getIdCuentaOrigen(), nuevoSaldoOrigen)) {
                return false;
            }
            
            if (!cuentaNegocio.actualizarSaldo(transferencia.getIdCuentaDestino(), nuevoSaldoDestino)) {
                return false;
            }
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean validarTransferencia(int idCuentaOrigen, double monto) {
        Cuenta cuentaOrigen = cuentaNegocio.obtenerPorId(idCuentaOrigen);
        if (cuentaOrigen == null) {
            return false;
        }
        
        if (!cuentaOrigen.isEstado()) {
            return false;
        }
        
        if (cuentaOrigen.getSaldo() < monto) {
            return false;
        }
        
        if (monto < 1.0) {
            return false;
        }

        return true;
    }
    
    @Override
    public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
        List<Cuenta> todasLasCuentas = cuentaNegocio.obtenerTodasLasCuentas();
        for (Cuenta cuenta : todasLasCuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta) && cuenta.isEstado()) {
                return cuenta;
            }
        }
        return null;
    }
    
    @Override
    public Cuenta obtenerCuentaPorCBU(String cbu) {
        List<Cuenta> todasLasCuentas = cuentaNegocio.obtenerTodasLasCuentas();
        for (Cuenta cuenta : todasLasCuentas) {
            if (cuenta.getCbu().equals(cbu) && cuenta.isEstado()) {
                return cuenta;
            }
        }
        return null;
    }
    
    @Override
    public int contarTransferenciasRealizadasPorClienteEnMes(int idCliente, int mes, int anio) {
        return transferenciaDao.contarTransferenciasRealizadasPorClienteEnMes(idCliente, mes, anio);
    }
} 