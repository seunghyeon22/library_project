const pwdInput = document.querySelectorAll(".pwdInput");
const submitBtn = document.querySelector(".btn");

let usercode = 0;
let principal = getPrincipal();

let flag = [false, false]

pwdInput[1].onchange = () => {
	let regExp = /^(?=.*[0-9])(?=.*[a-zA-Z])((?=.*\d)|(?=.*\w)).{9,20}$/; //비밀번호 유효성
	
	if(!regExp.test(pwdInput[1].value)) {
		alert("형식에 맞춰서 PW를 입력해주세요.");
		flag = false;
	}else {
		alert("사용가능한 비밀번호입니다.");
		flag = true;
	}
}

pwdInput[2].onchange = () => {
	if(pwdInput[2].value != pwdInput[1].value) {
		alert("새 비밀번호와 일치하지 않습니다.");
		flag = false;
	}else {
		flag = true;
	}
}

function isEmpty(str) {
	return typeof str == "undefined" || str == null || str ==""; 
}

submitBtn.onclick = () => {
	if(isEmpty(pwdInput[0].value) ||
		isEmpty(pwdInput[1].value) ||
		isEmpty(pwdInput[2].value)) {
			alert("제대로 기입해주세요.");
	} else if(flag != true) {
		alert("제대로 기입해주세요.");
	}
	$.ajax ({
		type: "put",
		url: `/api/v1/user/updatePwd`,
		data: JSON.stringify({
			usercode: principal.usercode,
			nowPassword: pwdInput[0].value,
			newPassword: pwdInput[1].value,
		}),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(response){
			console.log(response.data);
			if(response.data == 0) {
				alert("새비밀번호가 기존 비밀번호와 중복됩니다.");
			}else if(response.data == 1){
				alert("비밀번호 변경 완료. 다시 로그인 하세요.");
				location.replace("/logout");
				
			}else if(response.data == 2){
				alert("기존 비밀번호가 틀립니다.")
			}
		}, 
		error:function() {
			alert("비동기 처리 오류");
		}
		
	});
}

