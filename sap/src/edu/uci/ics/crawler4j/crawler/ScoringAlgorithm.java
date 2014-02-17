/**
 * 
 * @author sanjivkawa
 * Red Hat
 */

package edu.uci.ics.crawler4j.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ScoringAlgorithm 
{

    final static String SECURITYWORDS = "securityWords.properties"; //the security keyword list
    
    final static double SECURITYSCORE = 0.25; //sets the match percentage at 20%
    
    /** function: JSONObject parseJSON(File).
     *
     * This function will attempt to parse the JSON file that is received as a parameter input.
     * 
     * @param inputFile
     * @return jsonObject a parsed version of the JSON input file
     */
    public JSONObject parseJSON(File inputFile)
    {
        
        JSONParser parser = new JSONParser(); //new instance of the JSON file parser
        
        JSONObject jsonObject = new JSONObject();
       
        try
        {
            Object obj = parser.parse(new FileReader("communityCrawlerStorage/JSON/"+inputFile)); //the JSON file is read in and parsed, from there it is placed into an object
        
            jsonObject = (JSONObject) obj; //the object is casted into a JSON object and this is returned
            
        }
        catch(FileNotFoundException e)
        {
             Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(IOException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(ParseException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return jsonObject;
    }
    
    /** function HashMap iterateSecurityWords(JSONObject).
     *
     * This function will open the securityWords properties file and pass the current security keyword into the
     * processJSON function.
     * 
     * The procesJSON function will then return the frequency count of the current security word.
     * 
     * Both the security word and the occurrence will be stored into a hash map.
     * 
     * @param jsonObject
     * @return keywordFrequency - the occurrence of all security related words in the current JSON file
     */
    public HashMap iterateSecurityWords(JSONObject jsonObject)
    {
        Properties securityWords = new Properties(); 
        
        try
        {
            FileInputStream propInputStream = new FileInputStream(SECURITYWORDS);
            securityWords.load(propInputStream); //load the properties file, it can now be read from.
        }
        catch(FileNotFoundException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(IOException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        
        HashMap<String, Integer> keywordFrequency = new HashMap<String, Integer>();
        
        for(Integer i=0; i<securityWords.size(); i++) //the for loop will increment until all security words have been exhausted
        {
            
            String securityWord = securityWords.getProperty("word"+i).toLowerCase(); /* this will read the security words one by one from the  properties file
                                                                                 it will then convert the current word to lowecase and store it as a String*/
 
            Integer freqCount = processJSON(jsonObject,securityWord); //the parsed JSON object and current security keyword will be passed into processJSON
             
            keywordFrequency.put(securityWord,freqCount); // place the current security word as well as the occurrence into a hash map
          
        }
        
        return keywordFrequency;
    }
    
    /** function: Integer processJSON(JSONObject, String).
     * 
     * The purpose of this function is to compare the current security keyword against the current JSON file.
     * A frequency count will be generated from this.
     *
     * This function utilizes the StringUtils function from the apache common lang3 package.
     *
     * @param jsonObject - a parsed version of the current JSON file
     * @param securityWord - the current security word loaded in from the properties file
     * 
     * @return freqCount - the occurrence of the given security keyword.
     */
    public Integer processJSON(JSONObject jsonObject, String securityWord)
    {                         
        int freqCount = 0;
        
        for(Integer i=0; i<jsonObject.size(); i++) //the for loop will iterate throughout the current JSON file
        {
            String jsonLine = (String) jsonObject.get(i.toString()); //each index in the object array will be casted to a string
                
            String patternString = "\\b(" + StringUtils.join(securityWord, "") + ")\\b"; //the security word is brought in and set as the search pattern
                
            Pattern pattern = Pattern.compile(patternString); //the securityWord pattern is complied

            Matcher matcher = pattern.matcher(jsonLine.toLowerCase()); /*The current index in the object is converted to lowercase and fed into the matcher.
                                                                         The security keyword to look for is also fed in here*/
           
            while (matcher.find()) //if the security keyword is found in the current index of the object, the frequency increases.
            {
                freqCount++; //update the frequency count
            }      
        }
        
        return freqCount;
    }
    
    /** function: boolean scoreJSON(HashMap).
     *
     * This function will iterate through the provided HashMap and pick out the keywords with correlating frequencies.
     * It will then provide a score of the JSON file and return a boolean that suggests whether or not the JSON file is
     * a valid security issue.
     * 
     * 
     * @param keywordFrequency
     * @return validSecurity - a T/F boolean that indicates whether or not the JSON file is a security issue.
     */
    public boolean scoreJSON(HashMap keywordFrequency)
    {
        
        double score = 0; //this will be the total score of the current JSON file
        double doubleScore = 0; //this will be used to double the score, based on the occurrence of the keyword
        double validKeys = 0; //this is used to identify which keywords in the properties file have been used
        double hashSize = keywordFrequency.size(); //this holds the total size of the keywords within the properties file
        
        Iterator it = keywordFrequency.keySet().iterator();
        
        while(it.hasNext()) //iterate throught the hashmap in order to determine the keyword and frequency count
        {
            String key = it.next().toString(); //keyword from the properties file that is stored in the hashmap
            Integer value = (Integer)keywordFrequency.get(key); //frequency of correlating kewyword
            
            if(value!=0) //if the frequency count is not 0, that means that the keyword has matches
            {
                validKeys++; //a total of how many keywords have a frequency above 0
            }
            
            if (value > 2) //if the frequcny of a keyword is larger than 2
            {
                doubleScore += value; //store the value of these frequencies
                validKeys++; //update the valid key count
            }
            else
            {
                score += value; //if the frequency is <=1 store it into score
            }
  
            score += value;
        }
               
        doubleScore = doubleScore * 2; //if the frequency for one keyword appears >1 time, double it
        
        score = doubleScore + score; //the score combines the frequency for keyword occurence >=1
        
        double JSONScore = score/(validKeys*hashSize); //the total frequency divided by the amount of vaild keywords multiplied by all the keywords
        
        if(JSONScore > 1) //surpases 100% match
        {
            JSONScore = 1; //set it to 100%
        }
         
        boolean validSecurity; 
       
        if((JSONScore) > SECURITYSCORE) //if the score of the JSON file is > than what is specificied
        {
            validSecurity = true; //the JSON file is a security issue
        }
        else
        {
            validSecurity = false; //the JSON file is not a security issue
        }
        
        return validSecurity; 
    }
    
    /** function void writeSecurityIssue(Boolean, File, HashMap).
     *
     * The purpose of this function is to write out the valid security issue to a db file.
     * 
     * The file will be appended with each valid JSON file.
     * 
     * The contents of the file will contain the JSON file name, the keyword to frequency map and the matching percentile.
     * 
     * This function will only process if the validSecurity boolean is true.
     * 
     * @param validSecurity
     * @param inputFile
     * @param keywordFrequency
     */
    public void writeSecurityIssue(boolean validSecurity, File inputFile, HashMap keywordFrequency, String pageURL)
    {
        if(validSecurity == true)
        {
            
            
          try      
          {      
                Connection c = null;
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:HTMLandJSON.db","",""); //creates HTMLandJSON.db flat file
                c.setAutoCommit(false);
                 
                String fileName = inputFile.getName(); //gathers the JSON file name
                
                String id = fileName.substring(0, fileName.lastIndexOf('.')); //the ID is the file number less the extension
                
                
                Statement st = c.createStatement();
                
                st.executeUpdate("create table if not exists Frequency_Table(ID, Keyword, Frequency);"); //creates frequency table
                
                st.executeUpdate("create table if not exists Score_Table(ID, Score, JSON_File);"); //creates store table
                
                st.executeUpdate("create table if not exists URL_Score_Table(ID, HTML_URL, Score);"); //creates url_score table
                
                
                
                double score = 0;
                double doubleScore = 0;
                double validKeys = 0;
                double hashSize = keywordFrequency.size();
        
                Iterator it = keywordFrequency.keySet().iterator();
        
                while(it.hasNext()) //identical to while loop located in scoreJSON function
                {
                    String key = it.next().toString(); //gathers the security keyword
                    Integer value = (Integer)keywordFrequency.get(key); //gathers the correlating frequency count
                    
                    if(value > 0)
                    {
                        st.executeUpdate("insert into Frequency_Table (ID, Keyword, Frequency) values ('"+Integer.parseInt(id)+"','"+key+"','"+value+"');"); //adds in each value
                    }
                    
                    if(value!=0)
                    {
                        validKeys++; 
                    }
            
                    if (value > 2)
                    {
                        doubleScore += value;
                        validKeys++;
                    }
                    else
                    {
                        score += value;
                    }
  
                    score += value;
                }
               
                doubleScore = doubleScore * 2;
        
                score = doubleScore + score;
        
                double JSONScore = score/(validKeys*hashSize)*100;
        
                if(JSONScore > 100)
                {
                    JSONScore = 100.00;
                }
        
                st.executeUpdate("insert into Score_Table (ID, Score, JSON_File) values ('"+Integer.parseInt(id)+"', '"+JSONScore+"' ,'"+fileName+"');"); //adds in each value
                
                st.executeUpdate("insert into URL_Score_Table (ID, HTML_URL, Score) values ('"+Integer.parseInt(id)+"', '"+pageURL+"' ,'"+JSONScore+"');"); //adds in each value
                
                st.close();
                c.commit();
                c.close();
                
        }
        catch(SQLException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(ClassNotFoundException e)
        {
            Logger.getLogger(ScoringAlgorithm.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    }
}
