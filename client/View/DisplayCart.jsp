<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>
Displaying your cart
<table>

<%ArrayList<String[]> details=(ArrayList<String[]>)request.getAttribute("cart"); %>
<%for(String detail[] :details){ %>
<tr>
	<%for(int i=0;i<detail.length;i++){ %>
		<td><%= detail[i] %></td>
	<%} %>
</tr>

<%} %>

<form action="/ExampleClient/View/checkout.jsp" method="post">
<input type="submit" value="Proceed to checkout">
</form>
</table>
</body>
</html>