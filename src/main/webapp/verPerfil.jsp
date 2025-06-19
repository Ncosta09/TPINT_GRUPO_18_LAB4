<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Mi Perfil</title>
    <link rel="stylesheet" href="css/common.css" type="text/css" />
    <link rel="stylesheet" href="css/verPerfil.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarCliente.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="profile-container">
                <h2 class="profile-title">
                    <span class="profile-icon">👤</span>
                    Mi Perfil
                </h2>
                
                <div class="profile-section">
                    <h3 class="section-title">Información Personal</h3>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">DNI:</label>
                            <div class="field-value">12345678</div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Nombre:</label>
                            <div class="field-value">María</div>
                        </div>
                    </div>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Apellido:</label>
                            <div class="field-value">González</div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Fecha de Nacimiento:</label>
                            <div class="field-value">15/03/1985</div>
                        </div>
                    </div>
                </div>
                
                <div class="profile-section">
                    <h3 class="section-title">Información de Contacto</h3>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Email:</label>
                            <div class="field-value">maria.gonzalez@email.com</div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Teléfono:</label>
                            <div class="field-value">11-1234-5678</div>
                        </div>
                    </div>
                </div>
                
                <div class="profile-section">
                    <h3 class="section-title">Dirección</h3>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Dirección:</label>
                            <div class="field-value">Av. Corrientes 1234</div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Ciudad:</label>
                            <div class="field-value">Buenos Aires</div>
                        </div>
                    </div>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Código Postal:</label>
                            <div class="field-value">1043</div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Provincia:</label>
                            <div class="field-value">CABA</div>
                        </div>
                    </div>
                </div>
                
                <div class="profile-section">
                    <h3 class="section-title">Información de Cuenta</h3>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Usuario:</label>
                            <div class="field-value">maria.gonzalez</div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Tipo de Usuario:</label>
                            <div class="field-value">Cliente</div>
                        </div>
                    </div>
                    <div class="profile-row">
                        <div class="profile-field">
                            <label class="field-label">Fecha de Registro:</label>
                            <div class="field-value">01/01/2020</div>
                        </div>
                        <div class="profile-field">
                            <label class="field-label">Estado:</label>
                            <div class="field-value"><span class="badge badge-active">Activo</span></div>
                        </div>
                    </div>
                </div>
                
                <div class="profile-actions">
                    <button class="btn btn-secondary" onclick="window.location.href='homeCliente.jsp'">Volver</button>
                    <button class="btn" onclick="editProfile()">Editar Perfil</button>
                    <button class="btn btn-secondary" onclick="changePassword()">Cambiar Contraseña</button>
                </div>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
    <script src="js/verPerfil.js"></script>
</body>
</html> 