package serverlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.TipoCuenta;
import dominio.exceptions.TipoCuentaExistenteException;
import negocio.CuentaNegocio;
import negocio.TipoCuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoCuentaNegocioImpl;

@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio;
    private TipoCuentaNegocio tipoCuentaNegocio;

    @Override
    public void init() throws ServletException {
        super.init();
        cuentaNegocio = new CuentaNegocioImpl();
        tipoCuentaNegocio = new TipoCuentaNegocioImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String accion = request.getParameter("accion");
        switch (accion) {
            case "alta":
                altaCuenta(request, response);
                break;
            case "baja":
                bajaCuenta(request, response);
                break;
            case "actualizar":
                modificarCuenta(request, response);
                break;
            default:
                response.sendRedirect("ServletListaCuentas"); 
                break;
        }
    }

    private void altaCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("cliente"));
        String descripcionTipoCuenta = request.getParameter("tipoCuenta");

        TipoCuenta tipoCuenta = tipoCuentaNegocio.obtenerPorDescripcion(descripcionTipoCuenta);
        if (tipoCuenta == null) {
            request.setAttribute("error", "Tipo de cuenta no válido.");
            List<Cliente> listaClientes = cuentaNegocio.obtenerTodos();
            request.setAttribute("listaClientes", listaClientes);
            request.getRequestDispatcher("AltaCuentas.jsp").forward(request, response);
            return;
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(new dominio.Cliente(idCliente));
        cuenta.setTipoCuenta(tipoCuenta);
        cuenta.setSaldo(10000.00);

        List<Cliente> listaClientes = cuentaNegocio.obtenerTodos();
        request.setAttribute("listaClientes", listaClientes);

        try {
            int idCuentaCreada = cuentaNegocio.crearCuenta(cuenta);
            request.setAttribute("mensaje", "Cuenta creada correctamente");
        } catch (TipoCuentaExistenteException e) {
            request.setAttribute("error", e.getMessage());
        } catch (RuntimeException e) {
            request.setAttribute("error", "Error del sistema: " + e.getMessage());
        }

        request.getRequestDispatcher("AltaCuentas.jsp").forward(request, response);
    }

    private void bajaCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCuenta = Integer.parseInt(request.getParameter("id"));

        boolean desactivada = cuentaNegocio.bajaCuenta(idCuenta);

        if (desactivada) {
            request.setAttribute("mensaje", "Cuenta dada de baja correctamente.");
        } else {
            request.setAttribute("mensaje", "Error al dar de baja la cuenta.");
        }

        request.getRequestDispatcher("listaCuentas.jsp").forward(request, response);
    }

    private void modificarCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcionTipoCuenta = request.getParameter("tipoCuenta");
        double saldo = Double.parseDouble(request.getParameter("saldo"));

        TipoCuenta tipoCuenta = tipoCuentaNegocio.obtenerPorDescripcion(descripcionTipoCuenta);
        if (tipoCuenta == null) {
            request.setAttribute("mensaje", "Tipo de cuenta no válido.");
            request.getRequestDispatcher("listaCuentas.jsp").forward(request, response);
            return;
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setId(id);
        cuenta.setTipoCuenta(tipoCuenta);
        cuenta.setSaldo(saldo);

        boolean actualizado = cuentaNegocio.modificarCuenta(cuenta);

        if (actualizado) {
            request.setAttribute("mensaje", "Cuenta actualizada correctamente");
        } else {
            request.setAttribute("mensaje", "Ocurrió un error al actualizar la cuenta");
        }

        request.getRequestDispatcher("listaCuentas.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String accion = request.getParameter("accion");

        if ("formAlta".equals(accion)) {
            List<Cliente> listaClientes = cuentaNegocio.obtenerTodos();
            request.setAttribute("listaClientes", listaClientes);
            request.getRequestDispatcher("AltaCuentas.jsp").forward(request, response);
            return;
        }
        
        List<Cuenta> cuentas = cuentaNegocio.obtenerTodasLasCuentas();
        request.setAttribute("cuentas", cuentas);
        request.getRequestDispatcher("listaCuentas.jsp").forward(request, response);
    }
}