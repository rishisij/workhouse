import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
 
public class Main {
    public static void main(String[] args)  {
        try {
            URL my_url = new URL("http://viralpatel.net/blogs/category/java/");
            BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));
            String strTemp = "";
            while(null != (strTemp = br.readLine())){
            System.out.println(strTemp);
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}