<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dominio.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Pagar Cuotas</title>
    <link rel="stylesheet" href="CSS/common.css" type="text/css" />
    <link rel="stylesheet" href="CSS/pagarCuotas.css" type="text/css" />

    <!-- DataTables CDN -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
</head>
<body>
<jsp:include page="/header.jsp" />

<div class="container">
    <jsp:include page="/sidebarCliente.jsp" />

    <div class="overlay" id="overlay"></div>

    <div class="content">
        <!-- Notificaciones -->
        <% if (request.getAttribute("mensaje") != null) { %>
            <div class="alert alert-success">
                <%= request.getAttribute("mensaje") %>
            </div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <div class="loans-container">
            <h2 class="section-title">
                <span class="section-icon">💰</span>
                Mis Préstamos
            </h2>

            <table>
                <thead>
                <tr>
                    <th>ID Préstamo</th>
                    <th>Cuenta</th>
                    <th>Monto Original</th>
                    <th>Cuotas Totales</th>
                    <th>Cuotas Pagadas</th>
                    <th>Cuotas Pendientes</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <%
                List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamosCliente");
                if (prestamos != null) {
                    for (Prestamo prestamo : prestamos) {
                        Integer cuotasPagadas = (Integer) request.getAttribute("cuotasPagadas_" + prestamo.getIdPrestamo());
                        Integer cuotasPendientes = (Integer) request.getAttribute("cuotasPendientes_" + prestamo.getIdPrestamo());
                        Cuenta cuenta = (Cuenta) request.getAttribute("cuenta_" + prestamo.getIdPrestamo());
                %>
                    <tr>
                        <td>PR-<%=prestamo.getIdPrestamo()%></td>
                        <td><%=cuenta.getNumeroCuenta()%> - <%=cuenta.getTipoCuenta().getDescripcion()%></td>
                        <td>$<%=String.format("%,.2f", prestamo.getImportePedido())%></td>
                        <td><%=prestamo.getCantidadCuotas()%></td>
                        <td><%=cuotasPagadas%></td>
                        <td><%=cuotasPendientes%></td>
                        <td>
                            <% if ("pendiente".equals(prestamo.getEstado())) { %>
                                <span class="badge badge-pending">Pendiente</span>
                            <% } else if ("aprobado".equals(prestamo.getEstado())) { %>
                                <span class="badge badge-approved">Aprobado</span>
                            <% } else if ("rechazado".equals(prestamo.getEstado())) { %>
                                <span class="badge badge-rejected">Rechazado</span>
                            <% } else { %>
                                <span class="badge badge-pending"><%=prestamo.getEstado()%></span>
                            <% } %>
                        </td>
                        <td>
                            <% if ("aprobado".equals(prestamo.getEstado())) { %>
                                <a href="ServletListarPrestamos?idPrestamo=<%=prestamo.getIdPrestamo()%>" class="btn btn-sm">Ver Cuotas</a>
                            <% } %>
                        </td>
                    </tr>
                <%
                    }
                }
                %>
                </tbody>
            </table>
        </div>

        <%
        String idPrestamoParam = request.getParameter("idPrestamo");
        if (idPrestamoParam != null && !idPrestamoParam.trim().isEmpty()) {
        %>
            <div class="installments-section active">
                <h2 class="section-title">
                    <span class="section-icon">📅</span>
                    Cuotas del Préstamo: PR-<%=idPrestamoParam%>
                </h2>

                <div class="loan-info">
                    <h4>Información del Préstamo</h4>
                    <div class="loan-info-grid">
                        <%
                        Prestamo prestamoSeleccionado = null;
                        if (prestamos != null) {
                            for (Prestamo prestamo : prestamos) {
                                if (prestamo.getIdPrestamo() == Integer.parseInt(idPrestamoParam)) {
                                    prestamoSeleccionado = prestamo;
                                    break;
                                }
                            }
                        }
                        
                        if (prestamoSeleccionado != null) {
                        %>
                            <p><strong>Monto Original:</strong> $<%=String.format("%,.2f", prestamoSeleccionado.getImportePedido())%></p>
                            <p><strong>Cuotas Totales:</strong> <%=prestamoSeleccionado.getCantidadCuotas()%></p>
                            <p><strong>Valor por Cuota:</strong> $<%=String.format("%,.2f", prestamoSeleccionado.getImporteCuota())%></p>
                            <p><strong>Estado:</strong> <%=prestamoSeleccionado.getEstado()%></p>
                        <%
                        }
                        %>
                    </div>
                </div>

                <table id="installmentsTable" class="display">
                    <thead>
                    <tr>
                        <th>Cuota N°</th>
                        <th>Fecha Vencimiento</th>
                        <th>Monto</th>
                        <th>Estado</th>
                        <th>Fecha Pago</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                    List<Cuota> cuotasPrestamo = (List<Cuota>) request.getAttribute("cuotasPrestamo");
                    if (cuotasPrestamo != null) {
                        for (Cuota cuota : cuotasPrestamo) {
                    %>
                        <tr>
                            <td><%=cuota.getNumeroCuota()%></td>
                            <td>
                                <% if (cuota.getFechaVencimiento() != null) { %>
                                    <%=cuota.getFechaVencimiento()%>
                                <% } else { %>
                                    -
                                <% } %>
                            </td>
                            <td>$<%=String.format("%,.2f", cuota.getMonto())%></td>
                            <td>
                                <% if (cuota.isPagada()) { %>
                                    <span class="badge badge-paid">Pagada</span>
                                <% } else { %>
                                    <span class="badge badge-pending">Pendiente</span>
                                <% } %>
                            </td>
                            <td>
                                <% if (cuota.getFechaPago() != null) { %>
                                    <%=cuota.getFechaPago()%>
                                <% } else { %>
                                    -
                                <% } %>
                            </td>
                            <td>
                                <% if (cuota.isPagada()) { %>
                                    <button class="btn btn-sm" disabled>Pagar</button>
                                <% } else { %>
                                    <button class="btn btn-sm btn-success pay-btn" 
                                            onclick="pagarCuota('<%=cuota.getIdCuota()%>', '<%=cuota.getNumeroCuota()%>', '<%=cuota.getMonto()%>')">
                                        Pagar
                                    </button>
                                <% } %>
                            </td>
                        </tr>
                    <%
                        }
                    }
                    %>
                    </tbody>
                </table>

                <div style="text-align: center; margin-top: 20px;">
                    <a href="ServletListarPrestamos" class="btn">Volver a Préstamos</a>
                </div>
            </div>
        <%
        }
        %>
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
                <p><strong>Préstamo:</strong> <span id="paymentLoan"></span></p>
                <p><strong>Cuota N°:</strong> <span id="paymentInstallment"></span></p>
                <p><strong>Monto:</strong> $<span id="paymentAmount"></span></p>
            </div>

            <form action="ServletPagarCuotas" method="post">
                <input type="hidden" name="idCuota" id="idCuota" />
                <input type="hidden" name="idPrestamo" value="<%=request.getParameter("idPrestamo")%>" />
                
                <div class="form-group">
                    <label for="paymentAccount">Seleccionar cuenta para el pago:</label>
                    <select id="paymentAccount" name="idCuentaPago" required>
                        <option value="">Seleccione una cuenta...</option>
                        <%
                        List<Cuenta> cuentasCliente = (List<Cuenta>) request.getAttribute("cuentasCliente");
                        if (cuentasCliente != null) {
                            for (Cuenta cuenta : cuentasCliente) {
                        %>
                            <option value="<%=cuenta.getId()%>"><%=cuenta.getNumeroCuenta()%> - <%=cuenta.getTipoCuenta().getDescripcion()%> ($<%=String.format("%,.2f", cuenta.getSaldo())%>)</option>
                        <%
                            }
                        }
                        %>
                    </select>
                </div>
                
                <div class="modal-footer">
                    <button type="button" class="btn" style="background-color: #6c757d;" id="cancelPayment">Cancelar</button>
                    <button type="submit" class="btn btn-success">Confirmar Pago</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="notification" id="notification">Cuota pagada exitosamente</div>

<script src="js/common.js"></script>
<script src="js/pagarCuotas.js"></script>

</body>
</html>
