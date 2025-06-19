<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Sistema Bancario - Mis Cuentas</title>
    <link rel="stylesheet" href="CSS/layout.css" />
</head>
<body>

    <jsp:include page="Header.jsp" />
    <div class="container">
        <jsp:include page="MenuCliente.jsp" />

        <div class="content">
            <div class="profile-container">
                <h2 class="profile-title">💳 Mis Cuentas</h2>

                <div class="profile-section">
                    <table style="width:100%">
                        <thead>
                            <tr>
                                <th>N° Cuenta</th>
                                <th>Tipo</th>
                                <th>Saldo</th>
                                <th>CBU</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>123456789</td>
                                <td>Cuenta Corriente</td>
                                <td>$ 25.000</td>
                                <td>0001112223334445556667</td>
                            </tr>
                            <tr>
                                <td>987654321</td>
                                <td>Cuenta Caja de Ahorro</td>
                                <td>$ 12.750</td>
                                <td>0002223334445556667778</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script src="JS/script.js"></script>
</body>
</html>
