package jprog;

public class prog18 {

	public static void main(String[] args) {
		int[] a={4,24,45};
		int root_node=a[0];
		int[] left_child = new int[2];
		int[] right_child = new int[2];
		int b=0;
		int c=0;
		for(int i =1;i<a.length;i++)
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
		System.out.println(left_child+" "+root_node+" "+right_child);
		

	}

}
