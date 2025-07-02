<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dominio.Cuenta, java.util.ArrayList" %>
<%@ page import="dominio.Usuario" %>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);

    if (session == null || session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Lista de Cuentas</title>
    <link rel="stylesheet" href="CSS/listaCuentas.css" type="text/css" />
    
    <!-- DataTables -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- DataTables -->
<link rel="stylesheet"
      href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
</head>
<body>
<jsp:include page="/header.jsp" />

<div class="container">
    <jsp:include page="/sidebarAdmin.jsp" />
    <div class="overlay" id="overlay"></div>

    <div class="content">
        <div class="table-container">
            <div class="table-header">
                <h2 class="table-title">🏦 Lista de Cuentas Bancarias</h2>
                <!-- <div class="search-container">
                    <input type="text" class="search-input" id="searchInput" placeholder="Buscar por cliente...">
                    <button class="btn" id="searchBtn">Buscar</button>
                </div> -->
            </div>

            <%
                String mensaje = request.getParameter("mensaje");
                if (mensaje != null) {
            %>
                <div style="margin: 10px 0; color: green;"><strong><%= mensaje %></strong></div>
            <%
                }
            %>

            <!-- <div class="filter-container">
                <select class="filter-select" id="tipoFilter">
                    <option value="">Todos los tipos</option>
                    <option value="caja de ahorro">Caja de Ahorro</option>
                    <option value="cuenta corriente">Cuenta Corriente</option>
                </select>

                <select class="filter-select" id="estadoFilter">
                    <option value="">Todos los estados</option>
                    <option value="activa">Activa</option>
                    <option value="inactiva">Inactiva</option>
                </select>

                <button class="btn" id="filterBtn">Filtrar</button>
                <button class="btn btn-secondary" onclick="clearFilters()">Limpiar</button>
                <button class="btn btn-success" onclick="window.location.href='ServletCuenta?accion=formAlta'">+ Nueva Cuenta</button>
            </div> -->

            <table id="cuentasTable">
                <thead>
                    <tr>
                        <th>Cliente</th>
                        <th>Número de Cuenta</th>
                        <th>CBU</th>
                        <th>Tipo de Cuenta</th>
                        <th>Saldo</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody id="cuentasTableBody">
<%
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
    if (cuentas == null) cuentas = new ArrayList<>();

    for (Cuenta c : cuentas) {
%>
                <tr data-cliente="<%= c.getCliente().getNombre() + " " + c.getCliente().getApellido() %>"
                    data-tipo="<%= c.getTipoCuenta().getDescripcion().trim().toLowerCase() %>"
                    data-estado="<%= c.isEstado() ? "activa" : "inactiva" %>">
                    
                    <td><%= c.getCliente().getNombre() + ", " + c.getCliente().getApellido() %></td>
                    <td><%= c.getNumeroCuenta() %></td>
                    <td><%= c.getCbu() %></td>
                    <td><%= c.getTipoCuenta().getDescripcion() %></td>
                    <td>$<%= String.format("%.2f", c.getSaldo()) %></td>
                    <td>
                        <span class="badge <%= c.isEstado() ? "badge-active" : "badge-inactive" %>">
                            <%= c.isEstado() ? "Activa" : "Inactiva" %>
                        </span>
                    </td>
                    <td class="actions">
                        <button class="btn btn-sm" onclick="viewAccount('<%= c.getId() %>')">Ver</button>
                        <button class="btn btn-sm btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/ServletModificarCuenta?idCuenta=<%= c.getId() %>'">Editar</button>
                        
                        <% if (c.isEstado()) { %>
                            <form action="ServletBajaCuenta" method="post" style="display:inline;" onsubmit="return confirm('¿Estás seguro que querés dar de baja esta cuenta?');">
                                <input type="hidden" name="idCuenta" value="<%= c.getId() %>">
                                <button type="submit" class="btn btn-sm btn-danger">Dar de baja</button>
                            </form>
                        <% } else { %>
                            <button class="btn btn-sm btn-disabled" disabled>Inactiva</button>
                        <% } %>
                    </td>
                </tr>
<%
    }
%>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="notification" id="notification"></div>

    <script type="text/javascript">
    $(document).ready(function() {
    	  $('#cuentasTable').DataTable({
    	    paging: true,
    	    pageLength: 10,
    	    lengthMenu: [5, 10, 15, 20],
    	    searching: true
    	  });
    	});

    </script>

<script src="js/common.js"></script>
<script src="js/listaCuentas.js"></script>
</body>
</html>