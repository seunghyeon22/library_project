const keyword = document.querySelector("#keyword");
const select = document.querySelector(".search_Details");
const searchBtn = document.querySelector(".btn-search");

let nowPage = 1;

search(nowPage);

keyword.onkeyup = () => {
	if(window.event.keyCode == 13){
		window.event.preventDefault();
		searchBtn.click();
	}
}

searchBtn.onclick=()=>{
	nowPage = 1;
	search(nowPage);
}

function search(page){
	$.ajax({
		type: "get",
		url : `/api/v1/book/all/main/search/best/${page}`,
		data :{
			"page" : page,
			"keyword" : keyword.value,
			"select" : select.value,
			"contentCount" : 10
		},
		dataType : "json",
		success : function(response){
			let bookList = response.data;
			getBooklist(bookList);
			searchPageNumber(bookList[0].total_count);
			getBookitems();
		},
		error : function(){
			alert("비동기 처리 오류");
			}
	})
}


function getBooklist(data){
	const result = document.querySelector(".search-results");
	let tableStr = ``;
	for(let i =0; i<data.length; i++){
		if(data[i].book_file_img==null){
		tableStr +=`<div class="row">
                                                            <div class="thumb">
                                                                    <img src="/static/images/default.png" "> 
                                                            </div>
                                                            <div class="sbox">
                                                                <div class="item">
                                                                    <div class="bif">    
                                                                        <ul class="con2">
                                                                       		<li>도서명 : ${data[i].bookname}</li>
                                                                            <li>${data[i].bookcode}</li>
                                                                            <li>청구기호 : ${data[i].call_sign}</li>
                                                                            <li>저자 : ${data[i].author}</li>
                                                                            <li>출판사 : ${data[i].publisher}</li>
                                                                            <li>출판년도 : ${data[i].year_of_publication}</li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>`;
	}else{	
	tableStr +=`<div class="row">
                                                            <div class="thumb">
                                                                    <img src="/images/book_img/${data[i].book_file_img}">   
                                                            </div>
                                                            <div class="sbox">
                                                                <div class="item">
                                                                    <div class="bif">    
                                                                        <ul class="con2">
                                                                       		<li>도서명 : ${data[i].bookname}</li>
                                                                            <li>${data[i].bookcode}</li>
                                                                            <li>청구기호 : ${data[i].call_sign}</li>
                                                                            <li>저자 : ${data[i].author}</li>
                                                                            <li>출판사 : ${data[i].publisher}</li>
                                                                            <li>출판년도 : ${data[i].year_of_publication}</li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>`;
		
	}	
	}
	result.innerHTML=tableStr;
	
}

function getBookitems(){
	const bookItems = document.querySelectorAll("ul");
	for(let i=0; i<bookItems.length;i++){
		bookItems[i].onclick =()=>{
			location.href = "/book/bookdtl/"+bookItems[i].querySelectorAll("li")[1].textContent;
		}
	}
}



function searchPageNumber(data){
	const pagination = document.querySelector(".pagination");
	
	const totalBookCount = data;
	const totalPageCount = data%10 ==0?  data/10 : Math.ceil((data/10));
	
	const startIndex = nowPage%10 ==0? nowPage-9:nowPage-(nowPage%10)+1; 
	const endIndex = startIndex+9<=totalPageCount? startIndex+9 : totalPageCount;
	
	let pageStr =`
	<div class="control"><i class="fa-solid fa-angles-left"></i></div>
    <div class="control"><i class="fa-solid fa-angle-left"></i></div>
	`;
	for(let i= startIndex; i<endIndex+1; i++){
		pageStr+=`<div class="active">${i}</div>`;
	}
	pageStr +=`
    <div class="control"><i class="fa-solid fa-angle-right"></i></div>
    <div class="control"><i class="fa-solid fa-angles-right"></i></div>
	`;
	pagination.innerHTML=pageStr;
		
	
	const controlBtn = document.querySelectorAll(".control");
	
	controlBtn[0].onclick=() =>{
		nowPage=1;
		search(nowPage); 
	}	
	controlBtn[1].onclick=()=>{
		nowPage =startIndex !=1 ?startIndex-1 : 1;
		search(nowPage)
	}
	controlBtn[2].onclick=()=>{
		nowPage = endIndex !=totalPageCount ? endIndex+1 :totalPageCount;
		search(nowPage)
	}
	controlBtn[3].onclick=() =>{
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