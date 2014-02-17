package jprog;

public class prog1 {

	public static void main(String[] args) {
		String b = "";
		String[] a = b.split(" ");
		StringBuilder c =new StringBuilder();
		
		for(int i = a.length-1;i >= 0;i-- ){
			c =	c.append(a[i]+" ");
		}
		String d=c.toString().trim();
		if(d.equals(b))
		{
			System.out.println("Yes a palindrome");
		}
		else
		{
			System.out.println("Not a palindrome");
		}
		System.out.println(d+"    " +b);
	}

}
