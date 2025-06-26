package serverlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dominio.Cliente;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletListaClientes")
public class ServletListaClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletListaClientes() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
	         
		 List<Cliente> lista = clienteNegocio.obtenerTodos();
		

	     request.setAttribute("listaClientes", lista);
	     request.getRequestDispatcher("/listaClientes.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
