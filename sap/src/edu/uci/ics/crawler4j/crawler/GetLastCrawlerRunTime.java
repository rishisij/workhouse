/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.ics.crawler4j.crawler;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanjivkawa
 */
public class GetLastCrawlerRunTime 
{   
    /** Date lastCrawlerDate().
     * 
     * This function will grab the XML file via the JBoss community forums RSS feed.
     * 
     * From there it will extract the first entry and store it before the crawler is run to signify that the date and time of the last
     * updated post was the start time of the crawler being run.
     * 
     * @return lastCrawlerDate is a Date that is returned which specifies the last date that the crawler was run.
     */
    public static Date lastCrawlerDate()
    {
               
        File xmlFile = PullRSS.xmlFile();
        HashMap dateUrl = null;
        
        try 
        {
            dateUrl = PullRSS.processXML(xmlFile);
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(GetLastCrawlerRunTime.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(GetLastCrawlerRunTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Date lastCrawlerDate = PullRSS.lastCrawlerRun(dateUrl);
        
        return lastCrawlerDate;
    }
}
