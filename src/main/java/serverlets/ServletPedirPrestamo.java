package serverlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.Prestamo;
import dominio.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/ServletPedirPrestamo")
public class ServletPedirPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletPedirPrestamo() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute("usuarioLogueado") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }

	    try {
	        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

	        ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();
	        Cliente cliente = clienteNeg.obtenerPorId(usuario.getIdUsuario());

	        CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
	        List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasPorCliente(cliente.getIdCliente());

	        request.setAttribute("cuentasCliente", cuentas);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensaje", "Error al cargar cuentas: " + e.getMessage());
	    }

	    request.getRequestDispatcher("/PedirPrestamo.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute("usuarioLogueado") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }

	    try {
	        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

	        ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();
	        Cliente cliente = clienteNeg.obtenerPorId(usuario.getIdUsuario());

	        
	        CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
	        List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasPorCliente(cliente.getIdCliente());
	        request.setAttribute("cuentasCliente", cuentas);

	        String cuentaDestinoStr = request.getParameter("cuentaDestino");
	        String montoStr = request.getParameter("monto");
	        String plazoStr = request.getParameter("plazo");

	        if (cuentaDestinoStr == null || montoStr == null || plazoStr == null ||
	            cuentaDestinoStr.isEmpty() || montoStr.isEmpty() || plazoStr.isEmpty()) {

	            request.setAttribute("mensaje", "Por favor, complete todos los campos.");
	            request.getRequestDispatcher("/PedirPrestamo.jsp").forward(request, response);
	            return;
	        }

	        int idCuenta = Integer.parseInt(cuentaDestinoStr);
	        double monto = Double.parseDouble(montoStr);
	        int meses = Integer.parseInt(plazoStr);

	        if (monto < 10000 || monto > 500000 || meses <= 0 || meses > 60) {
	            request.setAttribute("mensaje", "Valores fuera de rango permitidos.");
	            request.getRequestDispatcher("/PedirPrestamo.jsp").forward(request, response);
	            return;
	        }

	        Prestamo p = new Prestamo();
	        p.setIdCliente(cliente.getIdCliente());
	        p.setIdCuenta(idCuenta);
	        p.setFechaAlta(new Date());
	        p.setImportePedido(monto);
	        p.setPlazoMeses(meses);
	        p.setCantidadCuotas(meses);
	        p.setImporteCuota(Math.round((monto / meses) * 100.0) / 100.0);//Calculamos monto de las cuotas segun los meses.
	        p.setEstado("pendiente");

	        PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
	        boolean exito = prestamoNegocio.pedirPrestamo(p);

	        if (exito) {
	            request.setAttribute("mensaje", "Solicitud de préstamo enviada con éxito.✅");
	        } else {
	            request.setAttribute("mensaje", "No se pudo enviar la solicitud. ❌");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensaje", "Error al procesar la solicitud: " + e.getMessage());
	    }

	    request.getRequestDispatcher("/PedirPrestamo.jsp").forward(request, response);
	}

}
