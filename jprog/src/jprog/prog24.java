package jprog;

public class prog24 {

	public static void main(String[] args) {
		
		int[] r1 = {0,0,3,3};
		int[] r2 = {3,1,4,3};
		if(r1[0]<=r2[0] && r1[2]>=r2[0] && r1[1]<=r2[1] && r1[3]>=r2[1])
		{
			System.out.println("Overlapped");
		}
		else
			System.out.println("Not OverLapped");
		
	}

}
