
var receipt;
var usedPoints;

function fillBaseData(receiptData){
	receipt = receiptData;
	//var discount = receiptData.discountPercentage==null?"0%":receiptData.discountPercentage+"%";
	//var finalPrice = receiptData.finalPrice==null?"0":receiptData.finalPrice+"";
	//var initialPrice = receiptData.initialPrice==null?"0":receiptData.initialPrice+"";
	$("#receipt-discount-percentage").html("Please Click Calculate");
	$("#final-receipt-price").html("Please Click Calculate");
	$("#initial-receipt-price").html("Please Click Calculate");
}

function buyStuff(){
	var points = parseInt($("#spending-points").val());
	if(isNaN(points) || points < 0 || points > parseInt( $("#spending-points").attr('max') ) ){
		points = 0;
	}
	$.ajax({
		url:"shopping-cart/complete-shopping/"+points,
		type:"POST",
		contentType:"application/json",
		complete:function(data){
			dta = jQuery.parseJSON(data.responseText);
			console.log(dta);
		}
	});
}

function calculateReceipt(){
	var points = parseInt($("#spending-points").val());
	if(isNaN(points) || points < 0 || points > parseInt( $("#spending-points").attr('max') ) ){
		points = 0;
	}
	$.ajax({
		url:"shopping-cart/calculate-receipt",
		type:"POST",
		contentType:"application/json",
		complete:function(data){
			dta = jQuery.parseJSON(data.responseText);
			console.log(dta);
			$("#initial-receipt-price").html(dta.initalPrice + " $");
			$("#final-receipt-price").html(dta.finalPrice + " $");
			$("#receipt-discount-percentage").html(dta.discountPercentage+" %");
		}
	});
}

function buildTableRow(propName,propVal){
	return "<tr><th>"+propName+"</th><td>"+propVal+"</td></tr>";
}

function buildEntryWellRow(receiptEntry){
	var str = "";
	str += "<div class=\"row\">" +
			"	<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">" +
			"		<div class=\"well\">";
	
	var product = receiptEntry.product;
	str += 	"<table class=\"table\">";
	str +=	buildTableRow("Product Name",product.name);
	str +=  buildTableRow("Amount",receiptEntry.numberOfProducts);
	
	if(receiptEntry.priceAfterDiscount != null){
		str += buildTableRow("Price of One","<del>"+receiptEntry.originalPrice+" $</del>");
		str += buildTableRow("Price after Discount", receiptEntry.priceAfterDiscount+" $");
	}else{
		str += buildTableRow("Price of One",receiptEntry.originalPrice + " $");
	}
	str += buildTableRow("Total Price", receiptEntry.totalPrice + " $");
	
	str += "<tr colspan=\"2\"><button class=\"btn btn-danger\" onclick=\"removeReceiptEntry('"+receiptEntry.receiptEntryId+"')\">Remove Entry</button></tr>"
	str += "</table></div></div></div>";
	return str;
}

function buildEntries(receiptEntries){
	var str = "";
	
	for(var i in receiptEntries){
		var receiptEntry = receiptEntries[i];
		console.log(receiptEntry);
		if(receiptEntry.numberOfProducts == 0){
			continue;
		}
		str += buildEntryWellRow(receiptEntry);
	}

	$("#receipt-entries-container-div").html(str);
}

function removeReceiptEntry(id){
	console.log("shopping-cart/remove-entry/"+id);
	$.ajax({
		url:"shopping-cart/remove-entry/"+id,
		contentType:"application/json",
		type:"POST",
		complete:function(dta){
			buildEntries(jQuery.parseJSON(dta.responseText));
		}
	});
}