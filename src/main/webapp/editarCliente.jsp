<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cliente" %>

<%
    Cliente c = (Cliente) request.getAttribute("cliente");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Cliente</title>
    <link rel="stylesheet" href="CSS/form.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        <div class="content">
            <div class="form-container">
                <h2>Modificar Cliente</h2>

                <form action="ServletListaClientes" method="post">
                    <input type="hidden" name="accion" value="modificar" />
                    <input type="hidden" name="idCliente" value="<%= c.getIdCliente() %>" />

                    <label for="dni">DNI:</label>
                    <input type="text" id="dni" name="dni" value="<%= c.getDni() %>" required pattern="\\d{7,8}" title="Ingrese un DNI válido (7-8 dígitos)">

                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= c.getNombre() %>" required pattern="[A-Za-z\s]{2,}" title="Solo letras, mínimo 2 caracteres">

                    <label for="apellido">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" value="<%= c.getApellido() %>" required pattern="[A-Za-z\s]{2,}" title="Solo letras, mínimo 2 caracteres">

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="<%= c.getEmail() %>" required>

                    <label for="telefono">Teléfono:</label>
                    <input type="text" id="telefono" name="telefono" value="<%= c.getTelefono() %>" required pattern="\\d{7,15}" title="Solo números, mínimo 7 dígitos">

                    <button type="submit" class="btn">Guardar Cambios</button>
                    <a href="ServletListaClientes" class="btn btn-secondary">Cancelar</a>
                </form>

                <% if (request.getAttribute("mensaje") != null) { %>
                    <p class="mensaje"><%= request.getAttribute("mensaje") %></p>
                <% } %>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
</body>
</html>
