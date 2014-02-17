
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;


public class TikaSample extends JPanel{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws Exception {
        // parse out the directory that we want to crawl
      /*  if (args.length != 1) {
            showUsageAndExit();
        }
 
        File directory = new File(args[0]);
        if (!directory.isDirectory()) {
            showUsageAndExit();
        }
     
		parseAllFilesInDirectory(directory);
		
    }
 
    private static void parseAllFilesInDirectory(File directory) throws Exception {*/
    	JFileChooser fc = new JFileChooser();        
  		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  		fc.showOpenDialog(null);
  		File file = fc.getSelectedFile();
    	/*for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                parseAllFilesInDirectory(file);
            } else {*/
		    	
		    	Parser parser = new   PDFParser();
                Metadata metadata = new Metadata();
                ParseContext parseContext = new ParseContext();
                //((PDFParser) parser).setSortByPosition( false );
                ContentHandler handler = new BodyContentHandler(-1);
                parser.parse(new FileInputStream(file), handler, metadata, parseContext);
               
                System.out.println("-------------------------------------------------------");
                
                System.out.println("File: " + file);
                for (String name : metadata.names()) {
                    System.out.println("metadata: " + name + " - " + metadata.get(name));
                }
                
               
              System.out.println("Content: " + handler.toString());
                
  
            }
        
    
 
    /*private static void showUsageAndExit() {
        System.err.println("Usage: java TikaSample <directory to crawl>");
        System.exit(1);
    }*/
}