
package edu.uci.ics.crawler4j.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class CrawlerController {
	
	/*
	 * constants for loading .properties files
	 */

    
	OutputStream outStream = null;
    public static void main(String[] args) throws Exception
    {
     
        
        long start = System.currentTimeMillis();
        
        // Creates database
        sqlitejdbc.createDatabase();
        
        MakeDownloadDirs creator = new MakeDownloadDirs(); //added by sanjiv kawa
        
        InsertLastCrawlerRun ilrc = new InsertLastCrawlerRun();//added by sanjiv kawa
        ilrc.insertLastRunDate();//added by sanjiv kawa
        
        creator.createDirs();
        
        // Loads property files for the crawler logic and sites to add to seed
        Properties myProps = new Properties();
        FileInputStream propInputStream = new FileInputStream("crawlerLogic.properties");
        myProps.load(propInputStream);
        
        Properties mySites = new Properties();
        FileInputStream siteInputStream = new FileInputStream("seeds.properties");
        mySites.load(siteInputStream);
        
        // sets variables for crawler logic
        args = new String[5];
        args[0] = myProps.getProperty("storage");
        args[1] = myProps.getProperty("crawlerNumber");
        args[2] = myProps.getProperty("politeness");
        args[3] = myProps.getProperty("depth");
        args[4] = myProps.getProperty("maxfetch");
        
        // Throws exception if missing any of the necessary arguments
        if (args.length != 5) {
            System.out.println("Needed parameters: ");
            System.out.println("\t Storage folder");
            System.out.println("\t Number of crawlers");
            System.out.println("\t Politeness delay");
            System.out.println("\t Depth of crawler");
            System.out.println("\t Max pages to fetch");
            System.out.println("\t Check config.properties");
            return;
        }
        
        /*
         * crawlStorageFolder is a folder where intermediate crawl data is
         * stored.
         */
        String crawlStorageFolder = args[0];
        
        /*
         * numberOfCrawlers shows the number of concurrent threads that should
         * be initiated for crawling.
         */
        int numberOfCrawlers = Integer.parseInt(args[1]);
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        
        /*
         * Sets how often a crawler requests a page
         */
        config.setPolitenessDelay(Integer.parseInt(args[2]));
        
        /*
         * Sets maximum crawl depth, -1 is infinite
         */
        config.setMaxDepthOfCrawling(Integer.parseInt(args[3]));
        
        /*
         * Sets the max pages of numbers to crawl
         */
        config.setMaxPagesToFetch(Integer.parseInt(args[4]));
        
        /*
         * Includes HTTP secure pages to be crawled if set to true.
         * By default is set to false
         */
        config.setIncludeHttpsPages(true);
        
        /*
         * This config parameter can be used to set your crawl to be resumable
         * (meaning that you can resume the crawl from a previously
         * interrupted/crashed crawl). Note: if you enable resuming feature and
         * want to start a fresh crawl, you need to delete the contents of
         * rootFolder manually.
         */
        config.setResumableCrawling(false);
        
        /*
         * Instantiate the controller
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        
        /*
         * While loops loads each URL value from .properties file
         * adds it as a seed URL for the crawler
         */
        
        Enumeration eProps = mySites.propertyNames();
        while (eProps.hasMoreElements()) {
            String key = (String) eProps.nextElement();
            String value = mySites.getProperty(key);
            controller.addSeed(value);
            
        }
        /*
         * Starts the crawler. This is a blocking operation
         */
        controller.addSeed("http://www.istockphoto.com/browse/");
        //controller.addSeed("http://www.ics.uci.edu/");
        //controller.addSeed("https://en.wikipedia.org/wiki/Benzene");
       // controller.addSeed("http://www.tutorialspoint.com/java/java_string_subsequence.htm");
        controller.start(Crawler.class, numberOfCrawlers);
        
        
        /*
         * Sends shutdown and waits for the finish
         */
        
        
        controller.Shutdown();
        controller.waitUntilFinish();
        
        
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start) + " ms\n");
        
    }
}
