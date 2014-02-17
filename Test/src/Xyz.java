import java.io.*;
import java.net.URL;




public class Xyz {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	
		System.out.println("hiiiiiiiiiiiii");
		try {
			
		    URL url = new URL("http://i.istockimg.com/file_thumbview_approve/25586714/2/stock-photo-25586714-moving-into-a-new-home-and-eating-takaway.jpg");
		    
		
		//See javax.imageio package for more info. That's using the AWT image. Otherwise you could do:
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		{
		   out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();
		//And you may then want to save the image so do:

		FileOutputStream fos = new FileOutputStream("D://borrowed_image1.jpg");
		fos.write(response);
		fos.close();
		} catch (IOException e) {
		}

	}

}
