package jprog;

public class prog23 {

	public static void main(String[] args) {
		int a=21;
		int b=0;
		int c=1;
		int d=0;
		System.out.print(b+",");
		for(int i = 1;i < a;i++)
		{
			System.out.print(c+",");
			d=c;
			c=b+c;
			b=d;
		}
	}

}
