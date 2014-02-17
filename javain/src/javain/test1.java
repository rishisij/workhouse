package javain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class test1 {

	public static void main(String[] args) {
		 String formatString = "MM/dd/yyyy";  // the SimpleDateFormat API will help you here
	      SimpleDateFormat sdf = new SimpleDateFormat(formatString);
	      
	      String dateString = "6/12/1337";
	      try {
	         Date date;
			try {
				date = sdf.parse(dateString);
				System.out.println(date);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
	         
	      } catch (ParseException e) {
	         e.printStackTrace();
	      }
	}

}
