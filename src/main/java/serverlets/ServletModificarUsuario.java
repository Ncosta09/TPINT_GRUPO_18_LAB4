package serverlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;


@WebServlet("/ServletModificarUsuario")
public class ServletModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
	

    public ServletModificarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String action = request.getParameter("action");
        boolean ok = false;
        
     // Lógica de negocio
        if ("cambiarUsuario".equals(action)) {
            String nuevoUsername = request.getParameter("nuevoUsuario").trim();
            if (!nuevoUsername.isEmpty()) {
                ok = usuarioNegocio.actualizarUsername(idUsuario, nuevoUsername);
            }
        } else if ("cambiarContrasena".equals(action)) {
            String nuevaPass = request.getParameter("nuevaContrasena");
            if (nuevaPass != null && nuevaPass.length() >= 6) {
                ok = usuarioNegocio.actualizarContrasena(idUsuario, nuevaPass);
            }
        }
        
        if (ok) {
            session.invalidate();
            response.sendRedirect("Login.jsp");
        } else {
            response.sendRedirect("ServletPerfil");
        }

    }
}
