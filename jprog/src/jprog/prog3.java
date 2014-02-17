package jprog;

public class prog3 {
	String a = "a b c";
	String[] b=a.split(" ");

	public static void main(String[] args) {
		String g="";
		prog3 p = new prog3();
		StringBuilder sb = new StringBuilder();
		int c = p.b.length;
		int d=0;
				
		while(c!=0)
		{
			c=c-1;
			d+=c;
		}
		d=d+p.b.length-1;
		//int e=d;
		d=d*p.b.length;
		System.out.println(d);
		int f = p.b.length;
		int i=0;
		while(i!=f)
		{ 	
		
			
			
			
			System.out.println(p.b[i%f]);
			System.out.println(p.b[i%f]+" "+p.b[(i+1)%f]);
			System.out.println(p.b[i%f]+" "+p.b[(i+2)%f]);
			System.out.println(p.b[i%f]+" "+p.b[(i+1)%f]+" "+p.b[(i+2)%f]);
			System.out.println(p.b[i%f]+" "+p.b[(i+2)%f]+" "+p.b[(i+1)%f]);
			i++;
		}
		
	}
}
