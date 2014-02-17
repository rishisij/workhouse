
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

public class CopyOfBasicCrawler extends WebCrawler {
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
    public static void sap(int a,String na, String n,String ng) {
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
			stmt.executeUpdate("insert into chembiomolecules values ('"+a+"','"+na+"','"+n+"','"+ng+"')");
			
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
    			String reg1 = "<a.href=\"(.*?)\">(.*?)</a>";
    			String reg = "(.*?)";
    			Pattern pat = Pattern.compile(reg,Pattern.DOTALL);
    			Pattern pat1 = Pattern.compile(reg1,Pattern.DOTALL);
    			Matcher mat = pat.matcher(html);
    			/*String reg3="(<th(.*?)>|<td(.*?)>).*?(</th>|</td>)";
    			Pattern pat3 = Pattern.compile(reg3,Pattern.DOTALL);
				String reg2="(<th.*?>|<td.*?>)(.*?)(</th>|</td>).*?(<th.*?>|<td.*?>)(.*?)(</th>|</td>)";
				Pattern pat2 = Pattern.compile(reg2,Pattern.DOTALL);
				String reg4=".*?colspan=\"(.*?)\".*?";
				Pattern pat4 = Pattern.compile(reg4,Pattern.DOTALL);
				String reg5 = "(<th.*?>|<td.*?>)(.*?)(</th>|</td>)";
    			Pattern pat5 = Pattern.compile(reg5,Pattern.DOTALL);*/
				if(mat.find())
    			{	
					//sap1(count++,"",mat.group(1),"");
					Matcher mat1=pat1.matcher(mat.group(1));
					while(mat1.find())
					{
						sap1(count++,mat1.group(2),mat1.group(1),"");
						/*Matcher mat3=pat3.matcher(mat1.group(1));
						if(mat3.find())
						{	
							
							if(mat3.group(2)!=null)
							{	
								Matcher mat4=pat4.matcher(mat3.group(2));
								if(mat4.find())
								{	
									if(mat4.group(1)!=null)
									{
										Matcher mat5=pat5.matcher(mat1.group(1));
										if(mat5.find())
										{
											String key=mat5.group(2);
											
											 
								        	for (int i=0;i<4;i++){	
								        		key=key.replaceAll("<a.*?>+", "");
												key=key.replaceAll("</a>+", "");
								        		key=key.replaceAll("<sub.*?>+", "");
												key=key.replaceAll("</sub>+", "");
								        		key=key.replaceAll("<div.*?>+", "");
								        		key=key.replaceAll("</div>+", "");
								        		key=key.replaceAll("<img.*?/>", "");
								        		key=key.replaceAll("<sup.*?>+", "");
												key=key.replaceAll("</sup>+", "");
								        		key=key.replaceAll("<span.*?>+", "");
												key=key.replaceAll("</span>+", "");
								        		key=key.replaceAll("<abbr.*?>+", "");
												key=key.replaceAll("</abbr>+", "");
								        		key=key.replaceAll("<li.*?>+", "");
												key=key.replaceAll("</li>+", "");
								        		key=key.replaceAll("<a.*?href.*?>+", "");
								        		key=key.replaceAll("<b.*?>+", "");
												key=key.replaceAll("</b>+", "");
								        		key=key.replaceAll("<br />+", ",");
												key=key.replaceAll("<br>+", "");
								        		key=key.replaceAll("<p.*?>+", "");
												key=key.replaceAll("</p>+", "");
								        		key=key.replaceAll("<ul.*?>+", "");
												key=key.replaceAll("</ul>+", "");
								        		key=key.replaceAll("<i.*?>+", "");
												key=key.replaceAll("</i>+", "");
								        		key=key.replaceAll("<s.*?>+", "");
												key=key.replaceAll("</s>+", "");
								        		key=key.replaceAll("<table.*?>+","" );
												key=key.replaceAll("</table>+", "");
								        		key=key.trim().replaceAll(" +", " ");
								        		key=key.replaceAll("<h[1-6]>+","");
												key=key.replaceAll("</h[1-6]>+","");
								        		key=key.replaceAll("[']", "");
								        		key=key.replaceAll("\\n", " ");
												key=key.replaceAll("<tr.*?>+", "");
												key=key.replaceAll("<td.*?>+", "");
												key=key.replaceAll("</tr>+", "");
												key=key.replaceAll("</td>+", "");
												key=key.replaceAll("<th.*?>+", "");
												key=key.replaceAll("</th>+", "");
												key=key.replaceAll("&#160;", "");
												key=key.replaceAll("<strong.*?>","");
												key=key.replaceAll("</strong>","");
												
										}	
											System.out.println("Found header====>"+key);
										}
									}
								}
							}
							
							else if(mat3.group(3)!=null)
							{
								Matcher mat4=pat4.matcher(mat3.group(3));
								if(mat4.find())
								{	
									if(mat4.group(1)!=null)
									{
										Matcher mat5=pat5.matcher(mat1.group(1));
										if(mat5.find())
										{
											String key=mat5.group(2);
											
											 
								        	for (int i=0;i<4;i++){	
								        		key=key.replaceAll("<a.*?>+", "");
												key=key.replaceAll("</a>+", "");
								        		key=key.replaceAll("<sub.*?>+", "");
												key=key.replaceAll("</sub>+", "");
								        		key=key.replaceAll("<div.*?>+", "");
								        		key=key.replaceAll("</div>+", "");
								        		key=key.replaceAll("<img.*?/>", "");
								        		key=key.replaceAll("<sup.*?>+", "");
												key=key.replaceAll("</sup>+", "");
								        		key=key.replaceAll("<span.*?>+", "");
												key=key.replaceAll("</span>+", "");
								        		key=key.replaceAll("<abbr.*?>+", "");
												key=key.replaceAll("</abbr>+", "");
								        		key=key.replaceAll("<li.*?>+", "");
												key=key.replaceAll("</li>+", "");
								        		key=key.replaceAll("<a.*?href.*?>+", "");
								        		key=key.replaceAll("<b.*?>+", "");
												key=key.replaceAll("</b>+", "");
								        		key=key.replaceAll("<br />+", ",");
												key=key.replaceAll("<br>+", "");
								        		key=key.replaceAll("<p.*?>+", "");
												key=key.replaceAll("</p>+", "");
								        		key=key.replaceAll("<ul.*?>+", "");
												key=key.replaceAll("</ul>+", "");
								        		key=key.replaceAll("<i.*?>+", "");
												key=key.replaceAll("</i>+", "");
								        		key=key.replaceAll("<s.*?>+", "");
												key=key.replaceAll("</s>+", "");
								        		key=key.replaceAll("<table.*?>+","" );
												key=key.replaceAll("</table>+", "");
								        		key=key.trim().replaceAll(" +", " ");
								        		key=key.replaceAll("<h[1-6]>+","");
												key=key.replaceAll("</h[1-6]>+","");
								        		key=key.replaceAll("[']", "");
								        		key=key.replaceAll("\\n", " ");
												key=key.replaceAll("<tr.*?>+", "");
												key=key.replaceAll("<td.*?>+", "");
												key=key.replaceAll("</tr>+", "");
												key=key.replaceAll("</td>+", "");
												key=key.replaceAll("<th.*?>+", "");
												key=key.replaceAll("</th>+", "");
												key=key.replaceAll("&#160;", "");
												key=key.replaceAll("<strong.*?>","");
												key=key.replaceAll("</strong>","");
												
										}	
								        	System.out.println("Found header====>"+key);
										}
									}
								}
							}
							else
							{
								Matcher mat2=pat2.matcher(mat1.group(1));
								if(mat2.find())
								{
									String n=mat2.group(2);
									String np=mat2.group(5);
									
									 
						        	for (int i=0;i<4;i++){	
						        		n=n.replaceAll("<a.*?>+", "");
										n=n.replaceAll("</a>+", "");
						        		n=n.replaceAll("<sub.*?>+", "");
										n=n.replaceAll("</sub>+", "");
						        		n=n.replaceAll("<div.*?>+", "");
						        		n=n.replaceAll("</div>+", "");
						        		n=n.replaceAll("<img.*?/>", "");
						        		n=n.replaceAll("<sup.*?>+", "");
										n=n.replaceAll("</sup>+", "");
						        		n=n.replaceAll("<span.*?>+", "");
										n=n.replaceAll("</span>+", "");
						        		n=n.replaceAll("<abbr.*?>+", "");
										n=n.replaceAll("</abbr>+", "");
						        		n=n.replaceAll("<li.*?>+", "");
										n=n.replaceAll("</li>+", "");
						        		n=n.replaceAll("<a.*?href.*?>+", "");
						        		n=n.replaceAll("<b.*?>+", "");
										n=n.replaceAll("</b>+", "");
						        		n=n.replaceAll("<br />+", ",");
										n=n.replaceAll("<br>+", "");
						        		n=n.replaceAll("<p.*?>+", "");
										n=n.replaceAll("</p>+", "");
						        		n=n.replaceAll("<ul.*?>+", "");
										n=n.replaceAll("</ul>+", "");
						        		n=n.replaceAll("<i.*?>+", "");
										n=n.replaceAll("</i>+", "");
						        		n=n.replaceAll("<s.*?>+", "");
										n=n.replaceAll("</s>+", "");
						        		n=n.replaceAll("<table.*?>+","" );
										n=n.replaceAll("</table>+", "");
						        		n=n.trim().replaceAll(" +", " ");
						        		n=n.replaceAll("<h[1-6]>+","");
										n=n.replaceAll("</h[1-6]>+","");
						        		n=n.replaceAll("[']", "");
						        		n=n.replaceAll("\\n", " ");
										n=n.replaceAll("<tr.*?>+", "");
										n=n.replaceAll("<td.*?>+", "");
										n=n.replaceAll("</tr>+", "");
										n=n.replaceAll("</td>+", "");
										n=n.replaceAll("<th.*?>+", "");
										n=n.replaceAll("</th>+", "");
										n=n.replaceAll("&#160;", "");
										n=n.replaceAll("<strong.*?>","");
										n=n.replaceAll("</strong>","");
										
								}	
									
						        	 
						        	for (int i=0;i<4;i++){	
						        		np=np.replaceAll("<a.*?>+", "");
										np=np.replaceAll("</a>+", "");
						        		np=np.replaceAll("<sub.*?>+", "");
										np=np.replaceAll("</sub>+", "");
						        		np=np.replaceAll("<div.*?>+", "");
						        		np=np.replaceAll("</div>+", "");
						        		np=np.replaceAll("<img.*?/>", "");
						        		np=np.replaceAll("<sup.*?>+", "");
										np=np.replaceAll("</sup>+", "");
						        		np=np.replaceAll("<span.*?>+", "");
										np=np.replaceAll("</span>+", "");
						        		np=np.replaceAll("<abbr.*?>+", "");
										np=np.replaceAll("</abbr>+", "");
						        		np=np.replaceAll("<li.*?>+", "");
										np=np.replaceAll("</li>+", "");
						        		np=np.replaceAll("<a.*?href.*?>+", "");
						        		np=np.replaceAll("<b.*?>+", "");
										np=np.replaceAll("</b>+", "");
						        		np=np.replaceAll("<br />+", ",");
										np=np.replaceAll("<br>+", "");
						        		np=np.replaceAll("<p.*?>+", "");
										np=np.replaceAll("</p>+", "");
						        		np=np.replaceAll("<ul.*?>+", "");
										np=np.replaceAll("</ul>+", "");
						        		np=np.replaceAll("<i.*?>+", "");
										np=np.replaceAll("</i>+", "");
						        		np=np.replaceAll("<s.*?>+", "");
										np=np.replaceAll("</s>+", "");
						        		np=np.replaceAll("<table.*?>+","" );
										np=np.replaceAll("</table>+", "");
						        		np=np.trim().replaceAll(" +", " ");
						        		np=np.replaceAll("<h[1-6]>+","");
										np=np.replaceAll("</h[1-6]>+","");
						        		np=np.replaceAll("[']", "");
						        		np=np.replaceAll("\\n", " ");
										np=np.replaceAll("<tr.*?>+", "");
										np=np.replaceAll("<td.*?>+", "");
										np=np.replaceAll("</tr>+", "");
										np=np.replaceAll("</td>+", "");
										np=np.replaceAll("<th.*?>+", "");
										np=np.replaceAll("</th>+", "");
										np=np.replaceAll("&#160;", "");
										np=np.replaceAll("<strong.*?>","");
										np=np.replaceAll("</strong>","");
										
								}	
						        	sap(count++,url,n,np);
						        	System.out.println("Found info Table===>"+n+"=============="+np);
								}
							}
					}
					else
					{
						Matcher mat2=pat2.matcher(mat1.group(1));
						if(mat2.find())
						{
							String n=mat2.group(2);
							String np=mat2.group(5);
							
							 
				        	for (int i=0;i<4;i++){	
				        		n=n.replaceAll("<a.*?>+", "");
								n=n.replaceAll("</a>+", "");
				        		n=n.replaceAll("<sub.*?>+", "");
								n=n.replaceAll("</sub>+", "");
				        		n=n.replaceAll("<div.*?>+", "");
				        		n=n.replaceAll("</div>+", "");
				        		n=n.replaceAll("<img.*?/>", "");
				        		n=n.replaceAll("<sup.*?>+", "");
								n=n.replaceAll("</sup>+", "");
				        		n=n.replaceAll("<span.*?>+", "");
								n=n.replaceAll("</span>+", "");
				        		n=n.replaceAll("<abbr.*?>+", "");
								n=n.replaceAll("</abbr>+", "");
				        		n=n.replaceAll("<li.*?>+", "");
								n=n.replaceAll("</li>+", "");
				        		n=n.replaceAll("<a.*?href.*?>+", "");
				        		n=n.replaceAll("<b.*?>+", "");
								n=n.replaceAll("</b>+", "");
				        		n=n.replaceAll("<br />+", ",");
								n=n.replaceAll("<br>+", "");
				        		n=n.replaceAll("<p.*?>+", "");
								n=n.replaceAll("</p>+", "");
				        		n=n.replaceAll("<ul.*?>+", "");
								n=n.replaceAll("</ul>+", "");
				        		n=n.replaceAll("<i.*?>+", "");
								n=n.replaceAll("</i>+", "");
				        		n=n.replaceAll("<s.*?>+", "");
								n=n.replaceAll("</s>+", "");
				        		n=n.replaceAll("<table.*?>+","" );
								n=n.replaceAll("</table>+", "");
				        		n=n.trim().replaceAll(" +", " ");
				        		n=n.replaceAll("<h[1-6]>+","");
								n=n.replaceAll("</h[1-6]>+","");
				        		n=n.replaceAll("[']", "");
				        		n=n.replaceAll("\\n", " ");
								n=n.replaceAll("<tr.*?>+", "");
								n=n.replaceAll("<td.*?>+", "");
								n=n.replaceAll("</tr>+", "");
								n=n.replaceAll("</td>+", "");
								n=n.replaceAll("<th.*?>+", "");
								n=n.replaceAll("</th>+", "");
								n=n.replaceAll("&#160;", "");
								n=n.replaceAll("<strong.*?>","");
								n=n.replaceAll("</strong>","");
								
						}	
							
				        	 
				        	for (int i=0;i<4;i++){	
				        		np=np.replaceAll("<a.*?>+", "");
								np=np.replaceAll("</a>+", "");
				        		np=np.replaceAll("<sub.*?>+", "");
								np=np.replaceAll("</sub>+", "");
				        		np=np.replaceAll("<div.*?>+", "");
				        		np=np.replaceAll("</div>+", "");
				        		np=np.replaceAll("<img.*?/>", "");
				        		np=np.replaceAll("<sup.*?>+", "");
								np=np.replaceAll("</sup>+", "");
				        		np=np.replaceAll("<span.*?>+", "");
								np=np.replaceAll("</span>+", "");
				        		np=np.replaceAll("<abbr.*?>+", "");
								np=np.replaceAll("</abbr>+", "");
				        		np=np.replaceAll("<li.*?>+", "");
								np=np.replaceAll("</li>+", "");
				        		np=np.replaceAll("<a.*?href.*?>+", "");
				        		np=np.replaceAll("<b.*?>+", "");
								np=np.replaceAll("</b>+", "");
				        		np=np.replaceAll("<br />+", ",");
								np=np.replaceAll("<br>+", "");
				        		np=np.replaceAll("<p.*?>+", "");
								np=np.replaceAll("</p>+", "");
				        		np=np.replaceAll("<ul.*?>+", "");
								np=np.replaceAll("</ul>+", "");
				        		np=np.replaceAll("<i.*?>+", "");
								np=np.replaceAll("</i>+", "");
				        		np=np.replaceAll("<s.*?>+", "");
								np=np.replaceAll("</s>+", "");
				        		np=np.replaceAll("<table.*?>+","" );
								np=np.replaceAll("</table>+", "");
				        		np=np.trim().replaceAll(" +", " ");
				        		np=np.replaceAll("<h[1-6]>+","");
								np=np.replaceAll("</h[1-6]>+","");
				        		np=np.replaceAll("[']", "");
				        		np=np.replaceAll("\\n", " ");
								np=np.replaceAll("<tr.*?>+", "");
								np=np.replaceAll("<td.*?>+", "");
								np=np.replaceAll("</tr>+", "");
								np=np.replaceAll("</td>+", "");
								np=np.replaceAll("<th.*?>+", "");
								np=np.replaceAll("</th>+", "");
								np=np.replaceAll("&#160;", "");
								np=np.replaceAll("<strong.*?>","");
								np=np.replaceAll("</strong>","");
								
						}	
				        	sap(count++,url,n,np);
				        	System.out.println("Found info Table===>"+n+"=============="+np);
						}
					}
					Matcher mat5=pat5.matcher(mat1.group(1));
					if(mat5.find())
					{
						String key=mat5.group(2);
				       	cleanup(key);
				       	System.out.println("Found header====>"+key);
					}
					Matcher mat2=pat2.matcher(mat1.group(1));
					if(mat2.find())
					{
						String n=mat2.group(2);
						String np=mat2.group(5);
						
						 
			        	for (int i=0;i<4;i++){	
			        		n=n.replaceAll("<a.*?>+", "");
							n=n.replaceAll("</a>+", "");
			        		n=n.replaceAll("<sub.*?>+", "");
							n=n.replaceAll("</sub>+", "");
			        		n=n.replaceAll("<div.*?>+", "");
			        		n=n.replaceAll("</div>+", "");
			        		n=n.replaceAll("<img.*?/>", "");
			        		n=n.replaceAll("<sup.*?>+", "");
							n=n.replaceAll("</sup>+", "");
			        		n=n.replaceAll("<span.*?>+", "");
							n=n.replaceAll("</span>+", "");
			        		n=n.replaceAll("<abbr.*?>+", "");
							n=n.replaceAll("</abbr>+", "");
			        		n=n.replaceAll("<li.*?>+", "");
							n=n.replaceAll("</li>+", "");
			        		n=n.replaceAll("<a.*?href.*?>+", "");
			        		n=n.replaceAll("<b.*?>+", "");
							n=n.replaceAll("</b>+", "");
			        		n=n.replaceAll("<br />+", ",");
							n=n.replaceAll("<br>+", "");
			        		n=n.replaceAll("<p.*?>+", "");
							n=n.replaceAll("</p>+", "");
			        		n=n.replaceAll("<ul.*?>+", "");
							n=n.replaceAll("</ul>+", "");
			        		n=n.replaceAll("<i.*?>+", "");
							n=n.replaceAll("</i>+", "");
			        		n=n.replaceAll("<s.*?>+", "");
							n=n.replaceAll("</s>+", "");
			        		n=n.replaceAll("<table.*?>+","" );
							n=n.replaceAll("</table>+", "");
			        		n=n.trim().replaceAll(" +", " ");
			        		n=n.replaceAll("<h[1-6]>+","");
							n=n.replaceAll("</h[1-6]>+","");
			        		n=n.replaceAll("[']", "");
			        		n=n.replaceAll("\\n", " ");
							n=n.replaceAll("<tr.*?>+", "");
							n=n.replaceAll("<td.*?>+", "");
							n=n.replaceAll("</tr>+", "");
							n=n.replaceAll("</td>+", "");
							n=n.replaceAll("<th.*?>+", "");
							n=n.replaceAll("</th>+", "");
							n=n.replaceAll("&#160;", "");
							n=n.replaceAll("<strong.*?>","");
							n=n.replaceAll("</strong>","");
							
					}	
						
			        	 
			        	for (int i=0;i<4;i++){	
			        		np=np.replaceAll("<a.*?>+", "");
							np=np.replaceAll("</a>+", "");
			        		np=np.replaceAll("<sub.*?>+", "");
							np=np.replaceAll("</sub>+", "");
			        		np=np.replaceAll("<div.*?>+", "");
			        		np=np.replaceAll("</div>+", "");
			        		np=np.replaceAll("<img.*?/>", "");
			        		np=np.replaceAll("<sup.*?>+", "");
							np=np.replaceAll("</sup>+", "");
			        		np=np.replaceAll("<span.*?>+", "");
							np=np.replaceAll("</span>+", "");
			        		np=np.replaceAll("<abbr.*?>+", "");
							np=np.replaceAll("</abbr>+", "");
			        		np=np.replaceAll("<li.*?>+", "");
							np=np.replaceAll("</li>+", "");
			        		np=np.replaceAll("<a.*?href.*?>+", "");
			        		np=np.replaceAll("<b.*?>+", "");
							np=np.replaceAll("</b>+", "");
			        		np=np.replaceAll("<br />+", ",");
							np=np.replaceAll("<br>+", "");
			        		np=np.replaceAll("<p.*?>+", "");
							np=np.replaceAll("</p>+", "");
			        		np=np.replaceAll("<ul.*?>+", "");
							np=np.replaceAll("</ul>+", "");
			        		np=np.replaceAll("<i.*?>+", "");
							np=np.replaceAll("</i>+", "");
			        		np=np.replaceAll("<s.*?>+", "");
							np=np.replaceAll("</s>+", "");
			        		np=np.replaceAll("<table.*?>+","" );
							np=np.replaceAll("</table>+", "");
			        		np=np.trim().replaceAll(" +", " ");
			        		np=np.replaceAll("<h[1-6]>+","");
							np=np.replaceAll("</h[1-6]>+","");
			        		np=np.replaceAll("[']", "");
			        		np=np.replaceAll("\\n", " ");
							np=np.replaceAll("<tr.*?>+", "");
							np=np.replaceAll("<td.*?>+", "");
							np=np.replaceAll("</tr>+", "");
							np=np.replaceAll("</td>+", "");
							np=np.replaceAll("<th.*?>+", "");
							np=np.replaceAll("</th>+", "");
							np=np.replaceAll("&#160;", "");
							np=np.replaceAll("<strong.*?>","");
							np=np.replaceAll("</strong>","");
							
					}	
			        	sap(count++,url,n,np);
			        	System.out.println("Found info Table===>"+n+"=============="+np);
					}*/
				}
    		}
				else
				{	
					count++;
					sap1(count,url,"","");
				}
           System.out.println("======================================");
         }
                
    }
}

	