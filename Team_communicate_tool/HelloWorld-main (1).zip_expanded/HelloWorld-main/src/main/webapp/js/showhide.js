    function openModal() {
      var modal = document.getElementById('test5');
      modal.classList.remove('hide');
      modal.classList.add('show');
    }
  
    function closeModal() {
      var modal = document.getElementById('test5');
      modal.classList.remove('show');
      modal.classList.add('hide');
    }
		function closeModal() {
			var modal = document.getElementById('todoPopUp');
			modal.classList.remove('showTodo');
			modal.classList.add('none');
		}
		function show() {
			document.querySelector(".background_modal").className = "background_modal show_modal";
		}

		function close() {
			document.querySelector(".background_modal").className = "background_modal";
		}

		document.querySelector("#show_modal").addEventListener('click', show);
		document.querySelector("#close").addEventListener('click', close);