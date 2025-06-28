package serverlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cuenta;
import negocioImpl.CuentaNegocioImpl;

@WebServlet("/ServletListaCuentas")
public class ServletListaCuentas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletListaCuentas() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
        List<Cuenta> cuentas = cuentaNegocio.obtenerTodasLasCuentas();
        request.setAttribute("cuentas", cuentas);
        request.getRequestDispatcher("/listaCuentas.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
        response.sendRedirect("ServletListaCuentas");
    }
}