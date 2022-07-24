const bookImg = document.querySelector(".book-file-img")
const inputDatas = document.querySelectorAll(".input-datas");
const inputText = document.querySelector(".input-text");
const imgbox = document.querySelector(".img-box");
const inputbox = document.querySelector(".input-bar");
const updateBtn = document.querySelector(".sub-btn");
const deleteBtn =document.querySelector(".del-btn");

let path = window.location.pathname;
let bookcode =path.substring(path.lastIndexOf("/")+1);



load();

function load(){
	$.ajax({
		type : "get",
		url : `/api/v1/book/all/${bookcode}`,
		data :{
			"bookcode" :bookcode
		},
		dataType : "json",
		success : function(response){
			getBookDtl(response.data);
		},
		error : function(){
			alert("비동기 처리 오류");
		}		
	});
}

function getBookDtl(data){
	if(data.book_file_img==null){
		imgbox.innerHTML =`
		<img src="/static/images/default.png" alt="">
		<input type="file" class="book-file-img" name="file">`
	}else{
		imgbox.innerHTML =`
		<img src="/image/book_img/${data.book_file_img}" alt="">
	 	<input type="file" class="book-file-img" name="file" value="${data.book_file_img}">`;
	 	}
	inputDatas[0].value = data.bookcode;
	inputDatas[1].value = data.call_sign;
	inputDatas[2].value = data.bookname;
	inputDatas[3].value = data.author;
	inputDatas[4].value = data.publisher;
	inputDatas[5].value = data.year_of_publication;
	inputText.value = data.summary;
        		
}

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


updateBtn.onclick =()=>{
	let Flag = confirm("수정 하시겠습니까?");
	if(Flag ==true){
		submit();		
	}
	
}

function submit(){
	let formData = new FormData(document.querySelector('form'));
	$.ajax({
			type : "post",
			url : `/api/v1/book/admain/${bookcode}`,
			data: formData,
			enctype: "multipart/form-data",
			processData: false,
			contentType: false,
			success : function(response){	
				alert("도서 수정 완료.");
				location.replace(`/book/bookdtl/${bookcode}`)
			},
			error :function(){
				alert("비동기처리 오류");
			}
		})
}



deleteBtn.onclick=()=>{
	location.replace(`/book/bookdtl/${bookcode}`);
}

