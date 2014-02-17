package Topcoder;

import java.util.ArrayList;
import java.util.HashSet;

public class dasf {
/**/
	public static void main(String[] args) {
		int Count=0;
		String s="abcyhgasga";
		String su ="";
		String[] s1=s.split("");
		String[] s4= new String[s1.length-1];
		
		HashSet<String> s3 = new HashSet<String>();
		for(int i =1;i<s1.length;i++)
		{
			s4[i-1]=s1[i];
		}
		for(int j=0;j<s4.length;j++)
		{
			
			su=su.concat(s4[j]);
			
		}
		Count++;
		main(s4);
		System.out.println(su);
	}

}
