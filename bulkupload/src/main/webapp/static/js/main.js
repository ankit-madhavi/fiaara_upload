$(document).ready(function() {

});

$("#btnSubmit").click(function(event) {
	event.preventDefault();
	$('#sql-query').val('');
	var file = $('#fileInput').val();
	var tablename = $('#tableName').val();
	var filename = check(file, "File");
	var table = check(tablename, "TableName");
	if (filename && table) {
		$('#FileError').val('');
		var form = $('#fileUploadForm')[0];
		var data = new FormData(form);
		$.ajax({
			type: "POST",
			enctype: 'multipart/form-data',
			url: "http://localhost:8080/read/"+tablename,
			data: data,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			success: function(response) {
				$('#tableName').attr("disabled", true);
				$('#sql-query').val(response);
			},
			error: function(error) {
				$('#errorMessage').html("Something Went Worng..")
				$('#error').show();
				$("#error").delay(8000).fadeOut("slow");
			}
		});
	}
});


$("#fileInput").click(function() {
	$('#tableName').attr("disabled", false);
	});

function check(filed, message) {
	if (filed == "" || filed == null) {
		$("#" + message + "Error").html(message + " is Required");
		$("#" + message + "Error").show();
		flag = false;
	} else {
		$("#" + message + "Error").hide();
		flag = true;
	}
	return flag;
}


$("#copy").click(function() {
	var copyText = $("#sql-query").val();
	navigator.clipboard.writeText(copyText);
});

$("#download").click(function() {
	var tablename = $('#tableName').val();
	window.location.href = "http://localhost:8080/download/"+tablename;

});
