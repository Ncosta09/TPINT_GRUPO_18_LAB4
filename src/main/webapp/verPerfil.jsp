<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dominio.Cliente" %>
<%@ page import="dominio.Usuario" %>
<%@ page import="dominio.Sexo" %>
<%@ page import="dominio.Nacionalidad" %>
<%@ page import="dominio.Provincia" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="java.util.Date" %>


<%
  List<Sexo> sexos = (List<Sexo>) request.getAttribute("listaSexo");
  List<Nacionalidad> nacs = (List<Nacionalidad>) request.getAttribute("listaNac");
  List<Provincia> provs = (List<Provincia>) request.getAttribute("listaProv");
  List<Localidad> locs = (List<Localidad>) request.getAttribute("listaLoca");
%>

<%
    
    Cliente clienteLogueado = null;

    if (session != null && session.getAttribute("clienteLogueado") != null) {
    	clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
    }
    
    Usuario usuarioLogueado = null;

    if (session != null && session.getAttribute("usuarioLogueado") != null) {
        usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Mi Perfil</title>
    <link rel="stylesheet" href="CSS/verPerfil.css" type="text/css" />
    <link rel="stylesheet" href="CSS/dialog.css" type="text/css" />
    <link rel="stylesheet" href="CSS/common.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
	<dialog id="dlgUser">
	    <form method="post" action="ServletModificarUsuario">
	        <h3>Cambiar nombre de usuario</h3>
	        <input type="hidden" name="idUsuario" value="${usuarioLogueado.idUsuario}">
	        
	        <div class="form-group">
	            <label for="nuevoUsuario">Nuevo usuario:</label>
	            <input type="text" id="nuevoUsuario" name="nuevoUsuario" value="${u.username}" required autofocus>
	        </div>
	        
	        <div class="button-group">
	            <button class="btn btn-secondary" type="button" onclick="this.closest('dialog').close()">
	                Cancelar
	            </button>
	            <button class="btn" type="submit" name="action" value="cambiarUsuario">
	                Guardar
	            </button>
	        </div>
	    </form>
	</dialog>
	
	<dialog id="dlgPass">
	    <form method="post" action="ServletModificarUsuario">
	        <h3>Cambiar contraseña</h3>
	        <input type="hidden" name="idUsuario" value="${usuarioLogueado.idUsuario}">
	        
	        <div class="form-group">
	            <label for="nuevaContrasena">Nueva contraseña:</label>
	            <input type="password" id="nuevaContrasena" name="nuevaContrasena" required>
	        </div>
	        
	        <div class="button-group">
	            <button class="btn btn-secondary" type="button" onclick="this.closest('dialog').close()">
	                Cancelar
	            </button>
	            <button class="btn" type="submit" name="action" value="cambiarContrasena">
	                Guardar
	            </button>
	        </div>
	    </form>
	</dialog>
    
    <div class="container">
        <jsp:include page="/sidebarCliente.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="profile-container">
                <h2 class="profile-title">
                    <span class="profile-icon">👤</span>
                    Mi Perfil
                </h2>
                
                <div class="profile-section">
                    <h3 class="section-title">Información Personal</h3>
                    <div class="profile-row">
                        <div class="profile-field">
                        	<label class="field-label">Nombre:</label>
                            <div class="field-value"><%= clienteLogueado.getNombre() %></div>
                        </div>
                        <div class="profile-field">
                        	<label class="field-label">Apellido:</label>
                            <div class="field-value"><%= clienteLogueado.getApellido() %></div>
                        </div>
                    </div>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Sexo:</label>
                     		<% 
						       for (Sexo s : sexos) {
						           if (s.getIdSexo() == clienteLogueado.getIdSexo()) {
						    %>
                            <div class="field-value"><%= s.getDescripcion() %></div>
						    <%
						             break;
						           }
						       }
						    %>
                        </div>
                        <div class="profile-field">
                        	<label class="field-label">DNI:</label>
                            <div class="field-value"><%= clienteLogueado.getDni() %></div>
                        </div>
                    </div>
                    <div class="profile-row">
                        <div class="profile-field">
                        	<label class="field-label">Fecha de Nacimiento:</label>
                            <div class="field-value"><%= clienteLogueado.getFechaNacimiento() %></div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Nacionalidad:</label>
                            <div class="field-value"><%= clienteLogueado.getApellido() %></div>
                        </div>
                    </div>
                </div>
                
                <div class="profile-section">
                    <h3 class="section-title">Información de Contacto</h3>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Email:</label>
                            <div class="field-value"><%= clienteLogueado.getEmail() %></div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Teléfono:</label>
                            <div class="field-value"><%= clienteLogueado.getTelefono() %></div>
                        </div>
                    </div>
                </div>
                
                <div class="profile-section">
                    <h3 class="section-title">Dirección</h3>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Dirección:</label>
                            <div class="field-value"><%= clienteLogueado.getDireccion() %></div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Localidad:</label>
                            <% 
						       for (Localidad l : locs) {
						           if (l.getIdLocalidad() == clienteLogueado.getIdLocalidad()) {
						    %>
                            <div class="field-value"><%= l.getNombre() %></div>
						    <%
						             break;
						           }
						       }
						    %>
                        </div>
                    </div>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Provincia:</label>
                            <% 
						       for (Provincia p : provs) {
						           if (p.getIdProvincia() == clienteLogueado.getIdLocalidad()) {
						    %>
                            <div class="field-value"><%= p.getNombre() %></div>
						    <%
						             break;
						           }
						       }
						    %>
                        </div>
                        <div class="profile-field">
                        </div>
                    </div>
                </div>
                
                <div class="profile-section">
                    <h3 class="section-title">Información de Cuenta</h3>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Usuario:</label>
                            <div class="field-value"><%= usuarioLogueado.getNombreUsuario() %></div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Estado:</label>
                            <div class="field-value">
                            <% 
						       
						           if (usuarioLogueado.getEstado() == 1) {
						    %>
						    		<span class="badge badge-active">Activo</span>
						    <% 
						           } else {
						    %>
									<span class="badge badge-inactive">Inactivo</span>
							<% 
						           } 
						    %>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="profile-actions">
                    <button class="btn btn-secondary" onclick="window.location.href='homeCliente.jsp'">Volver</button>
                    <!--  <button class="btn" onclick="editProfile()">Editar Perfil</button> -->
                    <button class="btn" onclick="document.getElementById('dlgPass').showModal()">Cambiar Contraseña</button>
                    <button class="btn" onclick="document.getElementById('dlgUser').showModal()">Cambiar Usuario</button>
                </div>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
    <!-- <script src="js/verPerfil.js"></script> -->
</body>
</html> 
