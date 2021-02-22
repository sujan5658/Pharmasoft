<c:choose>
	<c:when test="${message.status eq true}">
		<script type="text/javascript">
			swal("Error Occured", "${message.message}", "error");
		</script>
	</c:when>
	<c:when
		test="${message.status eq false and message.message ne 'defaultMessage'}">
		<script type="text/javascript">
			swal("Deleted", "${message.message}", "success");
		</script>
	</c:when>
</c:choose>
<form class="form-inline" method="post" action="searchProduct">
	Search : By Generic Name : <select class="form-control" name="genericName">
	<c:forEach items="${genericNames}" var="product">
		<option>${product}</option>
	</c:forEach>
	</select> : By Brand Name : <select class="form-control" name="brandName">
		<option>None</option>
		<c:forEach items="${brandNames}" var="product">
			<option>${product}</option>
		</c:forEach>
	</select> : By Company Name :<select class="form-control" name="companyName">
		<option>None</option>
		<c:forEach items="${companyNames}" var="product">
			<option>${product}</option>
		</c:forEach>
	</select>
	<input type="hidden" name="page" value="productList"/>
	<button type="submit" class="btn btn-primary">Search</button>
</form>
<c:choose>
	<c:when test="${searchedProducts.size() eq 0}">
			<script>swal("Search Result","Sorry Product Not Found !!!","error");</script>
	</c:when>
	<c:otherwise>
		<table class="table table-hover">
	<thead style="background-color: #441E71; color: white;">
		<tr>
			<th>SN</th>
			<th>Generic Name</th>
			<th>Brand Name</th>
			<th>Company Name</th>
			<th>Distributor</th>
			<th>Manufacture Date</th>
			<th>Expire Date</th>
			<th>Quantity</th>
			<th>Selling Price</th>
			<th>Cost Price</th>
			<th>Manage</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="id" value="${1}" />
		<c:forEach items="${searchedProducts}" var="product">
			<tr>
				<td><c:out value="${id}" /></td>
				<c:set var="id" value="${id+1}" />
				<td>${product.genericName}</td>
				<td>${product.brandName}</td>
				<td>${product.companyName}</td>
				<td>${product.distributor}</td>
				<td>${product.manufactureDate}</td>
				<td>${product.expireDate}</td>
				<td>${product.quantity}</td>
				<td>${product.sellingPrice}</td>
				<td>${product.costPrice}</td>
				<td>
					<div class="row">
						<div class="col-sm-6">
							<form:form method="post" action="updateProduct">
								<input type="hidden" value="${product.id}" name="id" />
								<button type="submit" class="btn btn-success">
									<small>Update</small>
								</button>
							</form:form>
						</div>
						<div class="col-sm-6">
							<form:form method="post" action="deleteProduct" onsubmit="return confirm('Confirm Deletion ?')">
								<input type="hidden" value="${product.id}" name="id" />
								<button type="submit" class="btn btn-danger">
									<small>Delete</small>
								</button>
							</form:form>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${productList.size() eq 0}">
			<h4>There are no any product recently.</h4>
	</c:when>
	<c:otherwise>
		<h4>Available Medicines</h4>
	</c:otherwise>
</c:choose>
<table class="table table-hover">
	<thead style="background-color: #441E71; color: white;">
		<tr>
			<th>SN</th>
			<th>Generic Name</th>
			<th>Brand Name</th>
			<th>Company Name</th>
			<th>Distributor</th>
			<th>Manufacture Date</th>
			<th>Expire Date</th>
			<th>Quantity</th>
			<th>Selling Price</th>
			<th>Cost Price</th>
			<th>Manage</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="id" value="${1}" />
		<c:forEach items="${productList}" var="product">
			<tr>
				<td><c:out value="${id}" /></td>
				<c:set var="id" value="${id+1}" />
				<td>${product.genericName}</td>
				<td>${product.brandName}</td>
				<td>${product.companyName}</td>
				<td>${product.distributor}</td>
				<td>${product.manufactureDate}</td>
				<td>${product.expireDate}</td>
				<td>${product.quantity}</td>
				<td>${product.sellingPrice}</td>
				<td>${product.costPrice}</td>
				<td>
					<div class="row">
						<div class="col-sm-6">
							<form:form method="post" action="updateProduct">
								<input type="hidden" value="${product.id}" name="id" />
								<button type="submit" class="btn btn-success">
									<small>Update</small>
								</button>
							</form:form>
						</div>
						<div class="col-sm-6">
							<form:form method="post" action="deleteProduct">
								<input type="hidden" value="${product.id}" name="id" />
								<button type="submit" class="btn btn-danger">
									<small>Delete</small>
								</button>
							</form:form>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>