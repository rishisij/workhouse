
public class hi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sess="989890";
		System.out.println("gigigi");
		String ai[]=sess.split("(?!^)");
		int ant=0;
		System.out.println("gigigidafdas");
		for(String bo:ai){
			System.out.println(bo);
			int i=Integer.parseInt(bo);
			i=i+ant;
			ant=i;
		}
		System.out.println(ant);

	}

}
