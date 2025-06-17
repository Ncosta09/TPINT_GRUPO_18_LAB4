const burgerMenu = document.getElementById('burgerMenu');
      const sidebar = document.getElementById('sidebar');
      const overlay = document.getElementById('overlay');
      
      burgerMenu.addEventListener('click', function() {
          sidebar.classList.toggle('active');
          overlay.classList.toggle('active');
      });
      
      overlay.addEventListener('click', function() {
          sidebar.classList.remove('active');
          overlay.classList.remove('active');
      });