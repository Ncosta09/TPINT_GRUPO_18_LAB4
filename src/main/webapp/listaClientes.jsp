<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dominio.Cliente" %>
<%@ page import="dominio.Usuario" %>
<%
    
    if (session == null || session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
%>
<%
    List<Cliente> lista = (List<Cliente>) request.getAttribute("listaClientes");
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Panel Administrador</title>
    <link rel="stylesheet" href="CSS/listaClientes.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="table-container">
                <div class="table-header">
                    <h2 class="table-title">Lista de Clientes</h2>
                    <div class="search-container">
                        <input type="text" class="search-input" id="searchInput" placeholder="Buscar cliente...">
                        <button class="btn" onclick="searchClients()">Buscar</button>
                    </div>
                </div>

                <!-- Mensaje de éxito o error -->
                <%
                    String mensaje = request.getParameter("mensaje");
                    if (mensaje != null) {
                %>
                    <div style="margin: 10px 0; color: green;"><strong><%= mensaje %></strong></div>
                <%
                    }
                %>
                
                <div class="filter-container">
                    <select class="filter-select" id="statusFilter">
                        <option value="">Todos los estados</option>
                        <option value="activo">Activo</option>
                        <option value="inactivo">Inactivo</option>
                        <option value="suspendido">Suspendido</option>
                    </select>
                    
                    <select class="filter-select" id="typeFilter">
                        <option value="">Todos los tipos</option>
                        <option value="cliente">Cliente</option>
                        <option value="admin">Administrador</option>
                    </select>
                    
                    <button class="btn" onclick="filterClients()">Filtrar</button>
                    <button class="btn btn-secondary" onclick="clearFilters()">Limpiar</button>
                </div>
                
                <table id="clientsTable">
                    <thead>
                        <tr>
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Teléfono</th>
                            <th>Estado</th>
                            <th>Tipo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="clientsTableBody">
                        <%
                            for (Cliente c : lista) {
                        %>
                        <tr>
                            <td><%= c.getDni() %></td>
                            <td><%= c.getNombre() + " " + c.getApellido() %></td>
                            <td><%= c.getEmail() %></td>
                            <td><%= c.getTelefono() %></td>
                            <td>
                                <span class="badge <%= (c.getUsuario().getEstado() == 1) ? "badge-active" : "badge-inactive" %>">
                                    <%= (c.getUsuario().getEstado() == 1) ? "Activo" : "Inactivo" %>
                                </span>
                            </td>
                            <td><%= (c.getUsuario().getTipoUsuario() == 1) ? "Administrador" : "Cliente" %></td>
                            <td class="actions">
                                <button class="btn btn-sm" onclick="viewClient('<%= c.getDni() %>')">Ver</button>
                                <button class="btn btn-sm btn-secondary" onclick="window.location.href='ServletListaClientes?accion=editar&idCliente=<%= c.getIdCliente() %>'">Editar</button>
                                
                                <% if (c.getUsuario().getEstado() == 1) { %>
                                    <form action="ServletBajaCliente" method="post" style="display:inline;" onsubmit="return confirm('¿Estás seguro que querés dar de baja este cliente?');">
                                        <input type="hidden" name="idUsuario" value="<%= c.getUsuario().getIdUsuario() %>">
                                        <button type="submit" class="btn btn-sm btn-danger">Dar de baja</button>
                                    </form>
                                <% } else { %>
                                    <button class="btn btn-sm btn-disabled" disabled>Inactivo</button>
                                <% } %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
=======
    
    <script src="js/common.js"></script>
    <script src="js/listaClientes.js"></script>
</body>
</html>