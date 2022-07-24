const boardList = document.querySelector('.board_list');
const boardWriting = document.querySelector('.board_writing');
const search = document.querySelector(".search_input");
const searchBtn = document.querySelector(".search_input_btn");

let principal = getPrincipal();

let nowPage = 1;

load(nowPage);

search.onkeyup = () => {
	if(window.event.keyCode == 13){
		window.event.preventDefault = false;
		searchBtn.click();
	}
	
}

searchBtn.onclick= () => {
	load(nowPage);
}

boardWriting.onclick= () => {
	if(principal == null){
		alert("로그인을 해주세요")
		location.href = "/auth/login"
	}else {
		location.href = "/book/bulletinWriting";
	}
	
}

function load(page) {
	$.ajax({
		type : 'GET',
		url : `/api/v1/board/search/${page}`,
		data : {
			"page" : page,
			"keyword" : search.value
		},
		dataType : "JSON",
		success : function(result){
			let list = result.data;
			getBoardList(list);
			createPageNumber(result.data[0].total_count);
		},
		error : function(){
			alert("비동기 처리 오류");
		}
	})
}

function createPageNumber(data) {
	const paging = document.querySelector('.paging');
	
	const totalPageCount = data % 10 == 0 ? data / 10  : Math.ceil(data / 10);
	
	const startIndex = nowPage % 10 == 0 ? nowPage - 9 : nowPage - (nowPage % 10) + 1;
	const endIndex = startIndex + 9 <= totalPageCount ? startIndex + 9 : totalPageCount;
	
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

function getBoardList(data){
	const tableBody = boardList.querySelector('tbody');
	let tableStr = ``;
	for(let i = 0; i < data.length; i++){
		tableStr += `
		<tr>
            <td>${data[i].boardcode}</td>
            <td class="tit"><a href ="/book/bulletinBoard/${data[i].boardcode}">${data[i].board_title}</a></td>
            <td>${data[i].board_username}</td>
            <td>${data[i].create_date}</td>
        </tr>
		`;
	}
	tableBody.innerHTML = tableStr;
}

