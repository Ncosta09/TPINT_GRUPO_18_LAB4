document.addEventListener('DOMContentLoaded', function () {
    const loanForm = document.getElementById('loanForm');
    const cancelBtn = document.getElementById('cancelBtn');
    const notification = document.getElementById('notification');
    const montoSolicitado = document.getElementById('montoSolicitado');
    const cantidadCuotas = document.getElementById('cantidadCuotas');
    const calculationResult = document.getElementById('calculationResult');

    cancelBtn.addEventListener('click', function () {
        window.location.href = 'homeCliente.jsp';
    });

    function calculateLoan() {
        const monto = parseFloat(montoSolicitado.value);
        const cuotas = parseInt(cantidadCuotas.value);

        if (monto && cuotas) {
            const tasaMensual = 0.24 / 12;
            const cuotaValor = monto * (tasaMensual * Math.pow(1 + tasaMensual, cuotas)) / (Math.pow(1 + tasaMensual, cuotas) - 1);
            const totalAPagar = cuotaValor * cuotas;

            document.getElementById('resumenMonto').textContent = '$' + monto.toLocaleString('es-AR', { minimumFractionDigits: 2 });
            document.getElementById('resumenCuotas').textContent = cuotas;
            document.getElementById('resumenCuotaValor').textContent = '$' + cuotaValor.toLocaleString('es-AR', { minimumFractionDigits: 2 });
            document.getElementById('resumenTotal').textContent = '$' + totalAPagar.toLocaleString('es-AR', { minimumFractionDigits: 2 });

            calculationResult.classList.add('show');
        } else {
            calculationResult.classList.remove('show');
        }
    }

    montoSolicitado.addEventListener('input', calculateLoan);
    cantidadCuotas.addEventListener('change', calculateLoan);

    loanForm.addEventListener('submit', function (e) {
        e.preventDefault();
        let isValid = true;
        const monto = parseFloat(montoSolicitado.value);

        if (!monto || monto < 10000 || monto > 500000) {
            document.getElementById('monto-error').style.display = 'block';
            isValid = false;
        } else {
            document.getElementById('monto-error').style.display = 'none';
        }

        if (!cantidadCuotas.value) {
            document.getElementById('cuotas-error').style.display = 'block';
            isValid = false;
        } else {
            document.getElementById('cuotas-error').style.display = 'none';
        }

        if (!document.getElementById('cuentaDestino').value) {
            document.getElementById('cuenta-error').style.display = 'block';
            isValid = false;
        } else {
            document.getElementById('cuenta-error').style.display = 'none';
        }

        if (isValid) {
            const submitBtn = document.getElementById('submitBtn');
            submitBtn.disabled = true;
            submitBtn.textContent = 'Enviando...';

            setTimeout(function () {
                notification.style.display = 'block';

                setTimeout(function () {
                    notification.style.display = 'none';
                    window.location.href = 'homeCliente.jsp';
                }, 3000);
            }, 2000);
        }
    });
});
