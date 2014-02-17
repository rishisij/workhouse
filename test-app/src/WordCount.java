import java.io.IOException;
import java.util.*;
 	
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;
 	
public class WordCount {
 	
   public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
		
		private Text word = new Text();
		
		public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) {
				String str1 = tokenizer.nextToken();
				word.set(str1);
				output.collect( new Text("max"), word);
			}
		}
   }
 	
   public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
 	     public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
 	      int size = 0;
 		  String str3 = "";
 		 String str2 = "";
 	       while (values.hasNext()) {
 	    	   str2 = values.next().toString();
 	
 	    	
 	         if( size < str2.length())
 	         {
 	     
 	        	 size = str2.length();
 	        	 str3 = str2;
 	        	 
 	         }
 	       }
 	      
 	       output.collect( new Text("max"), new Text(str3));
 	     }
 	   }
 	
   
    public static void main(String[] args) throws Exception {
 	     JobConf conf = new JobConf(WordCount.class);
 	     conf.setJobName("wordcount1");
 	
 	     conf.setOutputKeyClass(Text.class);
 	     conf.setOutputValueClass(Text.class);
 	
 	     conf.setMapperClass(Map.class);
 	     conf.setCombinerClass(Reduce.class);
 	     conf.setReducerClass(Reduce.class);
 	
 	     conf.setInputFormat(TextInputFormat.class);
 	     conf.setOutputFormat(TextOutputFormat.class);
 	
 	     FileInputFormat.setInputPaths(conf, new Path(args[0]));
 	     FileOutputFormat.setOutputPath(conf, new Path(args[1]));
 	
 	     JobClient.runJob(conf);
 	   }
}