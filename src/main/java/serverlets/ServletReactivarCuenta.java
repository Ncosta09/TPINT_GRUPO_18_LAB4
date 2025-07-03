package serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;

@WebServlet("/ServletReactivarCuenta")
public class ServletReactivarCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String idCuentaStr = request.getParameter("idCuenta");
        if (idCuentaStr == null || idCuentaStr.isEmpty()) {
            response.sendRedirect("ServletListaCuentas?mensaje=ID de cuenta inválido");
            return;
        }

        int idCuenta;
        try {
            idCuenta = Integer.parseInt(idCuentaStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("ServletListaCuentas?mensaje=ID de cuenta inválido");
            return;
        }

        boolean ok = cuentaNegocio.reactivarCuenta(idCuenta);
        if (ok) {
            response.sendRedirect("ServletListaCuentas?mensaje=Cuenta dada de alta correctamente");
        } else {
            response.sendRedirect("ServletListaCuentas?mensaje=Error al dar de alta la cuenta");
        }
    }
}