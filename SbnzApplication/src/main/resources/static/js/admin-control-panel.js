
var PRODUCT_CATEGORY_KEYWORD = "PRODUCT";
var USER_CATEGORY_KEYWORD = "USER";
var SALES_EVENT_KEYWORD = "SALES_EVENT";

function createViewWellStart(header){
	var str =			"<div class=\"col-lg-10 col-md-10 col-sm-10 col-xs-10\">";
	str +=				"<div class=\"well\">";
	str +=					"<fieldset><legend>" + header + "</legend>";
	return str;
}

function createViewWellEnd(){
	var str = 				"</fieldset>";
	str +=				"</div>";
	str+=			"</div>";
	
	return str;
}

function createControlWellStart(){
	var str =			"<div class=\"col-lg-2 col-md-2 col-sm-2 col-xs-2\">";
	str +=				"<div class=\"well\">";
	str +=					"<fieldset>";
	return str;
}

function createControlWellEnd(){
	var str = 				"</fieldset>";
	str +=				"</div>";
	str+=			"</div>";
	
	return str;
}

function buildControllWellControl(userCat){
	var jsonenc = encodeURIComponent(JSON.stringify(userCat));
	
	var str = "<button class=\"btn btn-primary\"" +
			"onclick=\"acceptChangesUserCat('"+jsonenc+"')\" id=\"user-cat-accept-"+userCat.id+"\">Accept Changes</button>";
	return str;
}

function acceptChangesUserCat(encodedjson){
	var data = jQuery.parseJSON(decodeURIComponent(encodedjson));
	
	var min = parseInt($("#spendingBoundMin-"+data.id).val());
	var max = parseInt($("#spendingBoundMax-"+data.id).val());
	var perc = parseInt($("#pointsPercentage-"+data.id).val());
	
	data.spendingBoundMin = min;
	data.spendingBoundMax = max;
	data.pointsPercentage = perc;
	
	var ob = {id:data.id, min:min, max:max, perc:perc};
	
	console.log(ob);
	
	$.ajax({
		url:"admin-control-panel/edit-user-category",
		contentType:"application/json",
		data:JSON.stringify(ob),
		dataType:"application/json",
		type:"POST",
		complete:function(data){
			var dta = jQuery.parseJSON(data.responseText);
			$("#user-categories-row-panel").html(buildUserCatsPanel(dta));
		}
	});
}

function buildUserCatsEditControlsFormGroup(propName,realPropName,propVal,id){
	var str = 	"<div class=\"form-group\">";
	str+=			"<label>"+propName+"</label>";
	str+=			"<input onkeyup=\"userCatChanged('"+id+"')\" class=\"form-control\" type=\"number\" min=\"0\" value=\""+propVal+"\" id=\""+realPropName+"-"+id+"\">";
	str+=		"</div>";
	return str;
}

function userCatChanged(id){
	var min = parseInt($("#spendingBoundMin-"+id).val());
	var max = parseInt($("#spendingBoundMax-"+id).val());
	var perc = parseInt($("#pointsPercentage-"+id).val());
	
	if((isNaN(min) || min < 0) || (isNaN(max) || max < 0) || (isNaN(perc) || perc < 0)){
		$("#user-cat-accept-"+id).attr('disabled',true);
	}else{
		$("#user-cat-accept-"+id).attr('disabled',false);
	}
	
	console.log(id);
	
	
}

function buildUserCatsEditControls(userCategory){
	var str = buildUserCatsEditControlsFormGroup("Points Percentage","pointsPercentage", userCategory.pointsPercentage, userCategory.id);
	str += buildUserCatsEditControlsFormGroup("Spending Bound Max","spendingBoundMax", userCategory.spendingBoundMax, userCategory.id);
	str += buildUserCatsEditControlsFormGroup("Spending Bound Min","spendingBoundMin", userCategory.spendingBoundMin, userCategory.id);
	return str;
}

function buildUserCatsPanel(allUserCategories){
	//build ui with components for each user category
	var str = "<div class=\"container-fluid\">";
	
	for(var i in allUserCategories){
		var userCat = allUserCategories[i];
		str += "<div class=\"row\">";
		str += createViewWellStart(userCat.categoryName);
		str += buildUserCatsEditControls(userCat);
		str += createViewWellEnd();
		
		str += createControlWellStart();
		str += buildControllWellControl(userCat);
		str += createControlWellEnd();
		
		str += "</div>";
	}
	
	str+= "</div>";
	
	return str;
}

var allCategoriesAvailable;

function buildCategoryList(allCategories){
	var str = "<div class=\"col-lg-2 col-md-2 col-sm-2 col-xs-2\">" +
				"<div class=\"list-group\">";
	
	for(var i in allCategories){
		var cat = allCategories[i];
		var encd = encodeURIComponent(JSON.stringify(cat));
		str += "<a href=\"#\" class=\"list-group-item\" onclick=\"clickedCategoryLink('"+encd+"')\" id=\"category-link-"+cat.id+"\">"+cat.categoryName+" - ["+cat.id+"]</a>";
	}
	
	str += "</div></div>";
	
	return str;
}

function buildCategoryEditingControl(){
	var str = "<div class=\"col-lg-10 col-md-10 col-sm-10 col-xs-10\" id=\"editing-product-category\">";
	
	str +=	"<div class=\"form-group\">";
	str +=		"<label>Category Name</label>";
	str +=		"<input type=\"text\" class=\"form-control\" id=\"editing-prod-category-name\">";
	str += "</div>";
	
	str +=	"<div class=\"form-group\">";
	str +=		"<label>Max Discount (%)</label>";
	str +=		"<input type=\"number\" min=\"0\" class=\"form-control\" id=\"editing-prod-category-discount\">";
	str += "</div>";
	
	str +=	"<div class=\"form-group\">";
	str +=		"<label>Parent Category</label>";
	str +=			"<select class=\"form-control\" id=\"editing-prod-category-parent\">";
	str +=				"<option></option>";
	
	for(var i in allCategoriesAvailable){
		str += "<option>"+allCategoriesAvailable[i].id+"</option>";
	}
	
	str +=			"</select>";
	str += "</div>";
	
	str += 	"<div class=\"form-group\">";
	str += 	"<button type=\"button\" class=\"btn btn-primary\" onclick=\"editedExistingProductCategory()\">Accept Edit</button>";
	str += "&nbsp;";
	str += 	"<button type=\"button\" class=\"btn btn-primary\" onclick=\"createdNewProductCategory()\">Create New</button>";
	str += 	"</div>";
	
	str += "<input type=\"hidden\" id=\"prod-cat-id\">";
	
	str += "</div>";
	
	return str;
}

function createdNewProductCategory(){
	var name = $("#editing-prod-category-name").val();
	var parent = $("#editing-prod-category-parent").val()==""?null:parseInt($("#editing-prod-category-parent").val());
	var perc = $("#editing-prod-category-discount").val();
	var hiddenId = null;
	
	var ob = {name:name,parent:parent,perc:perc,id:hiddenId};
	
	$.ajax({
		url:"admin-control-panel/new-product-category",
		contentType:"application/json",
		data:JSON.stringify(ob),
		dataType:"application/json",
		type:"POST",
		complete:function(data){
			var dta = jQuery.parseJSON(data.responseText);
			$("#product-categories-row-panel").html(buildProductCatsPanel(dta));
		}
	});
}

function editedExistingProductCategory(){
	var name = $("#editing-prod-category-name").val();
	var parent = $("#editing-prod-category-parent").val()==""?null:parseInt($("#editing-prod-category-parent").val());
	var perc = $("#editing-prod-category-discount").val();
	var hiddenId = $("#prod-cat-id").val();
	
	var ob = {name:name,parent:parent,perc:perc,id:parseInt(hiddenId)};
	console.log(ob);
	$.ajax({
		url:"admin-control-panel/edit-product-category",
		contentType:"application/json",
		data:JSON.stringify(ob),
		dataType:"application/json",
		type:"POST",
		complete:function(data){
			var dta = jQuery.parseJSON(data.responseText);
			if(dta == null)
				return;
			$("#product-categories-row-panel").html(buildProductCatsPanel(dta));
		}
	});
}

function clickedCategoryLink(encodedCat){
	var category = jQuery.parseJSON(decodeURIComponent(encodedCat));
	
	for(var i in allCategoriesAvailable){
		var cat = allCategoriesAvailable[i];
		$("#category-link-"+cat.id).removeClass('active');
	}
	$("#category-link-"+category.id).addClass('active');
	console.log(category);
	
	$("#editing-prod-category-name").val(category.categoryName);
	$("#editing-prod-category-parent").val((category.parentCategory==null)?"":category.parentCategory.id);
	$("#editing-prod-category-discount").val(category.maxDiscount);
	$("#prod-cat-id").val(category.id);
}

function buildProductCatsPanel(allProductCategories){
	allCategoriesAvailable = allProductCategories;
	var str = "<div class=\"container-fluid\"><div class=\"row\">";
	
	str += buildCategoryList(allProductCategories);
	str += buildCategoryEditingControl();
	str+= "</div></div>";
	
	return str;
}

var allAvailableSalesEvents;

function buildSalesEventEditingControls(){
	var str = "<div class=\"col-lg-10 col-md-10 col-sm-10 col-xs-10\" id=\"editing-sales-event\">";
	
	str += "<input type=\"hidden\" id=\"sales-event-id\">";
	
	str +=  "<div class=\"form-group\">";
	str +=		"<label>Sales Event Name</label>";
	str +=		"<input type=\"text\" class=\"form-control\" id=\"editing-sales-event-name\">";
	str +=	"</div>";
	
	str +=	"<div class=\"form-group\">";
	str +=		"<label>Start Date</label>";
	str +=		"<input type=\"date\" class=\"form-control\" id=\"editing-sales-event-start-date\">";
	str += "</div>";
	
	str +=	"<div class=\"form-group\">";
	str +=		"<label>End Date</label>";
	str +=		"<input type=\"date\" class=\"form-control\" id=\"editing-sales-event-end-date\">";
	str += "</div>";
	
	str +=	"<div class=\"form-group\">";
	str +=		"<label>Discount (%)</label>";
	str +=		"<input type=\"number\" class=\"form-control\" id=\"editing-sales-event-discount\">";
	str += "</div>";
	
	str += "<div class=\"form-group\">";
	str +=		"<button type=\"button\" class=\"btn btn-primary\" onclick=\"createNewSalesEvent()\" >Create New</button>&nbsp;";
	str +=		"<button type=\"button\" class=\"btn btn-primary\" onclick=\"acceptChangesSalesEvent()\">Accept Changes</button>";
	str += "</div>";
	
	str += "<fieldset id=\"available-cats\"><legend>Valid For Categories:</legend>";
	for(var i in allCategoriesAvailable){
		var cat = allCategoriesAvailable[i];
		str += "<div class=\"checkbox\"></label><input type=\"checkbox\" value=\""+cat.id+"\">"+cat.categoryName+"</label></div>";
	}
	str += "</fieldset>";
	
	str += "</div>";
	
	return str;
}

function createNewSalesEvent(){
	var id = null;
	var name = $("#editing-sales-event-name").val();
	var startDate = $("#editing-sales-event-start-date").val();
	var endDate = $("#editing-sales-event-end-date").val();
	var discount = $("#editing-sales-event-discount").val();
	
	
	var selected = [];
	$("#available-cats input:checked").each(function(){
		selected.push(parseInt($(this).attr('value')));
	});
	
	var ob = {id:id, name:name, startDate:new Date(startDate).getTime(),endDate:new Date(endDate).getTime(), discount:discount, validForCategories:selected};

	$.ajax({
		url:"admin-control-panel/new-sales-event",
		contentType:"application/json",
		data:JSON.stringify(ob),
		dataType:"application/json",
		type:"POST",
		complete:function(data){
			var dta = jQuery.parseJSON(data.responseText);
			$("#sales-event-row-panel").html(buildSalesEventsPanel(dta));
		}
	});
	
	
}

function acceptChangesSalesEvent(){
	var id = $("#sales-event-id").val();
	var name = $("#editing-sales-event-name").val();
	var startDate = $("#editing-sales-event-start-date").val();
	var endDate = $("#editing-sales-event-end-date").val();
	var discount = $("#editing-sales-event-discount").val();
	
	var selected = [];
	$("#available-cats input:checked").each(function(){
		selected.push(parseInt($(this).attr('value')));
	});
	
	var ob = {id:id, name:name, startDate:new Date(startDate).getTime(),endDate:new Date(endDate).getTime(), discount:discount, validForCategories:selected};

	$.ajax({
		url:"admin-control-panel/edit-sales-event",
		contentType:"application/json",
		data:JSON.stringify(ob),
		dataType:"application/json",
		type:"POST",
		complete:function(data){
			var dta = jQuery.parseJSON(data.responseText);
			$("#sales-event-row-panel").html(buildSalesEventsPanel(dta));
		}
	});
}

function buildSalesEventList(){
	var str = "<div class=\"col-lg-2 col-md-2 col-sm-2 col-xs-2\">" +
					"<div class=\"list-group\">";

	for(var i in allAvailableSalesEvents){
		var salesEvent = allAvailableSalesEvents[i];
		var encd = encodeURIComponent(JSON.stringify(salesEvent));
		str += "<a href=\"#\" class=\"list-group-item\" onclick=\"clickedSalesEventLink('"+encd+"')\" " +
				"id=\"sales-event-link-"+salesEvent.id+"\">" + salesEvent.eventName + " - [" + salesEvent.id +"]</a>";
	}
	
	str += "</div></div>";
	
	return str;
}

function clickedSalesEventLink(encoded){
	var salesEvent = jQuery.parseJSON(decodeURIComponent(encoded));
	
	$("#sales-event-id").val(salesEvent.id);
	$("#editing-sales-event-name").val(salesEvent.eventName);
	$("#editing-sales-event-start-date").val(yyyymmdd(new Date(salesEvent.startDate)));
	$("#editing-sales-event-end-date").val(yyyymmdd(new Date(salesEvent.endDate)));
	$("#editing-sales-event-discount").val(salesEvent.eventDiscount);
	
	var cats = {};
	for(var i in allCategoriesAvailable){
		var cat = allCategoriesAvailable[i];
		for(var j in cat.relation){
			var se = cat.relation[j].salesEvent;
			if(se.id == salesEvent.id){
				cats[cat.id] = ""+cat.id;
			}
		}
	}
	
	$("#available-cats input").each(function(){
		if(cats.hasOwnProperty($(this).attr('value'))){
			$(this).attr('checked',true);			
		}else{
			$(this).attr('checked',false);
		}
	});
}

function buildSalesEventsPanel(allSalesEvents){
var str = "<div class=\"container-fluid\">";

	allAvailableSalesEvents = allSalesEvents;

	str += buildSalesEventList();
	str += buildSalesEventEditingControls();
	
	str+= "</div>";
	
	return str;
}

var idControls = "#controls-row";
var idUserCatsRow = "#user-categories-row";
var idProductCatsRow = "#product-categories-row";
var idSalesEventsRow = "#sales-event-row";

var buttonUserCats = "#button-user-categories";
var buttonProdCats = "#button-product-categories";
var buttonSaleEvts = "#button-sales-events";

primaryButton = "btn-primary";
defaultButton = "btn-default";

function setDefaultButtons(){
	$(buttonProdCats).removeClass(primaryButton);
	$(buttonSaleEvts).removeClass(primaryButton);
	$(buttonUserCats).removeClass(primaryButton);
	
	$(buttonProdCats).addClass(defaultButton);
	$(buttonSaleEvts).addClass(defaultButton);
	$(buttonUserCats).addClass(defaultButton);
}

function hidePanels(){
	$(idProductCatsRow).hide();
	$(idSalesEventsRow).hide();
	$(idUserCatsRow).hide();
}

function showPanel(panelId){
	$(panelId).show();
}

function setPrimaryButton(buttonId){
	$(buttonId).addClass(primaryButton);
	$(buttonId).removeClass(defaultButton);
}

function switchPanel(buttonId,panelId){
	setDefaultButtons();
	setPrimaryButton(buttonId);
	hidePanels();
	showPanel(panelId);
}
