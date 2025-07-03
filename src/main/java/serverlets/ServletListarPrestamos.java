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
import dominio.Cuota;
import dominio.Prestamo;
import dominio.Usuario;
import negocio.CuotaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuotaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;


@WebServlet("/ServletListarPrestamos")
public class ServletListarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletListarPrestamos() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
		
		String mensaje = (String) session.getAttribute("mensaje");
		String error = (String) session.getAttribute("error");
		
		if (mensaje != null) {
			request.setAttribute("mensaje", mensaje);
			session.removeAttribute("mensaje");
		}
		if (error != null) {
			request.setAttribute("error", error);
			session.removeAttribute("error");
		}
		
		try {
	        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
	        
	        ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();
	        Cliente cliente = clienteNeg.obtenerPorIdUsuario(usuario.getIdUsuario());
	        
	        PrestamoNegocioImpl prestamoNeg = new PrestamoNegocioImpl();
	        List<Prestamo> prestamos = prestamoNeg.listarPrestamosPorCliente(cliente.getIdCliente());
	        
			CuotaNegocioImpl cuotaNeg = new CuotaNegocioImpl();
	        CuentaNegocioImpl cuentaNeg = new CuentaNegocioImpl();
	        
	        for (Prestamo prestamo : prestamos) {
	            int cuotasPagadas = prestamoNeg.obtenerCuotasPagadas(prestamo.getIdPrestamo());
	            int cuotasPendientes = prestamoNeg.obtenerCuotasPendientes(prestamo.getIdPrestamo());
	            
	            Cuenta cuenta = cuentaNeg.obtenerPorId(prestamo.getIdCuenta());
	            
	            request.setAttribute("cuotasPagadas_" + prestamo.getIdPrestamo(), cuotasPagadas);
	            request.setAttribute("cuotasPendientes_" + prestamo.getIdPrestamo(), cuotasPendientes);
	            request.setAttribute("cuenta_" + prestamo.getIdPrestamo(), cuenta);
	        }
	        
	        List<Cuenta> cuentasCliente = cuentaNeg.obtenerCuentasPorCliente(cliente.getIdCliente());
	        request.setAttribute("cuentasCliente", cuentasCliente);

	        request.setAttribute("prestamosCliente", prestamos);
	        
	        String idPrestamoParam = request.getParameter("idPrestamo");
	        if (idPrestamoParam != null && !idPrestamoParam.trim().isEmpty()) {
	            int idPrestamo = Integer.parseInt(idPrestamoParam);
	            List<Cuota> cuotasPrestamo = cuotaNeg.obtenerCuotasPorPrestamo(idPrestamo);
	            request.setAttribute("cuotasPrestamo", cuotasPrestamo);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensaje", "Error al cargar prestamos: " + e.getMessage());
	    }
		
		request.getRequestDispatcher("/pagarCuotas.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} 