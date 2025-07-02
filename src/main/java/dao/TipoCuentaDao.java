package dao;

import dominio.TipoCuenta;
import java.util.List;

public interface TipoCuentaDao {
    List<TipoCuenta> obtenerTodos();
    TipoCuenta obtenerPorId(int tipoId);
    TipoCuenta obtenerPorDescripcion(String descripcion);
} 