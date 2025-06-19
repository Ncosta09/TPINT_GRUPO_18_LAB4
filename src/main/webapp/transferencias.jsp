<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Sistema Bancario - Transferencias</title>
    <link rel="stylesheet" href="CSS/layout.css" />
</head>
<body>

    <jsp:include page="Header.jsp" />
    <div class="container">
        <jsp:include page="MenuCliente.jsp" />

        <div class="content">
            <div class="form-container">
                <h2 class="form-title">💸 Transferencia</h2>
                <form method="post" action="TransferenciaServlet">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="cuentaOrigen">Cuenta Origen:</label>
                            <select id="cuentaOrigen" name="cuentaOrigen" required>
                                <option value="123456789">123456789 - Cuenta Corriente</option>
                                <option value="987654321">987654321 - Caja de Ahorro</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="cuentaDestino">CBU Destino:</label>
                            <input type="text" id="cuentaDestino" name="cuentaDestino" required />
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="monto">Monto:</label>
                            <input type="number" id="monto" name="monto" min="1" step="0.01" required />
                        </div>
                        <div class="form-group">
                            <label for="detalle">Detalle:</label>
                            <input type="text" id="detalle" name="detalle" />
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="reset" class="btn btn-secondary">Cancelar</button>
                        <button type="submit" class="btn">Transferir</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="JS/script.js"></script>
</body>
</html>
