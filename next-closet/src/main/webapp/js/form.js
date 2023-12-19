
		const formButtonOpen = document.getElementById('formModalOpen');
		const formModal = document.getElementById('formEasyModal');
		const formButtonClose = document.getElementsByClassName('formModalClose')[0];
	
		// ボタンがクリックされた時
		formButtonOpen.addEventListener('click', modalOpen);
		function modalOpen() {
		  formModal.style.display = 'block';
		}
	
		// バツ印がクリックされた時
		formModal.addEventListener('click', modalClose);
		function modalClose() {
		  formModal.style.display = 'none';
		}
	
		// モーダルコンテンツ以外がクリックされた時
		addEventListener('click', outsideClose);
		function outsideClose(e) {
		  if (e.target == formModal) {
		    formModal.style.display = 'none';
		  }
		}
