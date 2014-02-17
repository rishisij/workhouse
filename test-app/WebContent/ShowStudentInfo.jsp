<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="roseindia.Extends" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Example of Extends 
Attribute of page Directive in JSP</title></head>

<body>
<font size="20" color="red">
<%
Extends ex = new Extends();
out.print(ex.show());
%>
</font>
</body>
</html>