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
import dominio.Prestamo;
import dominio.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;


@WebServlet("/ServletGestionPrestamos")
public class ServletGestionPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletGestionPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
		
		
		try {
	        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
	        
	        
//	        ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();
//	        Cliente cliente = clienteNeg.obtenerPorId(usuario.getIdUsuario());
	        PrestamoNegocioImpl prestamo=new PrestamoNegocioImpl();
	        List<Prestamo> prestamos=prestamo.listarTodos();

	        request.setAttribute("prestamosPendientes", prestamos);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensaje", "Error al cargar prestamos: " + e.getMessage());
	    }
		
		
		   request.getRequestDispatcher("/prestamosAdmin.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
