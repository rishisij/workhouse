
package edu.uci.ics.crawler4j.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import edu.uci.ics.crawler4j.crawler.RawClass;

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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.sql.*;

public class Crawler1 extends WebCrawler {
	
	// file name for properties file to load
	final static String PROPCONFIG = "crawlerLogic.properties";
        final static String RESTRICT = "sites.properties"; //addded by Sanjiv Kawa
        public static int count = 1;
        public static String oops = "seeds.properties";
        FileOutputStream pOUTPUT;
        PrintStream pPRINT;
        //osWriter = new FileOutputStream("seeds.properties",true);
        //OutputStream outStream = null;
       //File outputFile = new File("seeds.properties");;
	/*
	 * Boolean to determine if website should be crawled or not
	 */
    @Override
    //osWriter.write()
    public boolean shouldVisit(WebURL url) {
    	
    	
    	
    	Properties myProps = new Properties();
    	FileInputStream propInputStream;
		try {
			propInputStream = new FileInputStream(PROPCONFIG);
			myProps.load(propInputStream);  
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 
		String regexString = myProps.getProperty("regexUrlWords");
		String[] regexWords = regexString.split(" ");
		
		
				
		//loads patterns from .properties
    	Pattern FILTERS = Pattern.compile(myProps.getProperty("regexUrlFileType"));
    	Pattern FILTERS1 = Pattern.compile(myProps.getProperty("regexUrlWords"));
    	
    	String href = url.getURL().toLowerCase();  
    	boolean flagged = true;
    	
    	


		try {
			flagged = sqlitejdbc.checkSiteFlagged(href);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    	
    	// returns true if does not match patters
        return !FILTERS.matcher(href).matches() && !FILTERS1.matcher(href).find() && href.startsWith("www.istockphoto.com");
    }
    
    /*
     * Vists page and gets information, checks content against filters
     */
    public static void sap(String url,String na, String n) {
		try {
      
	   
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
					e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3308/sap", "", "");
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into html values ('"+count+"','"+url+"','"+na+"','"+n+"');");
			count++;
			stmt.close();
			conn.commit();
			conn.close();
			//String strSelect = "insert into html (Category, tabcol1, tabcol2) values (url,na,n)";
			
			
		//	System.out.println("The SQL query is: " + strSelect); // Echo For debugging
			//System.out.println();
			//ResultSet rset = stmt.executeQuery(strSelect);
			//System.out.println("The records selected are:");
			//int rowCount = 0;
			//while(rset.next()) {   // Move the cursor to the next row
				//int id          = rset.getInt("id");
				//String Category = rset.getString("category");
				//String Tabcol1  = rset.getString("tabcol1");
				//String Tabcol2  = rset.getString("tabcol2");            
				//System.out.println(id + ", " + Category + ", " + Tabcol1 + ", " + Tabcol2);
				//++rowCount;
			//}
			//System.out.println("Total number of records = " + rowCount);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
    @Override
    public void visit(Page page) {
    	
    		
            int docid = page.getWebURL().getDocid();
            String url = page.getWebURL().getURL();
            String domain = page.getWebURL().getDomain();
            String path = page.getWebURL().getPath();
            String subDomain = page.getWebURL().getSubDomain();
            String parentUrl = page.getWebURL().getParentUrl();
            //Crawler a=new Crawler();
            //a.sap();
            
            Properties restrictToURL = new Properties(); //added by sanjiv kawa
            FileInputStream propInStream = null; //added by sanjiv kawa
            
            try //added by sanjiv kawa
            {
                propInStream = new FileInputStream(RESTRICT);
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            }
            try //added by sanjiv kawa
            {
                restrictToURL.load(propInStream);
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            String restrictedToURL1 = restrictToURL.getProperty("restrict1"); //added by sanjiv kawa
            String restrictedToURL2 = restrictToURL.getProperty("restrict2"); //added by sanjiv kawa

            boolean patternString1 = org.apache.commons.lang3.StringUtils.contains(url, restrictedToURL1); //added by sanjiv kawa
            boolean patternString2 = org.apache.commons.lang3.StringUtils.contains(url, restrictedToURL2); //added by sanjiv kawa
               
            if(patternString1 == true || patternString1 == false || patternString2 == true) //added by sanjiv kawa - restricts urls to fit /thread/ or /threads/ format
            {

                System.out.println("Docid: " + docid);
                System.out.println("URL: " + url);
                //System.out.println("Domain: '" + domain + "'");
                //System.out.println("Sub-domain: '" + subDomain + "'");
                //System.out.println("Path: '" + path + "'");
                System.out.println("Parent page: " + parentUrl);
                if (page.getParseData() instanceof HtmlParseData) {
        			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
        			String text = htmlParseData.getText();
        			String html = htmlParseData.getHtml();
        			List<WebURL> links = htmlParseData.getOutgoingUrls();
        			//System.out.println("Text length: " + text.length());
        			//System.out.println("Html length: " + html.length());
        			//System.out.println("Number of outgoing links: " + links.size());
        			//String pattern1="catlinks\">(.*?)</div>";
        			String pattern1 = "(.+)";
        			Pattern FILTERSul1 = Pattern.compile(pattern1, Pattern.DOTALL);
        			Matcher m1 = FILTERSul1.matcher(html);
        			//String pattern = "(/wiki/Category:(.*?)\")";
        	
        			String pattern = "<tr>.*?<td>(.*?)</td>.*?<td>(.*?)</td>.*?</tr>";
        			Pattern FILTERSul = Pattern.compile(pattern, Pattern.DOTALL);
        			if (m1.find())
        			{
        				
        			Matcher m = FILTERSul.matcher(m1.group(1));
        			//outputFile = new File("seeds.properties");
		        	try {
						FileOutputStream outStream = new FileOutputStream("seeds.properties",true);
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	//Writer osWriter;
					//osWriter = new OutputStreamWriter(outStream);
		           	while (m.find()) 
        			{
        		       
					
		        		String n=m.group(2).replaceAll("<span.*?><a rel.*?>(.*?)</a>.*?</span>.*", "$1");
		        		//System.out.println(m.group(2));
		        	for (int i=0;i<4;i++){	
		        		n=n.replaceAll("<a.*?>(.*?)</a>+", "$1");
		        		n=n.replaceAll("<sub>(.*?)</sub>+", "$1");
		        		n=n.replaceAll("<div.*?>(.*?)</div>", "$1");
		        		n=n.replaceAll("<div.*?>", "");
		        		n=n.replaceAll("<img.*?/>", "");
		        		n=n.replaceAll("</div>", "");
		        		n=n.replaceAll("<sup>(.*?)</sup>+", "$1");
		        		n=n.replaceAll("<span.*?>(.*?)</span>+", "$1");
		        		n=n.replaceAll("<abbr.*?>(.*?)</abbr>+", "$1");
		        		n=n.replaceAll("<sup.*?>(.*?)</sup>+", "$1");
		        		n=n.replaceAll("<li>(.*?)</li>+", "$1");
		        		n=n.replaceAll("<a.*?href.*?>(.*?)</a>", "$1");
		        		n=n.replaceAll("<b>(.*?)</b>+", "$1");
		        		n=n.replaceAll("<br />", ",");
		        		n=n.replaceAll("<p>(.*?)</p>", "$1");
		        		n=n.replaceAll("<ul>", "");	
		        		n=n.replaceAll("<i>(.*?)</i>", "$1");
		        		n=n.replaceAll("<s>(.*?)</s>", "$1");
		        		n=n.replaceAll("</ul>", "");
		        		n=n.replaceAll("<table>(.*?)</table>+","$1" );
		        		n=n.replaceAll("<table.*?>(.*?)</table>+","$1" );
		        		n=n.trim().replaceAll(" +", " ");
		        		n=n.replaceAll("<h[1-6]>(.*?)</h[1-6]>+","$1");
		        		n=n.replaceAll("[']", "");
		        		n=n.replaceAll("\\n", " ");
		        		n=n.replaceAll("<li.*?>(.*?)</li>+", "$1");
		        	}	
		        	String na = m.group(1).replaceAll("<span.*?><a rel.*?>(.*?)</a>.*?</span>.*", "$1");
		        	for (int y=0;y<4;y++){
		        		na=na.replaceAll("<table.*?>(.*?)</table>+","$1" );
		        		na=na.replaceAll("<li.*?>(.*?)</li>+", "$1");
		        		na=na.replaceAll("<h[1-6]>(.*?)</h[1-6]>+","$1");
		        		na=na.replaceAll("<a.*?>(.*?)</a>+", "$1");
		        		na=na.replaceAll("<sub>(.*?)</sub>+", "$1");
		        		na=na.replaceAll("<div.*?>(.*?)</div>", "$1");
		        		na=na.replaceAll("<div.*?>", "");
		        		na=na.replaceAll("<img.*?/>", "");
		        		na=na.replaceAll("</div>", "");
		        		na=na.replaceAll("<sup>(.*?)</sup>+", "$1");
		        		na=na.replaceAll("<span.*?>(.*?)</span>+", "$1");
		        		na=na.replaceAll("<abbr.*?>(.*?)</abbr>+", "$1");
		        		na=na.replaceAll("<sup.*?>(.*?)</sup>+", "$1");
		        		na=na.replaceAll("<li>(.*?)</li>+", "$1");
		        		na=na.replaceAll("<a.*?href.*?>(.*?)</a>", "$1");
		        		na=na.replaceAll("<b>(.*?)</b>+", "$1");
		        		na=na.replaceAll("<br />", ",");
		        		na=na.replaceAll("<p>(.*?)</p>", "$1");
		        		na=na.replaceAll("<ul>", "");	
		        		na=na.replaceAll("<a.*?>", "");
		        		na=na.replaceAll("</a>", "");
		        		na=na.replaceAll("</ul>", "");
		        		na=na.replaceAll("<i>(.*?)</i>", "$1");
		        		na=na.replaceAll("<s>(.*?)</s>", "$1");
		        		na=na.replaceAll("<i>(.*?)</i>", "$1");
		        		na=na.replaceAll("<table>(.*?)</table","$1" );
		        		na=na.trim().replaceAll(" +", " ");
		        		na=na.replaceAll("\\n", " ");
		        		na=na.replaceAll("[']", "");
		        	}
		        	
		        		System.out.println("Found value:->" + na + "<==========>" + n);
		        		sap(url,na,n);
		        		//System.out.println("Found value:->" + m.group(2));
		        		//System.out.println(n);
		        		//System.out.println(m.group(2));
        		        try{
        		        	count++;
        		        	//pOUTPUT = new FileOutputStream(oops,true);
        		            //pPRINT = new PrintStream(pOUTPUT);
        		            //pPRINT.println ("key"+count+"= https://en.wikipedia.org/wiki/Category:"+m.group(2));
        		           // pPRINT.flush();
        		            //pPRINT.close();
        		            // Create file
        		  //      	outStream.append("site = https://en.wikipedia.org/wiki/"+m.group(2)+"\n");
        		        	//OutputStreamWriter os = new OutputStreamWriter("seeds.properties");
        		        	//os.append(m.group(2));        		        	//FileWriter fstream = new FileWriter(System.currentTimeMillis() + "out.txt");
        		              //  BufferedWriter out = new BufferedWriter(fstream);
        		            //out.write("Hello Java"
        		            //savefile(out);
        		            //Close the output stream
        		        	//System.out.println("hi");
        		            }catch (Exception e){//Catch exception if any
        		              System.err.println("Error: " + e.getMessage());
        		            }
        		        //System.out.println("Found value:1 " + m.group(2) );
        		         //System.out.println("Found key: " + m.group(1).replaceAll("<.*?>", "") + "-------->>" + m.group(2).replaceAll("<.*?>", ""));
        		      } //else {
        			//try {
					//	osWriter.close();
						//osWriter.save();
				//	} catch (IOException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					}   //System.out.println("NO MATCH");
        		      //}
        			//System.out.println(html);
                }
                            
                //try 
                {
                 //   DownloadPage.writeURLtoFile(page);
                } 
                //catch (IOException e1) 
                {
		// TODO Auto-generated catch block
                //    e1.printStackTrace();
		}
         
                {
                	
                }

            /*
            try {
            	
				filterHTML.filterThreadPosts(page);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            */
            
                System.out.println("======================================");
           // }
        }

    }
	private Object replaceAll(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	private void savefile(BufferedWriter out) {
		// TODO Auto-generated method stub
		
	}
    
    
    /*
     * Before exit of thread email is sent 
     */
    /*
    public void onBeforeExit() {
    	try {
			sendEmail.sendmail();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    
    }
       */ 
}