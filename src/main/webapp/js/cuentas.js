// Obtener elementos del DOM
const burgerMenu = document.getElementById('burgerMenu'); // asumido que está en Header.jsp
const sidebar = document.getElementById('sidebar');
const overlay = document.getElementById('overlay');
const cuentaForm = document.getElementById('cuentaForm');
const cancelBtn = cuentaForm.querySelector('button[type="reset"]'); // botón cancelar
const notification = document.getElementById('notification');


if (burgerMenu) {
    burgerMenu.addEventListener('click', function () {
        sidebar.classList.toggle('active');
        overlay.classList.toggle('active');
    });
}


overlay.addEventListener('click', function () {
    sidebar.classList.remove('active');
    overlay.classList.remove('active');
});


cancelBtn.addEventListener('click', function () {
 window.location.href = '#'; //Cambiar al correcto luego
});

// Al enviar el formulario
cuentaForm.addEventListener('submit', function (e) {
	e.preventDefault();
});

let currentPage = 1;
let totalPages = 1;
let currentAccount = '';
let movementsData = [];

// Datos de ejemplo para movimientos
const sampleMovements = {
    '1234567890': [
        { fecha: '2024-01-15', descripcion: 'Transferencia recibida', tipo: 'transferencia', monto: 5000, saldo: 25430.50 },
        { fecha: '2024-01-14', descripcion: 'Pago de servicios', tipo: 'pago', monto: -1200, saldo: 20430.50 },
        { fecha: '2024-01-13', descripcion: 'Depósito en efectivo', tipo: 'deposito', monto: 3000, saldo: 21630.50 },
        { fecha: '2024-01-12', descripcion: 'Retiro en cajero', tipo: 'retiro', monto: -500, saldo: 18630.50 },
        { fecha: '2024-01-11', descripcion: 'Transferencia enviada', tipo: 'transferencia', monto: -1500, saldo: 19130.50 }
    ],
    '0987654321': [
        { fecha: '2024-01-15', descripcion: 'Intereses ganados', tipo: 'deposito', monto: 150, saldo: 19800.00 },
        { fecha: '2024-01-10', descripcion: 'Depósito programado', tipo: 'deposito', monto: 2000, saldo: 19650.00 },
        { fecha: '2024-01-05', descripcion: 'Retiro en cajero', tipo: 'retiro', monto: -800, saldo: 17650.00 },
        { fecha: '2024-01-01', descripcion: 'Transferencia recibida', tipo: 'transferencia', monto: 5000, saldo: 18450.00 }
    ]
};

function viewMovements(accountNumber) {
    currentAccount = accountNumber;
    currentPage = 1;
    
    document.getElementById('selectedAccount').textContent = accountNumber;
    document.getElementById('movementsSection').classList.add('active');
    
    // Cargar movimientos de la cuenta seleccionada
    movementsData = sampleMovements[accountNumber] || [];
    displayMovements();
}

function closeMovements() {
    document.getElementById('movementsSection').classList.remove('active');
    currentAccount = '';
    movementsData = [];
}

function filterMovements() {
    const movementType = document.getElementById('movementType').value;
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;
    
    let filteredData = sampleMovements[currentAccount] || [];
    
    // Filtrar por tipo
    if (movementType) {
        filteredData = filteredData.filter(movement => movement.tipo === movementType);
    }
    
    // Filtrar por fecha
    if (startDate) {
        filteredData = filteredData.filter(movement => movement.fecha >= startDate);
    }
    
    if (endDate) {
        filteredData = filteredData.filter(movement => movement.fecha <= endDate);
    }
    
    movementsData = filteredData;
    currentPage = 1;
    displayMovements();
}

function displayMovements() {
    const itemsPerPage = 10;
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const pageData = movementsData.slice(startIndex, endIndex);
    
    totalPages = Math.ceil(movementsData.length / itemsPerPage);
    
    const tbody = document.getElementById('movementsTableBody');
    tbody.innerHTML = '';
    
    pageData.forEach(movement => {
        const row = document.createElement('tr');
        const montoClass = movement.monto >= 0 ? 'movement-credit' : 'movement-debit';
        const montoSign = movement.monto >= 0 ? '+' : '';
        
        row.innerHTML = `
            <td>${formatDate(movement.fecha)}</td>
            <td>${movement.descripcion}</td>
            <td>${capitalizeFirst(movement.tipo)}</td>
            <td class="${montoClass}">${montoSign}$${Math.abs(movement.monto).toLocaleString()}</td>
            <td>$${movement.saldo.toLocaleString()}</td>
        `;
        
        tbody.appendChild(row);
    });
    
    updatePagination();
}

function updatePagination() {
    const pageInfo = document.getElementById('pageInfo');
    const prevBtn = document.querySelector('.pagination button:first-child');
    const nextBtn = document.querySelector('.pagination button:last-child');
    
    pageInfo.textContent = `Página ${currentPage} de ${totalPages}`;
    
    prevBtn.disabled = currentPage === 1;
    nextBtn.disabled = currentPage === totalPages;
}

function changePage(direction) {
    const newPage = currentPage + direction;
    
    if (newPage >= 1 && newPage <= totalPages) {
        currentPage = newPage;
        displayMovements();
    }
}

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('es-AR');
}

function capitalizeFirst(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

// Limpiar filtros
function clearFilters() {
    document.getElementById('movementType').value = '';
    document.getElementById('startDate').value = '';
    document.getElementById('endDate').value = '';
    
    if (currentAccount) {
        movementsData = sampleMovements[currentAccount] || [];
        currentPage = 1;
        displayMovements();
    }
}

// Agregar botón para limpiar filtros
document.addEventListener('DOMContentLoaded', function() {
    const filterContainer = document.querySelector('.filter-container');
    if (filterContainer) {
        const clearButton = document.createElement('button');
        clearButton.textContent = 'Limpiar';
        clearButton.className = 'btn btn-secondary';
        clearButton.onclick = clearFilters;
        filterContainer.appendChild(clearButton);
    }
});

document.addEventListener('DOMContentLoaded', function() {
    // Botón de nueva cuenta
    const btnNuevaCuenta = document.getElementById('btnNuevaCuenta');
    if (btnNuevaCuenta) {
        btnNuevaCuenta.addEventListener('click', function() {
            // Aquí iría la lógica para abrir modal o redirigir
            console.log('Nueva cuenta solicitada');
        });
    }
    
    // Botones de acción en las cuentas
    const actionButtons = document.querySelectorAll('.btn-action');
    actionButtons.forEach(button => {
        button.addEventListener('click', function() {
            const action = this.dataset.action;
            const cuentaId = this.dataset.cuentaId;
            
            switch(action) {
                case 'ver':
                    console.log('Ver cuenta:', cuentaId);
                    break;
                case 'transferir':
                    console.log('Transferir desde cuenta:', cuentaId);
                    break;
                case 'pagar':
                    console.log('Pagar desde cuenta:', cuentaId);
                    break;
            }
        });
    });
});
