 const comm = document.querySelector('.comm');
 const submitBtn = document.querySelector('.submit_btn')
 const commentTextarea = document.querySelector('.comment_textarea')
 
 let nowPage = 1;
 
 load(nowPage);
 
 function load(page) {
	let url = `/api/v1/comment/list/${boardcode}?page=${page}`
	
	fetch(url)
	.then(response => {
		if(response.ok){
			return response.json();
		}else{
			throw new Error("비동기 처리 오류");
			
		}
	})
	.then(result => {
		getCommentList(result);
		createPageNumber(result[0].commentTotalCount);
	})
	.catch(error => {
		console.log(error)
	})
}

function createPageNumber(data) {
	const paging = document.querySelector('.paging');
	
	const totalPageCount = data % 5 == 0 ? data / 5  : Math.ceil(data / 5);
	
	const startIndex = nowPage % 5 == 0 ? nowPage - 4 : nowPage - (nowPage % 5) + 1;
	const endIndex = startIndex + 4 <= totalPageCount ? startIndex + 4 : totalPageCount;
	
	let pageStr = `
		<div class="next_btn"><i class="fa-solid fa-angles-left"></i></div>
      	<div class="next_btn"><i class="fa-solid fa-angle-left"></i></div>
	`;
	for(let i = startIndex; i <= endIndex; i++){
		pageStr += `<div class="nums">${i}</div>`;
	}
	pageStr += `
		<div class="next_btn"><i class="fa-solid fa-angle-right"></i></div>
      	<div class="next_btn"><i class="fa-solid fa-angles-right"></i></div>
	`
	
	paging.innerHTML = pageStr;
	
	const nextBtn = document.querySelectorAll('.next_btn');
	
	nextBtn[0].onclick = () => {
		nowPage = startIndex;
		load(nowPage);
	}
	
	nextBtn[1].onclick = () => {
		nowPage = nowPage > 1 ? nowPage - 1 : nowPage;
		load(nowPage);
	}
	
	nextBtn[2].onclick  = () => {
		nowPage = nowPage != totalPageCount ? Number(nowPage) + 1 : nowPage;
		load(nowPage);
	}
	
	nextBtn[3].onclick = () =>  {
		nowPage =totalPageCount;
		load(nowPage);
	}
	
	const pageButton = paging.querySelectorAll('.nums');
	for(let i = 0; i < pageButton.length; i++) {
		pageButton[i].onclick = () => {
			nowPage = pageButton[i].textContent;
			load(nowPage);			
		}
	}
}

 function getCommentList(data) {
	const tablebody = document.querySelector('table');
	let tableStr = ``;
	for(let i = 0; i < data.length; i++){
		tableStr += `
			<tr>
                <td class="name">${data[i].comment_username}</td>
                <td>${data[i].comment_contents}</td>
                <td>${data[i].create_date}</td>
            </tr>
		`
	}
	tablebody.innerHTML = tableStr;
}

function isEmpty(str){
	return str =="" || str == null || typeof(str) == "undefined";
}

submitBtn.onclick = () => {
	if(principal != null){
		if(isEmpty(commentTextarea.value)){
			alert("내용작성해주세요")
		}else{
			comment();
		}
	}else {
		alert("로그인 해주세요.")
		location.href = "/auth/login";
	}
}

let principal = getPrincipal();

function comment() {
	$.ajax({
		type : 	'POST',
		url : `/api/v1/comment/post`,
		contentType: "application/json",
		data : JSON.stringify({
			boardcode: boardcode,
			comment_username: principal.username,
			comment_contents: commentTextarea.value
		}),
		dataType : "JSON",
		success : function(result){
			location.reload();
		},
		error: function(){
			alert("비동기 처리 오류");
		}
	})
}