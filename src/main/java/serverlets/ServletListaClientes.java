package serverlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletListaClientes")
public class ServletListaClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletListaClientes() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
        
        /*
        String accion = request.getParameter("accion");

        if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("idCliente"));
            Cliente cliente = clienteNegocio.obtenerPorId(id); // ⚠️ método que agregamos ahora
            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("/editarCliente.jsp").forward(request, response);
            return;
        }*/

        List<Cliente> lista = clienteNegocio.obtenerTodos();
        request.setAttribute("listaClientes", lista);
        request.getRequestDispatcher("/listaClientes.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		
		/*String accion = request.getParameter("accion");

        if ("modificar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("idCliente"));
            Cliente c = new Cliente();
            c.setIdCliente(id);
            c.setDni(request.getParameter("dni"));
            c.setNombre(request.getParameter("nombre"));
            c.setApellido(request.getParameter("apellido"));
            c.setEmail(request.getParameter("email"));
            c.setTelefono(request.getParameter("telefono"));

            ClienteNegocioImpl negocio = new ClienteNegocioImpl();
            boolean actualizado = negocio.modificarCliente(c);

            if (actualizado)
                request.setAttribute("mensaje", "Cliente actualizado correctamente");
            else
                request.setAttribute("mensaje", "Error al actualizar");

            request.setAttribute("listaClientes", negocio.obtenerTodos());
            request.getRequestDispatcher("/listaClientes.jsp").forward(request, response);
        }*/
    }
}
