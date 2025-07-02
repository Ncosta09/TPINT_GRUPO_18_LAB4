package serverlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dominio.Cliente;
import dominio.Cuenta;
import negocio.TransferenciaNegocio;
import negocioImpl.TransferenciaNegocioImpl;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;

@WebServlet("/ServletHomeCliente")
public class ServletHomeCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransferenciaNegocio transferenciaNegocio = new TransferenciaNegocioImpl();
    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

    public ServletHomeCliente() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
        String nombreCliente = "";
        double saldoTotal = 0.0;
        int transferenciasMes = 0;
        if (cliente != null) {
            nombreCliente = cliente.getNombre() + " " + cliente.getApellido();
            List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasPorCliente(cliente.getIdCliente());
            for (Cuenta c : cuentas) {
                saldoTotal += c.getSaldo();
            }
            LocalDate now = LocalDate.now();
            transferenciasMes = transferenciaNegocio.contarTransferenciasRealizadasPorClienteEnMes(
                cliente.getIdCliente(), now.getMonthValue(), now.getYear()
            );
        }

        request.setAttribute("nombreCliente", nombreCliente);
        request.setAttribute("saldoTotal", saldoTotal);
        request.setAttribute("transferenciasMes", transferenciasMes);
        request.setAttribute("prestamosActivos", 0);
        request.setAttribute("cuotasPendientes", 0);

        request.getRequestDispatcher("/homeCliente.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
} 