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
import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Provincia;
import dominio.Sexo;
import dominio.Usuario;
import dominio.exceptions.UsuarioExistenteException;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.InputsNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletAltaCliente
 */
@WebServlet("/ServletAltaCliente")
public class ServletAltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAltaCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		InputsNegocioImpl inputsNegocio = new InputsNegocioImpl();
        
		 List<Sexo> sexo = inputsNegocio.obtenerTodosSexo();
		 List<Nacionalidad> nac= inputsNegocio.obtenerTodosNacionalidad();
		 List<Provincia> prov = inputsNegocio.obtenerTodosProvincia();
		 List<Localidad> loca = inputsNegocio.obtenerTodosLocalidad();
		

	     request.setAttribute("listaSexo", sexo);
	     request.setAttribute("listaNac", nac);
	     request.setAttribute("listaProv", prov);
	     request.setAttribute("listaLoca", loca);
	     request.getRequestDispatcher("/AltaCliente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		try {
			
			
			String dni = request.getParameter("dni");
	        String cuil = request.getParameter("dni");
	        String nombre = request.getParameter("nombre");
	        String apellido = request.getParameter("apellido");
	        String fechaNacimiento = request.getParameter("fechaNacimiento");
	        String email = request.getParameter("email");
	        String telefono = request.getParameter("telefono");
	        String direccion = request.getParameter("direccion");
	        int idSexo = Integer.parseInt(request.getParameter("sexo"));
	        int idNacionalidad = Integer.parseInt(request.getParameter("nacionalidad"));
	        int idLocalidad = Integer.parseInt(request.getParameter("localidad"));
	        
	        String nombreUsuario = request.getParameter("usuario");
	        String passUsuario = request.getParameter("password");

	        
	        Usuario usuario = new Usuario();
	        usuario.setNombreUsuario(nombreUsuario);
	        usuario.setPassUsuario(passUsuario);
	        usuario.setTipoUsuario(2); 

	        UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
	        
	        try {
	            boolean exitoUsuario = usuarioNegocio.agregarUsuario(usuario);
	            
	            if (!exitoUsuario) {
	                request.setAttribute("error", "No se pudo guardar el usuario.");
	                doGet(request, response);
	                return;
	            }
	            
	            Usuario usuarioCreado = usuarioNegocio.obtenerUsuario(nombreUsuario, passUsuario);
	            if (usuarioCreado == null) {
	                request.setAttribute("error", "Error al obtener el usuario recién creado.");
	                doGet(request, response);
	                return;
	            }
	            
	            int idUsuario = usuarioCreado.getIdUsuario();
	            
	          
	            Cliente cliente = new Cliente();
	            cliente.setDni(dni);
	            cliente.setCuil(cuil);
	            cliente.setNombre(nombre);
	            cliente.setApellido(apellido);
	            cliente.setFechaNacimiento(java.sql.Date.valueOf(fechaNacimiento));
	            cliente.setEmail(email);
	            cliente.setTelefono(telefono);
	            cliente.setDireccion(direccion);
	            cliente.setIdSexo(idSexo);
	            cliente.setIdNacionalidad(idNacionalidad);
	            cliente.setIdLocalidad(idLocalidad);
	            cliente.setIdUsuario(idUsuario);

	            ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
	            boolean exito = clienteNegocio.altaCliente(cliente);

	            if (exito) {
	                response.sendRedirect("ServletHomeAdmin");
	            } else {
	                request.setAttribute("error", "No se pudo guardar el cliente");
	                doGet(request, response);
	            }
	            
	        } catch (UsuarioExistenteException e) {
	            request.setAttribute("error", e.getMessage());
	            doGet(request, response);
	        }

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        request.setAttribute("error", "Error en el alta: " + ex.getMessage());
	        doGet(request, response);
	    }
	}

}
