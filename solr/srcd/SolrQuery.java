import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;

public class SolrQuery {
	
  public static void main(String[] args) throws MalformedURLException, SolrServerException {
	  
	  String[][] s = new String[1000][50000] ;
	  int i=0;
	  int j=1;
	  int k=0;
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
			}
	  }
	  catch(Exception e)
	  {
		  System.out.println("File Not Found");
	  }
	  SolrServer server = new HttpSolrServer("http://qa-solr4-us.knewknovel.com/solr/");
        
	  ModifiableSolrParams params = new ModifiableSolrParams();
	  params.set("q", "*:*");
	  params.set("fl", "contentId,item_title");
	  params.set("start", 0);
	  params.set("rows", 1000);
		
		       
	  QueryResponse response = server.query(params);
	  SolrDocumentList documents = response.getResults();
	  Iterator<SolrDocument> itr = documents.iterator();
	  System.out.println("DOCUMENTS");
	  while(itr.hasNext())
	  {
	        SolrDocument doc = itr.next();
	        i++;
	    //    System.out.println(doc.getFieldValue("contentId"));
	        s[k][0]= doc.getFieldValue("contentId").toString();
	        Set<String> keys = ht.keySet();
			for(String key: keys){
				if(doc.getFieldValue("item_title").toString().toLowerCase().contains(ht.get(key).toString().toLowerCase()))
				{
					s[k][j]=key;
					j++;
				}
			}
			j=1;
			k++;
	  }
	  //System.out.println(i);
	//  for(int t=0;t<33;t++)
	  //System.out.println(s[0][t]);
		

  }
} 