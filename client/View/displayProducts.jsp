<%@page import="javax.xml.ws.Response"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="Connection.ServiceProxy" %>
<title>ProductList</title>
</head>
<body>
<table>
<tr><th>ProductName</th><th>Description</th><th>Price</th><th>SellerName</th><th>Seller Info</th></tr>

<%= session.getAttribute("username") %>
<form method="post" action="/ExampleClient/AddToCart">
<%ArrayList<String []> productList=(ArrayList<String[]>)request.getAttribute("productList"); %>
<% for(String s[]:productList){ %>
<%System.out.println(s.length+"inside diplayprojsp"); %>
<% if(s!=null){%>
<% ServiceProxy proxy=new ServiceProxy(); %>
<%int qty=proxy.getQuantity(s[0]); %>
<%if(qty>0){ %>

<tr>
<td><input type="submit" value="<%=s[0] %>" name="button"></td>

<% for(int i=1;i<s.length-1;i++){%>

<td><%=s[i] %></td>
<%} %>

<td><select name=<%= s[0]%> >
	<%for(int j=1;j<=proxy.getQuantity(s[0]);j++) {%>
	<option value=<%=j %>><%= j%></option>
	<%} %>
	<%} %>
</select></td>
<% }%>

<%} %>
</tr>

</form>
</table>

</body>
</html>