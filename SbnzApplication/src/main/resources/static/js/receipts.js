
function wellStart(){
	return "<div class=\"row\"><div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\"><div class=\"well\">";
}

function tableStart(){
	return "<table class=\"table\"><tr>" +
			"<th>Product Id</th>" +
			"<th>Product Name</th>" +
			"<th>Product Amount</th>" +
			"<th>Minimal Amount</th>" +
			"<th>Reason For Ordering More</th>" +
			"<th>Order Amount</th>" +
			"<th>Order More</th></tr>";
}

function receiptTableHeader(){
	return "<table class=\"table table-bordered\"><tr>" +
			"<th>Receipt Id</th>" +
			"<th>Initial Price</th>" +
			"<th>Final Price</th>" +
			"<th>Entries</th>" +
			"<th>Approve</th>" +
			"<th>Deny</th>";
}

function receiptEntryTableHeader(){
	return "<table class=\"table table-bordered\">" +
			"<tr>" +
			"<th>Product Id</th>" +
			"<th>Product Quantity</th>" +
			"</tr>";
}

function tableData(val){
	return "<td>" + val + "</td>";
}

function tableEnd(){
	return "</table>";
}

function wellEnd(){
	return "</div></div></div>";
}

var receiptPanel = "#receipt-panel";
function receiptPanelFcn(unprocessedReceipts){
	if(unprocessedReceipts.size == 0){
		$(receiptPanel).html("No body wants our stuff... :(");
	}else{
		var str = "";
		
		str += wellStart();
		str += receiptTableHeader();
		
		for(var i in unprocessedReceipts){
			var rec = unprocessedReceipts[i];
			
			str += "<tr>";
			str += tableData(rec.id);
			str += tableData(rec.initalPrice + " $");
			str += tableData(rec.finalPrice + " $");
			
			var entriesTable = receiptEntryTableHeader();
			for(var j in rec.receiptEntries){
				var entry = rec.receiptEntries[j];
				entriesTable += "<tr>"; 
				entriesTable += tableData(entry.product.id);
				entriesTable += tableData(entry.numberOfProducts);
				entriesTable += "</tr>";
			}
			entriesTable += "</table>";

			str += tableData(entriesTable);
			
			str += tableData("<button type=\"button\" " +
					"class=\"btn btn-success\" " +
					"onclick=\"approveReceipt('"+encodeURIComponent(JSON.stringify(rec))+"')\">" +
							"Approve </button>");
			
			str += tableData("<button type=\"button\" " +
					"class=\"btn btn-danger\" " +
					"onclick=\"denyReceipt('"+encodeURIComponent(JSON.stringify(rec))+"')\">" +
							"Deny</button>");
			
			str += "</tr>";
		}
		
		str += wellEnd();
		
		$(receiptPanel).html(str);
	}
}

function approveReceipt(data){
	var rec = jQuery.parseJSON(decodeURIComponent(data));
	$.ajax({
		url:"seller/approve-receipt",
		type:"POST",
		dataType:"application/json",
		contentType:"application/json",
		data:JSON.stringify(rec),
		complete:function(data){
			var dta = jQuery.parseJSON(data.responseText);
			receiptPanelFcn(dta.UNPROCESSED_RECEIPTS);
			productStockPanel(dta.PRODUCTS_FOR_ORDERING,dta.ALL_AVAILABLE_PRODUCTS);
		}
	});
}

function denyReceipt(data){
	var rec = jQuery.parseJSON(decodeURIComponent(data));
	$.ajax({
		url:"seller/reject-receipt",
		type:"POST",
		dataType:"application/json",
		contentType:"application/json",
		data:JSON.stringify(rec),
		complete:function(data){
			var dta = jQuery.parseJSON(data.responseText);
			console.log(dta);
			receiptPanelFcn(dta.UNPROCESSED_RECEIPTS);
			productStockPanel(dta.PRODUCTS_FOR_ORDERING,dta.ALL_AVAILABLE_PRODUCTS);
		}
	});	
}

function appendOneProd(product,reason){
	var str = "<tr>";
	
	str += tableData(product.id);
	str += tableData(product.name);
	str += tableData(product.amountInStock);
	str += tableData(product.minAmountInStock);
	str += tableData(reason);
	str += tableData("<input type=\"number\" class=\"form-control\" id=\"order-amount-"+product.id+"\">");
	str += tableData("<button type=\"button\" class=\"btn btn-primary\" onclick=\"orderProductAmount('"+product.id+"')\">Order</button>");
	
	str += "</tr>";
	
	return str;
}

function orderProductAmount(id){
	var amount = $("#order-amount-"+id).val();
	///order-products
	$.ajax({
		url:"seller/order-products",
		type:"POST",
		dataType:"application/json",
		contentType:"application/json",
		data:JSON.stringify({id:id,amount:amount}),
		complete:function(data){
			var dta = jQuery.parseJSON(data.responseText);
			console.log(dta);
			receiptPanelFcn(dta.UNPROCESSED_RECEIPTS);
			productStockPanel(dta.PRODUCTS_FOR_ORDERING,dta.ALL_AVAILABLE_PRODUCTS);
		}
	});
}

var prodContainer = "#product-container";
function productStockPanel(forOrdering,allProducts){
	
	var str = "";
	
	if(forOrdering.size == 0){
		$(prodContainer).html("All is well.");
	}else{

		str += wellStart();
		str += tableStart();
		
		for(var i in allProducts){
			
			var prod = allProducts[i];
			var prodId = prod.id;
			//if this product is not in the map for ordering, it does not need to be ordered
			if(! forOrdering.hasOwnProperty(prodId))
				continue;
			str += appendOneProd(prod,forOrdering[prodId]);
			
		}
		
		str += tableEnd();
		str += wellEnd();

		$(prodContainer).html(str);
	}
}