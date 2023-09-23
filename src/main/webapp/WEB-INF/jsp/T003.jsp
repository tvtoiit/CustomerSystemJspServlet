<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit - Training</title>
</head>
<body>
<%@page import="fjs.cs.dto.mstcustomer"%>  
<%
	List<mstcustomer> dto = (List<mstcustomer>) request.getAttribute("dto");
%>
<div id="main">
	<form action="/CustomerJspServlet/T003" method="POST">
		<h1 class="error">Thông báo</h1>
		<c:choose>
			<c:when test="${not empty dto}">
				<c:forEach items="${dto}" var="customer">
					<div class="comntent-customer_id">
						<span>Customer Id</span> <input type="text" name="customerId"
							value="${customer.customerId}" />
					</div>
					<div class="comntent-customer_name">
						<span>Customer Name</span> <input type="text" name="customerName"
							value="${customer.customerName}" />
					</div>
					<div class="comntent-customer_sex">
						<span>Sex</span> <select name="sex">
							<option value=""></option>
							<option value="1" ${customer.sex == '1' ? 'selected' : ''}>Nam</option>
							<option value="0" ${customer.sex == '0' ? 'selected' : ''}>Nữ</option>
						</select>
					</div>
					<div class="comntent-customer_birthday">
						<span>Customer birthday</span> <input type="text" name="birthday"
							value="${customer.birthDay}" />
					</div>
					<div class="comntent-customer_email">
						<span>Customer Email</span> <input type="text" name="email" value="${customer.email}" />
					</div>
					<div class="comntent-customer_address">
						<span>Customer Address</span> <input type="text" name="address"
							value="${customer.address}" />
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<!-- Hiển thị trường input mặc dù không có dữ liệu trong danh sách dto -->
				<div class="comntent-customer_id">
					<span>Customer Id</span> <input type="text" name="customerId" value="" />
				</div>
				<div class="comntent-customer_name">
					<span>Customer Name</span> <input type="text" name="customerName" value="" />
				</div>
				<div class="comntent-customer_sex">
					<span>Sex</span> <select name="sex">
						<option value=""></option>
						<option value="1">Nam</option>
						<option value="0">Nữ</option>
					</select>
				</div>
				<div class="comntent-customer_birthday">
					<span>Customer birthday</span> <input type="text" name="birthday" value="" />
				</div>
				<div class="comntent-customer_email">
					<span>Customer Email</span> <input type="text" name="email" value="" />
				</div>
				<div class="comntent-customer_address">
					<span>Customer Address</span> <input type="text" name="address" value="" />
				</div>
			</c:otherwise>
		</c:choose>
		<button type="submit">Save</button>
	</form>
</div>

</body>
</html>
