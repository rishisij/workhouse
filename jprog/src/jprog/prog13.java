package jprog;

import java.util.LinkedList;

public class prog13 {

	public static void main(String[] args) {
		int c=0;
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.add(123);
		ll.add(124);
		ll.add(6789);
		ll.add(1324);
		ll.add(543);
		ll.add(654);
		ll.add(8765);
		System.out.println(ll);
		for(int i = 0;i<ll.size()/2+1;i++)
		{
			c=ll.get(i);
			ll.set(i, ll.get(ll.size()-i-1));
			ll.set(ll.size()-i-1, c);
		}
		System.out.println(ll);
	}

}
