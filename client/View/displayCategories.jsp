<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<p align="right"><input type="button" onclick="signout()"value="signout"> 
<br>
<form action="/ExampleClient/View/SellProducts.jsp" method="post">
<input type="submit" value="Sell A Product">
<input type="hidden" value="<%=request.getAttribute("categories")%>" name="categories">

</form>
</p>
Welcome <%= session.getAttribute("username") %>
<br>
Your last log in date time:
<%if(request.getAttribute("lastLogged")!=null){ %>
<%=request.getAttribute("lastLogged") %>
<%} %>
<p align="right">
<form method="post" action="ViewMyHistory">
<input type="submit" value="View My History">
</form></p>
<table>
	<% String categories[]=(String[])request.getAttribute("categories");%>
	<%for(int i=1;i<categories.length;i++){%>
	<tr>
	<td>
	<%String url="/ExampleClient/Products?"+categories[i]; %>
	<a href=<%=url %>><%= categories[i]%></a>
	</td>
	</tr>
	<%} %>
</table>



<form action="/ExampleClient/DisplayCart" method="post" >
<input type="submit" value="show my cart">
</form>

<br>
<br>
<script type="text/javascript">
	function signout(){
		window.location.replace("http://localhost:8080/ExampleClient/View/signin.jsp");
	}
</script>
</body>
</html>