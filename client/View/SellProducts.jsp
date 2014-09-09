<%@page import="Connection.ServiceProxy"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller's Portal</title>
</head>
<body>
Welcome to seller portal
<form action="/ExampleClient/sellProducts" method="post">
<%ServiceProxy proxy=new ServiceProxy(); %>
<% String categories=proxy.getCategories();%>
<%String categoryList[]=categories.split("\\$"); %>
<%if(categoryList!=null){ %>
<%System.out.println(categoryList.length+"within jsp"); %>
Select One Category:<select name="categoryName">
<%for(int i=0;i<categoryList.length;i++){ %>
<option value="<%=categoryList[i] %>"><%=categoryList[i] %></option>
<%} %>
</select><%} %>
<br/>
ProductName: <input type="text" name="ProductName"><br/>
Description: <input type="text" name="Description"><br/>
Price: <input type="text" name="Price"><br/>
Quantity: <input type="text" name="Quantity" value="1"><br/>
Information: <input type="text" name="info"><br/> 
<input type="submit" value="Add Product"><br/>
</form>
</body>
</html>