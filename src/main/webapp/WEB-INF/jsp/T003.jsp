<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit - Training</title>
</head>
<body>
	<div id="main">
		<form action="/CustomerJspServlet/T003" method="POST">
			<h1 class="error">Thông báo</h1>
			<div class="comntent-customer_id">
				<span>Customer Id</span> <input type="text" name="customerId"
					value="" />
			</div>
			<div class="comntent-customer_name">
				<span>Customer Name</span> <input type="text" name="customerName"
					value="" />
			</div>
			<div class="comntent-customer_sex">
				<span>Sex</span> <select name="sex">
					<option value=""></option>
					<option value="1">Nam</option>
					<option value="0">Nữ</option>
				</select>
			</div>
			<div class="comntent-customer_birthday">
				<span>Customer birthday</span> <input type="text" name="birthday"
					value="" />
			</div>
			<div class="comntent-customer_email">
				<span>Customer Email</span> <input type="text" name="email" value="" />
			</div>
			<div class="comntent-customer_address">
				<span>Customer Address</span> <input type="text" name="address"
					value="" />
			</div>
			<button type="submit">Save</button>
		</form>
	</div>

</body>
</html>