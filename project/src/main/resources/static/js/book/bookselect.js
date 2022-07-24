const notice = document.querySelector(".notice");
const colTalbe = document.querySelector(".col-table"); 
const searchBtn = document.querySelector(".btn-search");
const keyword = document.querySelector("#keyword");
const select = document.querySelector(".search_Details");
const aaa = document.querySelector(".aaa")


let nowPage = 1;
let principal = getPrincipal();

search(nowPage);

keyword.onkeyup = () => {
	if(window.event.keyCode == 13){
		window.event.preventDefault();
		searchBtn.click();
	}
}

//검색
searchBtn.onclick=()=>{
	nowPage = 1;
	search(nowPage);
}

function search(page){
	$.ajax({
		type: "get",
		url : `/api/v1/book/all/main/search/${page}`,
		data :{
			"page" : page,
			"keyword" : keyword.value,
			"select" : select.value,
			"contentCount" : 20
		},
		dataType : "json",
		success : function(response){
			let bookList = response.data;
			getBookList(bookList);
			console.log(bookList[0].total_count);
			createPageNumber(bookList[0].total_count);
			getBookitems();
			principalUsers();
		},
		error : function(){
			alert("비동기 처리 오류");
			}
	})
}


//페이지 데이터 가져오기
function getBookList(data){
	const table = colTalbe.querySelector('tbody');
	let tableStr = ``;
	for(let i=0; i<data.length; i++){
		tableStr +=`
		 <tr class="notice">
            <td class="just-pc">${data[i].bookcode}</td>
            <td class="a-1">${data[i].bookname}</td>
            <td>${data[i].author}</td>
            <td>${data[i].publisher}</td>
		</tr>`
	}
	table.innerHTML =tableStr;
}


//페이징 처리
function createPageNumber(data){
	const pagination = document.querySelector(".pagination");
	
	const totalBookCount = data;
	const totalPageCount = data%20 ==0?  data/20 : Math.ceil(data/20);
	console.log(totalPageCount);
	
	const startIndex = nowPage%10 == 0? nowPage-9 : nowPage - (nowPage % 10) + 1; 
	const endIndex = startIndex + 9 <= totalPageCount? startIndex + 9 : totalPageCount;
	
	let pageStr =`
	<div class="control"><i class="fa-solid fa-angles-left"></i></div>
    <div class="control"><i class="fa-solid fa-angle-left"></i></div>
	`;
	for(let i= startIndex; i<endIndex + 1; i++){
		pageStr+=`<div class="active">${i}</div>`;
	}
	pageStr +=`
    <div class="control"><i class="fa-solid fa-angle-right"></i></div>
    <div class="control"><i class="fa-solid fa-angles-right"></i></div>
	`;
	pagination.innerHTML=pageStr;
		
	
	
	const controlBtn = document.querySelectorAll(".control");
	
	controlBtn[0].onclick =() =>{
		nowPage=1;
		search(nowPage); 
	}	
	controlBtn[1].onclick =()=>{
		nowPage =startIndex !=1 ?startIndex-1 : 1;
		search(nowPage)
	}
	controlBtn[2].onclick=()=>{
		nowPage = endIndex !=totalPageCount ? endIndex+1 :totalPageCount;
		search(nowPage)
	}
	controlBtn[3].onclick =() =>{
		nowPage=totalPageCount;
		search(nowPage); 
	}
	const bookListBtn = pagination.querySelectorAll(".active");
	for(let i=0; i<bookListBtn.length;i++){
	bookListBtn[i].onclick=()=>{
		nowPage = bookListBtn[i].textContent;
		search(nowPage);
		}
	}
}

function getBookitems(){
	const bookItems = document.querySelectorAll(".notice");
	for(let i=0; i<bookItems.length;i++){
		bookItems[i].onclick =()=>{
			location.href = "/book/bookdtl/"+bookItems[i].querySelectorAll('td')[0].textContent;
		}
	}
}
function principalUsers(){
	if(principal!=null){
		if(principal.roles=="ROLE_ADMIN,ROLE_USER"){
			aaa.innerHTML=`<button type="button" class = "insert_btn">도서추가</button>`;		
			
			const insertBtn = document.querySelector(".insert_btn");
			insertBtn.onclick =()=>{
				location.href = "/book/bookinsert"
			}
		}
	}
	
}



