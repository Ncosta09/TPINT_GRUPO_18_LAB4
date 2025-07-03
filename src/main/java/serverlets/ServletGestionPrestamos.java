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
import dominio.Prestamo;
import dominio.Usuario;
import dominio.Cuenta;
import dominio.Movimiento;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
import negocioImpl.CuotaNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import java.util.Date;




@WebServlet("/ServletGestionPrestamos")
public class ServletGestionPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletGestionPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
		
		
		try {
	        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
	        
	        
//	        ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();
//	        Cliente cliente = clienteNeg.obtenerPorId(usuario.getIdUsuario());
	        PrestamoNegocioImpl prestamo=new PrestamoNegocioImpl();
	        List<Prestamo> prestamos=prestamo.listarPrestamosPendientes();

	        request.setAttribute("prestamosPendientes", prestamos);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensaje", "Error al cargar prestamos: " + e.getMessage());
	    }
		
		
		   request.getRequestDispatcher("/prestamosAdmin.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute("usuarioLogueado") == null) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }

	    PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
	    CuotaNegocioImpl cuotas=new CuotaNegocioImpl();
	    CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
	    MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
	    Prestamo seleccionado=new Prestamo();
	    
	    
	    String mensaje = null;

	    try {
	        String idStr = request.getParameter("idPrestamo");
	        if (idStr != null && !idStr.isEmpty()) {
	            int idPrestamo = Integer.parseInt(idStr);

	            if (request.getParameter("btnAprobar") != null) {
	                seleccionado = prestamoNegocio.obtenerPrestamoPorId(idPrestamo);
	                
	                if (seleccionado != null) {
	                    boolean aprobado = prestamoNegocio.actualizarEstado(idPrestamo, "aprobado");
	                    
	                    if (aprobado) {
	                        boolean cuotasGeneradas = cuotas.generarCuotasParaPrestamo(idPrestamo, seleccionado.getCantidadCuotas(), seleccionado.getImporteCuota());
	                        
	                        if (cuotasGeneradas) {
	                            Cuenta cuenta = cuentaNegocio.obtenerPorId(seleccionado.getIdCuenta());
	                            
	                            if (cuenta != null) {
	                                double nuevoSaldo = cuenta.getSaldo() + seleccionado.getImportePedido();
	                                
	                                boolean saldoActualizado = cuentaNegocio.actualizarSaldo(seleccionado.getIdCuenta(), nuevoSaldo);
	                                
	                                if (saldoActualizado) {
	                                    Movimiento movimiento = new Movimiento();
	                                    movimiento.setIdCuenta(seleccionado.getIdCuenta());
	                                    movimiento.setIdTipoMovimiento(3); // Depósito por préstamo
	                                    movimiento.setFecha(new Date());
	                                    movimiento.setImporte(seleccionado.getImportePedido());
	                                    movimiento.setSaldo(nuevoSaldo);
	                                    
	                                    boolean movimientoGenerado = movimientoNegocio.insertarMovimiento(movimiento);
	                                    
	                                    if (movimientoGenerado) {
	                                        mensaje = "Préstamo aprobado correctamente ✅ - Monto depositado: $" + String.format("%,.2f", seleccionado.getImportePedido());
	                                    } else {
	                                        mensaje = "Préstamo aprobado pero error al generar movimiento";
	                                    }
	                                } else {
	                                    mensaje = "Préstamo aprobado pero error al actualizar saldo";
	                                }
	                            } else {
	                                mensaje = "Préstamo aprobado pero no se encontró la cuenta";
	                            }
	                        } else {
	                            mensaje = "Préstamo aprobado pero error al generar cuotas";
	                        }
	                    } else {
	                        mensaje = "Error al aprobar el préstamo";
	                    }
	                } else {
	                    mensaje = "No se encontró el préstamo";
	                }
	            } else if (request.getParameter("btnRechazar") != null) {
	                prestamoNegocio.actualizarEstado(idPrestamo, "rechazado");
	                mensaje = "Préstamo rechazado correctamente ✅";
	            } 
	        }

	     // Recargamos lista.
	        List<Prestamo> prestamos = prestamoNegocio.listarPrestamosPendientes();
	        request.setAttribute("prestamosPendientes", prestamos);

	        // Pasamos la notificacion.
	        if (mensaje != null) {
	            request.setAttribute("mensajeEstado", mensaje);
	        }

	        request.getRequestDispatcher("/prestamosAdmin.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensajeEstado", "Ocurrió un error: " + e.getMessage());
	        request.getRequestDispatcher("/prestamosAdmin.jsp").forward(request, response);
	    }
		
	}
		
	
	
	
		
		
		
		
		
	}


