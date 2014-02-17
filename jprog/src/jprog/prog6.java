package jprog;

import java.util.ArrayList;
import java.util.Arrays;

public class prog6 {

	public static void main(String[] args) {

		Integer[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,13,14,15};
	    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(a));
		for(int i = 0;i<a.length-1;i++ )
		{	
			int index = list.indexOf(i);
		   // System.out.println(index);
		    a[i]=index;
		   // System.out.println(b[i]);
			
		}
		ArrayList<Integer> list1 = new ArrayList<Integer>(Arrays.asList(a));
		for(int i = 0;i<a.length;i++ )
		{	
			int index = list1.indexOf(i);
		    if(index==-1)
		    {
		    	System.out.println(i);
		    }
		}
		

	}

}
