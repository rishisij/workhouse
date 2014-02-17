package Topcoder;

public class test {

	public static void main(String[] args) {

		test tt = new test();
		System.out.println(tt.fact(10,3));
		
	}
	float fact(float f,float c){
		if(c==1){
			return f;
		}
		float x = 0;
		float s = 0;
		float p = 0;
		float t = f-c+1;
		float e=f-c+1;
		for(float k=c-2;k>0;k--){
			
			for(float j=t;j>0;j--){
				e=j;
				for(float i = e;i>0;i--){
					x=x+i;
				}
				System.out.println(x);
				s=s+x;
				x=0;
			}
			System.out.println(s);
			t--;
			p=p+s;
			s=0;
		}
		return p;
	}
}
