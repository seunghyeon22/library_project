const idInput = document.querySelector(".id_input");
const phoneBtn = document.querySelector(".phone_btn");
const phoneInput = document.querySelector(".modal-input");
const pwdInput = document.querySelectorAll(".modal-input2");
const okBtn1 = document.querySelector(".okBtn1");
const okBtn2 = document.querySelector(".okBtn2");
const modal1 = document.querySelector(".modal1");
const modal2 = document.querySelector(".modal2");
const modalCloseBtn = document.querySelector(".modal-header button");
const modalCloseBtn2 = document.querySelector(".modal-header2 button");



let status = false;

//아이디 체크
phoneBtn.onclick = () => {
	status = false;
	$.ajax({
		type: "get",
		url: `/api/v1/auth/signup/check?username=${idInput.value}`,
		dataType: "json",
		success: (response) => {
			console.log(JSON.stringify(response.data));
			if(response.data == false) {
				modal1.classList.toggle("modal-show");
				document.querySelector("body").classList.toggle("body-scroll-disable");
			}else {
				alert("존재하지 않는 아이디입니다.")
			}		
		},
		error: (request, status, error) => {
			console.log("error code: " + request.status);
			console.log("error message: " + request.responseText);
			console.log("error: " + error);
		}
	});
}

modalCloseBtn.onclick = () => {
	modal1.classList.toggle("modal-show");
	document.querySelector("body").classList.toggle("body-scroll-disable");
}
modalCloseBtn2.onclick = () => {
	modal2.classList.toggle("modal-content-hidden");
	document.querySelector("body").classList.toggle("body-scroll-disable");
}

//휴대폰 유효성체크
function phoneCheck(e) {
    const number = e.value.replace(/[^0-9]/g, "");
    let phone = "";

    if (number.length < 4) {
        return number;
    } else if (number.length < 7) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3);
    } else if (number.length < 10) {
        phone += number.substr(0, 2);
        phone += "-";
        phone += number.substr(2, 3);
        phone += "-";
        phone += number.substr(5);
    } else if (number.length < 11) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 3);
        phone += "-";
        phone += number.substr(6);
    } else {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 4);
        phone += "-";
        phone += number.substr(7, 4);
    }

    e.value = phone;
}


//휴대폰 번호로 인증
okBtn1.onclick = () => {
	 let idValue = document.querySelector(".id_input").value
	
	$.ajax({
		type: "get",
		url: `/api/v1/auth/searchPassword?username=${idValue}&phone=${phoneInput.value}`,
		dataType: "json",
		success: (response) => {
			console.log(JSON.stringify(response.data));
			const cellPhoneMsg = document.querySelector(".cellPhone-msg");
			if(response.data == true) {
				modal1.classList.toggle("modal-show")
				modal2.classList.toggle("modal-content-hidden");
			}else {
				if(!cellPhoneMsg.classList.contains("cellPhone-msg-error")) {
					cellPhoneMsg.classList.add("cellPhone-msg-error");
				}
				cellPhoneMsg.innerHTML = `일치하지않는 휴대폰 번호입니다.`;
			}
		},
		error: (request, status, error) => {
			console.log("error code: " + request.status);
			console.log("error message: " + request.responseText);
			console.log("error: " + error);
		}
	});
	
	
}

function isEmpty(str) {
	return typeof str == "undefined" || str == null || str ==""; 
}

//새비밀번호
okBtn2.onclick = () => {
	const cellPhoneMsg2 = document.querySelector(".cellPhone-msg2");
	let regExp = /^(?=.*[0-9])(?=.*[a-zA-Z])((?=.*\d)|(?=.*\w)).{9,20}$/; //비밀번호 유효성
	
	if(isEmpty(pwdInput[0].value) || isEmpty(pwdInput[1].value)) {
		if(!cellPhoneMsg2.classList.contains("cellPhone-msg-error")) {
			cellPhoneMsg2.classList.add("cellPhone-msg-error");
		}
		cellPhoneMsg2.innerHTML = `제대로 기입해주세요`;
		return;
	}else if(!regExp.test(pwdInput[0].value) && !regExp.test(pwdInput[1].value)) {
		if(!cellPhoneMsg2.classList.contains("cellPhone-msg-error")) {
			cellPhoneMsg2.classList.add("cellPhone-msg-error");
		}
		cellPhoneMsg2.innerHTML = `비밀번호는 영문 대소문자, 숫자, 특수문자(!@#$%^&*만 허용)를 혼용하여 9~20자이내로 작성해주세요.`;
		return;
	}else if(pwdInput[1].value != pwdInput[0].value){
		if(!cellPhoneMsg2.classList.contains("cellPhone-msg-error")) {
			cellPhoneMsg2.classList.add("cellPhone-msg-error");
		}
		cellPhoneMsg2.innerHTML = `새 비밀번호가 일치하지 않습니다.`;
		return;
	}else{
		cellPhoneMsg2.innerHTML = ``;
		let idValue = document.querySelector(".id_input").value
	
		$.ajax({
			type: "put",
			url: `/api/v1/auth/newPwd`,
			data: JSON.stringify({
				username: idValue,
				password: pwdInput[0].value
			}),
			contentType: "application/json;charset-utf8",
			dataType: "json",
			success: function(response){
				if(response.data == true) {
					alert("비밀번호 변경 완료.");
					location.replace("/auth/login");
				}
			},
			error: function() {
				alert("비동기 처리 오류");
			}
			
			
		})
	}
	
	
}

