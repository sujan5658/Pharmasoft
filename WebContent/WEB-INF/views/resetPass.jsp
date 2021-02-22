<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Account</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.slim.min.js"></script>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/user.png" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/sweetalert.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/sweetalert-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/sweetalert.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrapp.min.js"></script>

<style type="text/css">
body {
	padding-top: 100px;
}

label {
	color: white;
	float: left;
	font-weight: bold;
}
</style>
</head>
<body
	background="${pageContext.request.contextPath}/resources/images/medicine.jpg">
	<center>
		<div class="card"
			style="width: 350px; background: transparent; padding-top: 5px">
			<img class="card-img-top"
				src="${pageContext.request.contextPath}/resources/images/user.png"
				alt="Card image" style="width: 50%">
			<div class="card-body">
				<form method="post" action="recoverPass">
					<div class="form-group">
						<label>Enter New User Name : </label>
						<input type="text" name="userName" class="form-control" placeholder="user Name"/>
					</div>
					<div class="form-group">
						<label>Enter New Password : </label>
						<input type="password" name="userPass" class="form-control" placeholder="*******"/>
					</div>
					<button type="submit" class="btn btn-success">Change</button>
				</form>
			</div>
		</div>
		
	</center>
</body>
</html>