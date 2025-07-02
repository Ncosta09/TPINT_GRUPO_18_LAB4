package dao;

import dominio.Transferencia;
import java.util.List;

public interface TransferenciaDao {
    boolean realizarTransferencia(Transferencia transferencia);
    int contarTransferenciasRealizadasPorClienteEnMes(int idCliente, int mes, int anio);
} 