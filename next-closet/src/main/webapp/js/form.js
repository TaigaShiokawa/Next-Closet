
		const buttonOpen = document.getElementById('formModalOpen');
		const modal = document.getElementById('formEasyModal');
		const buttonClose = document.getElementsByClassName('formModalClose')[0];
	
		// ボタンがクリックされた時
		buttonOpen.addEventListener('click', modalOpen);
		function modalOpen() {
		  modal.style.display = 'block';
		}
	
		// バツ印がクリックされた時
		buttonClose.addEventListener('click', modalClose);
		function modalClose() {
		  modal.style.display = 'none';
		}
	
		// モーダルコンテンツ以外がクリックされた時
		addEventListener('click', outsideClose);
		function outsideClose(e) {
		  if (e.target == modal) {
		    modal.style.display = 'none';
		  }
		}