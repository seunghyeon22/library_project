const inputBoxs = document.querySelectorAll(".input-box");
const addressBtn = document.querySelector(".addressBtn");
const inputEmailBoxs = document.querySelectorAll(".input_mbox");
const seletEmail = document.querySelector('.input_box_email');
const submitBtn = document.querySelector('.btn_ok');

let principal = getPrincipal();

load();

function load(){
	$.ajax({
		type: "get",
		url: "/api/v1/user/authentication",
		data: {
			usercode : principal.usercode
		},
		dataType: "json",
		success: function(response){
			pageLoad(response.data);
			console.log(response.data);
		},
		error: function() {
			alert("비동기 처리 오류")
		}
	})
}


//get
function pageLoad(principal) {
	let user = principal.user;
	/*let gender = inputBoxs[2].value = user.gender;*/
	
	usercode = principal.usercode;
	inputBoxs[0].value = user.username;
	inputBoxs[1].value = user.name;
	inputBoxs[3].value = user.birth;
	inputBoxs[4].value = user.phone;
	inputBoxs[5].value = user.address1;
	inputBoxs[6].value = user.address2;
	inputBoxs[7].value = user.address3;
	inputEmailBoxs[0].value = user.email.split("@")[0];
	inputEmailBoxs[1].value = user.email.split("@")[1];
	
	if(user.gender == "0"){
		inputBoxs[2].value = "남";
	} else {
		inputBoxs[2].value = "여";
	}
	 
}

function isEmpty(str) {
	return typeof str == "undefined" || str == null || str ==""; 
}


submitBtn.onclick = () => {
	if(isEmpty(inputBoxs[4].value) || 
		isEmpty(inputBoxs[5].value) || 
		isEmpty(inputBoxs[6].value)) {
		alert("제대로 기입해주세요.");
		return;
	}
	
	account = createAccount();
	$.ajax({
		type: "put",
		url: `/api/v1/user/update`,
		data: JSON.stringify(account),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(response) {
			if(response.data == true) {
				alert("회원정보 변경 완료.");
				location.replace("/user/mypage");
			}
		},
		error:function() {
			alert("비동기 처리 오류");
		}
	});
}

//put
function createAccount() {
	let account = {
		usercode: principal.usercode,
		phone: inputBoxs[4].value,
		address1: inputBoxs[5].value,
		address2: inputBoxs[6].value,
		address3: inputBoxs[7].value,
		email: inputEmailBoxs[0].value + "@" + inputEmailBoxs[1].value
		
	}
	return account;
}


// 휴대폰 유효성
inputBoxs[4].onchange = () => {
	let regExp4 = /^010-?([0-9]{3,4})-?([0-9]{4})$/;
	if(!regExp4.test(inputBoxs[4].value)) {
		alert("휴대폰번호를 제대로 입력해주세요.")
		return false;
	}
	return true;	
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
    
    
    
// 셀렉값 선택시 inputBoxs[12] 값 변경
seletEmail.onchange = () => {
	inputEmailBoxs[1].value = seletEmail.selectedIndex == 0 ? "" : seletEmail.options[seletEmail.selectedIndex].text;
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



















