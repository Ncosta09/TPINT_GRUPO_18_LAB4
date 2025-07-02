package negocio;

import dominio.Transferencia;
import dominio.Cuenta;
import java.util.List;

public interface TransferenciaNegocio {
    boolean realizarTransferencia(Transferencia transferencia);
    boolean validarTransferencia(int idCuentaOrigen, double monto);
    Cuenta obtenerCuentaPorNumero(String numeroCuenta);
    Cuenta obtenerCuentaPorCBU(String cbu);
    int contarTransferenciasRealizadasPorClienteEnMes(int idCliente, int mes, int anio);
} 