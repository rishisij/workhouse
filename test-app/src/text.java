import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class text {
	public static void main(String[] args) {
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("C:/Users/rishi.sijariya/Documents/Downloads/locations.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int cnt=0;
			int p=0;
			int n=0;
			int m=0;
			HashSet <String> newset = new HashSet <String>();
			PrintWriter writer = new PrintWriter("C:/Users/rishi.sijariya/Documents/Downloads/ram.txt");
			try {
				while ((strLine = br.readLine()) != null)   {
					String reg="\\{.*?http://(.*?)/.*?\\}";
					Pattern pat1 = Pattern.compile(reg);
					Matcher mat1=pat1.matcher(strLine);
					while(mat1.find())
					{
						newset.add(mat1.group(1));
						cnt++;
						
					}
				}
				System.out.println(newset.size());
				System.out.println(cnt);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
