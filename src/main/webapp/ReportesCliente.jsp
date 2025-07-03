<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List,
    java.math.BigDecimal
    ,java.text.SimpleDateFormat,
    dominio.Reporte,
    dominio.Cliente,
    dominio.Usuario"%>
<%
	Cliente clienteLogueado = null;
	
	if (session != null && session.getAttribute("clienteLogueado") != null) {
		clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
	}
    Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");

    // Obtener parámetros
    String start = request.getParameter("startDate");
    String end   = request.getParameter("endDate");

    BigDecimal totalIngresos = (BigDecimal) request.getAttribute("totalIngresos");
    BigDecimal totalEgresos  = (BigDecimal) request.getAttribute("totalEgresos");
    List<Reporte> movimientos = (List<Reporte>) request.getAttribute("movimientos");

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Reportes</title>
	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
	<link rel="stylesheet" href="CSS/common.css" type="text/css" />
	<link rel="stylesheet" href="CSS/reportes.css" />
</head>
<body>
<jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarCliente.jsp" />
       
           <div class="main-container-reportes">
    <!-- Panel de Parámetros -->
    <div class="card-reportes">
        <div class="card-header-reportes">
            <h2 class="card-title-reportes">
                <i class="fas fa-chart-bar"></i> Parámetros del Reporte
            </h2>
        </div>
        <div class="card-content-reportes">
            <form method="post" action="ServletReporte">
                <div class="form-group-reportes">
                    <label for="startDate">Fecha de Inicio</label>
                    <input type="date" id="startDate" name="startDate" required value="<%= start != null ? start : "" %>" />
                </div>
                <div class="form-group-reportes">
                    <label for="endDate">Fecha de Fin</label>
                    <input type="date" id="endDate" name="endDate" required value="<%= end != null ? end : "" %>" />
                </div>
                <div class="form-group-reportes">
                    <button type="submit" class="btn-reportes btn-primary-reportes">Generar Reporte</button>
                </div>
            </form>
        </div>
    </div>

    <% if (totalIngresos != null) { %>
    <!-- Resumen Ejecutivo -->
    <div class="stats-grid-reportes">
        <div class="stat-card-reportes stat-income-reportes">
            <span class="stat-title-reportes">Total Ingresos</span>
            <div class="stat-value-reportes"><%= totalIngresos %></div>
        </div>
        <div class="stat-card-reportes stat-expense-reportes">
            <span class="stat-title-reportes">Total Egresos</span>
            <div class="stat-value-reportes"><%= totalEgresos %></div>
        </div>
        <div class="stat-card-reportes stat-balance-reportes">
            <span class="stat-title-reportes">Balance Neto</span>
            <div class="stat-value-reportes"><%= totalIngresos.subtract(totalEgresos) %></div>
        </div>
    </div>

    <!-- Detalle de Transacciones -->
    <div class="card-reportes">
        <div class="card-header-reportes">
            <h2 class="card-title-reportes">Detalle de Transacciones</h2>
        </div>
        <div class="card-content-reportes">
            
        </div>
    </div>
    <% } %>

</div>
    </div>
    
     <!-- Declaracion del script del Sidebar -->
    <script src="JS/script.js"></script>
    <script src="js/common.js"></script>

</body>
</html>