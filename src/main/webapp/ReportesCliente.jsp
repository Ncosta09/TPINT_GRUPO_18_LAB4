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
	
	<!-- DataTables -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<!-- DataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
	<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
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
                    <i class="fas fa-chart-bar"></i>
                    Parámetros del Reporte
                </h2>
                <p class="card-description-reportes">
                    Selecciona los parámetros para generar tu reporte financiero personalizado
                </p>
            </div>
            <div class="card-content-reportes">
                <form class="form-grid-reportes" method="post" action="ServletReporte">
                    <div class="form-group-reportes">
                        <label for="startDate" class="form-label-reportes">Fecha de Inicio</label>
                        <input type="date" id="startDate" name="startDate" required value="<%= start != null ? start : "" %>" class="form-input-reportes" required>
                    </div>

                    <div class="form-group-reportes">
                        <label for="endDate" class="form-label-reportes">Fecha de Fin</label>
                        <input type="date" id="endDate" name="endDate" required value="<%= end != null ? end : "" %>" class="form-input-reportes" required>
                    </div>

                    <div class="form-group-reportes">
                        <button type="submit" id="generateBtn" class="btn-reportes btn-primary-reportes">
                            <span id="btnText">Generar Reporte</span>
                            <i id="loadingIcon" class="fas fa-spinner fa-spin" style="display: none;"></i>
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Empty State 
        <div id="emptyState" class="card-reportes">
            <div class="card-content-reportes empty-content-reportes">
                <i class="fas fa-chart-bar empty-icon-reportes"></i>
                <h3>Generar Reporte</h3>
                <p>Selecciona los parámetros y haz clic en "Generar Reporte" para ver los resultados</p>
            </div>
        </div>-->

        <!-- Resultados del Reporte -->
        <div id="reportResults" >
            <!-- Resumen Ejecutivo -->
                <% if (totalIngresos != null) { %>
            <div class="stats-grid-reportes">
                <div class="stat-card-reportes stat-income-reportes">
                    <div class="stat-header-reportes">
                        <span class="stat-title-reportes">Total Ingresos</span>
                        <i class="fas fa-trending-up stat-icon-reportes"></i>
                    </div>
                    <div class="stat-content-reportes">
                        <div id="totalIngresos" class="stat-value-reportes">$<%= totalIngresos %></div>
                    </div>
                </div>

                <div class="stat-card-reportes stat-expense-reportes">
                    <div class="stat-header-reportes">
                        <span class="stat-title-reportes">Total Egresos</span>
                        <i class="fas fa-trending-down stat-icon-reportes"></i>
                    </div>
                    <div class="stat-content-reportes">
                        <div id="totalEgresos" class="stat-value-reportes">$<%= totalEgresos %></div>
                    </div>
                </div>

                <div class="stat-card-reportes stat-balance-reportes">
                    <div class="stat-header-reportes">
                        <span class="stat-title-reportes">Balance</span>
                        <i class="fas fa-dollar-sign stat-icon-reportes"></i>
                    </div>
                    <div class="stat-content-reportes">
                        <div id="balanceNeto" class="stat-value-reportes">$<%= totalIngresos.subtract(totalEgresos) %></div>
                        <p class="stat-period-reportes">Diferencia I/E</p>
                    </div>
                </div>
            </div>

            <!-- Detalle de Transacciones -->
            <div class="card-reportes">
                <div class="card-header-reportes">
                    <h2 class="card-title-reportes">Detalle de Transacciones</h2>
                    <p class="card-description-reportes">Listado completo de operaciones en el período seleccionado</p>
                </div>
                <div class="card-content-reportes">
                    <div class="table-container-reportes">
                        <table class="data-table-reportes" id="reportesTable">
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Tipo</th>
                                    <th>Categoría</th>
                                    <th>Descripción</th>
                                    <th class="text-right-reportes">Monto</th>
                                </tr>
                            </thead>
                            <tbody id="transactionsTableBody">
                      			<% for (Reporte item : movimientos) { %>
			                    <tr>
			                        <td><%= sdf.format(item.getFecha()) %></td>
			                        <td><%= item.getTipo() %></td>
			                        <td><%= item.getCategoria() %></td>
			                        <td><%= item.getDescripcion() %></td>
			                        <td class="text-right-reportes"><%= item.getMonto() %></td>
			                    </tr>
                    <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
           <% } %>

            <!-- Análisis por Categorías 
            <div class="card-reportes">
                <div class="card-header-reportes">
                    <h2 class="card-title-reportes">Análisis por Categorías</h2>
                    <p class="card-description-reportes">Resumen de movimientos agrupados por categoría</p>
                </div>
                <div class="card-content-reportes">
                    <div id="categoriesAnalysis" class="categories-grid-reportes">
                    </div>
                </div>
            </div>-->
        </div>
    </div>
        
    </div>
    
        <!-- DataTables -->
    <script type="text/javascript">
    $(document).ready(function() {
    	  $('#reportesTable').DataTable({
    	    paging: true,
    	    pageLength: 10,
    	    lengthMenu: [5, 10, 15, 20],
    	    searching: true
    	  });
    	});

    </script>
    
     <!-- Declaracion del script del Sidebar -->
    <script src="JS/script.js"></script>
    <script src="js/common.js"></script>

</body>
</html>