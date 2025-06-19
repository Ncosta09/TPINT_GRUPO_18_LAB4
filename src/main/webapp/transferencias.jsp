<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Transferencias</title>
    <link rel="stylesheet" href="css/common.css" type="text/css" />
    <link rel="stylesheet" href="css/transferencias.css" type="text/css" />
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
                
                <form id="transferForm" action="transferencia" method="post">
                    <div class="form-group">
                        <label for="cuentaOrigen">Cuenta Origen:</label>
                        <select id="cuentaOrigen" name="cuentaOrigen" required>
                            <option value="">Seleccione una cuenta</option>
                            <option value="1234567890">1234567890 - Cuenta Corriente ($25,430.50)</option>
                            <option value="0987654321">0987654321 - Caja de Ahorro ($19,800.00)</option>
                        </select>
                        <div class="error-message" id="cuentaOrigen-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="tipoTransferencia">Tipo de Transferencia:</label>
                        <select id="tipoTransferencia" name="tipoTransferencia" required>
                            <option value="">Seleccione el tipo</option>
                            <option value="misma_entidad">Misma Entidad</option>
                            <option value="otra_entidad">Otra Entidad</option>
                            <option value="cbu">Por CBU</option>
                        </select>
                        <div class="error-message" id="tipoTransferencia-error"></div>
                    </div>
                    
                    <div class="form-group" id="destinatarioGroup">
                        <label for="destinatario">Destinatario:</label>
                        <input type="text" id="destinatario" name="destinatario" placeholder="Nombre del destinatario">
                        <div class="error-message" id="destinatario-error"></div>
                    </div>
                    
                    <div class="form-group" id="cuentaDestinoGroup">
                        <label for="cuentaDestino">Cuenta Destino:</label>
                        <input type="text" id="cuentaDestino" name="cuentaDestino" placeholder="Número de cuenta o CBU">
                        <div class="error-message" id="cuentaDestino-error"></div>
                    </div>
                    
                    <div class="form-group" id="cbuGroup" style="display: none;">
                        <label for="cbu">CBU:</label>
                        <input type="text" id="cbu" name="cbu" placeholder="22 dígitos" maxlength="22">
                        <div class="error-message" id="cbu-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="monto">Monto:</label>
                        <input type="number" id="monto" name="monto" placeholder="0.00" step="0.01" min="0" required>
                        <div class="error-message" id="monto-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="concepto">Concepto:</label>
                        <input type="text" id="concepto" name="concepto" placeholder="Descripción de la transferencia" required>
                        <div class="error-message" id="concepto-error"></div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='homeCliente.jsp'">Cancelar</button>
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
