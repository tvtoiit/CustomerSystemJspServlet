<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login - Training</title>
	<style type="text/css">
	  <%@include file="/WEB-INF/css/T001.css"%>
	</style>
	
</head>
<%@include file="/WEB-INF/include/header.jsp"%>

<body>
	<div class="content">
			<div class="content-text">Login</div>
			<div class="content-login">
				<form action="/CustomerJspServlet/T001" name="frmLogin" method="POST" >
					<div class="content-login__header">
						<h3>LOGIN</h3>
						<div id="lblErrorMessage">
							<%String error = (String)request.getAttribute("error"); %>
								<% if (error != null) { %>
									<label id="messageError"><%=error %></label>
								<%} %>
						</div>
					</div>
					<div class="content-login__container">

						<div class="form-group">
							<label for="fullname" class="form-label form-label__userID">User
								Id: </label> <input id="txtUserID" name="username" maxlength="8"
								type="text" class="form-control">
						</div>
						<div class="form-group__password">
							<label for="password" class="form-label">Password: </label> <input
								id="txtPassword" name="password" type="password" maxlength="8"
								class="form-control">
						</div>
						<div class="form-group__btn">
							<button type="submit" id="btnLogin" class="form-submit">Login</button>
							<button type="button" id="btnClear" onclick="clearForm()" class="form-submit">Clear</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	
<script >
	clearForm = () => {
		document.getElementById("txtUserID").value = "";
		document.getElementById("txtPassword").value = "";
		document.getElementById("messageError").innerHTML = "";
	}
</script>	
</body>
<%@ include file="/WEB-INF/include/footer.jsp"%>

</html>