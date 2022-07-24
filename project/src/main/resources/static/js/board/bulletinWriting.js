const submitBtn = document.querySelector('.submit_btn');
const inputItems = document.querySelectorAll('.input_items');
const textareaItem = document.querySelector('.textarea_item');
const backBtn = document.querySelector('.back_btn');

let principal = getPrincipal();

load();

function load() {
	$.ajax({
		type : "get",
		url : "/api/v1/user/authentication",
		data : {
			"usercode" : principal.usercode
		},
		dataType : "json",
		success : function(response){
			console.log(JSON.stringify(response))
			usernameLoad(response.data);
		},
		error : function(){
			alert("비동기통신 오류")
		}
	})
}

function usernameLoad(principal) {
	let user = principal.user;
	usercode = principal.usercode
	inputItems[1].value = user.username 
}

submitBtn.onclick = () => {
	submit();
}

backBtn.onclick = () => {
	location.href = "/book/bulletin";
}

function submit() {
	let url = "/api/v1/board/post"
	
	let option = {
		method: "POST",
		headers: {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify({
			board_title: inputItems[0].value,
			board_username: inputItems[1].value,
			board_contents: textareaItem.value
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
		location.href = "/book/bulletinBoard/" + data;
	})
}