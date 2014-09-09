<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
</head>
<body>
<%if(request.getAttribute("msg")!=null){ %>
<%String msg=request.getAttribute("msg").toString(); %>

${msg}
<%} %>

	<form method="post" action="/ExampleClient/signIn">
	UserName: <input type="text" name="user"> <br/>
	Password: <input type="password" name="pass"> <br/>
	<input type="submit" value="Login">
	</form>
</body>
</html>