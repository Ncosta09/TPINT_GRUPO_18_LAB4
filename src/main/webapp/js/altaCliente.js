document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('altaClienteForm');
    const notification = document.getElementById('notification');
    
    if (form) {
        form.addEventListener('submit', function(e) {
            // Validación básica del lado cliente
            const requiredFields = ['nombre', 'apellido', 'dni', 'email', 'usuario', 'password'];
            let isValid = true;
            
            requiredFields.forEach(fieldName => {
                const field = document.getElementById(fieldName);
                const errorElement = document.getElementById(fieldName + '-error');
                
                if (field && errorElement) {
                    if (!field.value.trim()) {
                        showError(fieldName + '-error', 'Este campo es obligatorio');
                        isValid = false;
                    } else {
                        hideError(fieldName + '-error');
                    }
                }
            });
            
            // Validación básica de email
            const email = document.getElementById('email').value;
            if (email && !email.includes('@')) {
                showError('email-error', 'Formato de email inválido');
                isValid = false;
            }
            
            if (!isValid) {
                e.preventDefault();
            }
        });
    }
    
    function getFieldLabel(fieldName) {
        const labels = {
            'nombre': 'Nombre',
            'apellido': 'Apellido',
            'dni': 'DNI',
            'fechaNacimiento': 'Fecha de Nacimiento',
            'email': 'Email',
            'telefono': 'Teléfono',
            'direccion': 'Dirección',
            'ciudad': 'Ciudad',
            'codigoPostal': 'Código Postal',
            'provincia': 'Provincia',
            'usuario': 'Usuario',
            'password': 'Contraseña'
        };
        return labels[fieldName] || fieldName;
    }
    
    function showNotification(message, type) {
        if (notification) {
            notification.textContent = message;
            notification.style.backgroundColor = type === 'success' ? '#4CAF50' : '#f44336';
            notification.style.display = 'block';
            
            setTimeout(() => {
                notification.style.display = 'none';
            }, 3000);
        }
    }
}); 