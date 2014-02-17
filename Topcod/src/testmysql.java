import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class testmysql {

	public static void main(String[] args) {
		
		String url1 = "jdbc:mysql://localhost:3306/chem";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con1 = DriverManager.getConnection(url1, "root", "root"); 
			Statement stmt1 = con1.createStatement();
			ResultSet rs1 =  stmt1.executeQuery("select * from html;");
			while (rs1.next())
			{
				int per = rs1.getInt("id");
				System.out.println(per);
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("hifdhsohofds");;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
