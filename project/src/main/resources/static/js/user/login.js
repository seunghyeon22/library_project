const loginInputs = document.querySelectorAll(".login_input input");
const loginBtn = document.querySelector("button");

let submitFlag = [false, false];


function isEmpty(str){
	return str =="" || str == null || typeof(str) == "undefined";
}


for(let i = 0; i < loginInputs.length; i++){
	loginInputs[i].onkeyup = () => {
	if(window.event.keyCode == 13) {
		window.event.preventDefault =false;
		loginBtn.click();
		}
	}
	
}

loginBtn.onclick = () => {
	if(isEmpty(loginInputs[0].value)) {
		alert("아이디를 입력해주세요.");
		submitFlag[0] = false;
	} else if(isEmpty(loginInputs[1].value)) {
		alert("비밀번호를 입력해주세요.");
		submitFlag[1] = false;
	} else {
		submitFlag.forEach(value => {
			value = true;
		});
		document.querySelector('form').submit();
	}
}


async function usernameCheck(username) {
	const url = `/api/v1/login/username?username=${username}`
	
	let responseData = false;
	
	await request(url)
	.then(result => {
		console.log("-> " + result);
		responseData = result.data;
	})
	.catch(error => {
		console.log(error);
	})
	
	return responseData;
}


