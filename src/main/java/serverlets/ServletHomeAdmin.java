package serverlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.ClienteNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/ServletHomeAdmin")
public class ServletHomeAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
    private PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();

    public ServletHomeAdmin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int totalClientes = clienteNegocio.contarClientes(false);
        int totalClientesMes = clienteNegocio.contarClientes(true);
        
        int prestamosActivos = prestamoNegocio.listarPrestamosAprobados().size();
        int prestamosPendientes = prestamoNegocio.listarPrestamosPendientes().size();

        request.setAttribute("totalClientes", totalClientes);
        request.setAttribute("totalClientesMes", totalClientesMes);
        request.setAttribute("prestamosActivos", prestamosActivos);
        request.setAttribute("prestamosPendientes", prestamosPendientes);

        request.getRequestDispatcher("/homeAdmin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
