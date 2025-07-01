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
import dominio.Cliente;
import dominio.Usuario;
import negocio.CuentaNegocio;
import negocio.ClienteNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletCuentasCliente")
public class ServletCuentasCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();

    public ServletCuentasCliente() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        
        try {
            Cliente cliente = clienteNegocio.obtenerClientePorUsuario(usuario.getIdUsuario());
            
            if (cliente == null) {
                request.setAttribute("error", "No se encontró información del cliente");
                request.getRequestDispatcher("/cuentas.jsp").forward(request, response);
                return;
            }
            
            List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasPorCliente(cliente.getIdCliente());
            
            request.setAttribute("cuentas", cuentas);
            request.getRequestDispatcher("/cuentas.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar las cuentas: " + e.getMessage());
            request.getRequestDispatcher("/cuentas.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}