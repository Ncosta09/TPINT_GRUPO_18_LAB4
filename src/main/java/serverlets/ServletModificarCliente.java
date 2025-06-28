package serverlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.ClienteNegocio;
import negocio.InputsNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.InputsNegocioImpl;

import dominio.Cliente;

/**
 * Servlet implementation class ServletModificarCliente
 */
@WebServlet("/ServletModificarCliente")
public class ServletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
    private InputsNegocio inputsNegocio   = new InputsNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        Cliente c = clienteNegocio.obtenerPorId(idCliente);
        request.setAttribute("cliente", c);

        request.setAttribute("listaSexo", inputsNegocio.obtenerTodosSexo());
        request.setAttribute("listaNac",  inputsNegocio.obtenerTodosNacionalidad());
        request.setAttribute("listaProv", inputsNegocio.obtenerTodosProvincia());
        request.setAttribute("listaLoca", inputsNegocio.obtenerTodosLocalidad());

        request.getRequestDispatcher("/editarCliente.jsp")
           .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
        Cliente c = new Cliente();
        c.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        c.setDni (request.getParameter("dni"));
        c.setCuil (request.getParameter("cuil"));
        c.setNombre (request.getParameter("nombre"));
        c.setApellido (request.getParameter("apellido"));

        try {
            c.setFechaNacimiento(
              new SimpleDateFormat("yyyy-MM-dd")
                .parse(request.getParameter("fechaNacimiento"))
            );
        } catch (Exception e) {
        	request.setAttribute("error","Fecha inválida");
            doGet(request, response);
            return;
        }

        c.setDireccion (request.getParameter("direccion"));
        c.setEmail (request.getParameter("email"));
        c.setTelefono (request.getParameter("telefono"));
        c.setIdSexo (Integer.parseInt(request.getParameter("sexo")));
        c.setIdNacionalidad (Integer.parseInt(request.getParameter("nacionalidad")));
        c.setIdLocalidad (Integer.parseInt(request.getParameter("localidad")));

        boolean ok = clienteNegocio.modificarCliente(c);

        if (ok) {
            response.sendRedirect("ServletListaClientes?mensaje=Cliente modificado");
        } else {
        	request.setAttribute("error","No se pudo modificar");
            doGet(request, response);
        }
    }
}
