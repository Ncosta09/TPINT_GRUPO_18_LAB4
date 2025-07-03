package serverlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cuota;
import dominio.Cuenta;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuotaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import dominio.Movimiento;
import java.util.Date;


@WebServlet("/ServletPagarCuotas")
public class ServletPagarCuotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletPagarCuotas() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("ServletListarPrestamos");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
		
		try {
			String idCuotaParam = request.getParameter("idCuota");
			String idCuentaPagoParam = request.getParameter("idCuentaPago");
			
			if (idCuotaParam != null && idCuentaPagoParam != null) {
				int idCuota = Integer.parseInt(idCuotaParam);
				int idCuentaPago = Integer.parseInt(idCuentaPagoParam);
				
				CuotaNegocioImpl cuotaNeg = new CuotaNegocioImpl();
				Cuota cuota = cuotaNeg.obtenerCuotaPorId(idCuota);
				
				if (cuota != null && !cuota.isPagada()) {
					CuentaNegocioImpl cuentaNeg = new CuentaNegocioImpl();
					Cuenta cuentaPago = cuentaNeg.obtenerPorId(idCuentaPago);
					
					if (cuentaPago != null && cuentaPago.getSaldo() >= cuota.getMonto()) {
						boolean pagoExitoso = cuotaNeg.pagarCuota(idCuota);
						
						if (pagoExitoso) {
							double nuevoSaldo = cuentaPago.getSaldo() - cuota.getMonto();
							cuentaNeg.actualizarSaldo(idCuentaPago, nuevoSaldo);
							
							MovimientoNegocioImpl movimientoNeg = new MovimientoNegocioImpl();
							Movimiento movimiento = new Movimiento();
							movimiento.setIdCuenta(idCuentaPago);
							movimiento.setIdTipoMovimiento(4); // Pago préstamos
							movimiento.setFecha(new Date());
							movimiento.setImporte(-cuota.getMonto()); // Negativo porque es un egreso
							movimiento.setSaldo(nuevoSaldo);
							movimientoNeg.insertarMovimiento(movimiento);
							
							session.setAttribute("mensaje", "Cuota pagada exitosamente");
						} else {
							session.setAttribute("error", "Error al procesar el pago");
						}
					} else {
						session.setAttribute("error", "Saldo insuficiente en la cuenta seleccionada");
					}
				} else {
					session.setAttribute("error", "La cuota no existe o ya está pagada");
				}
			} else {
				session.setAttribute("error", "Datos incompletos para el pago");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Error al procesar el pago: " + e.getMessage());
		}
		
		String idPrestamoParam = request.getParameter("idPrestamo");
		if (idPrestamoParam != null && !idPrestamoParam.trim().isEmpty()) {
			response.sendRedirect("ServletListarPrestamos?idPrestamo=" + idPrestamoParam);
		} else {
			response.sendRedirect("ServletListarPrestamos");
		}
	}

}
