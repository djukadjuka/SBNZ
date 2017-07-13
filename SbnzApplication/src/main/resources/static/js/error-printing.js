function errorAlert(errorMessage){
	str = "<div class=\"alert alert-danger\">" + errorMessage + "</div>";
	$("#error-div").html(str);
}

function successAlert(successMessage){
	str = "<div class=\"alert alert-success\">" + successMessage + "</div>";
	$("#error-div").html(str);
}