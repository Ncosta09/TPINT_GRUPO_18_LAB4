document.addEventListener('DOMContentLoaded', function() {
    const registerToggle = document.getElementById('registerToggle');
    const confirmPasswordGroup = document.getElementById('confirm-password-group');
    const submitBtn = document.getElementById('submitBtn');
    const loginForm = document.getElementById('loginForm');
    
    let isRegisterMode = false;
    
    if (registerToggle) {
        registerToggle.addEventListener('click', function() {
            isRegisterMode = !isRegisterMode;
            
            if (isRegisterMode) {
                confirmPasswordGroup.style.display = 'block';
                submitBtn.textContent = 'Registrarse';
                registerToggle.textContent = '¿Ya tienes cuenta? Inicia sesión';
            } else {
                confirmPasswordGroup.style.display = 'none';
                submitBtn.textContent = 'Iniciar Sesión';
                registerToggle.textContent = '¿No tienes cuenta? Regístrate aquí';
            }
        });
    }
    
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            
            let isValid = true;
            
            // Validar usuario
            const usernameError = validateRequired(username, 'Usuario');
            if (usernameError) {
                showError('username-error', usernameError);
                isValid = false;
            } else {
                hideError('username-error');
            }
            
            // Validar contraseña
            const passwordError = validateRequired(password, 'Contraseña');
            if (passwordError) {
                showError('password-error', passwordError);
                isValid = false;
            } else {
                hideError('password-error');
            }
            
            // Validar confirmación de contraseña en modo registro
            if (isRegisterMode) {
                const confirmPassword = document.getElementById('confirmPassword').value;
                if (password !== confirmPassword) {
                    showError('confirm-password-error', 'Las contraseñas no coinciden');
                    isValid = false;
                } else {
                    hideError('confirm-password-error');
                }
            }
            
            if (isValid) {
                // Aquí se enviaría el formulario
                console.log('Formulario válido, enviando...');
                
                // Simulación de redirección según el modo
                if (isRegisterMode) {
                    // Redirigir a página de cliente después del registro
                    window.location.href = 'homeCliente.jsp';
                } else {
                    // Redirigir a página de admin después del login
                    window.location.href = 'homeAdmin.jsp';
                }
            }
        });
    }
}); 