document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('transferForm');
    const cuentaOrigenSelect = document.getElementById('cuentaOrigen');
    const cuentaDestinoInput = document.getElementById('cuentaDestino');
    const montoInput = document.getElementById('monto');
    const conceptoInput = document.getElementById('concepto');
    
    function showFieldError(fieldId, message) {
        const errorElement = document.getElementById(fieldId + '-error');
        const fieldElement = document.getElementById(fieldId);
        
        if (errorElement) {
            errorElement.textContent = message;
            errorElement.style.display = 'block';
            errorElement.className = 'error-message error';
        }
        
        if (fieldElement) {
            fieldElement.classList.remove('valid');
            fieldElement.classList.add('error');
        }
    }
    
    function clearFieldError(fieldId) {
        const errorElement = document.getElementById(fieldId + '-error');
        const fieldElement = document.getElementById(fieldId);
        
        if (errorElement) {
            errorElement.textContent = '';
            errorElement.style.display = 'none';
        }
        
        if (fieldElement) {
            fieldElement.classList.remove('error');
        }
    }
    
    function markFieldValid(fieldId) {
        const fieldElement = document.getElementById(fieldId);
        if (fieldElement) {
            fieldElement.classList.remove('error');
            fieldElement.classList.add('valid');
        }
    }
    
    function obtenerSaldoDeOpcion(optionText) {
        const match = optionText.match(/\$([\d\.,]+)/);
        if (match) {
            const saldoString = match[1].replace(/\./g, '').replace(',', '.');
            return parseFloat(saldoString);
        }
        return 0;
    }
    
    function validarCBU(cbu) {
        return /^\d{22}$/.test(cbu);
    }

    
    if (form) {
        form.addEventListener('submit', function(e) {
            let isValid = true;
            
            // Limpiar todos los errores previos
            clearFieldError('cuentaOrigen');
            clearFieldError('cuentaDestino');
            clearFieldError('monto');
            
            if (!cuentaOrigenSelect.value) {
                showFieldError('cuentaOrigen', 'Debe seleccionar una cuenta origen');
                isValid = false;
            }
            
            const cuentaDestino = cuentaDestinoInput.value.trim();
            if (!cuentaDestino) {
                showFieldError('cuentaDestino', 'Debe ingresar una cuenta destino');
                isValid = false;
            } else if (!validarCBU(cuentaDestino)) {
                showFieldError('cuentaDestino', 'Formato de cuenta destino inválido. Ingrese un número de cuenta (6-10 dígitos) o CBU (22 dígitos)');
                isValid = false;
            }
            
            const monto = montoInput.value;
            if (!monto) {
                showFieldError('monto', 'Debe ingresar un monto');
                isValid = false;
            } else {
                const valorMonto = parseFloat(monto);
                if (isNaN(valorMonto) || valorMonto <= 0) {
                    showFieldError('monto', 'El monto debe ser mayor a 0');
                    isValid = false;
                } else if (valorMonto > 1000000) {
                    showFieldError('monto', 'El monto no puede superar $1,000,000');
                    isValid = false;
                } else {
                    if (cuentaOrigenSelect.value) {
                        const optionText = cuentaOrigenSelect.options[cuentaOrigenSelect.selectedIndex].text;
                        const saldoCuenta = obtenerSaldoDeOpcion(optionText);
                        
                        if (valorMonto > saldoCuenta) {
                            showFieldError('monto', `Saldo insuficiente. Disponible: $${saldoCuenta.toFixed(2)}`);
                            isValid = false;
                        }
                    }
                }
            }

            
            if (cuentaOrigenSelect.value && cuentaDestino) {
                if (cuentaOrigenSelect.value === cuentaDestino) {
                    showFieldError('cuentaDestino', 'No puede transferir a la misma cuenta');
                    isValid = false;
                }
            }
            
            if (!isValid) {
                e.preventDefault();
                const firstError = document.querySelector('.error-message[style*="block"]');
                if (firstError) {
                    firstError.scrollIntoView({ behavior: 'smooth', block: 'center' });
                }
            } else {
                const confirmacion = confirm('¿Está seguro que desea realizar esta transferencia?');
                if (!confirmacion) {
                    e.preventDefault();
                } else {
                    const submitButton = form.querySelector('button[type="submit"]');
                    submitButton.disabled = true;
                    submitButton.textContent = 'Procesando...';
                }
            }
        });
    }
    
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.display = 'none';
        }, 5000);
    });
    
    montoInput.addEventListener('blur', function() {
        const valor = parseFloat(this.value);
        if (!isNaN(valor)) {
            this.value = valor.toFixed(2);
        }
    });
    
    cuentaDestinoInput.addEventListener('input', function() {
        clearFieldError('cuentaDestino');
    });
    
    montoInput.addEventListener('input', function() {
        clearFieldError('monto');
    });
    
    conceptoInput.addEventListener('input', function() {
        clearFieldError('concepto');
    });
    
    cuentaOrigenSelect.addEventListener('change', function() {
        clearFieldError('cuentaOrigen');
    });
}); 