import java.io.*;
//import com.hp.hpl.jena.rdf.model.*;
//import com.hp.hpl.jena.vocabulary.*;

public class FirstRDFReader extends Object {
  public static void main (String args[]) {
	  System.out.println("hi");
  String inputFile="amit.xml";
  //Model model = ModelFactory.createDefaultModel();
 try{
	 System.out.println("hi1");
	 try{
 InputStream in =new  FileInputStream(inputFile);
 System.out.println("hi2");
 System.out.println(in);
  if (in == null) {  
  System.out.println("File not found");
 }  
  System.out.println("hi1");
//  model.read(in," ");
  //model.write(System.out);
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 
 
 }catch(Exception e){}
  }
}
