const loanBookTable = document.querySelector(".loanBook_table");

load();

function load(){
	$.ajax({
		type: "GET",
		url: `/api/v1/booktotal/rental`,
		dataType : "json",
		success: function(response){
			getRentalBook(response)
		},
		error: function(){
			alert("비동기 처리 오류");
		}
		
	})
}

function getRentalBook(data){
	const tableBody = loanBookTable.querySelector('tbody');
	let tableStr = ``;
	for(let i = 0; i < data.length; i++){
		tableStr += `
			<tr>
			    <td>${data[i].rentalCode}</td>
			    <td>${data[i].bookname}</td>
			    <td>${data[i].rentalDate}</td>
			    <td>${data[i].returnDate}</td>
			    <td><span>${data[i].rentalStatus}</span></td>
			</tr>
		`
	}
	tableBody.innerHTML= tableStr;
}