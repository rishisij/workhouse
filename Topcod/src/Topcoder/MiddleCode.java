package Topcoder;

import java.util.ArrayList;

public class MiddleCode {
	String t="";
	public String encode(String s)
	{
		ArrayList ash = new ArrayList();
		String[] dash;
		dash=s.split("");
		for(int i=0;i<dash.length-1;i++)
		{
			ash.add(dash[i+1]);
		}
		
		method(ash);
		return t;
	}
	public void method(ArrayList ash)
	{ 
		String a="";
		String b="";
		 if(ash.size()==1)
		{
			t=t+ash.get(0);
			
		}
		else if(ash.size()==2)
		{
			a= (String) ash.get(0);
			b= (String) ash.get(1);
			
			int i = a.charAt(0);
			int j = b.charAt(0);
			if(i>=j)
			{
				t=t+b;
				ash.remove(1);
				method(ash);
				
			}
			else if(i<j){
				t=t+a;
				ash.remove(0);
				method(ash);
			}
		}
		else if(ash.size()%2==1)
		{
			a= (String) ash.get((ash.size()/2));
			t=t+a;
			ash.remove((ash.size()/2));
			method(ash);
		}
		else if(ash.size()%2==0){
			a= (String) ash.get(ash.size()/2);
			b= (String) ash.get((ash.size()/2)-1);
			int i = a.charAt(0);
			int j = b.charAt(0);
			if(i>=j){
				t=t+b;
				ash.remove((ash.size()/2)-1);
				method(ash);
				
			}
			else if(i<j){
				t=t+a;
				ash.remove((ash.size()/2));
				method(ash);
			}
		}
	}
}
