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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
<div id="error" class="col-sm-12" style="display: none;">
			<div class="alert alert-danger">
				<strong><em class="fa fa-thumbs-down"></em> &nbsp;</strong> 
				<span id="errorMessage">Something Went Wrong!!</span>
			</div>
		</div>


	<div class="row d-flex justify-content-center mt-30">

		<div class="col-md-8">

			<div class="card">
				<div class="card-header">
					<h5>File Upload</h5>
				</div>
				<div class="card-block">
					<form method="POST" id="fileUploadForm" enctype="multipart/form-data" class="dropzone dz-clickable">
						<div class="dz-default dz-message">
							<input type="file" class="form-control" name="file" id="fileInput" >
							<span id="FileError" class="error"></span>
							<div class="text-center m-t-20">
								<button type="submit" class="btn btn-primary"  id="btnSubmit">Upload Now</button>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	
	
	<div class="row d-flex justify-content-center mt-30">

		<div class="col-md-10">

			<div class="card">
				<div class="card-header">
					<div class="form-group row">
						<h5>Upload File SQL Query</h5>
						<button class="btn btn-outline-secondary" style="margin-left: 65%" id="download"><em class="fa fa-download"></em></button>
						<button class="btn btn-outline-secondary" style="margin-left: 4%" id="copy"><em class="fa fa-copy"></em></button>
					</div>
				</div>
				<div class="card-block">
					<textarea rows="10" cols="108" id="sql-query" disabled="disabled"></textarea>
				</div>
			</div>

		</div>
	</div>
	<script src="static/js/main.js"></script>

</body>
</html>
