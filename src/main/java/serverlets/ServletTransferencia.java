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
import dominio.Transferencia;
import dominio.Usuario;
import negocio.TransferenciaNegocio;
import negocio.CuentaNegocio;
import negocioImpl.TransferenciaNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

@WebServlet("/ServletTransferencia")
public class ServletTransferencia extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransferenciaNegocio transferenciaNegocio = new TransferenciaNegocioImpl();
    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

    public ServletTransferencia() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        
        if (usuarioLogueado.getTipoUsuario() == 2) { // Cliente
            Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
            if (cliente != null) {
                List<Cuenta> cuentasCliente = cuentaNegocio.obtenerCuentasPorCliente(cliente.getIdCliente());
                request.setAttribute("cuentasCliente", cuentasCliente);
            }
        }

        request.getRequestDispatcher("/transferencias.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            String cuentaOrigenStr = request.getParameter("cuentaOrigen");
            String cuentaDestinoStr = request.getParameter("cuentaDestino");
            String montoStr = request.getParameter("monto");
            String concepto = request.getParameter("concepto");

            if (cuentaOrigenStr == null || cuentaOrigenStr.trim().isEmpty()) {
                request.setAttribute("error", "Debe seleccionar una cuenta origen");
                doGet(request, response);
                return;
            }

            if (cuentaDestinoStr == null || cuentaDestinoStr.trim().isEmpty()) {
                request.setAttribute("error", "Debe ingresar una cuenta destino");
                doGet(request, response);
                return;
            }

            if (montoStr == null || montoStr.trim().isEmpty()) {
                request.setAttribute("error", "Debe ingresar un monto");
                doGet(request, response);
                return;
            }

            int idCuentaOrigen = Integer.parseInt(cuentaOrigenStr);
            double monto = Double.parseDouble(montoStr);

            if (monto <= 0) {
                request.setAttribute("error", "El monto debe ser mayor a 0");
                doGet(request, response);
                return;
            }

            Cuenta cuentaDestino = null;
            
            cuentaDestino = transferenciaNegocio.obtenerCuentaPorNumero(cuentaDestinoStr);
            
            if (cuentaDestino == null) {
                cuentaDestino = transferenciaNegocio.obtenerCuentaPorCBU(cuentaDestinoStr);
            }

            if (cuentaDestino == null) {
                request.setAttribute("error", "La cuenta destino no existe o no está activa");
                doGet(request, response);
                return;
            }

            if (idCuentaOrigen == cuentaDestino.getId()) {
                request.setAttribute("error", "No puede transferir a la misma cuenta");
                doGet(request, response);
                return;
            }

            if (!transferenciaNegocio.validarTransferencia(idCuentaOrigen, monto)) {
                request.setAttribute("error", "Saldo insuficiente o cuenta inactiva");
                doGet(request, response);
                return;
            }

            String detalleFinal = "";
            if (concepto != null && !concepto.trim().isEmpty()) {
                detalleFinal = concepto.trim();
            }
            
            Transferencia transferencia = new Transferencia();
            transferencia.setIdCuentaOrigen(idCuentaOrigen);
            transferencia.setIdCuentaDestino(cuentaDestino.getId());
            transferencia.setImporte(monto);
            transferencia.setDetalle(detalleFinal);
            transferencia.setFecha(new java.util.Date());

            boolean exito = transferenciaNegocio.realizarTransferencia(transferencia);

            if (exito) {
                request.setAttribute("mensaje", "Transferencia realizada con éxito");
                request.setAttribute("tipoMensaje", "success");
            } else {
                request.setAttribute("error", "Error al realizar la transferencia");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Formato de datos inválido");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error interno del sistema: " + e.getMessage());
        }

        doGet(request, response);
    }
} 