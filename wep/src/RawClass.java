
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.sql.*;
public class RawClass  {
	final static String rawprop="rawdata.properties";
	private static final Connection Null = null;
	public static void main(String args[]) throws IOException
	{
		Properties raw1= new Properties();
		try
		{
			FileInputStream rawIS = new FileInputStream(rawprop);
			raw1.load(rawIS);
		}
		catch( FileNotFoundException e)
		{
			e.printStackTrace();
		}
			
			String S = raw1.getProperty("key");
			System.out.println(S);
			
	}
	public void mysqlcon() throws Exception, Throwable
	{
		Connection conn = Null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/wep", "", "");
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		stmt.execute("");
		conn.commit();
		conn.close();
					
	}
}
	












	


