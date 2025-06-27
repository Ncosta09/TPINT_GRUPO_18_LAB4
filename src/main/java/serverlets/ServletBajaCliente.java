package serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import daoImpl.Conexion;
import java.sql.*;

@WebServlet("/ServletBajaCliente")
public class ServletBajaCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletBajaCliente() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idUsuarioStr = request.getParameter("idUsuario");

        if (idUsuarioStr != null && !idUsuarioStr.isEmpty()) {
            int idUsuario = Integer.parseInt(idUsuarioStr);

            try (Connection conn = Conexion.obtenerConexionDirecta()) {
                String sql = "UPDATE Usuarios SET estado = 0 WHERE id_usuario = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, idUsuario);

                int filasAfectadas = ps.executeUpdate();
                conn.commit();

                if (filasAfectadas > 0) {
                    response.sendRedirect("ServletListaClientes?mensaje=Cliente dado de baja correctamente");
                } else {
                    response.sendRedirect("ServletListaClientes?mensaje=No se encontró el cliente para dar de baja");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("ServletListaClientes?mensaje=Error al intentar dar de baja el cliente");
            }

        } else {
            response.sendRedirect("ServletListaClientes?mensaje=ID de usuario inválido");
        }
    }
}
