<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
	if (request.getSession().getAttribute("userName") == null) {
		response.sendRedirect("/pharmaSoft");
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Pharmacist Home</title>
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

thead {
	background-color: #465B58;
	color: white;
	text-align: center;
}

tbody {
	background-color: #CDEFE9;
}

.icon {
	height: 30px;
	width: 30px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="leftBar">
			<div class="row">
				<div class="col-sm-3">
					<img src="${pageContext.request.contextPath}/resources/images/user.png" class="logo">
				</div>
				<div class="col-sm-9">
					<small>Welcome</small>
					<h5><%=request.getSession().getAttribute("userName")%></h5>
				</div>
			</div>
			<hr>
			<div>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img src="${pageContext.request.contextPath}/resources/css/icons/dashboard.svg" class="icon">
					</div>
					<div class="col-sm-10">
						<a href="pdashboard"><button type="button"
								class="btn btn-info menu">DashBoard</button></a>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img src="${pageContext.request.contextPath}/resources/css/icons/bill.svg" class="icon">
					</div>
					<div class="col-sm-10">
						<a href="viewBill"><button type="button"
								class="btn btn-info menu">Bills</button></a>
					</div>
				</div>
				<hr>
				<h6>Change Credentials</h6>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img src="${pageContext.request.contextPath}/resources/css/icons/credentials.svg" class="icon">
					</div>
					<div class="col-sm-10">
						<a href="pcredentialsChange"><button type="button"
								class="btn btn-info menu">
								<small><b>Change Credentials</b></small>
							</button></a>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-2" style="padding-top: 8px">
						<img src="${pageContext.request.contextPath}/resources/css/icons/logout.svg" class="icon">
					</div>
					<div class="col-sm-10">
						<form action="logout" onsubmit="return confirm('Are You Sure ? ')">
						<button type="submit" class="btn btn-info menu">
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
						Be Sure While Billing. (*_*).  Today is : <%=formatter.format(new Date()) %>
					</div>
					<div class="col-sm-3">
						<img src="${pageContext.request.contextPath}/resources/css/icons/notification.svg" class="icon"><sup><span
							class="notify">New</span></sup>
					</div>
				</div>
			</div>
			<div class="infoBox" style="padding-bottom: 15px;">
				<c:choose>
					<c:when test="${page eq 'pdashboard'}">
						<%@include file="includes/pharmacist/dashboard.jsp"%>
					</c:when>
					<c:when test="${page eq 'pcredentials'}">
						<%@include file="includes/pharmacist/pchangeCredentials.jsp"%>
					</c:when>
					<c:when test="${page eq 'viewBill'}">
						<%@include file="includes/pharmacist/bill.jsp"%>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>