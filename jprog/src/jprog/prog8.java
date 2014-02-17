package jprog;

import java.util.ArrayList;
import java.util.Arrays;


public class prog8 {

	public static void main(String[] args) {
		Integer[] a = {1,2,3,4,5,6};
		Integer[] b = {2,3,1,0,5};
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(b));
		for(Integer i=0;i<a.length;i++)
		{
			if(!list.contains(a[i]))
			{
				System.out.println(a[i]);
			}
		}
	}

}
