package edu.uci.ics.crawler4j.crawler;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

 
public class Config 
{
	Properties properties;

   public String getProperty(String filename, String key) throws IOException
   {  
	FileInputStream propInputStream = new FileInputStream(filename);
	this.properties.load(propInputStream);
	String value = this.properties.getProperty(key);		
	return value;
   }
   
   public Set<String> stringPropertynames(String key)
   {
	   Set<String> value = this.properties.stringPropertyNames();
	   return value;
   }
   
   public Enumeration<?> propertyNames()
   {
	   Enumeration<?> value = this.properties.propertyNames();
	   return value;
	   
   }


  
}
   
   
 