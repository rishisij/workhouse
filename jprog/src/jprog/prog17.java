package jprog;

import java.util.ArrayList;
import java.util.Arrays;

public class prog17 {
	Integer[] ar = {4,24,45,531,5521,14515,3,42,323,4231,231,-1,-4,-43,0,54,201,214};
	ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(ar));
	ArrayList<Integer> right = new ArrayList<Integer>();
	ArrayList<Integer> left = new ArrayList<Integer>();
	ArrayList<Integer> lpivot = new ArrayList<Integer>();
	ArrayList<Integer> rpivot = new ArrayList<Integer>();
	ArrayList<Integer> al = new ArrayList<Integer>();
	Integer pivot = list.size()-1;
	public static void main(String[] args) {
		prog17 p = new prog17(); 
		int a=p.list.size();
		while(a!=0)
		{	
			p.al=	p.make(p.list);
			p.list.clear();
			p.list.addAll(p.al);
			p.al.clear();
			System.out.println(a);
		 	a--;
		}
	}
	public ArrayList<Integer> make(ArrayList<Integer> list)
	{	
		al.clear();
		if(!rpivot.isEmpty())
		{
			
			for(int i=lpivot.size()+1;i<lpivot.size()+rpivot.size()+1;i++)
			{
				if(list.get(i)>list.get(lpivot.size()+rpivot.size()+1))
				{
					right.add(list.get(i));
				}
				else if(list.get(i)<list.get(pivot))
				{
					left.add(list.get(i));
				}
				else
				{
					pivot=pivot-1;
				}
			}
			if(!left.isEmpty()) al.addAll(left);
			al.add(list.get(pivot));
			if(!right.isEmpty())al.addAll(right);
			rpivot.add(right.size()) ;
			lpivot.add(left.size());
			left.clear();
			right.clear();
			list.clear();
			return al;					
		}
		if(!lpivot.isEmpty())
		{
			
			for(int i=1;i<list.size()-1;i++)
			{
				if(list.get(i)>list.get(pivot))
				{
					right.add(list.get(i));
				}
				else if(list.get(i)<list.get(pivot))
				{
					left.add(list.get(i));
				}
				else
				{
					pivot=pivot-1;
				}
			}
			if(!left.isEmpty()) al.addAll(left);
			al.add(list.get(pivot));
			if(!right.isEmpty())al.addAll(right);
			rpivot.add(right.size()) ;
			lpivot.add(left.size());
			left.clear();
			right.clear();
			list.clear();
			return al;					
		}
		for(int i=0;i<list.size()-1;i++)
		{
			if(list.get(i)>=list.get(pivot))
			{
				right.add(list.get(i));
			}
			else
			{
				left.add(list.get(i));
			}
		}
		if(!left.isEmpty()) al.addAll(left);
		al.add(list.get(pivot));
		if(!right.isEmpty())al.addAll(right);
		rpivot.add(right.size()) ;
		lpivot.add(left.size());
		left.clear();
		right.clear();
		list.clear();
		return al;
	}
	
}