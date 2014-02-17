package edu.uci.ics.crawler4j.crawler;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

 
public class ConfigProperties 
{

	public ConfigProperties (String fileName) throws IOException {
		
    	Properties myProps = new Properties();
    	FileInputStream propInputStream = new FileInputStream(fileName);
    	myProps.load(propInputStream); 
    	
	}


  
}
   
   
 