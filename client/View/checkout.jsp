<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>
</head>
<body>
<%if(request.getAttribute("msg")!=null){ %>
<%String msg=request.getAttribute("msg").toString(); %>

${msg}
<%} %>
	<form action="/ExampleClient/Checkout" method="post">
	Credit Card Number: <input type="text" name="creditCard">
	<input type="submit" value="Pay">
	</form>

</body>
</html>