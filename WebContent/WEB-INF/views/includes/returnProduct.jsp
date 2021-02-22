<h4>Before Returning Medicine Check If It Is Available.</h4>
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
	<input type="hidden" name="page" value="returnProduct"/>
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
			</tr>
		</c:forEach>
	</tbody>
</table>
	</c:otherwise>
</c:choose>
<form action="productReturn" method="post" onsubmit="return confirm('Confirm Returning Medicine ?')">
	<table class="table table-hover">
		<tr>
			<th colspan="2"><h4>Return Product / Medicine</h4></th>
		</tr>
		<tr>
			<th><label>Generic Name </label></th>
			<td><select class="form-control" name="genericName" required="required">
					<c:forEach items="${genericNames}" var="product">
						<option>${product}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th><label>Brand Name </label></th>
			<td><select class="form-control" name="brandName" required="required">
					<c:forEach items="${brandNames}" var="product">
						<option>${product}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th><label>Company Name </label></th>
			<td><select class="form-control" name="companyName" required="required">
					<c:forEach items="${companyNames}" var="product">
						<option>${product}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th><label>Quantity </label></th>
			<td><input type="number" class="form-control"
				placeholder="Quantity of Product" name="quantity" required="required" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">Return
					Product</button>
			</td>
		</tr>
	</table>
</form>