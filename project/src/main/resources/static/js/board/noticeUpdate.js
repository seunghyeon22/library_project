const submitBtn = document.querySelector('.submit_btn');
const backBtn = document.querySelector('.back_btn');
const inputItems = document.querySelectorAll('.input_items');
const textareaItem = document.querySelector('.textarea_item');

let path = window.location.pathname;

load();

function load() {
	let noticecode = path.substring(path.lastIndexOf("/") + 1);
	let url = "/api/v1/notice/" + noticecode;
	
	fetch(url)
	.then(response => {
		if(response.ok){
			return response.json();
		}else{
			throw new Error("비동기 처리 오류");
		}
	})
	.then(result => {
		getBoardDtl(result);
	})
	.catch(error => {
		console.log(error);
	});
}

function getBoardDtl(data){
	inputItems[0].value = data[0].notice_title;
	inputItems[1].value = data[0].notice_username;
	textareaItem.value = data[0].notice_contents;
}

submitBtn.onclick = () => {
	submit();
}

backBtn.onclick = () => {
	location.href = "/book/notice/";
}

function submit() {
	let boardcode = path.substring(path.lastIndexOf("/") + 1);
	let url = `/api/v1/notice/${boardcode}`;
	
	let option = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			notice_title: inputItems[0].value,
			notice_contents: textareaItem.value
		})
	};
	
	fetch(url, option)
	.then(response => {
		if(response.ok){
			return response.json();
		}else {
			throw new Error("정상적인 데이터를 응답받지 못했습니다.");
		}
	})
	.then(data => {
		location.href = "/book/noticeBoard/" + data;
	})
}
