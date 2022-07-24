const boardView = document.querySelector('.board_view');
const btWrap = document.querySelector('.bt_wrap');

let path = window.location.pathname;
let boardcode = path.substring(path.lastIndexOf("/") + 1);
let principalUsername = getPrincipal();

load();

function load() {
	let url = `/api/v1/board/${boardcode}`;
	
	fetch(url)
	.then(response => {
		if(response.ok){
			return response.json();
		}else {
			throw new Error("비동기 처리 오류");
		}
	})
	.then(result => {
		getBoardlist(result);
		getButton(result);
		boardBtns();
	})
	.catch(error => {
		console.log(error);
	})
}

function getBoardlist(data) {
	boardView.innerHTML += `
        <div class="title">
        	${data[0].board_title}     
        </div>
        <div class="info">
        	<dl>
	            <dt>번호</dt>
	            <dd>${data[0].boardcode}</dd>
	        </dl>
	        <dl>
	            <dt>글쓴이</dt>
	            <dd>${data[0].board_username}</dd>
	        </dl>
	        <dl>
	            <dt>작성일</dt>
	            <dd>${data[0].create_date}</dd>
	        </dl>
        </div>
        <div class="cont">
        	${data[0].board_contents}
        </div>
	`;
}

function getButton(data) {
	if(principalUsername != null){
		if(principalUsername.username == data[0].board_username){
			btWrap.innerHTML = `
				<button type="button" class="update_btn">수정</button>
				<button type="button" class="delete_btn">삭제</button>
                <button type="button" class="list_btn" onclick="location.href = '/book/bulletin/'">목록</button>
			`
		}else if(principalUsername.roles == "ROLE_ADMIN,ROLE_USER" ){
			btWrap.innerHTML = `
				<button type="button" class="update_btn">수정</button>
				<button type="button" class="delete_btn">삭제</button>
                <button type="button" class="list_btn" onclick="location.href = '/book/bulletin/'">목록</button>
			`
		}
	}
}

function boardBtns() {
	const updateBtn = document.querySelector('.update_btn');
	const deleteBtn = document.querySelector('.delete_btn');
	
	updateBtn.onclick = () => {
		location.href = "/book/bulletin/" + boardcode;
	}
	
	deleteBtn.onclick = () => {
		let flag = confirm("정말로 삭제하시겠습니까?")
		if(flag == true){
			$.ajax({
				type : 'delete',
				url : `/api/v1/board/${boardcode}`,
				success : function(result){
					location.href = "/book/bulletin"
				},
				error: function(){
					alert("비동기 처리 오류");
				}
				
			})
		}
	}
}
