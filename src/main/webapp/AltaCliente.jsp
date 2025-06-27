<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dominio.Sexo" %>
<%@ page import="dominio.Nacionalidad" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="dominio.Provincia" %>

    
    <%
    List<Sexo> sexo = (List<Sexo>) request.getAttribute("listaSexo");
    List<Nacionalidad> nac = (List<Nacionalidad>) request.getAttribute("listaNac");
    List<Localidad> loca = (List<Localidad>) request.getAttribute("listaLoca");
    List<Provincia> prov = (List<Provincia>) request.getAttribute("listaProv");
	%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Panel Administrador</title>
	<link rel="stylesheet" href="CSS/altaCli.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="form-container">
                <h2 class="form-title">Alta de Usuario/Cliente</h2>
                
                <form id="altaClienteForm" action="ServletAltaCliente" method="post">
                
                <div class="form-row">
                        <div class="form-group">
                            <label for="dni">DNI:</label>
                            <input type="text" id="dni" name="dni" required>
                            <div class="error-message" id="dni-error">El DNI es obligatorio</div>
                        </div>
                        
                        <div class="form-group">
                            <label for="cuil">CUIL:</label>
                            <input type="text" id="cuil" name="cuil" required>
                            <div class="error-message" id="cuil-error">El CUIL es obligatorio</div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" required>
                            <div class="error-message" id="nombre-error"></div>
                        </div>
                        
                        <div class="form-group">
                            <label for="apellido">Apellido:</label>
                            <input type="text" id="apellido" name="apellido" required>
                            <div class="error-message" id="apellido-error"></div>
                        </div>
                    </div>
                    
                    <div class="form-row">
						<div class="form-group">
						    <label for="sexo">Sexo:</label>
						    <select id="sexo" name="sexo" required>
							  <option value="">-- Seleccione un sexo --</option>
							  <%
							    List<dominio.Sexo> sexos = (List<dominio.Sexo>) request.getAttribute("listaSexo");
							    for (dominio.Sexo s : sexos) {
							  %>
							    <option value="<%= s.getIdSexo() %>"><%= s.getDescripcion() %></option>
							  <%
							    }
							  %>
							</select>
						    <div class="error-message" id="sexo-error"></div>
						    
						</div>

                        
                        <div class="form-group">
                            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
                            <div class="error-message" id="fechaNacimiento-error"></div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" required>
                            <div class="error-message" id="email-error"></div>
                        </div>
                        
                        <div class="form-group">
                            <label for="telefono">Teléfono:</label>
                            <input type="tel" id="telefono" name="telefono" required>
                            <div class="error-message" id="telefono-error"></div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="direccion">Dirección:</label>
                            <input type="text" id="direccion" name="direccion" required>
                            <div class="error-message" id="direccion-error"></div>
                        </div>
                        
                        <div class="form-group">
						    <label for="nacionalidad">Nacionalidad:</label>
						    <select id="nacionalidad" name="nacionalidad" required>
							  <option value="">-- Seleccione una nacionalidad --</option>
							  <%
							    List<dominio.Nacionalidad> nacionalidades = (List<dominio.Nacionalidad>) request.getAttribute("listaNac");
							    for (dominio.Nacionalidad n : nacionalidades) {
							  %>
							    <option value="<%= n.getIdNacionalidad() %>"><%= n.getNombre() %></option>
							  <%
							    }
							  %>
							</select>
						    <div class="error-message" id="nacionalidad-error"></div>
						</div>
                    </div>
                    
					<div class="form-row">
					  <div class="form-group">
					    <label for="localidad">Localidad:</label>
					    <select id="localidad" name="localidad" required>
					      <option value="">-- Seleccione una localidad --</option>
					      <%
					        List<dominio.Localidad> localidades =
					          (List<dominio.Localidad>) request.getAttribute("listaLoca");
					        for (dominio.Localidad l : localidades) {
					      %>
					        
					        <option value="<%= l.getIdLocalidad() %>"
					                data-provincia="<%= l.getIdProvincia() %>">
					          <%= l.getNombre() %>
					        </option>
					      <%
					        }
					      %>
					    </select>
					    <div class="error-message" id="localidad-error"></div>
					  </div>
					
					  <div class="form-group">
					    <label for="provincia">Provincia:</label>
					    <select id="provincia" name="provincia" required disabled>
					      <option value="">-- Provincia --</option>
					      <%
					        List<dominio.Provincia> provincias =
					          (List<dominio.Provincia>) request.getAttribute("listaProv");
					        for (dominio.Provincia p : provincias) {
					      %>
					        <option value="<%= p.getIdProvincia() %>">
					          <%= p.getNombre() %>
					        </option>
					      <%
					        }
					      %>
					    </select>
					    <div class="error-message" id="provincia-error"></div>
					  </div>
					</div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="usuario">Usuario:</label>
                            <input type="text" id="usuario" name="usuario" required>
                            <div class="error-message" id="usuario-error"></div>
                        </div>
                        
                        <div class="form-group">
                            <label for="password">Contraseña:</label>
                            <input type="password" id="password" name="password" required>
                            <div class="error-message" id="password-error"></div>
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group">
                            <label for="confirmClave">Confirmar Contraseña:</label>
                            <input type="password" id="confirmClave" name="confirmClave" required>
                            <div class="error-message" id="confirmClave-error">Las contraseñas no coinciden</div>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='homeAdmin.jsp'">Cancelar</button>
                        <button type="submit" class="btn">Guardar Cliente</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="notification" id="notification"></div>



    <script src="js/common.js"></script>
   <script src="js/altaCliente.js"></script> 
</body>
</html>

