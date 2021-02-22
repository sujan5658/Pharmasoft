<table class="table table-hover table-striped">
	<thead>
		<tr>
			<th>SN</th>
			<th>Bill</th>
			<th>Manage</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="i" value="${1}"/>
		<c:forEach items="${bills}" var="bill">
			<tr>
				<td><c:out value="${i}"/></td>
				<c:set var="i" value="${i+1}"/>
				<td>${bill.fileName}</td>
				<td>
					<form method="post" action="singleBillView">
					<input type="hidden" name="billName" value="${bill.fileName}"/>
					<button type="submit" class="btn btn-success">View Bill</button>&emsp;
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>