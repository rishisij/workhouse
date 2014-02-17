import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

public class SolrjPopulator {
  public static void main(String[] args) throws IOException, SolrServerException {
    HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr");
   System.out.println(server);
   SolrQuery query = new SolrQuery();
   System.out.println(query);
   query.setQuery("sony");
   System.out.println(query);
   query.addFilterQuery("cat:electronics");
   System.out.println(query); 
   query.setFields("id","price");
   query.setStart(0);
   System.out.println(query);
   QueryResponse response = server.query(query);
   System.out.println(response);
   
  }
}