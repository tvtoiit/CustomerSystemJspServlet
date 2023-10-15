<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit - Training</title>
</head>
<body>
<div id="main">
	<form action="./T003" method="POST">
		<h1 class="error">Thông báo</h1>
			<div class="comntent-customer_id">
				<span>Customer Id</span>
					<span>${empty id ? '' : id }</span>
					<input type="hidden" name="customerId" value="${empty id ? '' : id }"/>
			</div>
			<div class="comntent-customer_name">
				<span>Customer Name</span> 
				<input type="text" name="customerName" value="${empty name ? '' : name}" />
			</div>
			<div class="comntent-customer_sex">
				<span>Sex</span> <select name="sex">
					<option value=""></option>
					<option value="0" ${sex == '0' ? 'selected' : ''}>Male</option>
		    		<option value="1" ${sex == '1' ? 'selected' : ''}>Female</option>
				</select>
			</div>
			<div class="comntent-customer_birthday">
				<span>Customer birthday</span> 
				<input type="text" name="birthday" value="${empty birthday ? '' : birthday }" />
			</div>
			<div class="comntent-customer_email">
				<span>Customer Email</span> 
				<input type="text" name="email" value="${empty email ? '' : email }" />
			</div>
			<div class="comntent-customer_address">
				<span>Customer Address</span> 
				<input type="text" name="address" value="${empty address ? '' : address }" />
			</div>
		<button type="submit">Save</button>
	</form>
</div>
</body>
</html>
