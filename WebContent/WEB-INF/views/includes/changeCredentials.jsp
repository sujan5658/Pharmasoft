<c:choose>
	<c:when test="${message.status eq true}">
		<script type="text/javascript">
			swal("Error Occured", "${message.message}", "error");
		</script>
	</c:when>
	<c:when
		test="${message.status eq false and message.message ne 'defaultMessage'}">
		<script type="text/javascript">
			swal("Changed", "${message.message}", "success");
		</script>
	</c:when>
</c:choose>
<h4>Change Credentials</h4>
<form:form action="credentialsChange" onsubmit="return confirm('Confirm Changing Credentials ?')">
	<div class="form-group">
		<label>Old User</label> <input type="text" class="form-control"
			name="oldUserName" style="width: 250px" placeholder="Old User Name" required="required" />
	</div>
	<div class="form-group">
		<label>New User</label> <input type="text" class="form-control"
			name="newUserName" style="width: 250px" placeholder="New User Name" required="required" />
	</div>
	<div class="form-group">
		<label>New Email</label> <input type="email" class="form-control"
			name="newEmail" style="width: 250px" placeholder="New Email" required="required" />
	</div>
	<div class="form-group">
		<label>Old User Password</label> <input type="text"
			class="form-control" name="oldPass" style="width: 250px"
			placeholder="Old User Password" required="required" />
	</div>
	<div class="form-group">
		<label>New Password</label> <input type="password"
			class="form-control" name="newPass" style="width: 250px"
			placeholder="New User Password" required="required" id="pass1" />
	</div>
	<div class="form-group">
		<label>Confirm Password</label> <input type="password"
			class="form-control" style="width: 250px"
			placeholder="Retype Password" oninput="checkPassword();" id="pass2" required="required" />
	</div>
	<button type="submit" class="btn btn-primary">Change</button>
	&emsp;<small id="viewError"></small>
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