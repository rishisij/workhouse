
package edu.uci.ics.crawler4j.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


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
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.StringUtils;

import java.sql.*;

public class BasicCrawler extends WebCrawler {
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif" + "|png|tiff?|mid|mp2|mp3|mp4"
			+ "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    static int count=1;
    static int cnt=349;
    FileOutputStream pOUTPUT;
    PrintStream pPRINT;
    public static String oops = "seeds2.properties";
	public boolean shouldVisit(WebURL url) 
	{
		
    	String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches() && href.startsWith("http://en.");
		}
    public static void sap(int a,String na, String n) {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
					System.out.println("dosjgf");
			} catch (IllegalAccessException e) {
			     System.out.println("dsflhals");
			} catch (ClassNotFoundException e) {
				System.out.println("jsaf");
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chem", "root", "root");
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into chem values ('"+a+"','"+na+"','"+n+"')");
			
			stmt.close();
			conn.commit();
			conn.close();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
    public static void sap1(int a,String na, String n,String ng) {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
					System.out.println("dosjgf");
			} catch (IllegalAccessException e) {
			     System.out.println("dsflhals");
			} catch (ClassNotFoundException e) {
				System.out.println("jsaf");
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chem", "root", "root");
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into checkurl values ('"+a+"','"+na+"','"+n+"','"+ng+"')");
			
			stmt.close();
			conn.commit();
			conn.close();
			
		} catch(SQLException ex) {
			System.out.println("sql Exception");
		}
	}
   
    public void visit(Page page) {
    		
            int docid = page.getWebURL().getDocid();
            String url = page.getWebURL().getURL();
            String domain = page.getWebURL().getDomain();
            String path = page.getWebURL().getPath();
            String subDomain = page.getWebURL().getSubDomain();
            String parentUrl = page.getWebURL().getParentUrl();
            if (page.getParseData() instanceof HtmlParseData) {
    			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
    			String text = htmlParseData.getText();
    			String html = htmlParseData.getHtml();
    			List<WebURL> links = htmlParseData.getOutgoingUrls();

    			//String reg1 = "<a.href=\".*?\">(.*?)</a>";
    			String reg1 = "<li>(.*?)</li>";
    			//String reg = "(.*?)";
    			//Pattern pat = Pattern.compile(reg,Pattern.DOTALL);
    			Pattern pat1 = Pattern.compile(reg1,Pattern.DOTALL);
    			Matcher mat = pat1.matcher(html);
    			while(mat.find()){
    				System.out.println(mat.group(1));
    				sap(count,url,mat.group(1));
    				count++;
    			}
            }
    }
}

	