<c:choose>
	<c:when test="${message.status eq true}">
		<script type="text/javascript">
			swal("Error Occured", "${message.message}", "error");
		</script>
	</c:when>
	<c:when
		test="${message.status eq false and message.message ne 'defaultMessage'}">
		<script type="text/javascript">
			swal("Deletion Succeed", "${message.message}", "success");
		</script>
	</c:when>
</c:choose>
<h4>Pharmacist List</h4>
<table class="table table-hover">
	<thead style="background-color: #441E71; color: white;">
		<tr>
			<th>SN</th>
			<th>Full Name</th>
			<th>Email</th>
			<th>Gender</th>
			<th>Address</th>
			<th>Contact No</th>
			<th>Registered Date</th>
			<th>User Name</th>
			<th>Manage</th>
		</tr>
	</thead>
	<tbody>
	<c:set var="id" value="1" />
	<c:forEach var="pharmacists" items="${pharmacists}">
		<tr>
			<td><c:out value="${id}" /></td>
			<c:set var="id" value="${id+1}" />
			<td>${pharmacists.fullName}</td>
			<td>${pharmacists.email}</td>
			<td>${pharmacists.gender}</td>
			<td>${pharmacists.contactAddress}</td>
			<td>${pharmacists.contactNumber}</td>
			<td>${pharmacists.registeredDate}</td>
			<td>${pharmacists.userName}</td>
			<td>
				<div class="row">
					<div class="col-sm-6">
					<form method="post" action="updatePharmacist">
					<input type="hidden" name="id" value="${pharmacists.id}"/>
						<button type="submit" class="btn btn-success">
							<small>Update</small>
						</button>
					</form>
					</div>
					<div class="col-sm-6">
					<form method="post" action="deletePharmacist">
						<input type="hidden" name="id" value="${pharmacists.id}" />
						<button type="submit" class="btn btn-danger">
							<small>Delete</small>
						</button>
					</form>
					</div>
				</div>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>