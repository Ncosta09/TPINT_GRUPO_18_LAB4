<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <span class="loan-icon">📋</span>
                    Solicitar Préstamo
                </h2>
                
                <div class="loan-info">
                    <h4>Información del Préstamo</h4>
                    <p><strong>Tasa de interés:</strong> 15% anual</p>
                    <p><strong>Plazo máximo:</strong> 60 meses</p>
                    <p><strong>Monto mínimo:</strong> $10,000</p>
                    <p><strong>Monto máximo:</strong> $500,000</p>
                </div>
                
                <form id="loanForm" action="solicitarPrestamo" method="post">
                    <div class="form-group">
                        <label for="monto">Monto del Préstamo:</label>
                        <input type="number" id="monto" name="monto" placeholder="0.00" step="0.01" min="10000" max="500000" required>
                        <div class="error-message" id="monto-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="plazo">Plazo (meses):</label>
                        <select id="plazo" name="plazo" required>
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
                        <label for="tipoPrestamo">Tipo de Préstamo:</label>
                        <select id="tipoPrestamo" name="tipoPrestamo" required>
                            <option value="">Seleccione el tipo</option>
                            <option value="personal">Personal</option>
                            <option value="hipotecario">Hipotecario</option>
                            <option value="automotriz">Automotriz</option>
                            <option value="emprendimiento">Emprendimiento</option>
                        </select>
                        <div class="error-message" id="tipoPrestamo-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="destino">Destino del Préstamo:</label>
                        <input type="text" id="destino" name="destino" placeholder="Descripción del destino del préstamo" required>
                        <div class="error-message" id="destino-error"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="cuentaDestino">Cuenta de Destino:</label>
                        <select id="cuentaDestino" name="cuentaDestino" required>
                            <option value="">Seleccione una cuenta</option>
                            <option value="1234567890">1234567890 - Cuenta Corriente</option>
                            <option value="0987654321">0987654321 - Caja de Ahorro</option>
                        </select>
                        <div class="error-message" id="cuentaDestino-error"></div>
                    </div>
                    
                    <div class="loan-summary" id="loanSummary" style="display: none;">
                        <h4>Resumen del Préstamo</h4>
                        <div class="summary-item">
                            <span>Monto solicitado:</span>
                            <span id="summaryMonto">$0.00</span>
                        </div>
                        <div class="summary-item">
                            <span>Plazo:</span>
                            <span id="summaryPlazo">0 meses</span>
                        </div>
                        <div class="summary-item">
                            <span>Tasa de interés:</span>
                            <span>15% anual</span>
                        </div>
                        <div class="summary-item">
                            <span>Cuota mensual:</span>
                            <span id="summaryCuota">$0.00</span>
                        </div>
                        <div class="summary-item total">
                            <span>Total a pagar:</span>
                            <span id="summaryTotal">$0.00</span>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='homeCliente.jsp'">Cancelar</button>
                        <button type="submit" class="btn">Solicitar Préstamo</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="notification" id="notification"></div>

    <script src="js/common.js"></script>
    <script src="js/pedirPrestamo.js"></script>
</body>
</html>
