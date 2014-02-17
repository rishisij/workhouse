<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,java.net.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletRequestWrapper" %>
 <!--  <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>-->
<!--<%@ page import ="javax.servlet.RequestDispatcher" %>-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Extracted :)</title>
</head>
<body>
<h3>The Server time is: <%= new java.util.Date( )%></h3>

<%!String f,g; %>

<% HttpServletRequest request1 = (HttpServletRequest) request ; %>
 <% f= request1.getParameter("user");%>
<% g = request1.getParameter("password");%>
<% HttpSession session1 = request1.getSession(true); %>
<% session1.setAttribute("UserName", f); %>

<jsp:declaration>


Statement stmt1;
Connection con1;
String url1 = "jdbc:mysql://localhost:3308/sap";

</jsp:declaration>
<jsp:scriptlet><![CDATA[
Class.forName("com.mysql.jdbc.Driver").newInstance();
con1 = DriverManager.getConnection(url1, "root", "root"); 
stmt1 = con1.createStatement();
ResultSet rs1 =  stmt1.executeQuery("select * from user where user =\'" + f + "\' AND password =\'" + g + "\';");
if (rs1.next())
{
	
int per = rs1.getInt("permission");
]]></jsp:scriptlet>

<span style="float: right">
<a href="logout.jsp">logout</a>
</span>
<table border="1" width="100%">
<tr>
<th>ID</th>
<th>Category</th>
<th>Tabcol1</th>
<th>Image</th>
<!-- <th>Tabcol2</th> -->
</tr>
<jsp:declaration>

Statement stmt;
Connection con;
String url = "jdbc:mysql://localhost:3308/sap";

</jsp:declaration>
<jsp:scriptlet><![CDATA[

Class.forName("com.mysql.jdbc.Driver").newInstance();
con = DriverManager.getConnection(url, "root", "root"); 

stmt = con.createStatement();
//ResultSet rs =  stmt.executeQuery("select * from html where tabcol1 != \"\" AND tabcol2 != \"\";");
ResultSet rs =  stmt.executeQuery("select * from stockp ;");
while(rs.next()) {  
	if(per==1){
	int id          = rs.getInt("pid");
	String Category = rs.getString("imageurl");
	String tabcol1  = rs.getString("image");
	String dam = "../stockp/"+tabcol1+".jpg";
	String s=" width=\"130\" height=\"101\"";
	//String tabcol2  = rs.getString("tabcol2");            
    out.print("<tr>");
    out.print("<td>");
    out.print(id);
    out.print("</td>");
    out.print("<td>");
    out.print(Category);
    out.print("</td>");
    out.print("<td>");
    out.print(tabcol1);
    out.print("</td>");
    out.print("<td>");
    out.print("<img src="+dam+s+">");
    out.print("</td>");
    //out.print("<td>");
    //out.print(tabcol2);
    //out.print("</td>");
    out.print("</tr>");
	}
	else
	{
		//String Category = rs.getString("category");
		//if(Category.matches(".*\\bBenzene\\b.*"))
		{
			
			int id          = rs.getInt("pid");
			String Category = rs.getString("imageurl");
			String tabcol1  = rs.getString("image");
			String dam = "../stockp/"+tabcol1+".jpg";
			
		    String s=" width=\"130\" height=\"101\"";
			//String tabcol2  = rs.getString("tabcol2");            
		    out.print("<tr>");
		    out.print("<td>");
		    out.print(id);
		    out.print("</td>");
		    out.print("<td>");
		    out.print("<img ");
		    out.print(Category);
		 
		    out.print("</td>");
		    out.print("<td>");
		    out.print(tabcol1);
		    out.print("</td>");
		    out.print("<td>");
		    out.print("<img src="+dam+s+">");
		    out.print("</td>");
		    //out.print("<td>");
		   // out.print(tabcol2);
		    //out.print("</td>");
		    out.print("</tr>");
		}
			
	}
}
out.print("Username-> " + session1.getAttribute("UserName"));

}
else
{
 //out.println("plz type correct username and password");
 
 String site = "http://localhost:8080/patchy" ;
 response.setStatus(response.SC_MOVED_TEMPORARILY);
 String url = "http://localhost:8080/patchy/?error="+URLEncoder.encode("1", "UTF-8");
 response.sendRedirect(url);
 return ;
 //HttpServletRequest request3 = (HttpServletRequest) request ;
//	HttpServletResponse response3 = (HttpServletResponse) response ;
 //	request3.setAttribute("msg", "Unknown login, please try again."); 
//	request3.getRequestDispatcher("index.html").forward(request3, response3);

 
 
 //out.println("plz type correct username and password");
 //response.setHeader("Location", site); 
}

]]></jsp:scriptlet>



 
</table>
</body>
</html>