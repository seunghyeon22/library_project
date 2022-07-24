const bookdtls = document.querySelector(".info");
const bookImg = document.querySelector(".noImg")
let path = window.location.pathname;
let bookcode = path.substring(path.lastIndexOf("/") + 1);

let principal = getPrincipal();

load();


function load(){
	$.ajax({
		type: "get",
		url: `/api/v1/book/all/${bookcode}`,
		data: {
			"bookcode": bookcode
		},
		dataType: "json",
		async: false,
		success: function(response) {
			let bookObject = response.data;
			getBookDtl(bookObject);
			console.log(bookObject);
			
		},
		error: function() {
			alert("비동기 처리 오류");
		}

	});
}

function getBookDtl(data) {
	bookImgLoad(data);
	bookdtls.innerHTML = `
		<ul>
       		<li style="line-height: 150%;"><b>제목 : ${data.bookname}</b></li>
       		<li><strong>코드 : ${data.bookcode}</strong></li>
      		<li><strong>청구기호 : ${data.call_sign}</strong></li>
       		<li><strong>저자 : ${data.author}</strong></li>
       		<li><strong>출판사 : ${data.publisher}</strong></li>
       		<li><strong>출판년도 : ${data.year_of_publication}</strong></li>
       		<li><strong>줄거리 </strong><textarea name="summary" class="input-text" disabled> ${data.summary}</textarea></li>  		
     	</ul> 
	`;
	
	const bookdtlsUl = bookdtls.querySelector("ul");
	const buttonLi = document.createElement("li");
	if(principal == null){
		buttonLi.innerHTML = ``;
		bookdtlsUl.appendChild(buttonLi);
	}else if (principal.roles == "ROLE_ADMIN,ROLE_USER") {
		buttonLi.innerHTML = `<button class="update-btn"> <i class="fa-solid fa-pencil"> 수정하기</i><button class="delete-btn"> <i class="fa-solid fa-pencil"> 삭제하기</i></button></button>`;
		bookdtlsUl.appendChild(buttonLi);
		
		const updateBtn = document.querySelector(".update-btn")
		const deleteBtn = document.querySelector(".delete-btn")
		
		updateBtn.onclick = () => {
			location.href = `/book/${bookcode}`;
		}
		
		deleteBtn.onclick = () => {
			let flag = confirm("도서를 삭제하시겠습니까?")
			if (flag==true){
				$.ajax({
					type:"delete ",
					url : `/api/v1/book/admain/${bookcode}`,
					data : {
						"bookcode": bookcode
					},
					dataType : "json",
					async: false,
					success : function(response) {
						alert
						location.replace("/book/bookselect")
					},
					error: function(){
						alert("비동기 처리 오류")
					}			
				});
			}
				
		}

	} else {
		if(principal.usercode == data.usercode){
			if(data.rental_status == 1){
				buttonLi.innerHTML = `<button type="button" class="return-btn"><i class="fa-solid fa-book"> 반납하기</i></button><button type="button" class="extension-btn"><i class="fa-solid fa-book"> 연장하기</i></button>`;
			}else{	
				buttonLi.innerHTML = `<button type="button" class="rental-btn"><i class="fa-solid fa-book"> 대여하기</i></button>`;
			}
			bookdtlsUl.appendChild(buttonLi);
		} else{
			if(data.rental_status == 1){
				buttonLi.innerHTML = `<button type="button" class="disabled-btn"><i class="fa-solid fa-book"> 대여중</i></button>`;
			}else{	
				buttonLi.innerHTML = `<button type="button" class="rental-btn"><i class="fa-solid fa-book"> 대여하기</i></button>`;
			}
			bookdtlsUl.appendChild(buttonLi);
		}
		if(data.rental_status != 1){
			const rentalBtn = document.querySelector(".rental-btn")
			rentalBtn.onclick = () => {
				let flag = confirm("도서를 대여하시겠습니까?")
			
				if (flag == true) {
					if (principal == null) {
						location.replace("/auth/login");
					} else {
						rentalBook(data);
					}
			
				}
			
			}
		}else{
			const returnBtn = document.querySelector(".return-btn");
			const extensionBtn = document.querySelector(".extension-btn");
			
			returnBtn.onclick = () => {
				let flag = confirm("도서를 반납하시겠습니까?")
				if (flag == true) {
					returnBook(data);
				}
			}	
			extensionBtn.onclick = () => {
				let flag = confirm("대여 기간을 연장하시겠습니까?")
				if (flag == true) {												
					extensionBook(data);				
				}
			}	
		}
	}
}

function bookImgLoad(data) {
	if(data.book_file_img== null){
		bookImg.innerHTML = `
			<img src="/static/images/default.png" alt="">
		`;
	}else{
		bookImg.innerHTML = `
			<img src="/images/book_img/${data.book_file_img}" alt="">
		`;
	}
}
function rentalBook(data){
	 $.ajax({
		type : "post",
		url : "/api/v1/book/user/rental",
		data : {
			"usercode": principal.usercode,
			"bookcode" : bookcode
				},
		async: false,
		dataType : "json",
		success: function(response) {
				alert("대여 완료");
				location.replace(`/book/bookdtl/${bookcode}`)	
			},
		error: function() {
				console.log("error code: " + request.status);
				alert("error message: " + request.responseText);
				console.log("error: " + error);
		}
	});
}
function returnBook(data){
	$.ajax({
		type : "put",
		url : "/api/v1/book/user/return",
		data : {
			"rental_code" : data.rental_code 
		},
		dataType : "json",
		async: false,
		success: function(response) {
			alert("반납 완료");
			location.replace(`/book/bookdtl/${bookcode}`);	
		},
		error: function() {
			alert("비동기 처리 오류");
		}		
	})
}
	//대여기간 연장
function extensionBook(data){
	$.ajax({
		type : "put",
		url : "/api/v1/book/user/extension",
		data : {
			"rental_code" : data.rental_code 
		},
		dataType : "json",
		async: false,
		success: function(response) {									
			alert("대여기간 연장 완료");	
		},
		error: function() {
				console.log("error code: " + request.status);
				alert("error message: " + request.responseText);
				console.log("error: " + error);
		}		
	})
}
