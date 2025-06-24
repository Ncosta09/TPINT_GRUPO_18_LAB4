<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Alta de Cuenta</title>
    <link rel="stylesheet" href="CSS/altaCuentas.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="form-container">
                <h2 class="form-title">
                    <span class="form-icon">🏦</span>
                    Alta de Cuenta Bancaria
                </h2>
                
                <form id="accountForm" action="altaCuenta" method="post">
                    <div class="form-group">
                        <label for="cliente">Cliente:</label>
                        <select id="cliente" name="cliente" required>
                            <option value="">Seleccione un cliente...</option>
                            <option value="1">Juan Gómez (DNI: 30123456)</option>
                            <option value="2">María López (DNI: 28987654)</option>
                            <option value="3">Carlos Rodríguez (DNI: 33456789)</option>
                            <option value="4">Laura Martínez (DNI: 25789012)</option>
                            <option value="5">Pedro Sánchez (DNI: 35678901)</option>
                            <option value="6">Ana Fernández (DNI: 27345678)</option>
                            <option value="7">Roberto Díaz (DNI: 32567890)</option>
                            <option value="8">Lucía García (DNI: 29876543)</option>
                        </select>
                        <div class="error-message" id="cliente-error">Debe seleccionar un cliente</div>
                    </div>
                    
                    <div class="form-group">
                        <label for="tipoCuenta">Tipo de Cuenta:</label>
                        <select id="tipoCuenta" name="tipoCuenta" required>
                            <option value="">Seleccione tipo de cuenta...</option>
                            <option value="CA">Caja de Ahorro</option>
                            <option value="CC">Cuenta Corriente</option>
                        </select>
                        <div class="error-message" id="tipoCuenta-error">Debe seleccionar un tipo de cuenta</div>
                    </div>
                    
                    <div class="info-box">
                        <p>Saldo inicial de la cuenta:</p>
                        <div class="amount">$10,000.00</div>
                    </div>
                    
                    <div class="account-preview" id="accountPreview">
                        <h4>Vista Previa de la Cuenta</h4>
                        <div class="preview-item">
                            <span>Cliente:</span>
                            <span id="previewCliente">-</span>
                        </div>
                        <div class="preview-item">
                            <span>Tipo de Cuenta:</span>
                            <span id="previewTipo">-</span>
                        </div>
                        <div class="preview-item">
                            <span>Número de Cuenta:</span>
                            <span id="previewNumero">Se generará automáticamente</span>
                        </div>
                        <div class="preview-item">
                            <span>CBU:</span>
                            <span id="previewCBU">Se generará automáticamente</span>
                        </div>
                        <div class="preview-item highlight">
                            <span>Saldo Inicial:</span>
                            <span>$10,000.00</span>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" id="cancelBtn">Cancelar</button>
                        <button type="submit" class="btn" id="submitBtn">Crear Cuenta</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="notification" id="notification">Cuenta creada exitosamente</div>
     <script src="js/altaCuenta.js"></script>
</body>
</html>
