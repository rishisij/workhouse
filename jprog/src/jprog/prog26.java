package jprog;

public class prog26 {

	public static void main(String[] args) {
		int min = 0;
		int max = 32;
		int Random;
		for(int i = 0;i < max;i++)
		{
			min=i;
			Random = (((max-min)*i)%max)*i%max;
			System.out.println(Random);		
		}

	}

}
