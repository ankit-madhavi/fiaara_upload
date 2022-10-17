$(document).ready(function() {

});

$("#btnSubmit").click(function(event) {
	event.preventDefault();
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
				console.log(response);
			},
			error: function(error) {
				$('#errorMessage').html(error.responseJSON.message)
				$('#error').show();
				$("#error").delay(8000).fadeOut("slow");
			}
		});
});
