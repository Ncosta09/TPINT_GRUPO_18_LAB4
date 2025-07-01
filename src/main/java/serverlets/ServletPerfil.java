package serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import negocio.ClienteNegocio;
import negocio.InputsNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.InputsNegocioImpl;

/**
 * Servlet implementation class ServletPerfil
 */
@WebServlet("/ServletPerfil")
public class ServletPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InputsNegocio inputsNegocio   = new InputsNegocioImpl();

    public ServletPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

        request.setAttribute("listaSexo", inputsNegocio.obtenerTodosSexo());
        request.setAttribute("listaNac",  inputsNegocio.obtenerTodosNacionalidad());
        request.setAttribute("listaProv", inputsNegocio.obtenerTodosProvincia());
        request.setAttribute("listaLoca", inputsNegocio.obtenerTodosLocalidad());

        request.getRequestDispatcher("/verPerfil.jsp")
           .forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
