package serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletBajaCliente")
public class ServletBajaCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 1) Creamos el negocio en vez de usar JDBC directo
    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idUsuarioStr = request.getParameter("idUsuario");
        if (idUsuarioStr == null || idUsuarioStr.isBlank()) {
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

        // 2) Delegamos al negocio
        boolean ok = clienteNegocio.darDeBaja(idUsuario);

        // 3) Redirigimos con feedback
        if (ok) {
            response.sendRedirect("ServletListaClientes?mensaje=Cliente dado de baja correctamente");
        } else {
            response.sendRedirect("ServletListaClientes?mensaje=Error al dar de baja");
        }
    }
}
