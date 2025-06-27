package negocio;

import java.util.List;

import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Provincia;
import dominio.Sexo;

public interface InputsNegocio {

	List<Sexo> obtenerTodosSexo();
	List<Nacionalidad> obtenerTodosNacionalidad();
	List<Localidad> obtenerTodosLocalidad();
	List<Provincia> obtenerTodosProvincia();
}
