const inputBoxs = document.querySelectorAll(".input_box");
const inputEmailBoxs = document.querySelectorAll(".input_mbox");
const submitBtn = document.querySelector(".btn_ok");
const cancelBtn = document.querySelector(".btn_no");
const usernameCheckBtn = document.querySelector(".usernameCheckBtn");
const seletEmail = document.querySelector('.input_box_email');
const addressBtn = document.querySelector(".addressBtn");
const genderValue = document.getElementsByName("gender");
let usernameCheckFlag = false;

//아이디 중복확인 및 유효성 검사
usernameCheckBtn.onclick = async () => {
	let regExp1 = /^[a-zA-Z0-9]{6,20}$/; //아이디 유효성
	let result = await usernameCheck(inputBoxs[0].value);
	let submitFlag = [false, false, false]
	
	console.log(await usernameCheck(inputBoxs[0].value));
	
	if(result == true && regExp1.test(inputBoxs[0].value)) {
		alert("사용가능한 아이디입니다.");
		usernameCheckFlag = true;
	} else if(result == false){
		alert("중복되는 아이디입니다.");
	 	usernameCheckFlag = false;
	} else {
		alert("형식에 맞춰서 ID를 입력해주세요.");
		usernameCheckFlag = false;
	}
}

// 비밀번호 유효성 
inputBoxs[1].onchange = () => {
	let regExp2 = /^(?=.*[0-9])(?=.*[a-zA-Z])((?=.*\d)|(?=.*\w)).{9,20}$/; //비밀번호 유효성
	
	if(!regExp2.test(inputBoxs[1].value)) {
		alert("형식에 맞춰서 PW를 입력해주세요.");
		return false;
	}else {
		alert("사용가능한 비밀번호입니다.")
	}
}

// 비밀번호확인
inputBoxs[2].onchange = () => {
	if(inputBoxs[2].value != inputBoxs[1].value) {
		alert("비밀번호가 같지않습니다.");
		return false;
	}
}

// 이름 유효성 검사
inputBoxs[3].onchange = () => {
	let regExp3 = /^[가-힝]{2,}$/; //이름
	if(!regExp3.test(inputBoxs[3].value)) {
		alert("이름은 제대로 입력해주세요.");
		return false;
	}
}

// 휴대폰 유효성
inputBoxs[7].onchange = () => {
	let regExp4 = /^010-?([0-9]{3,4})-?([0-9]{4})$/;
	if(!regExp4.test(inputBoxs[7].value)) {
		alert("휴대폰번호를 제대로 입력해주세요.")
		return false;
	}
	return true;		
}

// 이메일 유효성
inputEmailBoxs[0].onchange = () => {
	let regExp5 = /^[a-z0-9]{2,}$/; //이메일
	if(!regExp5.test(inputEmailBoxs[0].value)) {
		alert("제대로 입력해주세요.");
		return false;
	}
}

inputEmailBoxs[1].onchange = () => {
	let regExp6 = /^[a-z0-9-]{2,}\.[a-z0-9]{2,}$/i; //이메일 뒷부분
	if(!regExp6.test(inputEmailBoxs[1].value)) {
		alert("제대로 입력해주세요.")
		return false;
	}
}

// 셀렉값 선택시 inputBoxs[12] 값 변경
seletEmail.onchange = () => {
	inputEmailBoxs[1].value = seletEmail.selectedIndex == 0 ? "" : seletEmail.options[seletEmail.selectedIndex].text;
}



submitBtn.onclick = async () => {
	const url = `/api/v1/auth/signup`;
	const bodyObj = {
		username: inputBoxs[0].value,
		password: inputBoxs[1].value,
		name: inputBoxs[3].value,
		gender: genderValue[0].checked == true ? 0 : 1,
		birth: inputBoxs[6].value,
		phone: inputBoxs[7].value,
		address1: inputBoxs[8].value,
		address2: inputBoxs[9].value,
		address3: inputBoxs[10].value,
		email: inputEmailBoxs[0].value +"@"+inputEmailBoxs[1].value
	};
	const option = {
		method: "POST",
		headers: {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify(bodyObj)
	}
	if(usernameCheckFlag == false) {
		alert("중복확인을 검사해주세요. ");
		return;
	}
	await request(url, option)
	.then(result => {
		return result.data;
	})
	.then(data => {
		alert("회원가입 성공");
		location.href= "/auth/login"
	})
	.catch(error => {
		console.log(error);
		alert("정보를 제대로 기입했는지 확인해주세요.");
	});
}

cancelBtn.onclick = () => {
	location.href= "/mainpage";
}



//아이디 체크
async function usernameCheck(username) {
	const url = `/api/v1/auth/signup/check?username=${username}`
	
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

async function request(url, option) {
	const response = await fetch(url, option);
	
	if(response.ok){
		return response.json();
	}else{
		throw new Error("response Error: " + response);
	}
}



// 다음 우편번호 서비스
addressBtn.onclick = sample6_execDaumPostcode;

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
            } 

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

























