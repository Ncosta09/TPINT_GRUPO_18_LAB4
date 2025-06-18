const burgerMenu = document.getElementById('burgerMenu');
const sidebar = document.getElementById('sidebar');
const overlay = document.getElementById('overlay');
const clientForm = document.getElementById('clientForm');
const cancelBtn = document.getElementById('cancelBtn');
const notification = document.getElementById('notification');

burgerMenu.addEventListener('click', function () {
    sidebar.classList.toggle('active');
    overlay.classList.toggle('active');
});

overlay.addEventListener('click', function () {
    sidebar.classList.remove('active');
    overlay.classList.remove('active');
});

cancelBtn.addEventListener('click', function () {
    window.location.href = '#'; //Cambiar al correcto luego
});

clientForm.addEventListener('submit', function (e) {
    e.preventDefault();

});
