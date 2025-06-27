package negocioImpl;

import java.util.List;

import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Provincia;
import dominio.Sexo;
import negocio.InputsNegocio;
import dao.SexoDao;
import daoImpl.SexoDaoImpl;  
import dao.NacionalidadDao;                 
import daoImpl.NacionalidadDaoImpl;
import dao.ProvinciaDao;                
import daoImpl.ProvinciaDaoImpl;
import dao.LocalidadDao;                 
import daoImpl.LocalidadDaoImpl;  

public class InputsNegocioImpl implements InputsNegocio {
	
    private SexoDao sexoDao = new SexoDaoImpl();
    private NacionalidadDao nacionalidadDao = new NacionalidadDaoImpl();
    private ProvinciaDao provinciaDao = new ProvinciaDaoImpl();
    private LocalidadDao localidadDao = new LocalidadDaoImpl();
	
    public List<Sexo> obtenerTodosSexo() {
        return sexoDao.obtenerTodosSexo();
    }
    
    public List<Nacionalidad> obtenerTodosNacionalidad() {
        return nacionalidadDao.obtenerTodosNacionalidad();
    }
    
    public List<Provincia> obtenerTodosProvincia() {
        return provinciaDao.obtenerTodosProvincia();
    }
    	
    public List<Localidad> obtenerTodosLocalidad() {
        return localidadDao.obtenerTodosLocalidad();
    }
	
}
