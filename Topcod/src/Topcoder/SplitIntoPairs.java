package Topcoder;

import java.util.ArrayList;
import java.util.Arrays;


public class SplitIntoPairs {


	
	
	public int makepairs(int[] A, int X)  {
		
		ArrayList al=new ArrayList();
		ArrayList ali=new ArrayList();
		int count=0;
		int p=0;
		Arrays.sort(A);
		for(int i:A)
		{
			if(i<0){
				count++;
			}
			if(i>=X && i<0)
			{
				al.add(i);
			}
			if(i>=0){
				ali.add(i);
			}
		}
		if(count%2==0)
		{
			return (A.length/2);
		}
		else if(count%2==1)
		{
			for(int o=0;o<al.size();o++)
			{
				for(int op=0;op<ali.size();op++)
				{
					int sub=(int) al.get(o);
					int ord=(int) ali.get(op);
					int cal=sub*ord;
					if(cal >=X)
					{
						return (A.length/2);
					}
				}
			}
		}
		return (A.length/2)-1;
	}
	
	
	
	public static void main(String[] args) {
		int[] A={1000000,1000000};
		SplitIntoPairs sp = new SplitIntoPairs();
		System.out.println(sp.makepairs(A,-5));
	}

}
/*Class:
SplitIntoPairs
Method:
makepairs
Parameters:
int[], int
Returns:
int
Method signature:
int makepairs(int[] A, int X)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Constraints
-
A will contain between 2 and 50 elements, inclusive.
-
The number of elements in A will be even.
-
Each element in A will be between -1,000,000,000 and 1,000,000,000, inclusive.
-
X will be between -1,000,000,000 and -1, inclusive.*/
