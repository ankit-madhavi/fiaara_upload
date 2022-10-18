$(document).ready(function() {

});

$("#btnSubmit").click(function(event) {
	event.preventDefault();
	$('#sql-query').val('');
	var file = $('#fileInput').val();
	var filename = check(file, "File");
	if (filename) {
		$('#FileError').val('');
		var form = $('#fileUploadForm')[0];
		var data = new FormData(form);
		$.ajax({
			type: "POST",
			enctype: 'multipart/form-data',
			url: "http://localhost:8080/read",
			data: data,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			success: function(response) {
				$('#sql-query').val(response);
			},
			error: function(error) {
				$('#errorMessage').html(error.responseJSON.message)
				$('#error').show();
				$("#error").delay(8000).fadeOut("slow");
			}
		});
	}
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
	var query = $("#sql-query").val();
	if (query === "" || query == null) {
		$('#errorMessage').html("Query not available");
		$('#error').show();
		$("#error").delay(8000).fadeOut("slow");
	} else {
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/download/" + query,
			success: function() {
				$('#errorMessage').html('');
			},
			error: function(error) {
				$('#errorMessage').html(error.responseJSON.message);
				$('#error').show();
				$("#error").delay(8000).fadeOut("slow");
			}
		});
	}
});
