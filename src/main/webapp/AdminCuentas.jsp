<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sistema de Gestión Bancaria - Alta de Cuenta</title>
    <link rel="stylesheet" href="CSS/altaCli.css" />
</head>
<body>
    <jsp:include page="Header.jsp" />

    <div class="container">
        <div class="sidebar" id="sidebar">
            <ul class="sidebar-menu">
                <li><a href="#">Alta de Usuario/Cliente</a></li>
                <li><a href="#">Lista de Clientes</a></li>
                <li><a href="#" class="active">Gestión de Cuentas</a></li>
                <li><a href="#">Reportes</a></li>
            </ul>
        </div>

        <div class="overlay" id="overlay"></div>

        <div class="content">
            <div class="form-container">
                <h2 class="form-title">Alta de Cuenta</h2>
                <form id="cuentaForm" action="CuentaServlet" method="post">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="cliente">Cliente:</label>
                            <select id="cliente" name="cliente" required>
                                <option value="">Seleccione un cliente...</option>
                                <option value="1">Juan Pérez</option>
                                <option value="2">María López</option>
                                <option value="3">Carlos Ruiz</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="tipoCuenta">Tipo de Cuenta:</label>
                            <select id="tipoCuenta" name="tipoCuenta" required>
                                <option value="">Seleccione tipo...</option>
                                <option value="Caja de Ahorro">Caja de Ahorro</option>
                                <option value="Cuenta Corriente">Cuenta Corriente</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="numeroCuenta">Número de Cuenta:</label>
                            <input type="text" id="numeroCuenta" name="numeroCuenta" required>
                        </div>

                        <div class="form-group">
                            <label for="cbu">CBU:</label>
                            <input type="text" id="cbu" name="cbu" maxlength="22">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="fechaCreacion">Fecha de Creación:</label>
                            <input type="date" id="fechaCreacion" name="fechaCreacion" required>
                        </div>

                        <div class="form-group">
                            <label for="saldo">Saldo Inicial:</label>
                            <input type="text" id="saldo" name="saldo" value="10000" readonly>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="reset" class="btn btn-secondary">Cancelar</button>
                        <button type="submit" class="btn">Guardar Cuenta</button>
                    </div>
                </form>

                <!-- Tabla de Cuentas Existentes -->
                <h2 class="form-title">Cuentas Existentes</h2>
                <table border="1" cellpadding="8" style="width: 100%; border-collapse: collapse; text-align: center;">
                    <tr style="background-color: #f2f2f2;">
                        <th>Cliente</th>
                        <th>Número</th>
                        <th>CBU</th>
                        <th>Tipo</th>
                        <th>Fecha</th>
                        <th>Saldo</th>
                        <th>Acciones</th>
                    </tr>
                    <tr>
                        <td>Juan Pérez</td>
                        <td>12345678</td>
                        <td>0123456789012345678901</td>
                        <td>Caja de Ahorro</td>
                        <td>2025-06-01</td>
                        <td>$10.000</td>
                        <td>
                            <form method="post" action="#" style="display:inline;">
                                <input type="hidden" name="accion" value="editar" />
                                <button class="btn">Editar</button>
                            </form>
                            <form method="post" action="#" style="display:inline;">
                                <input type="hidden" name="accion" value="eliminar" />
                                <button class="btn btn-secondary">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <!-- Puedes duplicar esta fila como ejemplo -->
                </table>
            </div>
        </div>
    </div>

    <div class="notification" id="notification">Cuenta creada correctamente</div>

    <script src="JS/cuentas.js"></script>
</body>
</html>
