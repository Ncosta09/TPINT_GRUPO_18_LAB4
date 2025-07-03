document.addEventListener('DOMContentLoaded', function() {
    const burgerMenu = document.getElementById('burgerMenu');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay');
    const paymentModal = document.getElementById('paymentModal');
    const closeModal = document.getElementById('closeModal');
    const cancelPayment = document.getElementById('cancelPayment');

    burgerMenu.addEventListener('click', function() {
        sidebar.classList.toggle('active');
        overlay.classList.toggle('active');
    });

    overlay.addEventListener('click', function() {
        sidebar.classList.remove('active');
        overlay.classList.remove('active');
    });

    window.pagarCuota = function(idCuota, numeroCuota, monto) {
        document.getElementById('idCuota').value = idCuota;
        document.getElementById('paymentInstallment').textContent = numeroCuota;
        document.getElementById('paymentAmount').textContent = parseFloat(monto).toLocaleString();
        paymentModal.style.display = 'flex';
    };

    closeModal.addEventListener('click', function() {
        paymentModal.style.display = 'none';
        document.getElementById('paymentAccount').value = '';
    });

    cancelPayment.addEventListener('click', function() {
        paymentModal.style.display = 'none';
        document.getElementById('paymentAccount').value = '';
    });

    window.addEventListener('click', function(event) {
        if (event.target === paymentModal) {
            paymentModal.style.display = 'none';
            document.getElementById('paymentAccount').value = '';
        }
    });

    if ($.fn.DataTable) {
        $('#installmentsTable').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json'
            },
            pageLength: 10,
            order: [[0, 'asc']],
            columnDefs: [
                { orderable: false, targets: [5] } // Deshabilitar ordenamiento en columna de acciones
            ]
        });
    }
});