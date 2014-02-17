import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class Snippet {
	static Socket socket = null;
	static boolean reachable = false;
	public static void main(String args[])
	{
		try {
			socket = new Socket("www.google.com", 80);
			reachable = true;
			System.out.println("sdasds");
	    } catch (UnknownHostException e) {
			e.printStackTrace();}
		 catch (IOException e) {
			e.printStackTrace();}
	    finally 
	    {            
	    	if (socket != null)
	    	{
	    		try { 
	    			socket.close(); 
	    			} 
	    		catch(IOException e) 
	    		{
	    			System.out.println("hi");
	    		}
	    	} 	
	    }
	}
}