package serverlets;


	import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cuenta;
	import negocio.CuentaNegocio;
	import negocioImpl.CuentaNegocioImpl;

	@WebServlet("/CuentaServlet")
	public class ServletCuenta extends HttpServlet {
	    private static final long serialVersionUID = 1L;

	    private CuentaNegocio cuentaNegocio;

	    @Override
	    public void init() throws ServletException {
	        super.init();
	        cuentaNegocio = new CuentaNegocioImpl();
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String accion = request.getParameter("accion");

	        if ("actualizar".equals(accion)) {
	         
	            int id = Integer.parseInt(request.getParameter("id"));
	            String tipoCuenta = request.getParameter("tipoCuenta");
	            double saldo = Double.parseDouble(request.getParameter("saldo"));

	            Cuenta cuenta = new Cuenta();
	            cuenta.setId(id);
	            cuenta.setSaldo(saldo);

	            boolean actualizado = cuentaNegocio.modificarCuenta(cuenta);

	            if (actualizado) {
	                request.setAttribute("mensaje", "Cuenta actualizada correctamente");
	            } else {
	                request.setAttribute("mensaje", "Ocurrió un error al actualizar la cuenta");
	            }

	            
	            request.getRequestDispatcher("listaCuentas.jsp").forward(request, response);
	            return;
	        }


	        response.sendRedirect("listaCuentas.jsp");
	    }

	    
	}

