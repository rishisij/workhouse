package Tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class testt {
	public static void main(String[] args) {
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("C:/Users/rishi.sijariya/Documents/Downloads/tax.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			try {
				while ((strLine = br.readLine()) != null)   {
					 System.out.println (strLine);
					}
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
