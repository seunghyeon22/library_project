const boardView = document.querySelector('.board_view');

const btWrap = document.querySelector('.bt_wrap');

let path = window.location.pathname;
let noticecode = path.substring(path.lastIndexOf('/') + 1);
let principalAdmin = getPrincipal();

load();

function load() {
	let url = `/api/v1/notice/${noticecode}`;
	
	fetch(url)
	.then(response => {
		if(response.ok) {
			return response.json();
		}else {
			throw new Error("비동기 처리 오류");
		}
	})
	.then(result => {
		getBoardList(result);
		getButtton(result);
		getUpdateBtn();
	})
	.catch(error => {
		console.log(error);
	})
}

function getButtton() {
	if(principalAdmin != null){
		if(principalAdmin.roles == "ROLE_ADMIN,ROLE_USER") {
			btWrap.innerHTML = `
				<button type="button" class="update_btn">수정</button>
				<button type="button" class="delete_btn">삭제</button>
          		<button type="button" class="list_btn" onclick="location.href = '/book/notice/'">목록</button>
			`
		}
	}
	
}

function getUpdateBtn() {
	const updateBtn = document.querySelector('.update_btn');
	
	updateBtn.onclick = () => {
		location.href = "/book/notice/" + noticecode;
	}
}


function getBoardList(data) {
	boardView.innerHTML += `
		<div class="title">
            ${data[0].notice_title}
        </div>
        <div class="info">
            <dl>
                <dt>번호</dt>
                <dd>${data[0].noticecode}</dd>
            </dl>
            <dl>
                <dt>글쓴이</dt>
                <dd>${data[0].notice_username}</dd>
            </dl>
            <dl>
                <dt>작성일</dt>
                <dd>${data[0].create_date}</dd>
            </dl>
        </div>
        <div class="cont">
            ${data[0].notice_contents}
        </div>
	`;
}
