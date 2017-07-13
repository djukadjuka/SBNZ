function createTd(prop,propval){
	var td = "<th>"+prop+"</th><td>"+propval+"</td>";
	return td;
}

function createTr(prop,propval){
	return "<tr>" + createTd(prop,propval) + "</tr>";
}

function generateTableDataCategory(category){
	var fullString = "";
	console.log(category);
	fullString += createTr("Category Name", category.categoryName);
	fullString += createTr("Reward Point Percentage", category.pointsPercentage);
	fullString += createTr("Spending Bound MAX", category.spendingBoundMax);
	fullString += createTr("Spending Bound MIN", category.spendingBoundMin);
	
	return fullString;
}

function generateTableDataBuyer(buyerObject){
	var fullString = "";
	
	fullString += createTr("Address",buyerObject.address);
	fullString += createTr("Reward Points", buyerObject.rewardPoints);
	
	fullString += generateTableDataCategory(buyerObject.buyerCategory);
	
	return fullString;
}

function generateTableDataUser(userObject,buyerObject){
	
	var fullString = "";
	
	fullString += createTr("Name",userObject.name);
	fullString += createTr("Surname",userObject.surname);
	fullString += createTr("Username",userObject.username);
	fullString += createTr("Password",userObject.password);
	fullString += createTr("Date Registered", yyyymmdd(new Date(userObject.registryDate)));
	
	switch(userObject.role){
		case 0:{
			fullString += createTr("Role", "Buyer");
			fullString += generateTableDataBuyer(buyerObject);
			break;
		}
		case 1:{
			fullString += createTr("Role", "Seller");
			break;
		}
		case 2:{
			fullString += createTr("Role", "Manager");
			break;
		}
	}
	
	return fullString;
}

var purchHistPanel = "#purchase-history-panel";

function buildWellStart(){
	var str = "<div class=\"row\"><div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\"><div class=\"well\">";
	
	return str;
}

function closeWell(){
	var str = "</div></div></div>";
	return str;
}

function buildTableForEntry(entry){
	var str = "<table class=\"table\">";
	str += createTr("Product Name", entry.product.name);
	str += createTr("Product Price", entry.product.price + " $");
	str += createTr("Amount", entry.numberOfProducts);
	str += "</table>";
	return str;
}

function buildRestOfWell(receipt){
	
	var str = "<table class=\"table\">";
	
	var aquiredPoints = receipt.aquiredPoints==null?"0":receipt.aquiredPoints;
	var dateOfTransaction = yyyymmdd(new Date(receipt.dateOfTransaction));
	var discountPercentage = receipt.discountPercentage == null? "0 %": receipt.discountPercentage + " %";
	var finalPrice = receipt.finalPrice == null ? "0 $" : receipt.finalPrice+" $";
	var initialPrice = receipt.initalPrice == null? "0 $" : receipt.initalPrice + " $";
	var receiptState = receipt.receiptState;
	
	if(receiptState == 0){
		receiptState = "<span class=\"label label-success\">Bought</span>";
	}else if(receiptState == 1){
		receiptState = "<span class=\"label label-warning\">Denied</span>"
	}else{
		receiptState = "<span class=\"label label-warning\">Ordered</span>"
	}
	
	str += createTr("Date Of Transaction", dateOfTransaction);
	str += createTr("Status",receiptState);
	str += createTr("Aquired Points", aquiredPoints);
	str += createTr("Discount", discountPercentage);
	str += createTr("Initial Price", initialPrice);
	str += createTr("Final Price", finalPrice);
	
	var entries = receipt.receiptEntries;
	
	var entriesString = "";
	for(var i in entries){
		var entry = entries[i];
		entriesString += buildTableForEntry(entry);
	}
	
	str += createTr("Entries",entriesString);
	str += "</table>";
	
	return str;
}

function generatePurchasePanel(receipts){
	
	var str = "";
	
	for(var i in receipts){
		str += buildWellStart();
		str += buildRestOfWell(receipts[i]);
		str += closeWell();
	}
	
	$(purchHistPanel).html(str);
}