package jprog;

public class prog2 {
	String b = "";//Here give a String
	String d = "";//Here Which you Dont Want to come in a String
	String[] e = d.split(" ");
	public static void main(String[] args) {
		prog2 p = new prog2();
		for(int i = 0;i < p.e.length;i++ )
		{
			if(p.b.contains(p.e[i]))
			{
				p.b=p.b.replace(p.e[i],"");
			}
		}
		String f= p.b.trim();
		f=f.replaceAll("[ |\\s]+", " ");
		System.out.println(f);
	}

}
