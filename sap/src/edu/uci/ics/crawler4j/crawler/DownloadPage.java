package edu.uci.ics.crawler4j.crawler;


import java.net.*;
import java.io.*;
import java.util.*;

import edu.uci.ics.crawler4j.crawler.Page;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;

public class DownloadPage
{
    final static String DOWNLOADPATH = "crawlerLogic.properties"; //added by Sanjiv Kawa
    
	/*
	 * Opens a buffered stream on the url and copies the contents to writer
	 */
	public static void saveURL(URL url, Writer writer)
		throws IOException {
		BufferedInputStream in = new BufferedInputStream(url.openStream());
		for (int c = in.read(); c != -1; c = in.read()) {
			writer.write(c);
		}
	}

	/*
	 * Opens a buffered stream on the url and copies the contents to OutputStream
	 */
	public static void saveURL(URL url, OutputStream os) throws IOException 
	{
		InputStream is = url.openStream();
		// Buffer configuration 1048576
		byte[] buf = new byte[2048];
		int n = is.read(buf);
		long start = System.currentTimeMillis();
		while (n != -1) {
			os.write(buf, 0, n);
			n = is.read(buf);
		}
		long finish = System.currentTimeMillis();
		//System.out.println(finish - start);
	}
	
	/*
	 * Writes the contents of the url to a string by calling saveURL with a
	 * string writer as argument
	 */
	public static String getURL(URL url)
		throws IOException {
		StringWriter sw = new StringWriter();
		saveURL(url, sw);
		return sw.toString();
	}

	/*
	 * Writes the contents of the url to a new file by calling saveURL with
	 * a file writer as argument
	 */
	public static void writeURLtoFile(Page page) throws IOException {
		
                // added by Sanjiv Kawa
                Properties downloadPath = new Properties();
            
                FileInputStream propInputStream = new FileInputStream(DOWNLOADPATH);
                downloadPath.load(propInputStream);
                
              //  String downloadDir = downloadPath.getProperty("HTMLPages");
               
                
		String url = page.getWebURL().getURL();
		int file = page.getWebURL().getDocid();
		
		String filename = Integer.toString(file); //we will fire this out to the db
		
		URL aurl = new URL(url);
                
                String pageURL = aurl.toString(); //we will fire this out to the db
                
                
                //FileOutputStream os = new FileOutputStream(downloadDir + filename + ".html");
                //System.out.println(filename + ".html" + " downloaded");
                //saveURL(aurl, os);
                
                /*This is for the HTML Parser and JSON scoring
                * This class will bring in the most recently downloaded HTML file
                * 
                * It will then pass all HTML files into the input function for parsing.
                * 
                * Once the HTML files are parsed, they are passed into the HTMLProcessor function
                * so that relevant plaintext can be extracted and stored into an ArrayList.
                *       
                * Finally the HTML Parser program outputs a JSON file that holds the contents of the arrayList from
                * the HTMLProcessor function.
                * 
                * The ScoringALgorithm is first introduced when writeToJSON is called from the HTMLParser class.
                * The writeToJSON function returns the recently parsed JSON file.
                * The JSONFile is then parsed and matched against security keywords in order to identify a frequency.
                * This is done within the iterateSecurityWords function.
                * The JSON is then scored and determined whether or not it is a valid security issue.
                * 
                * A database is then created called HTMLandJSON.
                * 
                * There are 2 tables within the database.
                * 
                * The first is HTML_JSON_DB - this will hold the HTML file number and JSON file number for a specific URL
                * The seconf is JSON_Frequency_DB - this will hold the JSON file number, keyword and frequency count > 0 as well as total score 
                * 
                * Ultimately DownloadPage acts as the driver for the HTMLParser and ScoringAlgorithm by utilizing the functions that it calls
                */
                
                
                //File inputFile = new File (downloadDir + filename + ".html");
                
                HTMLParserAndSecurityController hpsc = new HTMLParserAndSecurityController(); //calls the controller for the HTMLParser and ScoringAlgorithm
                
               // hpsc.run(inputFile,pageURL);
                
                //os.close();
                
                MessageDigest mD = null;
                InputStream iS = null;
                
                try
                {
                    mD = MessageDigest.getInstance("MD5"); 
                 //   iS = new FileInputStream(inputFile); //creates a hash of each html file
                }
                catch(NoSuchAlgorithmException e)
                {
                    Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
                }
                
                try
                {
                    iS = new DigestInputStream(iS,mD);
                }
                finally
                {
                    iS.close();
                }
                
                byte[] digest = mD.digest();
                
                //sqliteJdbcForHTMLandJSON db = new sqliteJdbcForHTMLandJSON();
                //db.database(file, pageURL, inputFile, digest); //adds hash into db table
	}

}
