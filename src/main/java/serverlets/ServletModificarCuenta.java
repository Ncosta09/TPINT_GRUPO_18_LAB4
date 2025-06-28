package serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import dominio.Cuenta;

@WebServlet("/ServletModificarCuenta")
public class ServletModificarCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

    public ServletModificarCuenta() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
        Cuenta cuenta = cuentaNegocio.obtenerPorId(idCuenta);
        
        if (cuenta == null) {
            response.sendRedirect("ServletListaCuentas?mensaje=Cuenta no encontrada");
            return;
        }

        request.setAttribute("cuenta", cuenta);
        request.getRequestDispatcher("/editarCuenta.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            Cuenta cuenta = new Cuenta();
            cuenta.setId(Integer.parseInt(request.getParameter("idCuenta")));
            cuenta.setSaldo(Double.parseDouble(request.getParameter("saldo")));
            cuenta.setEstado("Activa".equals(request.getParameter("estado")));

            boolean ok = cuentaNegocio.modificarCuenta(cuenta);

            if (ok) {
                response.sendRedirect("ServletListaCuentas?mensaje=Cuenta modificada correctamente");
            } else {
                request.setAttribute("error", "No se pudo modificar la cuenta");
                doGet(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error en los datos ingresados");
            doGet(request, response);
        }
    }
}