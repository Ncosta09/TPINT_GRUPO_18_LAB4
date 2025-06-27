package serverlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Provincia;
import dominio.Sexo;
import negocioImpl.InputsNegocioImpl;

/**
 * Servlet implementation class ServletAltaCliente
 */
@WebServlet("/ServletAltaCliente")
public class ServletAltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAltaCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputsNegocioImpl inputsNegocio = new InputsNegocioImpl();
        
		 List<Sexo> sexo = inputsNegocio.obtenerTodosSexo();
		 List<Nacionalidad> nac= inputsNegocio.obtenerTodosNacionalidad();
		 List<Provincia> prov = inputsNegocio.obtenerTodosProvincia();
		 List<Localidad> loca = inputsNegocio.obtenerTodosLocalidad();
		

	     request.setAttribute("listaSexo", sexo);
	     request.setAttribute("listaNac", nac);
	     request.setAttribute("listaProv", prov);
	     request.setAttribute("listaLoca", loca);
	     request.getRequestDispatcher("/AltaCliente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
