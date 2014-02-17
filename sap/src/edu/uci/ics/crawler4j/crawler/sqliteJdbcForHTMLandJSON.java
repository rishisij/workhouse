/**
 *
 * @author sanjivkawa
 * Red Hat
 */

package edu.uci.ics.crawler4j.crawler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sqliteJdbcForHTMLandJSON 

{
    /** Function: void database(Integer, String, File, byte[]).
     * 
     * This function will create and insert entries into the HTML_Size_Hash_Table and HTML_JSON_Mapper_Table within the HTMLandJSON.db
     * It will be called through out the programs structure in order to create entries into the db tables.
     * 
     */
    public void database(Integer id, String pageURL, File inputFile, byte[] digest)
    {
        try
        {   
            Connection c = null;
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:HTMLandJSON.db","",""); //creates HTMLandJSON.db flat file
            } catch (SQLException ex) {
                Logger.getLogger(sqliteJdbcForHTMLandJSON.class.getName()).log(Level.SEVERE, null, ex);
            }
            c.setAutoCommit(false);
        
            String html = id + ".html"; //appends .html onto the end of the current file number
            String json = id + ".json"; //appends .json onto the end of the current file number
        
            Statement st = c.createStatement();
            st.executeUpdate("create table if not exists HTML_JSON_Mapper_Table(ID, HTML_File, HTML_URL, JSON_File);"); //creates HTML_JSON table
            st.executeUpdate("create table if not exists HTML_Size_Hash_Table(ID, HTML_Byte_Size, HTML_MD5);"); //creates byte size and hash table
            st.executeUpdate("insert or replace into HTML_JSON_Mapper_Table values ('"+id+"','"+html+"','"+pageURL+"','"+json+"');"); //adds in each value
            st.executeUpdate("insert or replace into HTML_Size_Hash_Table values ('"+id+"','"+inputFile.length()+"','"+digest+"');"); //adds in each value
             
            st.close();
            c.commit();
            c.close();
        }
        catch(ClassNotFoundException e)
        {
            Logger.getLogger(sqliteJdbcForHTMLandJSON.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(SQLException e)
        {
            Logger.getLogger(sqliteJdbcForHTMLandJSON.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /** Function: void database(Integer, String).
     * 
     * This function will create the HTML_JSON_Mapper_Table within the HTMLandJSON.db
     * It will be called through out the programs structure in order to create entries into the db tables.
     * 
     */
    public void database(Integer id, String pageURL)
    {
        try
        {   
            Connection c = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:HTMLandJSON.db","",""); //creates HTMLandJSON.db flat file
            c.setAutoCommit(false);
        
            String html = id + ".html"; //appends .html onto the end of the current file number
            String json = id + ".json"; //appends .json onto the end of the current file number
        
            Statement st = c.createStatement();
            st.executeUpdate("create table if not exists HTML_JSON_Mapper_Table(ID, HTML_File, HTML_URL, JSON_File);"); //creates HTML_JSON table

            st.executeUpdate("insert or replace into HTML_JSON_Mapper_Table values ('"+id+"','"+html+"','"+pageURL+"','"+json+"');"); //adds in each value

             
            st.close();
            c.commit();
            c.close();
        }
        catch(ClassNotFoundException e)
        {
            Logger.getLogger(sqliteJdbcForHTMLandJSON.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(SQLException e)
        {
            Logger.getLogger(sqliteJdbcForHTMLandJSON.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /** Function: void crawlerRunDates(Date).
     * 
     * This function will create and insert entries into the Last_Run_Date_Table within the HTMLandJSON.db
     * It will be used to store the last run date of the crawler.
     * 
     */
    public void crawlerRunDates(Date lastCrawlerRun)
    {
        try
        {   
            Connection c = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:HTMLandJSON.db","",""); //creates HTMLandJSON.db flat file
            c.setAutoCommit(false);
        
            Statement st = c.createStatement();
            st.executeUpdate("create table if not exists Last_Run_Date_Table(Date);"); //creates HTML_JSON table

            st.executeUpdate("insert or replace into Last_Run_Date_Table values ('"+lastCrawlerRun+"');"); //adds in each value

             
            st.close();
            c.commit();
            c.close();
        }
        catch(ClassNotFoundException e)
        {
            Logger.getLogger(sqliteJdbcForHTMLandJSON.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(SQLException e)
        {
            Logger.getLogger(sqliteJdbcForHTMLandJSON.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /** Function: void crawlerRunDates(Date).
     * 
     * This function will create the Last_Run_Date_Table within the HTMLandJSON.db
     * 
     */
    public void crawlerRunDates()
    {
        try
        {   
            Connection c = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:HTMLandJSON.db","",""); //creates HTMLandJSON.db flat file
            c.setAutoCommit(false);
        
            Statement st = c.createStatement();
            st.executeUpdate("create table if not exists Last_Run_Date_Table(Date);"); //creates Last_Run_Date_Table
             
            st.close();
            c.commit();
            c.close();
        }
        catch(ClassNotFoundException e)
        {
            Logger.getLogger(sqliteJdbcForHTMLandJSON.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(SQLException e)
        {
            Logger.getLogger(sqliteJdbcForHTMLandJSON.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
