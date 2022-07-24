
let principal = getPrincipal();
let usercode = principal.usercode;

load(usercode);
function load(usercode){
	$.ajax({
		type : "get",
		url : `/api/v1/lib/latebook/${usercode}`,
		data : {
			"usercode" : usercode
		},
		dataType : "json",
		success : function(response){
			let lentalData = response.data;
			console.log(lentalData);
			getUserList(lentalData);
		},
		errer : function(){
			alert("비동기 처리 오류");
		}
	})
}

function getUserList(data){
	const loanBook = document.querySelector(".loanBook_table");
	let tableStr =`
	<tr>
    	<th>번호</th>
    	<th>도서명</th>
        <th>대여날짜</th>
        <th>반납날짜</th>
        <th>반납하기</th>
      </tr>`;
	for(let i=0; i<data.length; i++){
		tableStr+=`
		<tr>
         	<td>${i+1}</td>
         	<td>${data[i].bookname}</td>
            <td>${data[i].rental_date}</td>
            <td>${data[i].return_date}</td>
            <td><button class="return-btn"> - 반납하기 - </button></td>
         </tr>
		`;
	}
	loanBook.innerHTML= tableStr; 
	const returnBtn = document.querySelectorAll(".return-btn");
	
	for(let i=0; i<returnBtn.length;i++){	
		returnBtn[i].onclick = () => {
			let flag = confirm("도서를 반납하시겠습니까?")
			if (flag == true) {
				returnBook(data[i]);
			}
		}
	}
}

function returnBook(data){
	$.ajax({
		type : "put",
		url : "/api/v1/book/user/return",
		data : {
			"rental_code" :	data.rental_code 
		},
		dataType : "json",
		success: function(response) {
			alert("반납 완료");
			location.replace(`/lib/latebook`);	
		},
		error: function() {
			alert("비동기 처리 오류");
		}		
	})
}