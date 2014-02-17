import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class pdfcorrup6t {
	public static AutoDetectParser autoParser;
	
	public static void main(String[] args) {
	
		try {  
		    PdfReader pdfReader = new PdfReader( "/home/gateway/Downloads/B_tree.pdf" );  
		    System.out.println("hi");
		    String textFromPdfFilePageOne = PdfTextExtractor.getTextFromPage( pdfReader, 2 ); 
		    System.out.println( textFromPdfFilePageOne );
		}  
		catch ( Exception e ) {  
			e.printStackTrace();
		}  
/*		ContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);
		Metadata metadata = new Metadata();
		new PDFParser().parse(input, handler, metadata, new ParseContext());
		String plainText = handler.toString();
		System.out.println(plainText);*/

	}

}
