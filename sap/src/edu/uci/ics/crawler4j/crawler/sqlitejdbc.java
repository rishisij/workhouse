package edu.uci.ics.crawler4j.crawler;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class sqlitejdbc {
	
	/*
	 * Creates a database if database does not exists
	 */
	public static void createDatabase() throws Exception {
		
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:flaggedUrl.db");
		Statement stat = conn.createStatement();
		stat.executeUpdate("create table if not exists flagged(docid, url, parentUrl, weight, contentLength);");
	    conn.close();
	}
	
	/*
	 * Adds row to database. Adds url and parent url
	 */
	public static void addRow(int docid, String url, String parentUrl, int weight) throws ClassNotFoundException, SQLException, IOException
	{
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:flaggedUrl.db");
		
		int contentLength = filterHTML.getContentLength(url);
		
		PreparedStatement prep = conn.prepareStatement(
				"insert or replace into flagged values (?,?,?,?,?);");
		
		// sets cell values
		prep.setInt(1, docid);
	    prep.setString(2, url);
	    prep.setString(3, parentUrl);
	    prep.setInt(4, weight);
	    prep.setInt(5, contentLength);
	    
	    
	    prep.addBatch();
	    
	    
	    // commits and closes connection
	    conn.setAutoCommit(false);
	    prep.executeBatch();
	    conn.setAutoCommit(true);
	    conn.close();
	}
	
	
	/*
	 * Check if site is in database, 
	 * returns boolean value
	 */
	public static boolean checkSiteFlagged(String url) throws SQLException, ClassNotFoundException, IOException 
	{
		boolean flagged = false;
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:flaggedUrl.db");
		
		PreparedStatement prep = conn.prepareStatement("select count(*) from flagged where url = (?)");
		prep.setString(1, url);
		ResultSet rs = prep.executeQuery();
	
		if (rs.getInt(1) != 0) {
			flagged = true;
			
			/*
			if (reCrawl(url))
			{
				flagged = false;
			} */
			
		}
		
		rs.close();
		conn.close();
		return flagged;	
	}
	
	/*
	 * Out puts contents of database to array list
	 * Returns string of list of flagged urls
	 */
	public static String dbOutput() throws ClassNotFoundException, SQLException {
		
		
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:flaggedUrl.db");
		
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT * from flagged ORDER BY weight desc");
		ArrayList output = new ArrayList();

		while (rs.next()) {
			output.add(rs.getString("url"));
			output.add(rs.getString("weight"));
		}
		rs.close();
		conn.close();
		return output.toString();
		
	}
	
	public static boolean reCrawl(String url) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:flaggedUrl.db");
		
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from flagged");
		
		while (rs.next()) {
			if (rs.getString("url").equals(url)) {
				if (rs.getInt("contentLength") < filterHTML.getContentLength(url)){
					return true;
				}
			}
		}
		
		return false;
		
		
	}
	
}


