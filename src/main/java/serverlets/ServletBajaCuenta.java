package serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocioImpl.CuentaNegocioImpl;

@WebServlet("/ServletBajaCuenta")
public class ServletBajaCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletBajaCuenta() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
        CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
        
        boolean resultado = cuentaNegocio.bajaCuenta(idCuenta);
        
        if (resultado) {
            response.sendRedirect("ServletListaCuentas?mensaje=Cuenta dada de baja correctamente");
        } else {
            response.sendRedirect("ServletListaCuentas?mensaje=Error al dar de baja la cuenta");
        }
    }
}