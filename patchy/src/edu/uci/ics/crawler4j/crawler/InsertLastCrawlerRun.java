/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.ics.crawler4j.crawler;

import java.util.Date;


/**
 *
 * @author sanjivkawa
 */
public class InsertLastCrawlerRun 
{
    /** Function void insertLastRunDate().
     * 
     * This function will retrieve the lastCrawlerRun date and write it into the database.
     * 
     */
    public void insertLastRunDate()
    {
        Date lastCrawlerRun = GetLastCrawlerRunTime.lastCrawlerDate(); //added by sanjiv kawa
        sqliteJdbcForHTMLandJSON db = new sqliteJdbcForHTMLandJSON(); //added by sanjiv kawa
        db.crawlerRunDates(lastCrawlerRun); //added by sanjiv kawa
    }
}
