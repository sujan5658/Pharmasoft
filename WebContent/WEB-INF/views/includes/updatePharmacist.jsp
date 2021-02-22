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
<form:form modelAttribute="updatePharmacist" action="pharmacistUpdate">
	<table class="table table-hover">
		<tr>
			<th colspan="2"><h4>Update Existing Pharmacist</h4></th>
		</tr>
		<tr>
			<th><label>Full Name</label>
			<form:errors path="fullName" cssClass="error"></form:errors></th>
			<td><form:input path="fullName" type="text" class="form-control"
					placeholder="Full Name" /></td>
		</tr>
		<tr>
			<th><label>Email </label>
			<form:errors path="email" cssClass="error"></form:errors></th>
			<td><form:input path="email" type="email" class="form-control"
					placeholder=" Email " /></td>
		</tr>
		<tr>
			<th><label>Gender :</label>
			<form:errors path="gender" cssClass="error"></form:errors></th>
			<td>Male : <form:radiobutton path="gender" value="M" />&nbsp;
				Female : <form:radiobutton path="gender" value="F" />
			</td>
		</tr>
		<tr>
			<th><label>Address </label>
			<form:errors path="contactAddress" cssClass="error"></form:errors></th>
			<td><form:input path="contactAddress" type="text"
					class="form-control" placeholder="Address" /></td>
		</tr>
		<tr>
			<th><label>Contact No </label>
			<form:errors path="contactNumber" cssClass="error"></form:errors></th>
			<td><form:input path="contactNumber" type="number"
					class="form-control" placeholder="Contact Number" /></td>
		</tr>
		<form:input path="userName" type="hidden" />
		<form:input path="userPass" type="hidden" />
		<form:input path="id" type="hidden"/>
		<tr>
			<th><label>Registered Date </label>
			<form:errors path="registeredDate" cssClass="error"></form:errors></th>
			<td><form:input path="registeredDate" type="text"
					class="form-control" placeholder="Registered Date" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">Update
					Pharmacist</button>
			</td>
		</tr>
	</table>
</form:form>