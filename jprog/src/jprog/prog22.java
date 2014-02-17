package jprog;

import java.util.ArrayList;

public class prog22 {

	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		int x=1000000;
		
		for(int i = 2 ;i<=x;i++ )
		{  
			for(int j=2;j<i;j++)
			{
				if(i%j==0)
				{
						break;
				}
			}
		}
		System.out.println(al);
	}

}
