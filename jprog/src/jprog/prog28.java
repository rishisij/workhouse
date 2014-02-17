package jprog;

public class prog28 {

	public static void main(String[] args) {
	String article="";
	String word = "";
	int count=0;
	String[] a = article.split(" ");
	if(a.length==0)return;
	for(int i=0;i<a.length;i++)
	{
		if(word.equals(a[i]))
			count++;
	}
	System.out.println("This "+word+" is appeared in the Article "+count+ " times");
	}

}
