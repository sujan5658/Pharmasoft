<c:choose>
	<c:when test="${message.status eq true}">
		<script type="text/javascript">
			swal("Error Occured", "${message.message}", "error");
		</script>
	</c:when>
	<c:when
		test="${message.status eq false and message.message ne 'defaultMessage'}">
		<script type="text/javascript">
			swal("Update Succeed", "${message.message}", "success");
		</script>
	</c:when>
</c:choose>
<form:form modelAttribute="product" action="productUpdate" onsubmit="return confirm('Confirm Updating Product ?')">
	<table class="table table-hover">
		<tr>
			<th colspan="2"><h4>Update Product / Medicine in Pharmacy</h4></th>
		</tr>
		<tr>
			<th><label>Generic Name </label>
			<form:errors path="genericName" cssClass="error"></form:errors></th>
			<form:input path="id" type="hidden" />
			<td><form:input path="genericName" type="text"
					class="form-control" placeholder="Generic Name" required="required" /></td>
		</tr>
		<tr>
			<th><label>Brand Name </label>
			<form:errors path="brandName" cssClass="error"></form:errors></th>
			<td><form:input path="brandName" type="text"
					class="form-control" placeholder="Brand Name" required="required" /></td>
		</tr>
		<tr>
			<th><label>Company Name </label>
			<form:errors path="companyName" cssClass="error"></form:errors></th>
			<td><form:input path="companyName" type="text"
					class="form-control" placeholder="Company Name" required="required" /></td>
		</tr>
		<tr>
			<th><label>Distributor </label>
			<form:errors path="distributor" cssClass="error"></form:errors></th>
			<td><form:input path="distributor" type="text"
					class="form-control" placeholder="Product Distributor" required="required" /></td>
		</tr>
		<tr>
			<th><label>Manufacture Date </label>
			<form:errors path="manufactureDate" cssClass="error"></form:errors></th>
			<td><form:input path="manufactureDate" type="text"
					class="form-control" placeholder="Manufacture Date" required="required" /></td>
		</tr>
		<tr>
			<th><label>Expire Date </label>
			<form:errors path="expireDate" cssClass="error"></form:errors></th>
			<td><form:input path="expireDate" type="text"
					class="form-control" placeholder="Expire Date" required="required" /></td>
		</tr>
		<tr>
			<th><label>Quantity </label>
			<form:errors path="quantity" cssClass="error"></form:errors></th>
			<td><form:input path="quantity" type="number"
					class="form-control" placeholder="Quantity of Product" required="required" /></td>
		</tr>
		<tr>
			<th><label>Cost Price </label>
			<form:errors path="costPrice" cssClass="error"></form:errors></th>
			<td><form:input path="costPrice" type="text"
					class="form-control" placeholder="Cost Price" required="required" /></td>
		</tr>
		<tr>
			<th><label>Selling Price</label>
			<form:errors path="sellingPrice" cssClass="error"></form:errors></th>
			<td><form:input path="sellingPrice" type="text"
					class="form-control" placeholder="Selling Price" required="required" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">Update Product</button>
			</td>
		</tr>
	</table>
</form:form>