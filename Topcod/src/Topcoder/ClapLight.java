package Topcoder;

public class ClapLight {
	static int i=0;
	public static void main(String[] args){
		int[] bg = {1000, 1000, 1000, 1000};

		ClapLight cl = new ClapLight();
		System.out.println(cl.threshold(bg));
		}
	
	public int threshold(int[] bg){
		int count = 0;
		
		if(bg.length < 4 || bg.length > 50)
			return 0;
		reu:
		for( i=1;i<=1001;i++){
			count=0;
			for(int j=0;j<bg.length;j++){
				if(i>bg[j])
					count++;
			}
			if(count>bg.length/2){
				for(int k=0; k < bg.length-3;k++){
					if(i > bg[k] && i <= bg[k+1] && i <= bg[k+2] && i > bg[k+3])
						continue reu;
					if(k==bg.length-4)
						System.out.println("");
						//return ;
				}
			}
		}
		return i;
	}
}
