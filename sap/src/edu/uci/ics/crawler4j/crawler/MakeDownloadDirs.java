/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.ics.crawler4j.crawler;

import java.io.File;

/**
 *
 * @author sanjivkawa
 */
public class MakeDownloadDirs 
{
    /** Function: void createDirs().
     * 
     * Simply enough, this function creates the storage directories for the HTML and JSON files.
     * It is called on program start up.
     * 
     */
    public void createDirs()
    {
        String makeDir1 = "communityCrawlerStorage/HTMLPages";
        String makeDir2 = "communityCrawlerStorage/JSON";
        boolean makeDirs = (new File(makeDir1)).mkdirs();
        makeDirs = (new File(makeDir2)).mkdirs();
    }
}
