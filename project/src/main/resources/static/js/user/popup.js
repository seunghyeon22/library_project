const phoneInput = document.querySelector("#cellPhone");
const okBtn = document.querySelector(".okBtn");
const phoneBtn = document.querySelector(".phone_btn");
const modal = document.querySelector(".modal");
const modalCloseBtn = document.querySelector(".modal-header button");

phoneBtn.onclick = () => {
	modal.classList.toggle("modal-show");
	document.querySelector("body").classList.toggle("body-scroll-disable");
}

modalCloseBtn.onclick = () => {
	modal.classList.toggle("modal-show");
	document.querySelector("body").classList.toggle("body-scroll-disable");
}

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

okBtn.onclick = () => {
	$.ajax({
		type: "get",
		url: `/api/v1/auth/searchId?phone=${phoneInput.value}`,
		dataType: "json",
		success: (response) => {
			console.log(JSON.stringify(response.data));
			const cellPhoneMsg = document.querySelector(".cellPhone-msg");
			if(response.data != null) {
				if(cellPhoneMsg.classList.contains("cellPhone-msg-error")) {
					cellPhoneMsg.classList.remove("cellPhone-msg-error");
				}
				cellPhoneMsg.innerHTML = `아이디: ${response.data}`;
				
			}else {
				if(!cellPhoneMsg.classList.contains("cellPhone-msg-error")) {
					cellPhoneMsg.classList.add("cellPhone-msg-error");
				}
				cellPhoneMsg.innerHTML = `해당 번호로 가입된 계정이 존재하지 않습니다.`;
			}
		},
		error: (request, status, error) => {
			console.log("error code: " + request.status);
			console.log("error message: " + request.responseText);
			console.log("error: " + error);
		}
	});
}