const inputDatas = document.querySelectorAll(".input-datas");
const inputText = document.querySelector(".input-text");
const subBtn = document.querySelector(".sub-btn");
const deleteBtn = document.querySelector(".del-btn");

let fileInput = document.getElementById("book-file-img");

fileInput.addEventListener('change', function(e){
	
	let file = e.target.files[0];
	let reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload =() =>{
		let img = document.querySelector(".img_load");
		img.src = URL.createObjectURL(file);
		
	}
})


let submitFlag = [false, false, false, false, false, false];

function isEmpty(str) {
	return str == "" || str == null || typeof(str) == "undefined";
}

subBtn.onclick = () => {

	if (isEmpty(inputDatas[0].value)) {
		alert("코드를 입력해주세요.");
		submitFlag[0] = false;
	} else if (isEmpty(inputDatas[1].value)) {
		alert("청구기호를 입력해주세요.");
		submitFlag[1] = false;
	} else if (isEmpty(inputDatas[2].value)) {
		alert("제목을 입력해주세요.");
		submitFlag[2] = false;
	} else if (isEmpty(inputDatas[3].value)) {
		alert("저자를 입력해주세요.");
		submitFlag[3] = false;
	} else if (isEmpty(inputDatas[4].value)) {
		alert("출판사를 입력해주세요.");
		submitFlag[4] = false;
	} else if (isEmpty(inputDatas[5].value)) {
		alert("출판년도를 입력해주세요.");
		submitFlag[5] = false;
	} else {
		for(let i = 0; i < submitFlag.length; i++){
			submitFlag[i] = true;
		}
		submitRequest();
	}
	
}


function submitRequest() {
	if(!submitFlag.includes(false)){
		let formData = new FormData(document.querySelector('form'));
		
		$.ajax({
			type: "post",
			url: "/api/v1/book/admain",
			data: formData,
			enctype: "multipart/form-data",
			processData: false,
			contentType: false,
			success: (response) => {
				alert("도서 추가 완료.");
				location.replace("/book/bookinsert");
			},
			error: (request, status, error) => {
				console.log("error code: " + request.status);
				console.log("error message: " + request.responseText);
				console.log("error: " + error);
			}
		});
	}
}

inputDatas[0].onblur = () => {
	if(isEmpty(inputDatas[0].value)){
		alert("코드를 입력해주세요.");
		submitFlag = false;
	}else{
		submitFlag[0] = checkBookCode();
	}
	
}

function checkBookCode() {
	let result = false;
	$.ajax({
			type: "get",
			url: "/api/v1/book/admain/code/check",
			data: {
				"bookcode": inputDatas[0].value
			},
			dataType: "json",
			success: function(response) {
				console.log(response.data)
				if (response.data == false) {
					alert("이미 존재하는 코드입니다.");
					result = false;
				} else {
					alert("사용 가능한 코드입니다.");
					result = true;
				}
			},
			error: function(data) {
				alert("비동기 통신 오류");
			}
		});
	return result;
}

deleteBtn.onclick=()=>{
	location.replace("/book/bookselect");
}


