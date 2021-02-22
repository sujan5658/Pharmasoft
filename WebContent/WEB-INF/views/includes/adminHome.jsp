<c:choose>
	<c:when test="${updateMessage.status eq true}">
		<script>
			swal("Error Occured","${updateMessage.message}","error");
		</script>
	</c:when>
	<c:when test="${updateMessage.status eq false and updateMessage.message ne 'defaultMessage'}">
		<script>
			swal("Update Succeed","${updateMessage.message}","success");
		</script>
	</c:when>
</c:choose>
<div class="row">
	<div class="col-sm-6">
		<table class="table table-hover table-striped">
			<tr>
				<th colspan="2"><h4>Pharmacy Info</h4></th>
			</tr>
			<tr>
				<th>Name</th>
				<td>${pharmaInfo.pharmacyName}</td>
			</tr>
			<tr>
				<th>Address</th>
				<td>${pharmaInfo.pharmacyAddress}</td>
			</tr>
			<tr>
				<th>Registered Date</th>
				<td>${pharmaInfo.registeredDate}</td>
			</tr>
			<tr>
				<th>Pan Number</th>
				<td>${pharmaInfo.panNo}</td>
			</tr>
			<tr>
				<th>Telephone</th>
				<td>${pharmaInfo.telephone}</td>
			</tr>
		</table>
	</div>
	<div class="col-sm-6" style="border-left: 2px solid black">
		<form:form action="updatePharmacy" modelAttribute="pharmaUpdate" onsubmit="return confirm('Wana Update Pharmacy Info ?')">
			<table class="table table-hover">
				<tr>
					<th colspan="2"><h4>Update Pharmacy Info</h4></th>
				</tr>
				<tr>
					<th><label>Name </label><form:errors path="pharmacyName" cssClass="error"></form:errors></th>
					<td><form:input path="pharmacyName" type="text" class="form-control"
						placeholder="Pharmacy Name" required="required" /></td>
				</tr>
				<tr>
					<th><label>Address </label><form:errors path="pharmacyAddress" cssClass="error"></form:errors></th>
					<td><form:input path="pharmacyAddress" type="text" class="form-control"
						placeholder="Pharmacy Address" required="required" /></td>
				</tr>
				<tr>
					<th><label>Registered Date </label><form:errors path="registeredDate" cssClass="error"></form:errors></th>
					<td><form:input path="registeredDate" type="text" class="form-control"
						placeholder="Pharmacy Registered Date" required="required" /></td>
				</tr>
				<tr>
					<th><label>Pan Number </label><form:errors path="panNo" cssClass="error"></form:errors></th>
					<td><form:input path="panNo" type="text" class="form-control"
						placeholder="Pan Number" required="required" /></td>
				</tr>
				<tr>
					<th><label>Telephone </label><form:errors path="telephone" cssClass="error"></form:errors></th>
					<td><form:input path="telephone" type="text" class="form-control"
						placeholder="Pharmacy Telephone" required="required" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-primary">UPDATE</button>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>