<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
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
	color: black;
	float: left;
}

a {
	float: left;
	text-decoration: none;
	font-size: small;
}

.errors {
	color: red;
}
</style>
</head>
<body
	background="${pageContext.request.contextPath}/resources/images/medicine.jpg">
	<center>
		<c:choose>
			<c:when test="${message.status eq true}">
				<script type="text/javascript">
					swal("Error Occured", "${message.message}", "error");
				</script>
			</c:when>
			<c:when
				test="${message.status eq false and message.message ne 'defaultMessage'}">
				<script type="text/javascript">
					swal("Recovered", "${message.message}", "success");
				</script>
			</c:when>
		</c:choose>
		<div class="card"
			style="width: 350px; background: transparent; padding-top: 5px">
			<img class="card-img-top"
				src="${pageContext.request.contextPath}/resources/images/user.png"
				alt="Card image" style="width: 50%">
			<div class="card-body">
				<form:form modelAttribute="user" action="login">
					<div class="form-group">
						<label for="user name"><b>User Name : </b><small><form:errors
									path="userName" cssClass="errors" /></small></label>
						<form:input path="userName" type="text" class="form-control"
							placeholder="User Name" />
					</div>
					<div class="form-group">
						<label for="user pass"><b>User Password : </b><small><form:errors
									path="userPass" cssClass="errors" /></small></label>
						<form:input path="userPass" type="password" class="form-control" />
					</div>
					<div class="form-group">
						<label for="user role"><b>Role : </b><small><form:errors
									path="role" cssClass="errors" /></small></label>
						<form:select path="role" class="form-control">
							<option>Administrator</option>
							<option>Pharmacist</option>
						</form:select>
						<small>
					</div>
					<button type="submit" class="btn btn-primary">LOG IN</button>
				</form:form>
				<a href="#myModal" data-toggle="modal" data-target="#myModal"><i>Forget
						Password</i></a>
			</div>
		</div>
	</center>
	<!-- The Modal -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Enter the registered Email Address</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<form method="post" action="recoverAccount">
						<div class="form-group">
							<label>Email : </label> <input type="email" name="email"
								class="form-control" placeholder="Valid Email"
								required="required" />
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>