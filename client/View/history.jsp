<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String[] buying_history=(String[])request.getAttribute("bought"); %>

<%String[] seller_history=(String[])request.getAttribute("sold"); %>

<table>
<tr><th>You Sold: </th></tr>
	<%for(int j=0;j<seller_history.length;j++){ %>
	<%if(seller_history[j]!=null){ %>
	<tr>
	<td>
	<%=seller_history[j] %>
	</td>	</tr>
	
	<%}%> 
	<%}%>
</table>

<table>
<tr><th>You Bought: </th></tr>



	<%for(int i=0;i<buying_history.length;i++){ %>
<%if(buying_history[i]!=null){ %>
<tr>	<td>
	<%=buying_history[i] %>
	<%}%> </td></tr>
	<%}%>
	
</table>
</body>
</html>