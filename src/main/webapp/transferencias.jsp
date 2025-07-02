<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dominio.Cuenta" %>
<%@ page import="dominio.Usuario" %>
<%@ page import="dominio.Cliente" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Transferencias</title>
    <link rel="stylesheet" href="CSS/common.css" type="text/css" />
    <link rel="stylesheet" href="CSS/transferencias.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarCliente.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="transfer-container">
                <h2 class="transfer-title">
                    <span class="transfer-icon">💸</span>
                    Nueva Transferencia
                </h2>
                
                <!-- Mensajes de error y éxito -->
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-error">
                        <%= request.getAttribute("error") %>
                    </div>
                <% } %>
                
                <% if (request.getAttribute("mensaje") != null) { %>
                    <div class="alert alert-success">
                        <%= request.getAttribute("mensaje") %>
                    </div>
                <% } %>
                
                <form id="transferForm" action="ServletTransferencia" method="post">
                    <div class="form-group">
                        <label for="cuentaOrigen">Cuenta Origen:</label>
                        <select id="cuentaOrigen" name="cuentaOrigen" required>
                            <option value="">Seleccione una cuenta</option>
                            <% 
                            List<Cuenta> cuentasCliente = (List<Cuenta>) request.getAttribute("cuentasCliente");
                            if (cuentasCliente != null) {
                                for (Cuenta cuenta : cuentasCliente) {
                                    if (cuenta.isEstado()) {
                            %>
                                <option value="<%= cuenta.getId() %>">
                                    <%= cuenta.getNumeroCuenta() %> - <%= cuenta.getTipoCuenta().getDescripcion() %> ($<%= String.format("%,.2f", cuenta.getSaldo()) %>)
                                </option>
                            <%
                                    }
                                }
                            }
                            %>
                        </select>
                        <div class="error-message" id="cuentaOrigen-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="cuentaDestino">Cuenta Destino (Número de cuenta o CBU):</label>
                        <input type="text" id="cuentaDestino" name="cuentaDestino" placeholder="Ingrese número de cuenta o CBU" required>
                        <div class="error-message" id="cuentaDestino-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="monto">Monto:</label>
                        <input type="number" id="monto" name="monto" placeholder="0.00" step="0.01" min="0" required>
                        <div class="error-message" id="monto-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="concepto">Concepto:</label>
                        <input type="text" id="concepto" name="concepto" placeholder="Descripción de la transferencia">
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='ServletHomeCliente'">Cancelar</button>
                        <button type="submit" class="btn">Realizar Transferencia</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="notification" id="notification"></div>

    <script src="js/common.js"></script>
    <script src="js/transferencias.js"></script>
</body>
</html>
