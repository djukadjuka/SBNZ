function register(){
	
	var name = $("#register-name").val();
	if(inputEmpty(name)){
		errorAlert("Name field cannot be empty!");
		return;
	}
	var surname = $("#register-surname").val();
	if(inputEmpty(surname)){
		errorAlert("Surname field cannot be empty!");
		return;
	}
	
	var username = $("#register-username").val();
	if(inputEmpty(username)){
		errorAlert("Username cannot be empty!");
		return;
	}
	var password = $("#register-password").val();
	if(inputEmpty(password)){
		errorAlert("Password cannot be empty!");
		return;
	}
	
	var registryDate = new Date().getTime();
	var role = 0;
	
	var user = {name:name,surname:surname,username:username,password:password,registryDate:registryDate,role:role};
	
	console.log(user);
	console.log("You tried to register!");
	
	$.ajax({
		url:"/login-register/register",
		data:JSON.stringify(user),
		dataType:"application/json",
		contentType:"application/json",
		type:"POST",
		complete:function(data){
			dta = jQuery.parseJSON(data.responseText);
			if(!dta.STATUS){
				errorAlert(dta.MESSAGE);
			}else{
				successAlert("You have successfully registered!");	
				redirToIndex();
			}
		}
	});
}

function login(){
	var username = $("#login-username").val();
	var password = $("#login-password").val();
	
	if(inputEmpty(username)){
		errorAlert("Username cannot be empty!");
		return;
	}
	
	if(inputEmpty(password)){
		errorAlert("Password cannot be empty!");
		return;
	}
	
	$.ajax({
		url:"/login-register/login",
		data:JSON.stringify({username:username,password:password,name:"",surname:"",registryDate:0,role:0}),
		dataType:"application/json",
		contentType:"application/json",
		type:"POST",
		complete:function(data){
			dta = jQuery.parseJSON(data.responseText);
			
			if(!dta.STATUS){
				errorAlert(dta.MESSAGE);
			}else{
				successAlert("You have successfully logged in!");	
				redirToIndex();
			}
		}
	});
}

function inputEmpty(name){
	if(name == ""){
		return true;
	}else{
		return false;
	}
}

function redirToIndex(){
	window.location.href = "index.html";
}