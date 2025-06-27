document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('altaClienteForm');
    const notification = document.getElementById('notification');
    
    function soloLetras(input) {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ\s]/g, '');
        });
    }

    soloLetras(document.getElementById('nombre'));
    soloLetras(document.getElementById('apellido'));
    
    function soloNumeros(input) {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/[^0-9]/g, '');
        });
    }

    soloNumeros(document.getElementById('dni'));
    soloNumeros(document.getElementById('cuil'));
    soloNumeros(document.getElementById('telefono'));
    
    
    if (form) {
        form.addEventListener('submit', function(e) {
            const requiredFields = ['nombre', 'apellido', 'dni','cuil', 'email', 'usuario', 'password'];
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
            const password = document.getElementById('password').value;
            const confirmClave = document.getElementById('confirmClave').value;

            if (password !== confirmClave) {
                showError('confirmClave-error', 'Las contraseñas no coinciden');
                isValid = false;
            } else {
                hideError('confirmClave-error');
            }
            
            if (!isValid) {
                e.preventDefault();
            }
        });
    }
    
    document.getElementById('localidad')
    .addEventListener('change', function() {
      // Toma el id de la opción seleccionada
      const selLoc = this;
      const opt    = selLoc.options[selLoc.selectedIndex];
      const idProv = opt.getAttribute('data-provincia');
      // Marca esa provincia que sea el mismo id
      const selProv = document.getElementById('provincia');
      selProv.value = idProv || '';
    });
    
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