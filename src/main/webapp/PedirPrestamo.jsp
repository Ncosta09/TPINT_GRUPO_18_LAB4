<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dominio.Usuario"%>
<%@ page import="dominio.Cuenta"%>
<%@ page import="java.util.List"%>

<%
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
<title>Sistema de Gestión Bancaria - Solicitar Préstamo</title>
<link rel="stylesheet" href="CSS/common.css" type="text/css" />
<link rel="stylesheet" href="CSS/pedirPrestamo.css" type="text/css" />
</head>
<body>
	
<jsp:include page="/header.jsp" />

	<div class="container">
<jsp:include page="/sidebarCliente.jsp" />

		<div class="overlay" id="overlay"></div>

		<div class="content">
			<div class="loan-container">
				<h2 class="loan-title">
					<span class="loan-icon">📋</span> Solicitar Préstamo
				</h2>

				<div class="loan-info">
					<h4>Información del Préstamo</h4>
					<p>
						<strong>Tasa de interés:</strong> 15% anual
					</p>
					<p>
						<strong>Plazo máximo:</strong> 60 meses
					</p>
					<p>
						<strong>Monto mínimo:</strong> $10,000
					</p>
					<p>
						<strong>Monto máximo:</strong> $500,000
					</p>
				</div>
				
				<% if (request.getAttribute("mensaje") != null) { %>
    <div class="alert-success" id="mensajeExito">
        <%= request.getAttribute("mensaje") %>
        <span style="float:right; cursor:pointer;" onclick="this.parentElement.style.display='none';">&times;</span>
    </div>
<% } %>


				<form id="loanForm" action="ServletPedirPrestamo" method="post">
					<div class="form-group">
						<label for="monto">Monto del Préstamo:</label> <input
							type="number" id="monto" name="monto" placeholder="0.00"
							step="0.01" min="10000" max="500000" required>
						<div class="error-message" id="monto-error"></div>
					</div>

					<div class="form-group">
						<label for="plazo">Plazo (meses):</label> <select id="plazo"
							name="plazo" required>
							<option value="">Seleccione el plazo</option>
							<option value="12">12 meses</option>
							<option value="24">24 meses</option>
							<option value="36">36 meses</option>
							<option value="48">48 meses</option>
							<option value="60">60 meses</option>
						</select>
						<div class="error-message" id="plazo-error"></div>
					</div>


					<div class="form-group">
						<label for="destino">Destino del Préstamo:</label> <input
							type="text" id="destino" name="destino"
							placeholder="Descripción del destino del préstamo" required>
						<div class="error-message" id="destino-error"></div>
					</div>

					<div class="form-group">
						<label for="cuentaDestino">Cuenta de Destino:</label> 
						
						<select id="cuentaDestino" name="cuentaDestino" required>
							<option value="">Seleccione una cuenta</option>
							<%
							List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasCliente");
							if (cuentas != null) {
								for (Cuenta cuenta : cuentas) {
							%>
							<option value="<%=cuenta.getId()%>">
								<%=cuenta.getNumeroCuenta()%> -
								<%=cuenta.getTipoCuenta().getDescripcion()%>
							</option>
							<%
							}
							}
							%>
						</select>
						
						<div class="error-message" id="cuentaDestino-error"></div>
					</div>

					<div class="loan-summary" id="loanSummary" style="display: none;">
						<h4>Resumen del Préstamo</h4>
						<div class="summary-item">
							<span>Monto solicitado:</span> <span id="summaryMonto">$0.00</span>
						</div>
						<div class="summary-item">
							<span>Plazo:</span> <span id="summaryPlazo">0 meses</span>
						</div>
						<div class="summary-item">
							<span>Tasa de interés:</span> <span>15% anual</span>
						</div>
						<div class="summary-item">
							<span>Cuota mensual:</span> <span id="summaryCuota">$0.00</span>
						</div>
						<div class="summary-item total">
							<span>Total a pagar:</span> <span id="summaryTotal">$0.00</span>
						</div>
					</div>

					<div class="form-actions">
						<button type="button" class="btn btn-secondary"
							onclick="window.location.href='ServletHomeCliente'">Cancelar</button>
						<button type="submit" class="btn" name="btnSolicitarPrestamo">Solicitar
							Préstamo</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="notification" id="notification"></div>

	<script src="js/common.js"></script>
	<script src="js/pedirPrestamo.js"></script>
	
	<script>
    setTimeout(() => {
        const mensaje = document.getElementById('mensajeExito');
        if (mensaje) {
            mensaje.style.transition = 'opacity 0.5s ease';
            mensaje.style.opacity = '0';
            setTimeout(() => mensaje.style.display = 'none', 500);
        }
    }, 5000);
</script>
	

</body>
</html>
