
$("#logout-button").click(function(){
	$.ajax({
		url:"/navbar/logout",
		contentType:"application/json",
		type:"GET",
		complete:function(data){
			window.location.href="login-register.html"
		}
	});
});

function yyyymmdd(date){
	var mm = date.getMonth() + 1;
	var dd = date.getDate();
	var yyyy = date.getFullYear();
	
	return [yyyy,(mm>9?'':'0') + mm, (dd>9?'':'0') + dd].join('-');
}