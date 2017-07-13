
var compareDate = new Date();
compareDate.setFullYear(2000);
var productPrefix = "product-row-";
var productPanelId = "products-panel";

function generateCategoryEvents(category,evts){
	var str = "";
	if(category == null)
		return evts;
	
	for(var eindex in category.relation){
		var event = category.relation[eindex].salesEvent;
		
		var std = new Date(event.startDate);
		std.setFullYear(2000);
		var endd = new Date(event.endDate);
		endd.setFullYear(2000);
		
		//var sd = event.startDate;
		//var ed = event.endDate;
		var sd = std;
		var ed = endd;
		if(!(compareDate >= sd && compareDate <= ed)){
			continue;
		}
		
		str = "";
		str += "<table class=\"table table-hover table-responsive\">";
		var evStart = new Date();
		var evEnd = new Date();
		evStart.setDate(new Date(event.startDate).getDate());
		evStart.setMonth(new Date(event.startDate).getMonth());
		evEnd.setDate(new Date(event.endDate).getDate());
		evEnd.setMonth(new Date(event.endDate).getMonth());
		
		str += generateTableRow("Event Name",event.eventName);
		str += generateTableRow("Event ID", event.id);
		str += generateTableRow("Discount", event.eventDiscount + "%");
		str += generateTableRow("Start Date", yyyymmdd(evStart));
		str += generateTableRow("End Date", yyyymmdd(evEnd));
		str += "</table>";
		var evtId = event.id;
		evts[evtId] = str;
	}
	
	return generateCategoryEvents(category.parentCategory,evts);
}

function generateCategoryEventsWell(product){
	var str = 	"<div class=\"container-fluid\">";
	str +=			"<div class=\"row\">";
	str +=				"<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">";
	str +=					"<div class=\"well\">";
	str +=						"<fieldset><legend>Sales Events</legend>";
	

	var evts = generateCategoryEvents(product.productCategory,{});
	if(jQuery.isEmptyObject(evts)){
		str += "This product has no active Sales Events.";
	}else{
		for(var evt in evts){
			str += evts[evt];
		}
	}
	
	str +=						"</fieldset>";
	str +=					"</div>";
	str+=				"</div>";
	str += 			"</div>";
	
	str +=		"</div>";
	return str;
}

function generateWellRow(product){

	var str = 	"<div class=\"row\" id=\"product-row-"+product.id+"\">" +
					"<div class=\"col-lg-10 col-md-10 col-sm-10 col-xs-10\">";
	str+=			generateProductWell(product);
	str+=			"</div>";
	str+= 			"<div class=\"col-lg-2 col-md-2 col-sm-2 col-xs-2\">";
	str+=			generateAddControlWell(product);
	str+=			"</div>";
	
	str += 		"</div>";
	
	return str;
}

function generateProductWell(product){
	var str = 	"<div class=\"well\">" +
					"<fieldset>" +
						"<legend>" + product.name +"</legend>";
	str+=				generateProductTable(product);
	str+=			"</fieldset>";
	str +=		generateCategoryEventsWell(product);
	str+=		"</div>";
	return str;
}

function generateProductTable(product){
	var str = "<table class=\"table table-hover table-responsive\" id=\""+product.id+"\">";
	str += generateTableRow("Product ID", product.id);
	str += generateTableRow("Price", product.price + "$");
	str += generateTableRow("Product Category",((product.productCategory==null)?"Uncategorized":product.productCategory.categoryName));

	//str += generateProductEvents(product);
	
	str += "</table>";
	
	return str;
}

function generateTableRow(propName,propVal){
	return "<tr><th>"+propName+"</th><td>"+propVal+"</td></tr>";
}

function generateAddControlWell(product){
	var str = 	"<div class=\"well\">";
	str += 			"<label>Amount:</label>";
	str += 			"<div class=\"form-group\">";
	str += 				"<input type=\"number\" class=\"form-control\" min=\"1\" max=\""+product.amountInStock+"\" id=\""+product.id+"amount"+"\" value=\"1\" onchange=\"change('"+product.id+"addButton','"+product.id+"amount')\" onkeyup=\"change('"+product.id+"addButton','"+product.id+"amount')\">";
	str += 			"</div>";
	str += 			"<button class=\"btn btn-primary\" onclick=\"addProductToCart(\'"+encodeURIComponent(JSON.stringify(product))+"'\)\" id=\""+product.id+"addButton\">Add To Cart</button>";
	str += 		"</div>";
	return str;
}

function addProductToCart(product){
	var prod = jQuery.parseJSON(decodeURIComponent(product));
	var vl = $("#"+prod.id+"amount").val();
	/*
		var el = document.getElementById(productPrefix+prod.id);
		el.parentNode.removeChild(el);	 
	 */
	//short:
	//send add to cart request,
	//do backend, return added to cart item with new amount
	//if amount == 0, remove element from DOM with up^
	//else set new max number.
	var payload = {productId:prod.id, productAmount:vl};
	$.ajax({
		url:"shopping/add-item-to-cart",
		type:"POST",
		contentType:"application/json",
		dataType:"application/json",
		data:JSON.stringify(payload),
		complete:function(data){
			console.log(data.responseText);
		}
	});
}

function change(buttonid,inputid){
	var max = parseInt($("#"+inputid).attr('max'));
	var min = parseInt($("#"+inputid).attr('min'));
	var val = parseInt($("#"+inputid).val());
	
	console.log(max);
	console.log(min);
	console.log(val);
	
	if(max < val || min > val){
		$("#"+buttonid).prop('disabled',true);
	}else{
		$("#"+buttonid).prop('disabled',false);
	}
}