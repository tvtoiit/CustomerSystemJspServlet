<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>search - training</title>
<style type="text/css">
  <%@include file="/WEB-INF/css/common.css"%>
</style>
<style type="text/css">
  <%@include file="/WEB-INF/css/T002.css"%>
</style>
</head>

<style type="text/jsp">
  <%@include file="/WEB-INF/include/header.jsp"%>
</style>
<body>
	<div class = "search-container">
		<div class = "search-container__dan">
				<div class = "search-container__text">
					Login > Search Customer
				</div>
			<div class = "search-container__context">
				<div class = "search-container__logo">
					<div>Welcome </div>
				</div>
				<a href = "#" class = "search-container__logout">
					Log Out
				</a>
			</div>
			<div class = "search-container__line"></div>
<form id = "form-Search" action ="/CustomerJspServlet/T002" method = "POST">
	<div class = "search-container__handalSearch">
		<div class = "search-container__handalSearch--margin handalSearch-customerName">
			<div class = "handalSearch-customercommon handalSearch-customerName__text">Customer Name</div>
			<c:if test ="${not empty name}">			
				<input id ="txtCustomerName" value="${name}" class="input_Customer--common" name ="txtCustomerName" maxLength ="50"/>
			</c:if>
			<c:if test="${empty name }">
				<input id = "txtCustomerName" class="input_Customer--common" name = "txtCustomerName" maxLength = "50"/>
			</c:if>
		</div>
		
		<div class = "search-container__handalSearch--margin handalSearch-customerSex">
			<div class = "handalSearch-customercommon handalSearch-customerSex__text">Sex</div>
			<select name="browser" class ="input_Customer--select" id ="cboSex">
				<option value="">blank</option>
		      	<option value="0" ${sex == '0' ? 'selected' : ''}>Male</option>
    			<option value="1" ${sex == '1' ? 'selected' : ''}>Female</option>
			</select>
		</div>
		
		<div class = "search-container__handalSearch--margin handalSearch-BirthdayFrom">
			<div class = "handalSearch-customercommon handalSearch-BirthdayFrom__text">Birthday</div>
			<c:if test="${empty birthdayFrom}">
				<input id ="txtBirthdayForm" class ="input_Customer--common txtCustomerValidateFROM" name ="txtBirthdayFromName" maxLength ="10"/>
			</c:if>
			<c:if test="${not empty birthdayFrom}">
				<input id ="txtBirthdayForm" value="${birthdayFrom}" class ="input_Customer--common txtCustomerValidateFROM" name ="txtBirthdayFromName" maxLength ="10"/>
			</c:if>
			<label for="html" class = "handalSearch-customercommon handalSearch-BirthdayFrom__ngangcach">～</label>
			<c:if test="${empty birthdayTo}">
				<input id ="txtBirthdayTo" class ="input_Customer--common txtCustomerValidateTO" name ="txtBirthdayToName" maxLength ="10"/>			
			</c:if>
			<c:if test="${not empty birthdayTo}">
				<input id ="txtBirthdayTo" value="${birthdayTo}" class ="input_Customer--common txtCustomerValidateTO" name ="txtBirthdayToName" maxLength ="10"/>	
			</c:if>
		</div>
		<div class = "handalSearch-btnSearch">
			<button type ="submit" id = "btnSearch">Search</button>
		</div>
	</div>

	
	<div class="search-container__btnContext--chuyenhuong">
    <div class="search-container__btnContext--start">
        <button type="submit" name="page" value="1">&lt;&lt;</button>
        <c:if test="${tag > 1}">
     		<button type="submit" name="page" value="2">&lt;</button>
        </c:if>
        <c:if test="${tag <= 1}">
        	<button disabled type="submit" name="page" value="2">&lt;</button>
        </c:if>
        <label for="html" class="search-container__btnContext--textstart">Previous</label>
    </div>
    
    <div class="search-container__btnContext--end">
        <label for="html" class="search-container__btnContext--textend">Next</label>
       	<c:if test="${tag == endl }">
       		<button disabled type="submit" name="page" value="3">&gt;</button>        	
       	</c:if>
       	<c:if test="${tag < endl }">
       		<button type="submit" name="page" value="3">&gt;</button>  
       	</c:if>
        <button type="submit" name="page" value="4">&gt;&gt;</button>
    </div>
     	<input type="hidden" name="tag" value="${tag}"/>
</div>

	
		<table class = "search-container__table">
	        <tr class = "search-container__table--tieude">
	        	<th><input type="checkbox" id = "checkAll" name="checkboxAll" value="" onclick = "checkBox(this)"></th>
	            <th>Customer ID</th>
	            <th>Customer Name</th>
	            <th>Sex</th>
	            <th>Birthday</th>
	            <th>Address</th>
	        </tr>
	        
	        <c:forEach items="${model.pageData}" var="dept">
	            <tr>
	                <td><input type="checkbox" name="checkboxAll" value="${dept.customerId}"></td>
	                <td><a href="/CustomerJspServlet/T003?id=${dept.customerId}"> ${dept.customerId} </a></td>
	                <td>${dept.customerName }</td>
	                <td>${dept.sex}</td>
	                <td>${dept.birthDay}</td>
	                <td>${dept.address}</td>
	            </tr>
	        </c:forEach> 
    </table>
		
		<div class = "search-container__btnnav">
			<a href="/CustomerJspServlet/T003" class="search-container__nav-btnAdd">Add New</a>
			<button type = "submit" class = "search-container__nav-btnAdd" onclick="deleteRows()" >Delete</button>
		</div>
	</form>
	</div>
</div>	
<script>

	
	
</script>
</body>
</html>