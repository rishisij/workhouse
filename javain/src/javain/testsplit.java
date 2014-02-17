package javain;

public class testsplit {

	public static void main(String[] args) {

		String a="abs.nms.hgj.aja";
		String[] b=a.split("\\.");
		String[] c =new String[b.length];
		c[0]=b[0];
		for(int i=0;i<b.length-1;i++)
		{
			c[i+1]=c[i]+"."+b[i+1]; 
		}
		
	}

}
