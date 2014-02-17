package jprog;

import java.util.ArrayList;
import java.util.Arrays;


public class prog5 {

	public static void main(String[] args) {
		Integer[] a = {1,2,3,4,5,6,7,8,9,10,11,13,14,15};
	    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(a));
		for(int i = 1;i<=a.length;i++ )
		{	
			int index = list.indexOf(i);
		    if(index==-1)
		    {
		    	System.out.println(i);
		    }
		}
	}
}
