$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "Doctor_API",
		type : type,
		data : $("#formDoc").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});


});



function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formDoc")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "Doctor_API",
		type : "DELETE",
		data : "id=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

$(document).on("click",".btnUpdate",function(event) {
			$("#hidItemIDSave").val(
			$(this).closest("tr").find('#hidItemIDUpdate').val());
			$("#id").val($(this).closest("tr").find('td:eq(0)').text());
			$("#name").val($(this).closest("tr").find('td:eq(1)').text());
			$("#address").val($(this).closest("tr").find('td:eq(2)').text());
			$("#location").val($(this).closest("tr").find('td:eq(3)').text());
			$("#mobile").val($(this).closest("tr").find('td:eq(4)').text());
		});

function validateItemForm() {
	// CODE
//	if ($("#id").val().trim() == "") {
//		return "Insert Item Doctor Id.";
//	}
	// NAME
	if ($("#name").val().trim() == "") {
		return "Insert Item Name.";
	}

	// PRICE-------------------------------
	if ($("#address").val().trim() == "") {
		return "Insert Item Address.";
	}
	
	if ($("#location").val().trim() == "") {
		return "Insert Item Description.";
	}
	
	if ($("#mobile").val().trim() == "") {
		return "Insert Item Description.";
	}
	// is numerical value
	var tmpPrice = $("#mobile").val().trim();
	
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value.";

	}

	return true;
}
