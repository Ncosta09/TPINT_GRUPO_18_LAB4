// Declarar TODAS las variables al inicio
let currentPage = 1;
let totalPages = 1;
let currentAccount = '';
let currentAccountNumber = '';
let movementsData = [];

// Función principal que se ejecuta al hacer clic en "Ver Movimientos"
function viewMovements(accountId, accountNumber) {
    
    // Asignar valores a las variables
    currentAccount = accountId;
    currentAccountNumber = accountNumber;
    currentPage = 1;
    
    // Actualizar la interfaz
    document.getElementById('selectedAccount').textContent = accountNumber;
    document.getElementById('movementsSection').classList.add('active');
    
    // Cargar los movimientos
    loadMovements();
}

function closeMovements() {
    document.getElementById('movementsSection').classList.remove('active');
    currentAccount = '';
    currentAccountNumber = '';
    movementsData = [];
}

function loadMovements() {
    if (!currentAccount) {
        return;
    }
    
    let url = 'ServletMovimientos?idCuenta=' + currentAccount;
    
    const tipoMovimiento = document.getElementById('movementType').value;
    const fechaDesde = document.getElementById('startDate').value;
    const fechaHasta = document.getElementById('endDate').value;
    
    if (tipoMovimiento) {
        url += '&tipoMovimiento=' + encodeURIComponent(tipoMovimiento);
    }
    if (fechaDesde) {
        url += '&fechaDesde=' + fechaDesde;
    }
    if (fechaHasta) {
        url += '&fechaHasta=' + fechaHasta;
    }
    
    console.log('URL final:', url);
    
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error del servidor: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            movementsData = data;
            currentPage = 1;
            displayMovements();
        })
        .catch(error => {
            alert('Error al cargar movimientos: ' + error.message);
        });
}

function filterMovements() {
    loadMovements();
}

function displayMovements() {
    const itemsPerPage = 10;
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const pageData = movementsData.slice(startIndex, endIndex);

    totalPages = Math.ceil(movementsData.length / itemsPerPage);

    const tbody = document.getElementById('movementsTableBody');
    tbody.innerHTML = '';

    if (pageData.length === 0) {
        const row = document.createElement('tr');
        row.innerHTML = '<td colspan="5" style="text-align: center;">No hay movimientos para mostrar</td>';
        tbody.appendChild(row);
    } else {
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
    }

    updatePagination();
}

function updatePagination() {
    const pageInfo = document.getElementById('pageInfo');
    const prevBtn = document.querySelector('.pagination button:first-child');
    const nextBtn = document.querySelector('.pagination button:last-child');

    if (pageInfo) pageInfo.textContent = `Página ${currentPage} de ${totalPages}`;
    if (prevBtn) prevBtn.disabled = currentPage === 1;
    if (nextBtn) nextBtn.disabled = currentPage === totalPages || totalPages === 0;
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

function clearFilters() {
    document.getElementById('movementType').value = '';
    document.getElementById('startDate').value = '';
    document.getElementById('endDate').value = '';
    if (currentAccount) {
        loadMovements();
    }
}

// Inicialización cuando se carga la página
document.addEventListener('DOMContentLoaded', function() {
    
    const filterContainer = document.querySelector('.filter-container');
    if (filterContainer) {
        const clearButton = document.createElement('button');
        clearButton.textContent = 'Limpiar';
        clearButton.className = 'btn btn-secondary';
        clearButton.onclick = clearFilters;
        filterContainer.appendChild(clearButton);
    }
	
	// Agrega event listeners a los botones "Ver Movimientos"
	document.querySelectorAll('.ver-movimientos').forEach(button => {
	    button.addEventListener('click', function() {
	        const accountId = this.getAttribute('data-id');
	        const accountNumber = this.getAttribute('data-numero');
	        viewMovements(accountId, accountNumber);
	    });
	});	
});
