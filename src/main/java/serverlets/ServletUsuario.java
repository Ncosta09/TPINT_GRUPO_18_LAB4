package serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
	
    public ServletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/Login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombreUsuario = request.getParameter("nombreUsuario");
        String clave = request.getParameter("clave");
        
        boolean valido = usuarioNegocio.validar(nombreUsuario, clave);
        
        if (valido) {
            // 3a. Login Exitoso
            HttpSession session = request.getSession(true);
            session.setAttribute("usuarioLogueado", valido);
            response.sendRedirect(request.getContextPath() + "/homeAdmin.jsp");
            
        } else {
            // Login Fallido, devuelve mensaje
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
	}

}
