/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.ics.crawler4j.crawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;

/*This is for the HTML Parser and JSON scoring
* This class will bring in the most recently downloaded HTML file
* 
* It will then pass all HTML files into the input function for parsing.
* 
* Once the HTML files are parsed, they are passed into the HTMLProcessor function
* so that relevant plaintext can be extracted and stored into an ArrayList.
*       
* Finally the HTML Parser program outputs a JSON file that holds the contents of the arrayList from
* the HTMLProcessor function.
* 
* The ScoringALgorithm is first introduced when writeToJSON is called from the HTMLParser class.
* The writeToJSON function returns the recently parsed JSON file.
* The JSONFile is then parsed and matched against security keywords in order to identify a frequency.
* This is done within the iterateSecurityWords function.
* The JSON is then scored and determined whether or not it is a valid security issue.
* 
* A database is then created called HTMLandJSON.
* 
* There are 2 tables within the database.
* 
* The first is HTML_JSON_DB - this will hold the HTML file number and JSON file number for a specific URL
* The second is JSON_Frequency_DB - this will hold the JSON file number, keyword and frequency count > 0 as well as total score 
* 
*/
public class HTMLParserAndSecurityController 
{
    public void run(File inputFile, String pageURL) throws FileNotFoundException, IOException
    {
      
                HTMLParser htmlParser = new HTMLParser();
        
                ScoringAlgorithm sa = new ScoringAlgorithm();
                
                File jsonFile = null; 
                
                long zeroBytes = 0;
 
                if(inputFile.length() > zeroBytes) //perform processing only if the inputFile is greater than 0 Bytes.
                {
                    Document htmlFile = htmlParser.parseHTML(inputFile);
                    ArrayList plainTextArr = htmlParser.processHTML(htmlFile);
                    jsonFile = htmlParser.writeToJSON(plainTextArr, inputFile);
                    
                    JSONObject jsonObject = sa.parseJSON(jsonFile);
                    HashMap keywordFrequency = sa.iterateSecurityWords(jsonObject);
                    boolean validSecurity = sa.scoreJSON(keywordFrequency);
                    sa.writeSecurityIssue(validSecurity, jsonFile, keywordFrequency, pageURL);
                }
                
                
    }
            
}
