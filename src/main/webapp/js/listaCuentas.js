
        const burgerMenu = document.getElementById('burgerMenu');
        const sidebar = document.getElementById('sidebar');
        const overlay = document.getElementById('overlay');
        const notification = document.getElementById('notification');
        const accountTypeSelects = document.querySelectorAll('.account-type-select');
        const saveButtons = document.querySelectorAll('.save-btn');
        const paginationButtons = document.querySelectorAll('#pagination button');
        const saldoInputs = document.querySelectorAll('.saldo-input');
        const estadoSelects = document.querySelectorAll('.estado-select');
        
        // Datos de clientes para validación
        const clientesCuentas = {};
        
        // Inicializar datos de cuentas por cliente
        function initializeClientData() {
            const rows = document.querySelectorAll('#cuentasTableBody tr');
            rows.forEach(row => {
                const cliente = row.getAttribute('data-cliente');
                const tipo = row.getAttribute('data-tipo');
                const estado = row.getAttribute('data-estado');
                
                if (!clientesCuentas[cliente]) {
                    clientesCuentas[cliente] = [];
                }
                
                if (estado === 'Activa') {
                    const tipoCode = tipo === 'Caja de Ahorro' ? 'CA' : 'CC';
                    clientesCuentas[cliente].push(tipoCode);
                }
            });
        }
        
        initializeClientData();
        
        burgerMenu.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            overlay.classList.toggle('active');
        });
        
        overlay.addEventListener('click', function() {
            sidebar.classList.remove('active');
            overlay.classList.remove('active');
        });
        
        // Manejo de cambios en tipo de cuenta
        accountTypeSelects.forEach((select, index) => {
            select.addEventListener('change', function() {
                const saveBtn = saveButtons[index * 3]; // Adjusted index
                const originalValue = this.getAttribute('data-original');
                
                if (this.value !== originalValue) {
                    saveBtn.classList.add('show');
                } else {
                    saveBtn.classList.remove('show');
                }
            });
        });

        // Manejo de cambios en saldo
        saldoInputs.forEach((input, index) => {
            input.addEventListener('input', function() {
                const saveBtn = saveButtons[index * 3 + 1]; // Adjusted index
                const originalValue = this.getAttribute('data-original');
                
                if (this.value !== '$' + parseFloat(originalValue).toLocaleString()) {
                    saveBtn.classList.add('show');
                } else {
                    saveBtn.classList.remove('show');
                }
            });
        });

        // Manejo de cambios en estado
        estadoSelects.forEach((select, index) => {
            select.addEventListener('change', function() {
                const saveBtn = saveButtons[index * 3 + 2]; // Adjusted index
                const originalValue = this.getAttribute('data-original');
                
                if (this.value !== originalValue) {
                    saveBtn.classList.add('show');
                } else {
                    saveBtn.classList.remove('show');
                }
            });
        });
        
        // Manejo de botones de guardar
        saveButtons.forEach((button, index) => {
            button.addEventListener('click', function() {
                const rowIndex = Math.floor(index / 3);
                const select = accountTypeSelects[rowIndex];
                const saldoInput = saldoInputs[rowIndex];
                const estadoSelect = estadoSelects[rowIndex];
                const cliente = select.getAttribute('data-cliente');

                if (index % 3 === 0) { // Tipo de cuenta
                    const newType = select.value;
                    const originalType = select.getAttribute('data-original');
                    
                    // Validar que el cliente no tenga otra cuenta activa del mismo tipo
                    if (clientesCuentas[cliente] && clientesCuentas[cliente].includes(newType)) {
                        showNotification('Error: El cliente ya tiene una cuenta activa de este tipo', true);
                        select.value = originalType; // Revertir cambio
                        button.classList.remove('show');
                        return;
                    }
                    
                    // Simular guardado
                    button.disabled = true;
                    button.textContent = '⏳';
                    
                    setTimeout(() => {
                        // Actualizar datos
                        select.setAttribute('data-original', newType);
                        
                        // Actualizar datos de cliente
                        if (clientesCuentas[cliente]) {
                            const oldTypeIndex = clientesCuentas[cliente].indexOf(originalType);
                            if (oldTypeIndex > -1) {
                                clientesCuentas[cliente][oldTypeIndex] = newType;
                            }
                        }
                        
                        // Actualizar atributo de la fila
                        const row = select.closest('tr');
                        const newTypeText = newType === 'CA' ? 'Caja de Ahorro' : 'Cuenta Corriente';
                        row.setAttribute('data-tipo', newTypeText);
                        
                        button.classList.remove('show');
                        button.disabled = false;
                        button.textContent = '💾';
                        
                        showNotification('Tipo de cuenta actualizado correctamente');
                    }, 1500);
                } else if (index % 3 === 1) { // Saldo
                    const newSaldo = saldoInput.value.replace(/[$,]/g, '');
                    const originalSaldo = saldoInput.getAttribute('data-original');

                    // Simular guardado
                    button.disabled = true;
                    button.textContent = '⏳';

                    setTimeout(() => {
                        // Actualizar datos
                        saldoInput.setAttribute('data-original', newSaldo);
                        saldoInput.value = '$' + parseFloat(newSaldo).toLocaleString();

                        button.classList.remove('show');
                        button.disabled = false;
                        button.textContent = '💾';

                        showNotification('Saldo actualizado correctamente');
                    }, 1500);
                } else { // Estado
                    const newEstado = estadoSelect.value;
                    const originalEstado = estadoSelect.getAttribute('data-original');

                    // Simular guardado
                    button.disabled = true;
                    button.textContent = '⏳';

                    setTimeout(() => {
                        // Actualizar datos
                        estadoSelect.setAttribute('data-original', newEstado);

                        // Actualizar atributo de la fila
                        const row = estadoSelect.closest('tr');
                        row.setAttribute('data-estado', newEstado);

                        // Actualizar badge
                        const badge = row.querySelector('.badge');
                        badge.textContent = newEstado;
                        badge.className = newEstado === 'Activa' ? 'badge badge-active' : 'badge badge-inactive';

                        button.classList.remove('show');
                        button.disabled = false;
                        button.textContent = '💾';

                        showNotification('Estado actualizado correctamente');
                    }, 1500);
                }
            });
        });
        
        // Función para mostrar notificaciones
        function showNotification(message, isError = false) {
            notification.textContent = message;
            notification.className = isError ? 'notification error' : 'notification';
            notification.style.display = 'block';
            
            setTimeout(() => {
                notification.style.display = 'none';
            }, 3000);
        }
        
        // Manejo de búsqueda
        document.getElementById('searchBtn').addEventListener('click', function() {
            const searchTerm = document.getElementById('searchInput').value.toLowerCase();
            const rows = document.querySelectorAll('#cuentasTableBody tr');
            
            rows.forEach(row => {
                const cliente = row.cells[0].textContent.toLowerCase();
                
                if (cliente.includes(searchTerm)) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
        
        // Manejo de filtros
        document.getElementById('filterBtn').addEventListener('click', function() {
            const tipoFilter = document.getElementById('tipoFilter').value;
            const estadoFilter = document.getElementById('estadoFilter').value;
            const rows = document.querySelectorAll('#cuentasTableBody tr');
            
            rows.forEach(row => {
                const tipo = row.getAttribute('data-tipo');
                const estado = row.getAttribute('data-estado');
                
                let showRow = true;
                
                if (tipoFilter && tipo !== tipoFilter) {
                    showRow = false;
                }
                
                if (estadoFilter && estado !== estadoFilter) {
                    showRow = false;
                }
                
                row.style.display = showRow ? '' : 'none';
            });
        });
        
        // Manejo de paginación
        paginationButtons.forEach(button => {
            button.addEventListener('click', function() {
                paginationButtons.forEach(btn => btn.classList.remove('active'));
                this.classList.add('active');
                
                console.log('Cambiando a página:', this.textContent);
            });
        });
        
        // Búsqueda en tiempo real
        document.getElementById('searchInput').addEventListener('input', function() {
            const searchTerm = this.value.toLowerCase();
            const rows = document.querySelectorAll('#cuentasTableBody tr');
            
            rows.forEach(row => {
                const cliente = row.cells[0].textContent.toLowerCase();
                
                if (searchTerm === '' || cliente.includes(searchTerm)) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });