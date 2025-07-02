package negocioImpl;

import dao.TipoCuentaDao;
import daoImpl.TipoCuentaDaoImpl;
import dominio.TipoCuenta;
import negocio.TipoCuentaNegocio;
import java.util.List;

public class TipoCuentaNegocioImpl implements TipoCuentaNegocio {
    
    private TipoCuentaDao tipoCuentaDao;
    
    public TipoCuentaNegocioImpl() {
        this.tipoCuentaDao = new TipoCuentaDaoImpl();
    }

    @Override
    public List<TipoCuenta> obtenerTodos() {
        return tipoCuentaDao.obtenerTodos();
    }

    @Override
    public TipoCuenta obtenerPorId(int tipoId) {
        return tipoCuentaDao.obtenerPorId(tipoId);
    }

    @Override
    public TipoCuenta obtenerPorDescripcion(String descripcion) {
        return tipoCuentaDao.obtenerPorDescripcion(descripcion);
    }
} 