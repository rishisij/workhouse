package jprog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class prog7 {

	public static void main(String[] args) {
		Integer[] a = {1,2,3,4,5,6,6,7,8,9,10,11,12,13,13,14,15};
		int i=1;
		Map<Integer,Integer> st = new HashMap<Integer,Integer>();
		HashSet<Integer> hs=new HashSet();
	    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(a));
		Iterator it = list.iterator();
		while(it.hasNext())
		{		
			Integer b=(Integer) it.next();
			if(st.containsKey(b))
			{
				i++;
			  st.put(b,(Integer) i);
			  //System.out.println(st);
			  i=1;
			}
			else
			{
				st.put(b,(Integer) i);
				//  System.out.println(st);
			}
			 for (Entry<Integer, Integer> entry : st.entrySet()) {
		            if (entry.getValue()==2) {
		                hs.add(entry.getKey());
		            }
			 }
		}
		System.out.println(hs);	
	}
}

		
