<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dominio.Prestamo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Panel Administrador</title>
	<link rel="stylesheet" href="CSS/prestamosAdmin.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="table-container">
                <div class="table-header">
                    <h2 class="table-title">Gestión de Préstamos</h2>
                    <div class="search-container">
                        <input type="text" class="search-input" id="searchInput" placeholder="Buscar préstamo...">
                        <button class="btn" onclick="searchLoans()">Buscar</button>
                    </div>
                </div>
                
                <div class="filter-container">
                    <select class="filter-select" id="statusFilter">
                        <option value="">Todos los estados</option>
                        <option value="pendiente">Pendiente</option>
                        <option value="aprobado">Aprobado</option>
                        <option value="rechazado">Rechazado</option>
                        <option value="activo">Activo</option>
                        <option value="finalizado">Finalizado</option>
                    </select>
                    
                    <select class="filter-select" id="typeFilter">
                        <option value="">Todos los tipos</option>
                        <option value="personal">Personal</option>
                        <option value="hipotecario">Hipotecario</option>
                        <option value="automotriz">Automotriz</option>
                        <option value="emprendimiento">Emprendimiento</option>
                    </select>
                    
                    <button class="btn" onclick="filterLoans()">Filtrar</button>
                    <button class="btn btn-secondary" onclick="clearFilters()">Limpiar</button>
                </div>
                
                <table id="loansTable">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Cliente</th>
                            <th>Tipo</th>
                            <th>Monto</th>
                            <th>Plazo</th>
                            <th>Estado</th>
                            <th>Fecha Solicitud</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="loansTableBody">
<%
	List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamosPendientes");
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	if (prestamos != null && !prestamos.isEmpty()) {
		for (Prestamo p : prestamos) {
%>
	<tr>
		<td>PR<%= String.format("%03d", p.getIdPrestamo()) %></td>
		<td><%= p.getIdCliente() %></td> <!-- Idealmente deberías mostrar el nombre del cliente -->
		<td>Tipo no definido</td> <!-- Si tenés un campo tipo, reemplazá esto por p.getTipo() -->
		<td>$<%= String.format("%,.2f", p.getImportePedido()) %></td>
		<td><%= p.getPlazoMeses() %> meses</td>
		<td>
			<%
				String estado = p.getEstado();
				String badgeClass = "badge ";
				if ("pendiente".equalsIgnoreCase(estado)) badgeClass += "badge-pending";
				else if ("aprobado".equalsIgnoreCase(estado)) badgeClass += "badge-approved";
				else if ("rechazado".equalsIgnoreCase(estado)) badgeClass += "badge-rejected";
				else if ("activo".equalsIgnoreCase(estado)) badgeClass += "badge-active";
				else badgeClass += "badge-default";
			%>
			<span class="<%= badgeClass %>"><%= estado %></span>
		</td>
		<td><%= sdf.format(p.getFechaAlta()) %></td>
		<td class="actions">
			<form method="post" action="ServletAutorizarPrestamo">
				<input type="hidden" name="idPrestamo" value="<%= p.getIdPrestamo() %>" />
				<button class="btn btn-sm" name="accion" value="ver">Ver</button>
				<%
					if ("pendiente".equalsIgnoreCase(estado)) {
				%>
					<button class="btn btn-sm btn-success" name="accion" value="aprobar">Aprobar</button>
					<button class="btn btn-sm btn-danger" name="accion" value="rechazar">Rechazar</button>
				<%
					} else {
				%>
					<button class="btn btn-sm btn-secondary" name="accion" value="editar">Editar</button>
				<%
					}
				%>
			</form>
		</td>
	</tr>
<%
		}
	} else {
%>
	<tr>
		<td colspan="8">No hay préstamos pendientes.</td>
	</tr>
<%
	}
%>


</tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
    <script src="js/prestamosAdmin.js"></script>
</body>
</html> 