
package edu.uci.ics.crawler4j.crawler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import com.sleepycat.je.EnvironmentConfig;



public class BasicCrawlController {

	public static void main(String[] args) 
	{
		
		try
		{
		String crawlStorageFolder = "crawler_data";

		int numberOfCrawlers = Integer.parseInt("1");

		CrawlConfig config = new CrawlConfig();

		config.setCrawlStorageFolder(crawlStorageFolder);

		config.setPolitenessDelay(1000);

		config.setMaxDepthOfCrawling(0);

		config.setMaxPagesToFetch(10000);

		config.setResumableCrawling(false);

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		Properties myProps = new Properties();
    	FileInputStream propInputStream;
		try {
			propInputStream = new FileInputStream("chemsyno.properties");
			myProps.load(propInputStream);  
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration eProps = myProps.propertyNames();
        while (eProps.hasMoreElements()) {
            String key = (String) eProps.nextElement();
            String value = myProps.getProperty(key);
            System.out.println(value);
            controller.addSeed(value);
        }
 
		//controller.addSeed("http://en.wikipedia.org/wiki/Category:Annulenes");
		//controller.addSeed("http://en.wikipedia.org/wiki/Category:Cycloalkenes");
		//controller.addSeed("http://www.webref.org/scientists/scientists.htm");
		  
		controller.start(BasicCrawler.class, numberOfCrawlers);
        }
        
		catch(Exception e)
		{
			System.out.println("hii errrrrr");
		}
	}
}
	
