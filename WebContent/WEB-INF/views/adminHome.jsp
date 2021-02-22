<%@page import="org.apache.jasper.tagplugins.jstl.core.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
	if (request.getSession().getAttribute("userName") == null
			|| !request.getSession().getAttribute("userName").equals("ValidUser")) {
		response.sendRedirect("/pharmaSoft");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.slim.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/ownStyle.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/user.png" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/sweetalert.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/sweetalert-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/sweetalert.js"></script>
<style type="text/css">
.error {
	color: red;
	font-size: small;
	font-style: italic;
}

.success {
	color: green;
	font-style: italic;
	font-size: small;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="leftBar">
			<div class="row">
				<div class="col-sm-3">
					<img
						src="${pageContext.request.contextPath}/resources/images/user.png"
						class="logo">
				</div>
				<div class="col-sm-9">
					<small>Welcome</small>
					<h5>Administrator</h5>
				</div>
			</div>
			<hr>
			<div>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img
							src="${pageContext.request.contextPath}/resources/css/icons/dashboard.svg"
							class="icon">
					</div>
					<div class="col-sm-10">
						<a href="dashboard"><button type="button"
								class="btn btn-info menu">DashBoard</button></a>
					</div>
				</div>
				<hr>
				<h5>Stock Management</h5>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img
							src="${pageContext.request.contextPath}/resources/css/icons/stuck.svg"
							class="icon">
					</div>
					<div class="col-sm-10">
						<a href="addProduct"><button type="button"
								class="btn btn-info menu">
								<small><b>Add Medicine</b></small>
							</button></a>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img
							src="${pageContext.request.contextPath}/resources/css/icons/returnProduct.svg"
							class="icon">
					</div>
					<div class="col-sm-10">
						<a href="returnProduct"><button type="button"
								class="btn btn-info menu">
								<small><b>Return Medicine</b></small>
							</button></a>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img
							src="${pageContext.request.contextPath}/resources/css/icons/capsule.svg"
							class="icon">
					</div>
					<div class="col-sm-10">
						<a href="productList"><button type="button"
								class="btn btn-info menu">
								<small><b>Medicine List</b></small>
							</button></a>
					</div>
				</div>
				<hr>
				<h5>Manage Pharmacist</h5>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img
							src="${pageContext.request.contextPath}/resources/css/icons/addUser.svg"
							class="icon">
					</div>
					<div class="col-sm-10">
						<a href="addPharmacist"><button type="button"
								class="btn btn-info menu">
								<small><b>Add Pharmacist</b></small>
							</button></a>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img
							src="${pageContext.request.contextPath}/resources/css/icons/userList.svg"
							class="icon">
					</div>
					<div class="col-sm-10">
						<a href="pharmacistList"><button type="button"
								class="btn btn-info menu">
								<small><b>Pharmacist List</b></small>
							</button></a>
					</div>
				</div>
				<hr>
				<h6>Administrator Credentials</h6>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img
							src="${pageContext.request.contextPath}/resources/css/icons/credentials.svg"
							class="icon">
					</div>
					<div class="col-sm-10">
						<a href="changeCredentials"><button type="button"
								class="btn btn-info menu">
								<small><b>Change Credentials</b></small>
							</button></a>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img
							src="${pageContext.request.contextPath}/resources/css/icons/logout.svg"
							class="icon">
					</div>
					<div class="col-sm-10">
						<form action="logout" onsubmit="return confirm('Are You Sure ?')"><button type="submit"
								class="btn btn-info menu">
								<small><b>Log Out</b></small>
							</button>
							</form>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="topBar">
				<div class="row">
					<div class="col-sm-9">
					<%SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); %>
						Be careful before making certain changes... (*_*).  Today is : <%=formatter.format(new Date()) %>
						
					</div>
					<div class="col-sm-3">
					</div>
				</div>
			</div>
			<div class="infoBox">
				<c:choose>
					<c:when test="${page eq 'adminHome'}">
						<%@ include file="includes/adminHome.jsp"%>
					</c:when>
					<c:when test="${page eq 'addProduct'}">
						<%@include file="includes/addProduct.jsp"%>
					</c:when>
					<c:when test="${page eq 'returnProduct'}">
						<%@include file="includes/returnProduct.jsp"%>
					</c:when>
					<c:when test="${page eq 'productList'}">
						<%@include file="includes/productList.jsp"%>
					</c:when>
					<c:when test="${page eq 'addPharmacist'}">
						<%@include file="includes/addPharmacist.jsp"%>
					</c:when>
					<c:when test="${page eq 'updateProduct'}">
						<%@include file="includes/updateProduct.jsp"%>
					</c:when>
					<c:when test="${page eq 'pharmacistList'}">
						<%@include file="includes/pharmacistList.jsp"%>
					</c:when>
					<c:when test="${page eq 'updatePharmacist'}">
						<%@include file="includes/updatePharmacist.jsp"%>
					</c:when>
					<c:when test="${page eq 'changeCredentials'}">
						<%@include file="includes/changeCredentials.jsp"%>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>