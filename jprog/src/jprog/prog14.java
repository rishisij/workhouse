package jprog;

public class prog14 {

	public static void main(String[] args) {
		int[] a = {2,3,1,0,5,7,1,4};
		int root_node=a[0];
		int[] left_child = new int[100];
		int[] right_child = new int[100];
		int b=0;
		int c=0;
		for(int i =0;i<a.length;i++)
		{
			
			if(a[i]<=root_node)
			{
				left_child[b]=a[i];
				b++;
			}
			else
			{
				right_child[c]=a[i];
				c++;
			}
		}
		if(b>c)
		{
			System.out.println(b);
		}
		else
		{
			System.out.println(c);
		}
	}

}
