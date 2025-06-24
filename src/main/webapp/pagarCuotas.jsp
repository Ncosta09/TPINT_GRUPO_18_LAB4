<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Pagar Cuotas</title>
    <link rel="stylesheet" href="CSS/common.css" type="text/css" />
    <link rel="stylesheet" href="CSS/pagarCuotas.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarCliente.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="cuotas-container">
                <h2 class="section-title">
                    <span class="section-icon">📅</span>
                    Mis Cuotas de Préstamos
                </h2>
                
                <table>
                    <thead>
                        <tr>
                            <th>Préstamo</th>
                            <th>Cuota N°</th>
                            <th>Monto</th>
                            <th>Fecha Vencimiento</th>
                            <th>Estado</th>
                            <th>Fecha Pago</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>PR-001</td>
                            <td>1/24</td>
                            <td>$2,150.00</td>
                            <td>15/07/2023</td>
                            <td><span class="badge badge-paid">Pagada</span></td>
                            <td>14/07/2023</td>
                            <td>
                                <button class="btn btn-sm" disabled>Pagar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>PR-001</td>
                            <td>2/24</td>
                            <td>$2,150.00</td>
                            <td>15/08/2023</td>
                            <td><span class="badge badge-paid">Pagada</span></td>
                            <td>13/08/2023</td>
                            <td>
                                <button class="btn btn-sm" disabled>Pagar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>PR-001</td>
                            <td>3/24</td>
                            <td>$2,150.00</td>
                            <td>15/09/2023</td>
                            <td><span class="badge badge-pending">Pendiente</span></td>
                            <td>-</td>
                            <td>
                                <button class="btn btn-sm pay-btn" 
                                        data-prestamo="PR-001" 
                                        data-cuota="3" 
                                        data-monto="2150.00">Pagar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>PR-001</td>
                            <td>4/24</td>
                            <td>$2,150.00</td>
                            <td>15/10/2023</td>
                            <td><span class="badge badge-pending">Pendiente</span></td>
                            <td>-</td>
                            <td>
                                <button class="btn btn-sm pay-btn" 
                                        data-prestamo="PR-001" 
                                        data-cuota="4" 
                                        data-monto="2150.00">Pagar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>PR-001</td>
                            <td>5/24</td>
                            <td>$2,150.00</td>
                            <td>15/11/2023</td>
                            <td><span class="badge badge-pending">Pendiente</span></td>
                            <td>-</td>
                            <td>
                                <button class="btn btn-sm pay-btn" 
                                        data-prestamo="PR-001" 
                                        data-cuota="5" 
                                        data-monto="2150.00">Pagar</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <div class="modal" id="paymentModal">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Pagar Cuota</h3>
                <span class="close" id="closeModal">&times;</span>
            </div>
            <div class="modal-body">
                <div class="payment-info">
                    <p><strong>Préstamo:</strong> <span id="paymentPrestamo"></span></p>
                    <p><strong>Cuota N°:</strong> <span id="paymentCuota"></span></p>
                    <p><strong>Monto:</strong> $<span id="paymentMonto"></span></p>
                </div>
                
                <div class="form-group">
                    <label for="cuentaPago">Seleccionar cuenta para el pago:</label>
                    <select id="cuentaPago" required>
                        <option value="">Seleccione una cuenta...</option>
                        <option value="CA-10012345">CA-10012345 - Caja de Ahorro ($85,450.00)</option>
                        <option value="CC-10012346">CC-10012346 - Cuenta Corriente ($40,000.00)</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" id="cancelPayment">Cancelar</button>
                <button class="btn" id="confirmPayment">Confirmar Pago</button>
            </div>
        </div>
    </div>
    
    <div class="notification" id="notification">Cuota pagada exitosamente</div>

    <script src="js/common.js"></script>
    <script src="js/pagarCuotas.js"></script>
</body>
</html> 