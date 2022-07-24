const removeBtn = document.querySelector('.btn');
const pwdInput = document.querySelector('.pwdInput');

let principal = getPrincipal();

removeBtn.onclick = () => {
		if(!confirm("정말로 회원탈퇴를 진행하시겠습니까?")){
			return false;
		}
	
	$.ajax ({
		type: "delete",
		url: `/api/v1/user/delete`,
		data: JSON.stringify({
			usercode: principal.usercode,
			password: pwdInput.value
		}),
		contentType: "application/json;charset=utf-8",
		dataType: "json",
		success: function(response){
			console.log(response.data);
			if(response.data == false) {
				alert("비밀번호가 틀렸습니다.")
			}else if(response.data == true){
				alert("회원탈퇴 완료");
				location.replace("/logout");
			}
		},
		error: function() {
			alert("비동기 처리 오류");
		}
		
	});
}