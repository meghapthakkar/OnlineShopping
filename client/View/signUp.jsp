<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%if(request.getAttribute("msg")!=null){ %>
<%String msg=request.getAttribute("msg").toString(); %>

${msg}
<%} %>

<form method="post" action="/ExampleClient/SignUp">
First Name: <input type="text" name="Fname"> <br/>
Last Name:<input type="text" name="Lname"> <br/>
EmailId/UserName: <input type="text" name="EmailId"> <br/>
Password: <input type="text" name="Password"> <br/>
Re-enter Password: <input type="text" name="Password1"> <br/>
<input type="submit" value="Signup"> 
</form>
</body>
</html>