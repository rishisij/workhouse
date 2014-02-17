import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.solr.common.SolrDocument;


public class cts {

	public static void main(String[] args) {
		/*if("2006 ASHRAE Handbook - Refrigeration (I-P Edition)".toLowerCase().contains("Refrigeration".toLowerCase()))
			System.out.println("hi");
		else
			System.out.println("test");
	}*/
		int i=0;
		  FileInputStream fstream;
		  String strLine;
		  String[] st;
		  Hashtable ht = new Hashtable();
		  try {
				fstream = new FileInputStream("/home/gateway/Documents/191_data/new_3dsdd.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				while ((strLine = br.readLine()) != null)   {
					st = strLine.split("@@");	
					ht.put(st[0],st[1]);
						
					//System.out.println(ht+"   " + st[0]+"  "+ st[1]);
				}
				Set<String> keys = ht.keySet();
				for(String key: keys){
					System.out.println("Value of "+key+" is: "+ht.get(key));
				}
				
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	}
}
