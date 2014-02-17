<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Web App for Crawling</title>
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
</head>
<body>
<table class="title">
  <tr><th>Web App for Crawling</th></tr>
</table>

<form action="hello.jsp" method = "POST">
<fieldset>
<legend>Testing</legend>
<table>
<tr>

<td><b>username:</b><input type = "text" name = "user" size="20" tabindex = "1" /></td><td></td></tr>
<tr><td><b>password:</b><input type = "password" name = "password" size="20" tabindex ="2" /></td><td></td></tr>
<tr><td style="text-align:right"><%
  //  if (request.getParameter("error") != null) {
    
    
   //    out.println("<font size=2>Invalid Username or Password<font>!");
    //}
%><input type = "submit" value = "submit" tabindex = "3" /></td>
<td style="text-align:right">

</td>
</tr>


</table>
</fieldset>
<p/>
</form>



 
<br/><br/><br/><br/><br/><br/>

</body></html>