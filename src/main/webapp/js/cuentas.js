let currentAccount = '';
let currentAccountNumber = '';
let movementsData = [];
let movementsTable;

// Función principal que se ejecuta al hacer clic en "Ver Movimientos"
function viewMovements(accountId, accountNumber) {
    // Asignar valores a las variables
    currentAccount = accountId;
    currentAccountNumber = accountNumber;

    // Actualizar la interfaz
    document.getElementById('selectedAccount').textContent = accountNumber;
    document.getElementById('movementsSection').classList.add('active');

    // Cargar los movimientos
    loadMovements();
}

function closeMovements() {
    document.getElementById('movementsSection').classList.remove('active');
    
    // Destruir DataTable si existe
    if (movementsTable) {
        movementsTable.destroy();
        movementsTable = null;
    }
    
    // Limpiar completamente la tabla
    const tbody = document.getElementById('movementsTableBody');
    tbody.innerHTML = '';
    
    currentAccount = '';
    currentAccountNumber = '';
    movementsData = [];
}

function loadMovements() {
    if (!currentAccount) {
        return;
    }

    let url = 'ServletMovimientos?idCuenta=' + currentAccount;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error del servidor: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            movementsData = data;
            displayMovements();
        })
        .catch(error => {
            alert('Error al cargar movimientos: ' + error.message);
        });
}

function displayMovements() {
    // Destruir DataTable si existe
    if (movementsTable) {
        movementsTable.destroy();
        movementsTable = null;
    }
    
    // Limpiar completamente el tbody
    const tbody = document.getElementById('movementsTableBody');
    tbody.innerHTML = '';

    if (movementsData.length === 0) {
        const row = document.createElement('tr');
        row.innerHTML = '<td colspan="5" style="text-align: center;">No hay movimientos para mostrar</td>';
        tbody.appendChild(row);
        return; // No inicializar DataTables si no hay datos
    }

    // Llenar la tabla con datos NUEVOS
    movementsData.forEach(movement => {
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

    // Inicializar DataTables después de llenar los datos
    setTimeout(() => {
        movementsTable = $('#movementsTable').DataTable({
            paging: true,
            pageLength: 10,
            lengthMenu: [5, 10, 15, 20],
            searching: true,
            ordering: true,
            order: [[0, 'desc']],
            language: {
                search: "Buscar:",
                lengthMenu: "Mostrar _MENU_ ",
                info: "_START_ a _END_ de _TOTAL_ registros",
                infoEmpty: "0 registros",
                paginate: {
                    first: "Primero",
                    last: "Último",
                    next: "Siguiente",
                    previous: "Anterior"
                },
                emptyTable: "No hay movimientos disponibles"
            },
            columnDefs: [
                {
                    targets: [3, 4],
                    type: 'num-fmt'
                }
            ]
        });
    }, 100);
}

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('es-AR');
}

function capitalizeFirst(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

// Inicialización cuando se carga la página
document.addEventListener('DOMContentLoaded', function() {
    // Agrega event listeners a los botones de "Ver Movimientos"
    document.querySelectorAll('.ver-movimientos').forEach(button => {
        button.addEventListener('click', function() {
            const accountId = this.getAttribute('data-id');
            const accountNumber = this.getAttribute('data-numero');
            viewMovements(accountId, accountNumber);
        });
    });
});