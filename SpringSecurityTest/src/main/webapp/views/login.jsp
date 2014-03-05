<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="common/header.jsp">
	<jsp:param value="pageTitle" name="Login"/>
</jsp:include>
<body>
	<form action="j_spring_security_check" method="post">
		<label for="j_username">User Name:</label>
		<input id="j_username" name="j_username" size="20" maxlength="50" type="text">
		<br/>
		<label for="j_userpassword">Password:</label>
		<input id="j_password" name="j_password" size="20" maxlength="10" type="password">
		<br/>
		<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true">
		<label for="_spring_security_remember_me" >RememberMe?:</label>
		<br/>
		<input type="submit" name="Login">
	</form>
<jsp:include page="common/footer.jsp"/>