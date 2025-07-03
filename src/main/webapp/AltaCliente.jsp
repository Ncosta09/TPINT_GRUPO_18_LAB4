<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dominio.Sexo" %>
<%@ page import="dominio.Nacionalidad" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="dominio.Provincia" %>
<%@ page import="dominio.Usuario" %>

<%
    if (session == null || session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
    
    // Recuperar valores anteriores obtenidos al existir un error 
    // Si la variable es distinta de null (tiene valor porque se guardo) lo trae, si no, no tiene valor, string vacio
    String dni = (String) request.getAttribute("dni");
    String cuil = (String) request.getAttribute("cuil");
    String nombre = (String) request.getAttribute("nombre");
    String apellido = (String) request.getAttribute("apellido");
    String fechaNacimiento = (String) request.getAttribute("fechaNacimiento");
    String email = (String) request.getAttribute("email");
    String telefono = (String) request.getAttribute("telefono");
    String direccion = (String) request.getAttribute("direccion");
    String sexoSeleccionado = (String) request.getAttribute("sexo");
    String nacionalidadSeleccionada = (String) request.getAttribute("nacionalidad");
    String localidadSeleccionada = (String) request.getAttribute("localidad");
    String usuario = (String) request.getAttribute("usuario");
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
                
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-error" id="errorAlert">
                        <%= request.getAttribute("error") %>
                    </div>
                <% } %>
                
                <form id="altaClienteForm" action="ServletAltaCliente" method="post">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="dni">DNI:</label>
                            <input type="text" id="dni" name="dni" value="<%= dni != null ? dni : "" %>" required>
                            <div class="error-message" id="dni-error">El DNI es obligatorio</div>
                        </div>
                        <div class="form-group">
                            <label for="cuil">CUIL:</label>
                            <input type="text" id="cuil" name="cuil" value="<%= cuil != null ? cuil : "" %>" required>
                            <div class="error-message" id="cuil-error">El CUIL es obligatorio</div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" value="<%= nombre != null ? nombre : "" %>" required>
                            <div class="error-message" id="nombre-error"></div>
                        </div>
                        <div class="form-group">
                            <label for="apellido">Apellido:</label>
                            <input type="text" id="apellido" name="apellido" value="<%= apellido != null ? apellido : "" %>" required>
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
                                        boolean selected = sexoSeleccionado != null && 
                                                          Integer.toString(s.getIdSexo()).equals(sexoSeleccionado);
                                %>
                                    <option value="<%= s.getIdSexo() %>" <%= selected ? "selected" : "" %>><%= s.getDescripcion() %></option>
                                <%
                                    }
                                %>
                            </select>
                            <div class="error-message" id="sexo-error"></div>
                        </div>
                        <div class="form-group">
                            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                            <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= fechaNacimiento != null ? fechaNacimiento : "" %>" required>
                            <div class="error-message" id="fechaNacimiento-error"></div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" value="<%= email != null ? email : "" %>" required>
                            <div class="error-message" id="email-error"></div>
                        </div>
                        <div class="form-group">
                            <label for="telefono">Teléfono:</label>
                            <input type="tel" id="telefono" name="telefono" value="<%= telefono != null ? telefono : "" %>" required>
                            <div class="error-message" id="telefono-error"></div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="direccion">Dirección:</label>
                            <input type="text" id="direccion" name="direccion" value="<%= direccion != null ? direccion : "" %>" required>
                            <div class="error-message" id="direccion-error"></div>
                        </div>
                        <div class="form-group">
                            <label for="nacionalidad">Nacionalidad:</label>
                            <select id="nacionalidad" name="nacionalidad" required>
                                <option value="">-- Seleccione una nacionalidad --</option>
                                <%
                                    List<dominio.Nacionalidad> nacionalidades = (List<dominio.Nacionalidad>) request.getAttribute("listaNac");
                                    for (dominio.Nacionalidad n : nacionalidades) {
                                        boolean selected = nacionalidadSeleccionada != null && 
                                                          Integer.toString(n.getIdNacionalidad()).equals(nacionalidadSeleccionada);
                                %>
                                    <option value="<%= n.getIdNacionalidad() %>" <%= selected ? "selected" : "" %>><%= n.getNombre() %></option>
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
                                    List<dominio.Localidad> localidades = (List<dominio.Localidad>) request.getAttribute("listaLoca");
                                    for (dominio.Localidad l : localidades) {
                                        boolean selected = localidadSeleccionada != null && 
                                                          Integer.toString(l.getIdLocalidad()).equals(localidadSeleccionada);
                                %>
                                    <option value="<%= l.getIdLocalidad() %>" 
                                            data-provincia="<%= l.getIdProvincia() %>"
                                            <%= selected ? "selected" : "" %>>
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
                                    List<dominio.Provincia> provincias = (List<dominio.Provincia>) request.getAttribute("listaProv");
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
                            <input type="text" id="usuario" name="usuario" value="<%= usuario != null ? usuario : "" %>" required>
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
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='ServletHomeAdmin'">Cancelar</button>
                        <button type="submit" class="btn">Guardar Cliente</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="notification" id="notification"></div>
    <script src="js/common.js"></script>
    <script src="js/altaCliente.js"></script>
    
    <script>
    // Script para seleccionar la provincia correcta basada en la localidad
    document.addEventListener('DOMContentLoaded', function() {
        const localidadSelect = document.getElementById('localidad');
        const provinciaSelect = document.getElementById('provincia');
        
        // Actualizar provincia cuando se carga la página
        if (localidadSelect.value) {
            const selectedOption = localidadSelect.options[localidadSelect.selectedIndex];
            const provinciaId = selectedOption.getAttribute('data-provincia');
            if (provinciaId) {
                for (let i = 0; i < provinciaSelect.options.length; i++) {
                    if (provinciaSelect.options[i].value === provinciaId) {
                        provinciaSelect.options[i].selected = true;
                        break;
                    }
                }
            }
        }
        
        // Actualizar provincia cuando cambia la localidad
        localidadSelect.addEventListener('change', function() {
            if (this.value) {
                const selectedOption = this.options[this.selectedIndex];
                const provinciaId = selectedOption.getAttribute('data-provincia');
                if (provinciaId) {
                    for (let i = 0; i < provinciaSelect.options.length; i++) {
                        if (provinciaSelect.options[i].value === provinciaId) {
                            provinciaSelect.options[i].selected = true;
                            break;
                        }
                    }
                }
            } else {
                provinciaSelect.selectedIndex = 0;
            }
        });
        
        // Ocultar alertas de error después de 5 segundos
        const errorAlert = document.getElementById('errorAlert');
        if (errorAlert) {
            setTimeout(function() {
                errorAlert.style.opacity = '0';
                setTimeout(function() {
                    errorAlert.style.display = 'none';
                }, 500);
            }, 5000);
        }
    });
    </script>
</body>
</html>