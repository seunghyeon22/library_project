const submitBtn = document.querySelector('.submit_btn');
const backBtn = document.querySelector('.back_btn');
const inputItems = document.querySelectorAll('.input_items');
const textareaItem = document.querySelector('.textarea_item');

let path = window.location.pathname;

load();

function load() {
	let qnadcode = path.substring(path.lastIndexOf("/") + 1);
	let url = "/api/v1/qnaboard/" + qnadcode;
	
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
	inputItems[0].value = data[0].qna_title;
	inputItems[1].value = data[0].qna_username;
	textareaItem.value = data[0].qna_contents;
}

submitBtn.onclick = () => {
	submit();
}

backBtn.onclick = () => {
	location.href = "/book/qAndA/";
}

function submit() {
	let qnacode = path.substring(path.lastIndexOf("/") + 1);
	let url = `/api/v1/qnaboard/${qnacode}`;
	
	let option = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			qna_title: inputItems[0].value,
			qna_contents: textareaItem.value
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
		location.href = "/book/qAndABoard/" + data;
	})
}