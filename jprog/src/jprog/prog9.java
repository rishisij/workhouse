package jprog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class prog9 {

	public static void main(String[] args) {
		Integer[] a = {3,25,2,4,25,6,6,42,6,63};
		TreeSet<Integer> hs=new TreeSet<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(a));
		Iterator<Integer> it =list.iterator();
		while(it.hasNext())
		{
			hs.add((Integer)it.next());
		}
		int b=hs.size()-2;
		Object[] c=hs.toArray();
		System.out.println(c[b]);
	}

}
