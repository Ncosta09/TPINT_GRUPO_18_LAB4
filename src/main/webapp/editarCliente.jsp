<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dominio.Cliente" %>
<%@ page import="dominio.Sexo" %>
<%@ page import="dominio.Nacionalidad" %>
<%@ page import="dominio.Provincia" %>
<%@ page import="dominio.Localidad" %>

<%
  Cliente c = (Cliente) request.getAttribute("cliente");
  List<Sexo> sexos = (List<Sexo>) request.getAttribute("listaSexo");
  List<Nacionalidad> nacs = (List<Nacionalidad>) request.getAttribute("listaNac");
  List<Provincia> provs = (List<Provincia>) request.getAttribute("listaProv");
  List<Localidad> locs = (List<Localidad>) request.getAttribute("listaLoca");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Modificar Cliente</title>
    <link rel="stylesheet" href="CSS/editarCliente.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
         <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="form-container">
                <h2 class="form-title">Modificar Cliente</h2>
                
                <form action="${pageContext.request.contextPath}/ServletModificarCliente" method="post" id="clientForm">
                    <input type="hidden" name="idCliente" value="<%= c.getIdCliente() %>">
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="dni">DNI:</label>
                            <input type="text" id="dni" name="dni" value="<%=c.getDni()%>" required>
                            <div class="error-message" id="dni-error">El DNI es obligatorio</div>
                        </div>
                        
                        <div class="form-group">
                            <label for="cuil">CUIL:</label>
                            <input type="text" id="cuil" name="cuil" value="<%=c.getCuil()%>" required>
                            <div class="error-message" id="cuil-error">El CUIL es obligatorio</div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" value="<%=c.getNombre()%>" required>
                            <div class="error-message" id="nombre-error">El nombre es obligatorio</div>
                        </div>
                        
                        <div class="form-group">
                            <label for="apellido">Apellido:</label>
                            <input type="text" id="apellido" name="apellido" value="<%=c.getApellido()%>" required>
                            <div class="error-message" id="apellido-error">El apellido es obligatorio</div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="sexo">Sexo:</label>
                            <select id="sexo" name="sexo" required>
                                <% for (Sexo s: sexos) { %>
                                    <option value="<%=s.getIdSexo()%>"
                                        <%=s.getIdSexo()==c.getIdSexo()?"selected":""%>>
                                        <%=s.getDescripcion()%>
                                    </option>
                                <% } %>
                            </select>
                            <div class="error-message" id="sexo-error">Seleccione un sexo</div>
                        </div>
                        
                        <div class="form-group">
                            <label for="nacionalidad">Nacionalidad:</label>
                            <select id="nacionalidad" name="nacionalidad" required>
                                <% for (Nacionalidad n: nacs) { %>
                                    <option value="<%=n.getIdNacionalidad()%>"
                                        <%=n.getIdNacionalidad()==c.getIdNacionalidad()?"selected":""%>>
                                        <%=n.getNombre()%>
                                    </option>
                                <% } %>
                            </select>
                            <div class="error-message" id="nacionalidad-error">Seleccione una nacionalidad</div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                            <input type="date" id="fechaNacimiento" name="fechaNacimiento"
                                value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(c.getFechaNacimiento()) %>" required>
                            <div class="error-message" id="fechaNacimiento-error">La fecha de nacimiento es obligatoria</div>
                        </div>
                        
                        <div class="form-group">
                            <label for="direccion">Dirección:</label>
                            <input type="text" id="direccion" name="direccion" value="<%=c.getDireccion()%>" required>
                            <div class="error-message" id="direccion-error">La dirección es obligatoria</div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="localidad">Localidad:</label>
                            <select id="localidad" name="localidad" required>
                                <% for (Localidad l: locs) { %>
                                    <option value="<%=l.getIdLocalidad()%>"
                                        <%=l.getIdLocalidad()==c.getIdLocalidad()?"selected":""%>
                                        data-provincia="<%=l.getIdProvincia()%>">
                                        <%=l.getNombre()%>
                                    </option>
                                <% } %>
                            </select>
                            <div class="error-message" id="localidad-error">Seleccione una localidad</div>
                        </div>
                        
                        <div class="form-group">
                            <label for="provincia">Provincia:</label>
                            <select id="provincia" name="provincia" disabled>
                                <% for (Provincia p: provs) { %>
                                    <option value="<%=p.getIdProvincia()%>"
                                        <%=p.getIdProvincia()==(c.getIdLocalidad())? "selected":""%>>
                                        <%=p.getNombre()%>
                                    </option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" value="<%=c.getEmail()%>" required>
                            <div class="error-message" id="email-error">Ingrese un email válido</div>
                        </div>
                        
                        <div class="form-group">
                            <label for="telefono">Teléfono:</label>
                            <input type="tel" id="telefono" name="telefono" value="<%=c.getTelefono()%>" required>
                            <div class="error-message" id="telefono-error">El teléfono es obligatorio</div>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='ServletListaClientes'">Cancelar</button>
                        <button type="submit" class="btn" id="submitBtn">Guardar Cambios</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="notification" id="notification">Cliente modificado correctamente</div>
</body>
</html>
