/**
 * 
 * @author sanjivkawa
 * Red Hat
 */

package edu.uci.ics.crawler4j.crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser 
{
    
    /** function: Document input(File).
     * 
     * This function will attempt to parse the HTML file that is received as a parameter input.
     * 
     * @param inputFile
     * @return htmlFile a parsed version of the current HTML file.
     */
    public Document parseHTML(File inputFile)
    {
        
        Document htmlFile = null;
        
        try
        {
            htmlFile = Jsoup.parse(inputFile, "UTF-8"); //htmlFile opens and parses the current html file specified by the inputFile parameter variable.
        }
        catch(IOException e)
        {
            Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(StringIndexOutOfBoundsException e)
        {
            Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return htmlFile; //returns the parsed html file
    }
    
    /** function: ArrayList processHTML(Document).
     * 
     * The purpose of this function is to locate the areas within the body section of the HTML file that contain text based paragraphs.
     * 
     * The function receives the parsed HTML file from the parseHTML function. 
     * 
     * Once the paragraphs are found within the parsed HTML file, they are converted from HTML format to plaintext.
     * 
     * An arrayList will hold the title of the HTML page, as well as the individual paragraphs in separate indexes.
     * 
     * @param htmlFile
     * @return HTMLPlainText an arrayList that contains the plaintext format of the HTML page's title as well as the individual paragraphs in separate indexes
     */
    public ArrayList processHTML(Document htmlFile)
    {
        Elements body = htmlFile.select("body"); //this selects the body section of the current html file
        
        ArrayList HTMLPlainText = new ArrayList(); //this array list will hold plaintext format of the HTML page's title and paragraphs in seperate indexes.

        HTMLPlainText.add(htmlFile.title()); //this adds the title of the html page into the first index of the array list
        
        for (Iterator<Element> it = body.iterator(); it.hasNext();) //this creates an Element iterator that will store the HTML of the body section 
        {
            Element pBody = it.next(); //this will access the next item in the iterator
            
            Elements pTag = pBody.getElementsByTag("p").parents(); /*this will acess the iterator and find the <p> tags within it. Once the <p> are 
                                                                    found, the parent of the <p> which will be a <div> is stored as a whole within pTag.*/
            
            for(int pTagCount = 0; pTagCount < pTag.size(); pTagCount++)
            {
                Element p = pTag.get(pTagCount); //p holds the current <div> that contains a <p>
                String plainText = p.text(); //plainText strips all of the HTML within the <div><p> so that the stored value is a plaintext string
                
                if (plainText.length() != 0) //some <p> will be empty, if they are there is no need to store them in the ArrayList
                {
                    HTMLPlainText.add(plainText); /*if the <p> is not empty, store it into the arrayList, 
                                                    index 0 will always be the title of the page
                                                    index 1 will always be the original post/first <p> tag
                                                    index 2-x will always be the reply posts/second <p> tag*/
                    pTagCount++; //increment the pTagCount so that the next <p> can be processed
                }
                
                pTag.parents().empty(); //delete the <p> from the iterator so that the next <p> that is selected for processing is the actual next <p>
            }
        }
        
        return HTMLPlainText; //return the ArrayList
    }
    
    /** function: File writeToJSON(ArrayList, File).
     * 
     * The purpose of this function is to receive the ArrayList from processHTML and output the contents of it to a JSON file.
     * 
     * The JSON file that is outputted will match the file name of the HTML file that has been processed.
     * 
     * Before the ArrayList can be outputted to a JSON format, the filename must be converted from .html to .json.
     * 
     * @param HTMLPlainText a populated ArrayList containing the pages title, original post and reply posts.
     * @param inputfile the filename of the html file.
     */
    public File writeToJSON(ArrayList HTMLPlainText, File inputfile)
    {
        JSONObject obj = new JSONObject(); //create JSON object
        
        String fileName = inputfile.getName(); //receive the inputFile name, which will be x.html
        
        //begin converstion from .html to .json
        
        File oldFile = new File(fileName); 
        int dotPos = fileName.lastIndexOf("."); //hits the "." in the filename extension
        String strExtension = fileName.substring(dotPos + 1); //shifts the position to the "h" in .html and stores the extension
        String strFilename = fileName.substring(0, dotPos); //inserts the filename into strFilename, removing the html
        String newfileExtension = "json"; 
        String strNewFileName = strFilename + "." + newfileExtension; //creates the new filename which is the same as the old filename + .json
        File newFile = new File(strNewFileName); 
        oldFile.renameTo(newFile); //commits changes to rename file from old filename to new filename (from .html to .json)
        
        //end conversion from .html to .json
        
        try
        {
            for(int i=0; i<HTMLPlainText.size(); i++) 
            {
                obj.put(i,HTMLPlainText.get(i)); //inserts the index and elements of the processed ArrayList into the JSONObject. 
            }
        }
        catch(StringIndexOutOfBoundsException e)
        {
            Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, e);
        }    

        try
        {   
            String directoryPath = "communityCrawlerStorage/JSON/"; //the directory that the .json files will be outputted to.
            
            File check = new File(directoryPath+newFile);
            
            if(!check.exists()) //checks to see if the file currently exists, if it does not, then create it.
            {
                FileWriter file = new FileWriter(directoryPath+newFile); /*note the json directory must be created, or previously exist. 
                                                                 This will create the new .json file that has the same name as the current .html file*/
            
                file.write(obj.toJSONString()); //write the contents of the JSONObject out to the .json file                     
                file.flush(); //clear streams
                file.close(); //close .json file
            }
        }
        catch(IOException e)
        {
            Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, e);
        }
                
        return newFile;
    }
}