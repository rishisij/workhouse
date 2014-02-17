import java.io.*;

public class BRRead {
   public static void main(String args[]) throws IOException
   {
      String str;
      // Create a BufferedReader using System.in
      BufferedReader br = new BufferedReader(new 
                         InputStreamReader(System.in));
      System.out.println("Enter characters, 'quit' to quit.");
      // read characters
      do {
         str = br.readLine();
         System.out.println(str);
      } while(!str.equals("quit"));
    		  
   }
}