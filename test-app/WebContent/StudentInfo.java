import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentInfo extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
static final long serialVersionUID = 1L;
public StudentInfo() { super(); }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("**** STUDENT INFO ****");
String roll = request.getParameter("roll");
PrintWriter out = response.getWriter();
response.setContentType("text/xml");
System.out.println(getResult(roll)); out.println(getResult(roll));
}
public String getResult(String roll){
String name = "";
String hostel = "";
String contact = "";
if(roll.equalsIgnoreCase("110")){
name = "Binod Kumar Suman"; hostel = "Ganga"; contact = "999999999";
} else if(roll.equalsIgnoreCase("120")){
name = "Pramod Kumar Modi"; hostel = "Godawari"; contact = "111111111111";
} else{ name = "Roll Number not found"; }
String result = "<Students>";
result += "<Student>"; result += "<Name>" + name + "</Name>";
result += "<Hostel>" +hostel + "</Hostel>";
result += "<Contact>" +contact + "</Contact>";
result += "</Student>"; result += "</Students>";
return result;
}
}