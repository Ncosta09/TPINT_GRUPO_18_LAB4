package serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletReactivarCliente")
public class ServletReactivarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String idUsuarioStr = request.getParameter("idUsuario");
        if (idUsuarioStr == null || idUsuarioStr.isEmpty()) {
            response.sendRedirect("ServletListaClientes?mensaje=ID inválido");
            return;
        }

        int idUsuario;
        try {
            idUsuario = Integer.parseInt(idUsuarioStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("ServletListaClientes?mensaje=ID inválido");
            return;
        }

        boolean ok = clienteNegocio.reactivarCliente(idUsuario);
        if (ok) {
            response.sendRedirect("ServletListaClientes?mensaje=Cliente dado de alta correctamente");
        } else {
            response.sendRedirect("ServletListaClientes?mensaje=Error al dar de alta el cliente");
        }
    }
}