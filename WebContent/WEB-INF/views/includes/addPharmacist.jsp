<c:choose>
	<c:when test="${message.status eq true}">
		<script type="text/javascript">
			swal("Error Occured", "${message.message}", "error");
		</script>
	</c:when>
	<c:when
		test="${message.status eq false and message.message ne 'defaultMessage'}">
		<script type="text/javascript">
			swal("Addition Succeed", "${message.message}", "success");
		</script>
	</c:when>
</c:choose>
<form:form action="pharmacistAddition" modelAttribute="pharmacist" onsubmit="return confirm('Confirm Addition of  New Pharmacist ? ')">
	<table class="table table-hover">
		<tr>
			<th colspan="2"><h4>Add New Pharmacist</h4></th>
		</tr>
		<tr>
			<th><label>Full Name</label><form:errors path="fullName" cssClass="error"></form:errors></th>
			<td><form:input path="fullName" type="text" class="form-control"
				placeholder="Full Name" required="required" /></td>
		</tr>
		<tr>
			<th><label>Email </label><form:errors path="email" cssClass="error"></form:errors></th>
			<td><form:input path="email" type="email" class="form-control"
				placeholder=" Email " required="required" /></td>
		</tr>
		<tr>
			<th><label>Gender :</label><form:errors path="gender" cssClass="error"></form:errors></th>
			<td>Male : <form:radiobutton path="gender"  value="M" />&nbsp;
				Female : <form:radiobutton path="gender" value="F" />
			</td>
		</tr>
		<tr>
			<th><label>Address </label><form:errors path="contactAddress" cssClass="error"></form:errors></th>
			<td><form:input path="contactAddress" type="text" class="form-control"
				placeholder="Address" required="required" /></td>
		</tr>
		<tr>
			<th><label>Contact No </label><form:errors path="contactNumber" cssClass="error"></form:errors></th>
			<td><form:input path="contactNumber" type="number" class="form-control"
				placeholder="Contact Number" required="required" /></td>
		</tr>
		<tr>
			<th><label>UserName </label><form:errors path="userName" cssClass="error"></form:errors></th>
			<td><form:input path="userName" type="text" class="form-control"
				placeholder="User Name" required="required" /></td>
		</tr>
		<tr>
			<th><label>Registered Date </label><form:errors path="registeredDate" cssClass="error"></form:errors></th>
			<td><form:input path="registeredDate" type="text" class="form-control"
				placeholder="Registered Date" required="required" /></td>
		</tr>
		<tr>
			<th><label>Password </label><form:errors path="userPass" cssClass="error"></form:errors></th>
			<td><form:input path="userPass" type="password" class="form-control"
				placeholder="*****" required="required" id="pass2" /></td>
		</tr>
		<tr>
			<th><label>Password Confirmation </label></th>
			<td><input type="password" class="form-control"
				placeholder="*****" required="required" id="pass1" oninput="checkPassword();" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">Add
					Pharmacist</button>&emsp;
					<small id="viewError"></small>
			</td>
		</tr>
	</table>
</form:form>
<script>
	function checkPassword(){
		var pass1 = document.getElementById('pass1').value;
		var pass2 = document.getElementById('pass2').value;
		if(pass1!=pass2){
			document.getElementById('viewError').innerHTML = "Retyped Password Not Matched.!!!!";
			document.getElementById('viewError').style.color="red";
		}
		else{
			document.getElementById('viewError').innerHTML = "Retyped Password Matched.!!!!";
			document.getElementById('viewError').style.color="green";
		}
	}
</script>