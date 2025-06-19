<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Sistema de Gestión Bancaria - Pedir Prestamo</title>
    <!-- Estilos -->
    <link rel="stylesheet" href="CSS/layout.css" />
    <link rel="stylesheet" href="CSS/perfil.css" />
    <link rel="stylesheet" href="CSS/pedirPrestamo.css" />
</head>
<body>
    <%--SP principales --%>
    <jsp:include page="Header.jsp" />
    <div class="container">
        <jsp:include page="MenuCliente.jsp" />

        <div class="content">
            <div class="loan-container">
                <h2 class="loan-title">
                    <span class="loan-icon">💰</span>
                    Solicitar Préstamo
                </h2>

                <div class="loan-info">
                    <h4>Información sobre Préstamos</h4>
                    <p>• Tasa de interés anual: 24%</p>
                    <p>• Monto mínimo: $10,000</p>
                    <p>• Monto máximo: $500,000</p>
                    <p>• Plazo mínimo: 6 cuotas</p>
                    <p>• Plazo máximo: 60 cuotas</p>
                </div>

                <form id="loanForm" action="pedirPrestamo" method="post">
                    <div class="form-group">
                        <label for="montoSolicitado">Monto Solicitado:</label>
                        <input type="number" id="montoSolicitado" name="montoSolicitado" placeholder="Ingrese el monto" step="1000" min="10000" max="500000" required>
                        <div class="error-message" id="monto-error">El monto debe estar entre $10,000 y $500,000</div>
                    </div>

                    <div class="form-group">
                        <label for="cantidadCuotas">Cantidad de Cuotas:</label>
                        <select id="cantidadCuotas" name="cantidadCuotas" required>
                            <option value="">Seleccione cantidad de cuotas...</option>
                            <option value="6">6 cuotas</option>
                            <option value="12">12 cuotas</option>
                            <option value="18">18 cuotas</option>
                            <option value="24">24 cuotas</option>
                            <option value="36">36 cuotas</option>
                            <option value="48">48 cuotas</option>
                            <option value="60">60 cuotas</option>
                        </select>
                        <div class="error-message" id="cuotas-error">Debe seleccionar la cantidad de cuotas</div>
                    </div>

                    <div class="form-group">
                        <label for="cuentaDestino">Cuenta Destino:</label>
                        <select id="cuentaDestino" name="cuentaDestino" required>
                            <option value="">Seleccione cuenta destino...</option>
                            <option value="CA-10012345">CA-10012345 - Caja de Ahorro</option>
                            <option value="CC-10012346">CC-10012346 - Cuenta Corriente</option>
                        </select>
                        <div class="error-message" id="cuenta-error">Debe seleccionar una cuenta destino</div>
                    </div>

                    <div class="calculation-result" id="calculationResult">
                        <h4>Resumen del Préstamo</h4>
                        <div class="result-item">
                            <span>Monto solicitado:</span>
                            <span id="resumenMonto">$0</span>
                        </div>
                        <div class="result-item">
                            <span>Cantidad de cuotas:</span>
                            <span id="resumenCuotas">0</span>
                        </div>
                        <div class="result-item">
                            <span>Tasa de interés anual:</span>
                            <span>24%</span>
                        </div>
                        <div class="result-item">
                            <span>Valor de cada cuota:</span>
                            <span id="resumenCuotaValor">$0</span>
                        </div>
                        <div class="result-item total">
                            <span>Total a pagar:</span>
                            <span id="resumenTotal">$0</span>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" id="cancelBtn">Cancelar</button>
                        <button type="submit" class="btn" id="submitBtn">Enviar Solicitud</button>
                    </div>
                </form>
            </div>
            <div class="notification" id="notification">Su solicitud fue enviada y está pendiente de aprobación</div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="js/menu"></script>
    <script src="js/pedirPrestamo.js"></script>
     
</body>
</html>
