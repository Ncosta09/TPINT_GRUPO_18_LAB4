<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dominio.Prestamo"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sistema de Gestión Bancaria - Panel Administrador</title>
<link rel="stylesheet" href="CSS/prestamosAdmin.css" type="text/css" />
<!-- DataTables -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- DataTables -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
<script
	src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>


</head>
<body>
	<jsp:include page="/header.jsp" />

	<div class="container">
		<jsp:include page="/sidebarAdmin.jsp" />

		<div class="overlay" id="overlay"></div>

		<div class="content">
			<div class="table-container">
				<div class="table-header">
					<%
					if (request.getAttribute("mensajeEstado") != null) {
					%>
					<div class="alert-box" id="alertBox">
						<%=request.getAttribute("mensajeEstado")%>
					</div>
					<%
					}
					%>
					<h2 class="table-title">Gestión de Préstamos</h2>
					<div class="search-container">
						<!--   <input type="text" class="search-input" id="searchInput" placeholder="Buscar préstamo...">
                        <button class="btn" onclick="searchLoans()">Buscar</button> -->
					</div>
				</div>

				<div class="filter-container">
					<select class="filter-select" id="statusFilter">
						<option value="">Todos los estados</option>
						<option value="pendiente">Pendiente</option>
						<option value="aprobado">Aprobado</option>
						<option value="rechazado">Rechazado</option>
					</select>
				</div>





				<table id="loansTable">
					<thead>
						<tr>
							<th>ID</th>
							<th>Cliente</th>
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
							<td>PR<%=String.format("%03d", p.getIdPrestamo())%></td>
							<td><%=p.getCliente() != null ? p.getCliente().getNombre() + " " + p.getCliente().getApellido() : "Cliente " + p.getIdCliente()%></td>
							<td>$<%=String.format("%,.2f", p.getImportePedido())%></td>
							<td><%=p.getPlazoMeses()%> meses</td>
							<td>
								<%
								String estado = p.getEstado();
								String badgeClass = "badge ";
								if ("pendiente".equalsIgnoreCase(estado))
									badgeClass += "badge-pending";
								else if ("aprobado".equalsIgnoreCase(estado))
									badgeClass += "badge-approved";
								else if ("rechazado".equalsIgnoreCase(estado))
									badgeClass += "badge-rejected";
								else if ("activo".equalsIgnoreCase(estado))
									badgeClass += "badge-active";
								else
									badgeClass += "badge-default";
								%> <span class="<%=badgeClass%>"><%=estado%></span>
							</td>
							<td><%=sdf.format(p.getFechaAlta())%></td>
							<td class="actions">
								<form method="post" action="ServletGestionPrestamos">
									<input type="hidden" name="idPrestamo"
										value="<%=p.getIdPrestamo()%>" />
									<button type="button" class="btn btn-sm btn-info" onclick="verPrestamo(this, <%= p.getIdPrestamo() %>)">Ver</button>
									<%
									if ("pendiente".equalsIgnoreCase(estado)) {
									%>
									<button type="submit" class="btn btn-sm btn-success"
										name="btnAprobar" value="aprobar">Aprobar</button>
									<button type="submit" class="btn btn-sm btn-danger"
										name="btnRechazar" value="rechazar">Rechazar</button>
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


	<script>
		$(document)
				.ready(
						function() {
							const tabla = $('#loansTable')
									.DataTable(
											{
												language : {
													url : '//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json'
												}
											});

							$('#statusFilter')
									.on(
											'change',
											function() {
												const valorSeleccionado = $(
														this).val();
												tabla.column(4).search(
														valorSeleccionado)
														.draw();
											});
						});
	</script>







<script>
    setTimeout(function() {
        var alertBox = document.getElementById('alertBox');
        if (alertBox) {
            alertBox.style.display = 'none';
        }
    }, 5000); 
</script>




<div id="modalPrestamo" class="modal" style="display:none;">
  <div class="modal-content">
    <div class="modal-header">
      <h3>Detalle del Préstamo</h3>
    </div>
    <div class="modal-body">
      <div class="detail-row">
        <span class="detail-label">ID:</span>
        <span class="detail-value highlight" id="detalleId"></span>
      </div>
      <div class="detail-row">
        <span class="detail-label">Cliente:</span>
        <span class="detail-value" id="detalleCliente"></span>
      </div>
      <div class="detail-row">
        <span class="detail-label">Importe:</span>
        <span class="detail-value highlight">$<span id="detalleImporte"></span></span>
      </div>
      <div class="detail-row">
        <span class="detail-label">Plazo:</span>
        <span class="detail-value"><span id="detallePlazo"></span> meses</span>
      </div>
      <div class="detail-row">
        <span class="detail-label">Estado:</span>
        <span class="detail-value" id="detalleEstado"></span>
      </div>
      <div class="detail-row">
        <span class="detail-label">Fecha Alta:</span>
        <span class="detail-value" id="detalleFecha"></span>
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn-close-modal" onclick="cerrarModal()">Cerrar</button>
    </div>
  </div>
</div>

</body>
</html>
