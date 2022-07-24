const submitBtn = document.querySelector('.submit_btn');
const inputItems = document.querySelectorAll('.input_items');
const textareaItem = document.querySelector('.textarea_item');
const backBtn = document.querySelector('.back_btn');

submitBtn.onclick = () => {
	submit();
}

backBtn.onclick = () => {
	location.href = "/book/notice";
}

function submit() {
	let url = "/api/v1/notice/post"
	
	let option = {
		method: "POST",
		headers: {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify({
			notice_title: inputItems[0].value,
			notice_username: inputItems[1].value,
			notice_contents: textareaItem.value
		})
	};
	
	fetch(url, option)
	.then(response => {
		if(response.ok){
			return response.json();
		}else{
			throw new Error("정상적인 데이터를 응답받지 못했습니다.");
		}
	})
	.then(data => {
		console.log(data)
		location.href = "/book/noticeBoard/" + data;
	})
}