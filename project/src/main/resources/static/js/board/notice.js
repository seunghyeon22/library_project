const boardList = document.querySelector('.board_list');
const boardWriting = document.querySelector('.board_writing');
const search = document.querySelector(".search_input");
const searchBtn = document.querySelector(".search_input_btn");
const boardListBtn = document.querySelector(".board_list_btn");

let principalAdmin = getPrincipal();

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

function load(page) {
	$.ajax({
		type : 'GET',
		url : `/api/v1/notice/search/${page}`,
		data : {
			"page" : page,
			"keyword" : search.value
		},
		dataType : "JSON",
		success : function(result){
			let list = result.data;
			getBoardList(list);
			createPageNumber(result.data[0].total_count);
			writingBtn(result)
		},
		error : function(e){
			console.log(e);
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
		console.log(nowPage != 1 ? true : false)
		nowPage = nowPage > 1 ? nowPage - 1 : nowPage;
		load(nowPage);
	}
	
	nextBtn[2].onclick  = () => {
		console.log(nowPage != totalPageCount ? true : false)
		console.log(nowPage != totalPageCount ? Number(nowPage) + 1 : nowPage)
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

function getBoardList(data) {
	const tableBody = boardList.querySelector('tbody');
	let tableStr = ``;
	for(let i = 0; i < data.length; i++){
		tableStr += `
		<tr>
           <td>${data[i].noticecode}</td>
           <td class="tit"><a href="/book/noticeBoard/${data[i].noticecode}">${data[i].notice_title}</a></td>
           <td>${data[i].notice_username}</td>
           <td>${data[i].create_date}</td>
        </tr>
		`;
	}
	tableBody.innerHTML = tableStr;
}

function writingBtn() {
	if(principalAdmin != null){
		if(principalAdmin.roles == "ROLE_ADMIN,ROLE_USER"){
			boardListBtn.innerHTML = `<button type="button" class="board_writing" onclick="location.href = '/book/bulletinWriting'">글쓰기</button>`
		}
	}	
}
