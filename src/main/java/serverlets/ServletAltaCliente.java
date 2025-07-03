package serverlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

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
        List<Nacionalidad> nac = inputsNegocio.obtenerTodosNacionalidad();
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
        
        // Capturar todos los parámetros
        String dni = request.getParameter("dni");
        String cuil = request.getParameter("cuil"); // Corregido: era "dni"
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String sexoStr = request.getParameter("sexo");
        String nacionalidadStr = request.getParameter("nacionalidad");
        String localidadStr = request.getParameter("localidad");
        String nombreUsuario = request.getParameter("usuario");
        String passUsuario = request.getParameter("password");
        
        // Guardar los parámetros en request para mantenerlos si hay error
        request.setAttribute("dni", dni);
        request.setAttribute("cuil", cuil);
        request.setAttribute("nombre", nombre);
        request.setAttribute("apellido", apellido);
        request.setAttribute("fechaNacimiento", fechaNacimiento);
        request.setAttribute("email", email);
        request.setAttribute("telefono", telefono);
        request.setAttribute("direccion", direccion);
        request.setAttribute("sexo", sexoStr);
        request.setAttribute("nacionalidad", nacionalidadStr);
        request.setAttribute("localidad", localidadStr);
        request.setAttribute("usuario", nombreUsuario);
        // No guardamos la contraseña por seguridad
        
        try {
        	
        	String dniExtraidoDelCuil = cuil.substring(2, cuil.length() - 1);
        	
        	if (!dni.equals(dniExtraidoDelCuil)) {
                request.setAttribute("error", "El CUIL no corresponde al DNI ingresado.");
                doGet(request, response);
                return;
            }
        	
            // Validar fecha de nacimiento
            if (fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
                Date fechaNac = java.sql.Date.valueOf(fechaNacimiento);
                Date hoy = new Date();
                
                if (fechaNac.after(hoy)) {
                    request.setAttribute("error", "La fecha de nacimiento no puede ser posterior al día de hoy.");
                    doGet(request, response);
                    return;
                }
            }
            
            // Verificar si el DNI ya existe
            ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
            List<Cliente> clientes = clienteNegocio.obtenerTodos();
            for (Cliente c : clientes) {
                if (c.getDni().equals(dni)) {
                    request.setAttribute("error", "El DNI ingresado ya está registrado en el sistema.");
                    doGet(request, response);
                    return;
                }
            }
            
            // Convertir parámetros a tipos adecuados
            int idSexo = Integer.parseInt(sexoStr);
            int idNacionalidad = Integer.parseInt(nacionalidadStr);
            int idLocalidad = Integer.parseInt(localidadStr);
            
            // Crear y guardar usuario
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
                
                // Crear y guardar cliente
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
                
                boolean exito = clienteNegocio.altaCliente(cliente);
                if (exito) {
	                response.sendRedirect("ServletHomeAdmin");
	            } else {
	                request.setAttribute("error", "No se pudo guardar el cliente");
	                doGet(request, response);
	            }
                
            } catch (UsuarioExistenteException e) {
                request.setAttribute("error", "El nombre de usuario ya existe. Por favor, elija otro.");
                doGet(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            String mensaje = ex.getMessage();
            if (mensaje == null || mensaje.isEmpty()) {
                mensaje = "Error en el alta: " + ex.getClass().getSimpleName();
            }
            request.setAttribute("error", mensaje);
            doGet(request, response);
        }
    }
}