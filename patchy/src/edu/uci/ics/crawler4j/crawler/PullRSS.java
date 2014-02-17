/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.ics.crawler4j.crawler;

/**
 *
 * @author sanjivkawa
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PullRSS 
{
    final static String DOWNLOADPATH = "crawlerLogic.properties";
    
    /** Function: URL xmlFile().
     * 
     * This function will attempt to open the XML file that contains the recent updated files from the JBOSS community forums.
     * 
     * @return xmlFile a File that contains the recent updated files in the JBOSS community forums.
     */    
    public static File xmlFile()
    {
        URL webURL = null;
        FileOutputStream fos = null;
        ReadableByteChannel rbc = null;
        String url = "https://community.jboss.org/community/feeds/threads?containerType=14&container=1";
        String outFile = "thread.xml";
        
        try 
        {
            webURL = new URL(url);
        } 
        catch (MalformedURLException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try 
        {
            rbc = Channels.newChannel(webURL.openStream());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {
            fos = new FileOutputStream(outFile);
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {
            fos.getChannel().transferFrom(rbc, 0, 1 << 24);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        try 
        {
            fos.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String inFile = outFile;
        
        File xmlFile = new File(inFile);
        
        return xmlFile;
        
    }
    
    /** Function: void processXML(File).
     * 
     * The xmlFile that is passed in as a parameter is parsed, from there the first tag within the XML file
     * that is hit is the entry tag. Nested within this tag is the updated tag which holds the date and time that
     * the thread was updated. The HREF tag is also nested within the entry tag, this holds the link of the correlating entry. 
     * This function will pull all updated tags and HREF tags from each entry in the XML file and store it into a HashMap.
     * 
     
     * @param xmlFile 
     */
    public static HashMap processXML(File xmlFile) throws ClassNotFoundException, SQLException
    {
        DocumentBuilderFactory buildFactory = null;
        DocumentBuilder buildDoc = null;
        Document doc = null;
        
        try
        {
            buildFactory = DocumentBuilderFactory.newInstance();
            buildDoc = buildFactory.newDocumentBuilder();
            doc = buildDoc.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
        }
        catch(ParserConfigurationException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(SAXException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(IOException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        
        NodeList list = doc.getElementsByTagName("entry"); //hit the first entry tag
        
        HashMap dateUrl = new HashMap<Date, String>();
        
        Date lastRunDate = null; 
        
        
        for(Integer i=0; i<list.getLength(); i++)
        {
            Node node = list.item(i); //the first child within the entry tag is now placed into node
            
            if(node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element el = (Element) node; //converts the first child within entry to an element
                
                String time = getTagValue("updated",el); //this will search for the updated tag within the entry tag
                               
                Date updatedTime = formatDate(time); //this will format the time/date of the updated tag
                
                //arr.add(updatedTime); //date data type is stored into the arrayList
                
                Node link = doc.getElementsByTagName("link").item(i+1); /*this will get the link tag within the entry tag.
                                                                          The purpose of the +1 is to get the link tag
                                                                          directly after the updated tag*/
        
                NamedNodeMap attr = link.getAttributes(); 
            
                Node nodeAttr = attr.getNamedItem("href"); //get the href attribute within the link tag
                
                String url = formatURLString(nodeAttr.toString()); //format the html tag"nodeAttr.toString()"
                
                dateUrl.put(updatedTime, url); //store date and url into hshmap
 
            }
        }
        
        sqliteJdbcForHTMLandJSON sql = new sqliteJdbcForHTMLandJSON();
        
        sql.crawlerRunDates(); //creates the Last_Run_Date_Table in the HTMLandJSON.db file.
        
        lastRunDate = lastCrawlerRun(dateUrl); //gets the last run date of the crawlers last run
                
        return dateUrl;
           
    }
    
    /** Function void RSSPuller(HashMap).
     * 
     * Initially this function will attempt to retrieve the lastRunDate that the crawler was run from the HTMLandJSON.db file.
     * 
     * From there it will compare the lastRunDate against all dates in the RSS file. If the date of the thread within the RSS file
     * is after the lastRunDate then the correlating HTML files are downloaded. The previous copies of the HTML files are overwritten
     * by the updated ones. A database table is created to keep track of which files are updated.
     * 
     * @param dateUrl - A hashmap
     */
    public static void RSSPuller(HashMap dateUrl)
    {
        Date lastRunDate = null;
        try 
        {
            lastRunDate = getLastCrawlerRun();
            
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Iterator it = dateUrl.keySet().iterator();
            
        while(it.hasNext()) //iterate through the HashMap
        {
            Date updatedTime = (Date) it.next(); //The key of the HashMap is the entry date
              
            if(lastRunDate.before(updatedTime)) //comparison against crawler last run and current entry's updated time
            {    
                String urlString = (String) dateUrl.get(updatedTime); //stores the value of Dates(keys) that are after the crawlers run

                URL url = null;
                
                try 
                {
                    url = new URL(urlString); //instansiate the string as a URL
                } 
                catch (MalformedURLException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                writeHTMLFile(url); //writes HTML file
                
                String id = getFileNameFromURL(url);
                
                String htmlFile = getFileNameFromURL(url)+".html";
                
                Connection c = null;
                try 
                {
                    Class.forName("org.sqlite.JDBC");
                } 
                catch (ClassNotFoundException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
                try 
                {
                    c = DriverManager.getConnection("jdbc:sqlite:HTMLandJSON.db","",""); //creates HTMLandJSON.db flat file
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
                try 
                {
                    c.setAutoCommit(false);
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Statement st = null;
                try 
                {
                    st = c.createStatement();
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
                try 
                {
                    st.executeUpdate("create table if not exists Updated_Files_Table(ID, HTML_File, HTML_URL, Date);"); //creates updated files table
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
                try 
                {
                    st.executeUpdate("insert or replace into Updated_Files_Table values ('"+id+"','"+htmlFile+"','"+url.toString()+"','"+updatedTime+"');"); //adds in each value
                    System.out.println("New Updated File: "+htmlFile);
                    System.out.println("URl: "+url);
                    System.out.println("Updated Time: "+updatedTime);
                    System.out.println(id+" Downloaded");
                    System.out.println("======================================");
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
                try 
                {
                    st.close();
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
                try 
                {
                    c.commit();
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
                try 
                {               
                    c.close();
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        System.out.println("No more updates to process");
    }
    
    /** Function: String getTagValue(String, Element).
     * 
     * The purpose of this function is to traverse the entry tag looking for a provided 
     * string tag name as well as the provided element.
     * 
     * @param tag - a provided child tag within a parent tag
     * @param el - an element 
     * @return the current value of the node
     */
    public static String getTagValue(String tag, Element el)
    {
        NodeList nlList = el.getElementsByTagName(tag).item(0).getChildNodes();
 
        Node nValue = (Node) nlList.item(0);
 
	return nValue.getNodeValue();
    }
    
    /** Function: String formatHTMLString(String).
     * 
     * This function takes in the raw URL string and formats it.
     * 
     * @param nodeAttr - a raw URL String
     * @return html - a sanitized URL String
     */
    public static String formatURLString(String nodeAttr)
    {
    	
        Pattern clearHref = Pattern.compile("\\b(?:href)\\b\\s*", Pattern.CASE_INSENSITIVE); //replaces href with ""
        Matcher matcher = clearHref.matcher(nodeAttr);
        String html = matcher.replaceAll("");
        html = html.replaceAll("=", ""); //replaces equals with ""
        html = html.replaceAll("^\"|\"$", ""); //replaces quotations with ""
        
        return html; //returns sanitized url
    }
    
    /** Function: Date formatDate(String).
     * 
     * This function takes in a raw date string, formats it and then parses it to a Date data type.
     * 
     * @param updatedTime - a raw date string
     * @return time - a formatted Date
     */
    public static Date formatDate(String updatedTime)
    {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        
        Date time = null;
        
        try
        {
            time = sdf.parse(updatedTime);
        }
        catch(ParseException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return time;
    }
    
    /** Function: String getFileNameFromURL(URL).
     * 
     * This function will return the fileName of the URL that is passed in.
     * 
     * @param urlIn - a valid URL
     * @return filename - filename of a URL
     */
    
    public static String getFileNameFromURL(URL urlIn)
    {
        String url = urlIn.getFile().toString();
        Pattern clearHref = Pattern.compile("\\b(?:thread)\\b\\s*", Pattern.CASE_INSENSITIVE); //replaces thread with ""
        Matcher matcher = clearHref.matcher(url);
        String filename = matcher.replaceAll("");
        filename = filename.replaceAll("/", ""); //replaces / with ""
        filename = filename.replaceAll("^\"|\"$", ""); //replaces quotations with ""
        
        return filename; //returns sanitized filename
    }
   
    
    /** Function: void writeHTMLFile(URL).
     * 
     * After taking in a URL, the entire page is downloaded and stored as a .HTML file. 
     * 
     * @param webURL a valid URL.
     */
    public static void writeHTMLFile(URL webURL)
    {
        
        FileOutputStream fos = null;
        ReadableByteChannel rbc = null;
        String filename = null;
        
        Properties downloadPath = new Properties();
            
        FileInputStream propInputStream = null;
        try 
        {
            propInputStream = new FileInputStream(DOWNLOADPATH);
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {
            downloadPath.load(propInputStream);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        String downloadDir = downloadPath.getProperty("HTMLPages");
               
        
        try 
        {
            rbc = Channels.newChannel(webURL.openStream());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {
            filename = getFileNameFromURL(webURL);
            fos = new FileOutputStream(downloadDir + filename +".html"); //ger the url's filename
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {
            fos.getChannel().transferFrom(rbc, 0, 1 << 24);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        try 
        {
            fos.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HTMLParserAndSecurityController hpsc = new HTMLParserAndSecurityController();
        sqliteJdbcForHTMLandJSON db = new sqliteJdbcForHTMLandJSON();
        
        File inputFile = new File (downloadDir + filename + ".html");
        try 
        {
            hpsc.run(inputFile, webURL.toString());
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Integer id = Integer.parseInt(filename);
        
        db.database(id, webURL.toString());
    }
    
    /** Function: Date lastCrawlerRun(HashMap).
     * 
     * This function will take in a HashMap via parameter. From there it will iterate through the HashMap
     * in order to locate the lastRunDate of the crawler. 
     * 
     * @param dateUrl - HashMap
     * @return lastRunDate - sets the last run date of the crawler
     * 
     */
    public static Date lastCrawlerRun(HashMap dateUrl)
    {
        Iterator it = dateUrl.keySet().iterator();
        
        Date lastRunDate = null;
        
        while(it.hasNext()) //iterate through the HashMap
        {
            Date updatedTime = (Date) it.next(); //The key of the HashMap is the entry date
              
            if(lastRunDate==null)
            {
                lastRunDate = updatedTime;
            }
            else if(updatedTime.after(lastRunDate))
            {
                lastRunDate = updatedTime;
            }
        }    
        
        return lastRunDate;
    }
    
    /** Function Date getLastCrawlerRun().
     * 
     * This function accesses the Last_Run_Date_Table within the HTMLandJSON.db flat file.
     * It extracts the last date that the crawler was run.
     * 
     * @return: lastDate a Date that the crawler was run. 
     */
    public static Date getLastCrawlerRun() throws ClassNotFoundException, SQLException
    {
        Connection c = null;
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:HTMLandJSON.db","",""); //creates HTMLandJSON.db flat file
        c.setAutoCommit(false);
                
        Statement st = c.createStatement();
        
        PreparedStatement ps = null;
        
        String query = "SELECT Date FROM Last_Run_Date_Table Limit 1"; //creates updated files table
        
        ps = c.prepareStatement(query);
       
        ResultSet rsx = ps.executeQuery();
        
        String lastDateString=rsx.getString("Date");
        
        DateFormat formatter; 
        Date lastDate = null; 
        formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            lastDate = (Date)formatter.parse(lastDateString);
            //Thu Oct 04 04:45:54 EST 2012
        } catch (ParseException ex) {
            Logger.getLogger(PullRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        System.out.println("The last date that the crawler was run is: "+lastDate);
  
        ps.close();
        rsx.close();
        st.close();
        c.commit();
        c.close(); 
       
        return lastDate;
    }
    /** void main(String[]).
     * 
     * Acts as a driver if the class is called independently via ant PullRSS.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NullPointerException, ClassNotFoundException, SQLException
    {
        File xmlFile = xmlFile();
        HashMap dateUrl = processXML(xmlFile);
        RSSPuller(dateUrl);
        
        //update time in db
        Date update = lastCrawlerRun(dateUrl);
        InsertLastCrawlerRun ilrc = new InsertLastCrawlerRun();
        ilrc.insertLastRunDate();
    }
}