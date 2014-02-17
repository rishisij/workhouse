package edu.uci.ics.crawler4j.crawler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.*;

import javax.xml.stream.events.Comment;

import org.apache.tika.sax.xpath.Matcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sleepycat.je.tree.Node;

import edu.uci.ics.crawler4j.crawler.Page;

public class filterHTML{
	
	final static String FILTERCONFIG = "filterWords.properties";
	final static String FILTERLOGIC = "filterLogic.properties";
	final static String PROPCONFIG = "crawlerLogic.properties";

	/*
	 * Parses specific content to be filtered and returns if page is page is flagged
	 */
	public static boolean filterThreadPosts(Page page) throws IOException, ClassNotFoundException, SQLException{
		
		boolean contentFlagged = false;
		int flaggedCount = 0;
		String contentToFilter = "";
		int docid = page.getWebURL().getDocid();
		
		// loads properties
    	Properties logicProps = new Properties();
    	FileInputStream propInputStream = new FileInputStream(FILTERLOGIC);
    	logicProps.load(propInputStream);   
    	
    	Properties myProps = new Properties();
    	FileInputStream myPropInputStream = new FileInputStream(PROPCONFIG);
    	myProps.load(propInputStream);    
		
		// gets url information and parses url
		String url = page.getWebURL().getURL();
		String parentUrl = page.getWebURL().getParentUrl();
		Document doc = Jsoup.connect(url).get();	
		
		// checks if specific divs want to be filtered, if true filters divs
		if (logicProps.getProperty("filterDiv").equals("true")) {
			
			String divClass = logicProps.getProperty("div1");
			
			List<String> divNames = splitString(divClass);
			
			// takes each tag name and appends content to string to be filtered.
			for (int j = 0; j < divNames.size(); j++) {
				Elements divContent = doc.getElementsByClass(divNames.get(j));
				String divContentString = divContent.text();
				contentToFilter += divContentString;
			}
						
		}
		
		// checks if specific tags want to be filtered, if true filters tags
		if (logicProps.getProperty("filterTag").equals("true")) {
			
			String tagClass = logicProps.getProperty("tag1");
			
			List<String> tagNames = splitString(tagClass);
			
			// takes each tag name and appends content to string to be filtered.
			for (int j = 0; j < tagNames.size(); j++) {
				Elements tagContents = doc.getElementsByClass(tagClass);
				String tagContentString = tagContents.text();
				contentToFilter += tagContentString;
				
			}
			
		}
		
		// Filters content of page for key words and gets number of keywords present
		contentFlagged = passFilter(contentToFilter, url);
		flaggedCount = countFilter(contentToFilter);
		
		// adds to database if not exist in database
		if (contentFlagged==true){
			if (!sqlitejdbc.checkSiteFlagged(url)){
				sqlitejdbc.addRow(docid, url, parentUrl, flaggedCount);
				System.out.println("**FLAGGED: Added to list**");
				DownloadPage.writeURLtoFile(page);
				
					//if (myProps.getProperty("downloadPage").equals("true")) {
						//DownloadPage.writeURLtoFile(page);
					//}
				
			}
			
			else if(sqlitejdbc.checkSiteFlagged(url)) {
				System.out.println("**FLAGGED: but already on list**");
			}

		}
		
		return contentFlagged ;		
	}	
	
	/*
	 * Filters content for keywords, returns true if keywords present
	 */
	public static boolean passFilter(String content, String url) throws IOException
	{
		boolean flagged = false;
		
		Properties filterProps = new Properties();
		FileInputStream filterInputStream = new FileInputStream(FILTERCONFIG);
		filterProps.load(filterInputStream);
		
		Enumeration eFilterProp = filterProps.propertyNames();
		while (eFilterProp.hasMoreElements()) {
			String key = (String) eFilterProp.nextElement();
			String filterWord = filterProps.getProperty(key);
			
			if (content.toLowerCase().contains(filterWord))
			{
				flagged = true;
			}
		}
					
		return flagged;	
	}
	
	/*
	 * Returns number of keywords present in flagged page
	 */
	public static int countFilter(String content) throws IOException
	{
		int count = 0;
		
		Properties filterProps = new Properties();
		FileInputStream filterInputStream = new FileInputStream(FILTERCONFIG);
		filterProps.load(filterInputStream);
		
		Enumeration eFilterProp = filterProps.propertyNames();
		while (eFilterProp.hasMoreElements()) {
			String key = (String) eFilterProp.nextElement();
			String filterWord = filterProps.getProperty(key);
			
			if (content.toLowerCase().contains(filterWord))
			{
				count++;
			}
		}
					
		return count;	
	}
	
	/*
	 * Takes string argument, splits it into a list at each space.
	 */
	public static List<String> splitString (String string) throws IOException {
		
		List<String> items = Arrays.asList(string.split("\\s"));
		
		return items;
		
	}
	
	public static int getContentLength(String urlString) throws IOException {
		
		URL url = new URL(urlString);
		
		HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
		connection.connect();
		int contentLength = connection.getContentLength();
		
		return contentLength;
		
		
	}
	
	public static void reCrawl ()
	
	{
		
		
	}
	
	
}
