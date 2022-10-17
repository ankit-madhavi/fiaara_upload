<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demo Project</title>
<c:url value="static/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<div class="row d-flex justify-content-center mt-100">

		<div class="col-md-8">

			<div class="card">
				<div class="card-header">
					<h5>File Upload</h5>
				</div>
				<div class="card-block">
					<form method="POST" id="fileUploadForm" enctype="multipart/form-data" class="dropzone dz-clickable">
						<div class="dz-default dz-message">
							<input type="file" class="form-control" name="file" id="fileInput" >
							<div class="text-center m-t-20">
								<button type="submit" class="btn btn-primary"  id="btnSubmit">Upload Now</button>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	<script src="static/js/main.js"></script>

</body>
</html>
