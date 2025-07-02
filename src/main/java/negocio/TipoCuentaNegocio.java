package negocio;

import dominio.TipoCuenta;
import java.util.List;

public interface TipoCuentaNegocio {
    List<TipoCuenta> obtenerTodos();
    TipoCuenta obtenerPorId(int tipoId);
    TipoCuenta obtenerPorDescripcion(String descripcion);
} 