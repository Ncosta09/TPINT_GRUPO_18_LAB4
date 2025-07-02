document.addEventListener('DOMContentLoaded', function() {
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.classList.add('fade-out');
            setTimeout(() => {
                alert.remove();
            }, 500);
        }, 5000);
    });
});

const burgerMenu = document.getElementById('burgerMenu');
        const sidebar = document.getElementById('sidebar');
        const overlay = document.getElementById('overlay');
        const accountForm = document.getElementById('accountForm');
        const cancelBtn = document.getElementById('cancelBtn');
        const notification = document.getElementById('notification');
        const clienteSelect = document.getElementById('cliente');
        const tipoCuentaSelect = document.getElementById('tipoCuenta');
        const accountPreview = document.getElementById('accountPreview');
        

		
		
        
        burgerMenu.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            overlay.classList.toggle('active');
        });
        
        overlay.addEventListener('click', function() {
            sidebar.classList.remove('active');
            overlay.classList.remove('active');
        });
        
        cancelBtn.addEventListener('click', function() {
            window.location.href = 'ServletHomeAdmin';
        });
        
        // Actualizar vista previa cuando cambian los selects
        function updatePreview() {
            const clienteId = clienteSelect.value;
            const tipoCuenta = tipoCuentaSelect.value;
            
            if (clienteId && tipoCuenta) {
                const cliente = clientesData[clienteId];
                const tipoTexto = tipoCuenta === 'CA' ? 'Caja de Ahorro' : 'Cuenta Corriente';
                
                document.getElementById('previewCliente').textContent = cliente.nombre;
                document.getElementById('previewTipo').textContent = tipoTexto;
                
                // Generar número de cuenta simulado
                const numeroSimulado = tipoCuenta + '-' + (10000000 + parseInt(clienteId) * 1000 + Math.floor(Math.random() * 100));
                document.getElementById('previewNumero').textContent = numeroSimulado;
                
                // Generar CBU simulado
                const cbuSimulado = '0110012300' + numeroSimulado.replace('-', '') + '01';
                document.getElementById('previewCBU').textContent = cbuSimulado;
                
                accountPreview.classList.add('show');
            } else {
                accountPreview.classList.remove('show');
            }
        }
        
        clienteSelect.addEventListener('change', updatePreview);
        tipoCuentaSelect.addEventListener('change', updatePreview);
        
		
		
		accountForm.addEventListener('submit', function(e) {
		    // Validación básica opcional (puede quedarse si querés)
		    const clienteId = clienteSelect.value;
		    const tipoCuenta = tipoCuentaSelect.value;
		    const tipoCuentaError = document.getElementById('tipoCuenta-error');

		    let isValid = true;

		    if (!tipoCuenta) {
		        tipoCuentaError.textContent = 'Debe seleccionar un tipo de cuenta';
		        tipoCuentaError.style.display = 'block';
		        isValid = false;
		    } else {
		        tipoCuentaError.style.display = 'none';
		    }

		    // Si no es válido, evitás el envío
		    if (!isValid) {
		        e.preventDefault();
		    }

		});