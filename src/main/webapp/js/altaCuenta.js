
        const burgerMenu = document.getElementById('burgerMenu');
        const sidebar = document.getElementById('sidebar');
        const overlay = document.getElementById('overlay');
        const accountForm = document.getElementById('accountForm');
        const cancelBtn = document.getElementById('cancelBtn');
        const notification = document.getElementById('notification');
        const clienteSelect = document.getElementById('cliente');
        const tipoCuentaSelect = document.getElementById('tipoCuenta');
        const accountPreview = document.getElementById('accountPreview');
        
        // Datos de clientes para validación
        const clientesData = {
            '1': { nombre: 'Juan Gómez', cuentas: ['CA'] },
            '2': { nombre: 'María López', cuentas: [] },
            '3': { nombre: 'Carlos Rodríguez', cuentas: ['CC'] },
            '4': { nombre: 'Laura Martínez', cuentas: ['CA', 'CC'] },
            '5': { nombre: 'Pedro Sánchez', cuentas: [] },
            '6': { nombre: 'Ana Fernández', cuentas: ['CA'] },
            '7': { nombre: 'Roberto Díaz', cuentas: [] },
            '8': { nombre: 'Lucía García', cuentas: ['CC'] }
        };
        
        burgerMenu.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            overlay.classList.toggle('active');
        });
        
        overlay.addEventListener('click', function() {
            sidebar.classList.remove('active');
            overlay.classList.remove('active');
        });
        
        cancelBtn.addEventListener('click', function() {
            window.location.href = 'homeAdmin.jsp';
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
            e.preventDefault();
            
            // Validación
            let isValid = true;
            
            // Validar cliente
            const clienteId = clienteSelect.value;
            if (!clienteId) {
                document.getElementById('cliente-error').style.display = 'block';
                isValid = false;
            } else {
                document.getElementById('cliente-error').style.display = 'none';
            }
            
            // Validar tipo de cuenta
            const tipoCuenta = tipoCuentaSelect.value;
            const tipoCuentaError = document.getElementById('tipoCuenta-error');
            
            if (!tipoCuenta) {
                tipoCuentaError.textContent = 'Debe seleccionar un tipo de cuenta';
                tipoCuentaError.style.display = 'block';
                isValid = false;
            } else if (clienteId && clientesData[clienteId].cuentas.includes(tipoCuenta)) {
                tipoCuentaError.textContent = 'El cliente ya tiene una cuenta activa de este tipo';
                tipoCuentaError.style.display = 'block';
                isValid = false;
            } else {
                tipoCuentaError.style.display = 'none';
            }
            
            if (isValid) {
                // Simular procesamiento
                document.getElementById('submitBtn').disabled = true;
                document.getElementById('submitBtn').textContent = 'Creando cuenta...';
                
                setTimeout(function() {
                    // Mostrar notificación de éxito
                    notification.style.display = 'block';
                    
                    // Ocultar notificación después de 3 segundos
                    setTimeout(function() {
                        notification.style.display = 'none';
                        window.location.href = 'listaCuentas.jsp';
                    }, 3000);
                }, 2000);
                
                console.log('Cuenta creada:', {
                    cliente: clientesData[clienteId].nombre,
                    tipoCuenta: tipoCuenta,
                    saldoInicial: 10000
                });
            }
        });