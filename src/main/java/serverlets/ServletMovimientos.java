package serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Movimiento;
import negocio.MovimientoNegocio;
import negocioImpl.MovimientoNegocioImpl;

@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();

    public ServletMovimientos() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
            String tipoMovimiento = request.getParameter("tipoMovimiento");
            String fechaDesde = request.getParameter("fechaDesde");
            String fechaHasta = request.getParameter("fechaHasta");
            
            List<Movimiento> movimientos;
            
            if ((tipoMovimiento != null && !tipoMovimiento.trim().isEmpty()) ||
                (fechaDesde != null && !fechaDesde.trim().isEmpty()) ||
                (fechaHasta != null && !fechaHasta.trim().isEmpty())) {
                
                movimientos = movimientoNegocio.obtenerMovimientosPorCuentaConFiltros(
                    idCuenta, tipoMovimiento, fechaDesde, fechaHasta);
            } else {
                movimientos = movimientoNegocio.obtenerMovimientosPorCuenta(idCuenta);
            }
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter out = response.getWriter();
            out.print("[");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            for (int i = 0; i < movimientos.size(); i++) {
                Movimiento mov = movimientos.get(i);
                if (i > 0) out.print(",");
                
                out.print("{");
                out.print("\"fecha\":\"" + sdf.format(mov.getFecha()) + "\",");
                out.print("\"descripcion\":\"" + mov.getTipoMovimientoDescripcion() + "\",");
                out.print("\"tipo\":\"" + mov.getTipoMovimientoDescripcion().toLowerCase() + "\",");
                out.print("\"monto\":" + mov.getImporte() + ",");
                out.print("\"saldo\":" + mov.getSaldo());
                out.print("}");
            }
            
            out.print("]");
            out.flush();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al cargar movimientos\"}");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}