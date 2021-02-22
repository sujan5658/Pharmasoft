<script type="text/javascript">
	function addRows() {
		var table = document.getElementById('mediTable');
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount - 2);
		for (var i = 0; i <= 4; i++) {
			var cell = 'cell' + i;
			cell = row.insertCell(i);
			var copycel = document.getElementById('col' + i).innerHTML;
			if (i == 0) {
				cell.innerHTML = table.rows.length - 13;
			} else {
				var key = table.rows.length - 13;
				switch (i) {
				case 1:
					cell.innerHTML = copycel;
					break;
				case 2:
					cell.innerHTML = '<input type="text" name="quantity" class="form-control" id="q' + key
							+ '" oninput="check();">';
					break;
				case 3:
					cell.innerHTML = '<input type="text" name="rate" class="form-control" id="r' + key
							+ '" oninput="check();">';
					break;
				case 4:
					cell.innerHTML = '<input type="text" name="total" class="form-control" id="t' + key
							+ '" oninput="checkRate();">';
					break;
				}
			}
		}
	}
	function deleteRows() {
		var table = document.getElementById('mediTable');
		var rowCount = table.rows.length;
		if (rowCount > '14') {
			var row = table.deleteRow(rowCount - 3);
			rowCount--;
		} else {
			alert('There should be atleast one row');
		}

		var discount = document.getElementById('disc').value;
		var grandTotal = document.getElementById('gt').value;
		var table = document.getElementById('mediTable');
		var rowCount = table.rows.length;
		rowCount = rowCount - 13;
		grandTotal = 0;
		for (var i = 1; i <= rowCount; i++) {
			var tmpTotal = document.getElementById('t' + i).value;
			grandTotal = parseFloat(grandTotal) + parseFloat(tmpTotal);
		}
		document.getElementById('gt').value = grandTotal;
	}
	function check() {
		var table = document.getElementById("mediTable"), rIndex, cIndex;

		// table rows
		for (var i = 1; i < table.rows.length; i++) {
			// row cells
			for (var j = 0; j < table.rows[i].cells.length; j++) {
				table.rows[i].cells[j].oninput = function() {
					rIndex = this.parentElement.rowIndex + 1;
					calculation(rIndex - 11);
				}
			}
		}
	}
	function getDiscount() {
		var discount = document.getElementById('disc').value;
		var grandTotal = document.getElementById('gt').value;
		if (discount == 0 || discount == null) {
			var discount = document.getElementById('disc').value;
			var grandTotal = document.getElementById('gt').value;
			var table = document.getElementById('mediTable');
			var rowCount = table.rows.length;
			rowCount = rowCount - 13;
			grandTotal = 0;
			for (var i = 1; i <= rowCount; i++) {
				var tmpTotal = document.getElementById('t' + i).value;
				grandTotal = parseFloat(grandTotal) + parseFloat(tmpTotal);
			}
			document.getElementById('gt').value = grandTotal;
		} else {
			document.getElementById('gt').value = parseFloat(grandTotal)
					- parseFloat(discount);
		}
	}
	function discount() {
		var table = document.getElementById('mediTable');
		var rowCount = table.rows.length;
		rowCount = rowCount - 13;
		var grandTotal = 0;
		var tmpTotal = 0;
		for (var i = 1; i <= rowCount; i++) {
			tmpTotal = document.getElementById('t' + i).value;
			grandTotal = parseFloat(grandTotal) + parseFloat(tmpTotal);
		}
		document.getElementById('gt').value = grandTotal;
	}
	function calculation(unique) {

		var quantity = document.getElementById('q' + unique).value;
		var rate = document.getElementById('r' + unique).value;
		var total = document.getElementById('t' + unique).value;

		total = quantity * rate;
		console.log(quantity);
		console.log(rate);
		console.log(total);
		document.getElementById('t' + unique).value = total;
		discount();
	}
	function checkRate() {
		var table = document.getElementById("mediTable"), rIndex, cIndex;

		// table rows
		for (var i = 1; i < table.rows.length; i++) {
			// row cells
			for (var j = 0; j < table.rows[i].cells.length; j++) {
				table.rows[i].cells[j].oninput = function() {
					rIndex = this.parentElement.rowIndex + 1;
					calculateRate(rIndex - 11);
				}
			}
		}
	}
	function calculateRate(unique) {
		var quantity = document.getElementById('q' + unique).value;
		var rate = document.getElementById('r' + unique).value;
		var total = document.getElementById('t' + unique).value;
		rate = total / quantity;
		rate = rate.toPrecision(4);
		document.getElementById('r' + unique).value = rate;
		discount();
	}
	function calculationq1() {
		var quantity = document.getElementById('q1').value;
		var rate = document.getElementById('r1').value;
		var total = document.getElementById('t1').value;
		total = quantity * rate;
		document.getElementById('t1').value = total;
		discount();
	}
	function calculateRateq1() {
		var quantity = document.getElementById('q1').value;
		var rate = document.getElementById('r1').value;
		var total = document.getElementById('t1').value;
		rate = total / quantity;
		rate = rate.toPrecision(4);
		document.getElementById('r1').value = rate;
		discount();
	}
	function setMedicines(){
		var medicineArray = document.getElementsByName('medicine');
		var quantityArray = document.getElementsByName('quantity');
		var rateArray = document.getElementsByName('rate');
		var totalArray = document.getElementsByName('total');
		var medicines = [];
		var rates = [];
		var quantities = [];
		var totals = []; 
		for(var i=0,n=medicineArray.length;i<n;i++){
			medicines.push(medicineArray[i].value);
			quantities.push(quantityArray[i].value);
			rates.push(rateArray[i].value);
			totals.push(totalArray[i].value);
		}
		document.getElementById('medicines').value=medicines.join(",");
		document.getElementById('quantities').value=quantities.join(",");
		document.getElementById('rates').value=rates.join(",");
		document.getElementById('totals').value=totals.join(",");
	} 
</script>
<c:choose>
	<c:when test="${message.status eq true}">
		<script type="text/javascript">
			swal("Error Occured", "${message.message}", "error");
		</script>
	</c:when>
</c:choose>
<form:form method="post" action="pbilling" modelAttribute="bill" onsubmit="return confirm('Confirm Billing ?')">
	<table class="table table-hover table-striped table-bordered"
		id="mediTable">
		<thead>
			<tr>
				<th colspan="4">Billing Section<br> Pharmacy Name :
					${pharmaInfo.pharmacyName}
				</th>
				<th>Bill No : ${bill.billNo}</th>
				<form:errors path="billNo" cssClass="error"></form:errors>
				<form:input path="billNo" type="hidden" value="${bill.billNo}" />
			</tr>
		</thead>
		<tbody>
			<tr>
				<th colspan="2">Registered Date :</th>
				<th>${pharmaInfo.registeredDate}</th>
				<th>Pan Number :</th>
				<th>${pharmaInfo.panNo}</th>
			</tr>
			<tr>
				<th colspan="2">Telephone :</th>
				<th>${pharmaInfo.telephone}</th>
				<th>Billing Date :</th>
				<th>${bill.billedDate}</th>
				<form:errors path="billedDate" cssClass="error"></form:errors>
				<form:input path="billedDate" type="hidden" value="${bill.billedDate}" />
			</tr>
			<tr>
				<td colspan="3">Doctor's Name :<form:errors path="doctorName" cssClass="error"></form:errors></td>
				<td colspan="2"><form:input path="doctorName" type="text" placeholder="Doctor's Name"
					class="form-control" required="required" /></td>
			</tr>
			<tr>
				<td colspan="3">Patient's Name : <form:errors path="patientName" cssClass="error"></form:errors></td>
				<td colspan="2"><form:input path="patientName" type="text" placeholder="Patient's Name"
					class="form-control" required="required" /></td>
			</tr>
			<tr>
				<td colspan="3">Patient's Address : <form:errors path="patientAddress" cssClass="error"></form:errors></td>
				<td colspan="2"><form:input path="patientAddress" type="text" placeholder="Patient's Address"
					class="form-control" required="required" /></td>
			</tr>
			<tr>
				<td colspan="3">Gender : <form:errors path="gender" cssClass="error"></form:errors></td>
				<td>Male : <form:radiobutton path="gender"  value="M" /></td>
				<td>Female : <form:radiobutton path="gender" value="F" /></td>
			</tr>
			<tr>
				<td colspan="3">Contact No : <form:errors path="contactNo" cssClass="error"></form:errors></td>
				<td colspan="2"><form:input path="contactNo" type="number" placeholder="Number"
					class="form-control" required="required" /></td>
			</tr>
			<tr>
		<thead>
			<th colspan="3">List Medicine To Sell</th>
			<th>
				<button class="btn btn-success" type="button" onclick="addRows();">
					<img src="${pageContext.request.contextPath}/resources/css/icons/plus.svg" class="icon">&emsp;<b>Add
						Row</b>
				</button>
			</th>
			<th>
				<button class="btn btn-success" type="button"
					onclick="deleteRows();">
					<img src="${pageContext.request.contextPath}/resources/css/icons/minus.svg" class="icon">&emsp;<b>Del
						Row</b>
				</button>
			</th>
		</thead>
		</tr>
		<tr>
			<th>SN</th>
			<th>Medicine Name</th>
			<th>Quantity</th>
			<th>Rate</th>
			<th>Total</th>
		</tr>
		<tr>
			<td id="col0">1</td>
			<td id="col1"><select class="form-control" name="medicine">
				<c:forEach items="${medicines}" var="medicine">
					<option>${medicine.brandName}</option>
				</c:forEach>
			</select></td>
			<td id="col2"><input type="text" name="quantity" id="q1"
				class="form-control" oninput="calculationq1();" required="required" /></td>
			<td id="col3"><input type="text" name="rate" id="r1"
				class="form-control" oninput="calculationq1();" required="required" /></td>
			<td id="col4"><input type="text" name="total" id="t1"
				class="form-control" oninput="calculateRateq1();" required="required" /></td>
		</tr>
		<tr>
			<td>Discount: <form:errors path="discount" cssClass="error"></form:errors></td>
			<td><form:input path="discount" type="text" name="discount" id="disc"
				class="form-control" /></td>
			<td><button type="button" class="btn btn-success"
					onclick="getDiscount();">Give</button></td>
			<td style="text-align: center;">Grand Total : <form:errors path="grandTotal" cssClass="error"></form:errors></td>
			<td><form:input path="grandTotal" type="text" name="grandTotal" id="gt"
				class="form-control" required="required" /></td>
		</tr>
		<tr>
			<td colspan="3" style="text-align: center;">Sold By : <form:errors path="seller" cssClass="error"></form:errors></td>
			<td colspan="2"><form:select path="seller" class="form-control">
				<c:forEach items="${pharmacists}" var="pharmacist">
					<option>${pharmacist.fullName}</option>
				</c:forEach>
			</form:select></td>
		</tr>
		</tbody>
	</table>
	<form:errors path="medicines" cssClass="error"></form:errors>
	<form:input path="medicines" type="hidden" id="medicines"/>
	<form:errors path="quantities" cssClass="error"></form:errors>
	<form:input path="quantities" type="hidden" id="quantities"/>
	<form:errors path="rates" cssClass="error"></form:errors>
	<form:input path="rates" type="hidden" id="rates"/>
	<form:errors path="totals" cssClass="error"></form:errors>
	<form:input path="totals" type="hidden" id="totals"/>
	<button type="submit" onclick="setMedicines();" class="btn btn-success">Submit</button>
</form:form>