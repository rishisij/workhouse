package edu.uci.ics.crawler4j.crawler;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.*;

public class TikaExample {
  public ConcurrentUpdateSolrServer server;
  public long start = System.currentTimeMillis();
  public AutoDetectParser autoParser;
  public int totalTika = 0;
  //public int totalSql = 0;

  public Collection docs = new ArrayList();
  

  public TikaExample(int i) {
    try {
      TikaExample idxer = new TikaExample("http://localhost:8983/solr");
      if(i==1){
    	  JFileChooser fc = new JFileChooser();        
  			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  			fc.showOpenDialog(null);
  			File file = fc.getSelectedFile();
  			idxer.doTikaDocuments(file);
      	}
      if(i==2){
    	  idxer.doSqlDocuments();
      }
      idxer.endIndexing();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public TikaExample(String url) throws IOException, SolrServerException {

    server = new ConcurrentUpdateSolrServer(url, 100, 4);
    System.out.println("gkk");
    server.setRequestWriter(new BinaryRequestWriter());
    server.setParser(new XMLResponseParser()); 
    autoParser = new AutoDetectParser();
  }

   
  public void endIndexing() throws IOException, SolrServerException {
    if (docs.size() > 0) { 
    	server.add(docs, 3000);    }
    	server.commit();
    	long endTime = System.currentTimeMillis();
    	log("Total Time Taken: " + (endTime - start) +
    			" milliseconds to index " + totalTika + " documents");
  	}

  public static void log(String msg) {
    System.out.println(msg);
  }
  public void doTikaDocuments(File root) throws IOException, SolrServerException {


    if(root.listFiles()!=null){
	 for (File file : root.listFiles()) {
      if (file.isDirectory()) {
        doTikaDocuments(file);
        continue;
      }
       	extract(file);
	 }
    }
    else
    {
    	extract(root);
    }
  }
  public void dumpMetadata(String fileName, Metadata metadata) {
    log("Dumping metadata for file: " + fileName);
    for (String name : metadata.names()) {
      log(name + ":" + metadata.get(name));
    }
    log("=============================================");
  }


  public void doSqlDocuments() throws SQLException {
    Connection con = null;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();

      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sap", "root", "root");

      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery("select * from html");

      while (rs.next()) {
        
        SolrInputDocument doc = new SolrInputDocument(); 
        String id = rs.getString("id");
        String title = rs.getString("name");
        String text = rs.getString("html");

        doc.addField("ido", id);
        doc.addField("Authoro", title);
        doc.addField("texto", text);

        docs.add(doc);
        if (docs.size() >= 1000) {
            UpdateResponse resp = server.add(docs, 3000);
            if (resp.getStatus() != 0) {
            	log("Some horrible error has occurred, status is: " +
            			resp.getStatus());
            }
            docs.clear();
          }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }
  public void extract(File rome) throws IOException, SolrServerException
  {
	File file = rome;
	  ContentHandler textHandler = new BodyContentHandler(10000);
      Metadata metadata = new Metadata();
      ParseContext context = new ParseContext();

      InputStream input = new FileInputStream(file);
      try {
        autoParser.parse(input, textHandler, metadata, context);
      } catch (Exception e) {

        log(String.format("File %s failed", file.getCanonicalPath()));
        e.printStackTrace();
        //continue;
      }
      
      dumpMetadata(file.getCanonicalPath(), metadata);
      SolrInputDocument doc = new SolrInputDocument();
      doc.addField("ido", file.getCanonicalPath());
      String author = metadata.get("Author");

      if (author != null) {
        doc.addField("Authoro", author);
      }

      doc.addField("texto", textHandler.toString());

      docs.add(doc);
      ++totalTika;
      if (docs.size() >= 1000) {

        UpdateResponse resp = server.add(docs, 300000);
        if (resp.getStatus() != 0) {
          log("Some horrible error has occurred, status is: " +
                  resp.getStatus());
        }
        docs.clear();
      }
      
  }
}