package serverlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import dominio.Reporte;
import negocio.ReporteNegocio;
import negocioImpl.ReporteNegocioImpl;

@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReporteNegocio reporteNegocio = new ReporteNegocioImpl();
       

    public ServletReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("clienteLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
        Date desde = java.sql.Date.valueOf(request.getParameter("startDate"));
        Date hasta = java.sql.Date.valueOf(request.getParameter("endDate"));

        // Totales
        request.setAttribute("totalIngresos", reporteNegocio.totalIngresos(cliente.getIdCliente(), desde, hasta));
        request.setAttribute("totalEgresos", reporteNegocio.totalEgresos(cliente.getIdCliente(), desde, hasta));

        // Detalle
        List<Reporte> movimientos = reporteNegocio.detalleMovimientos(cliente.getIdCliente(), desde, hasta);
        request.setAttribute("movimientos", movimientos);

        // Forward a la JSP de reportes
        request.getRequestDispatcher("ReportesCliente.jsp").forward(request, response);
	}

}
