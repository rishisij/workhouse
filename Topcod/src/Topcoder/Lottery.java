package Topcoder;


public class Lottery {
	public String[] sortByOdds(String[] rules){
		
		if(rules.length==0){
			return rules;
		}
		int i = 0;
		Object p;
		Object q;
		Object[][] c = new Object[2][rules.length];
		for(String temp:rules)
		{
			String[] temp1 = temp.split(":");
			String[] temp2 = temp1[1].split(" ");
			float a = Integer.parseInt(temp2[1]);
			float b = Integer.parseInt(temp2[2]);
			if(temp2[3].equals("F") && temp2[4].equals("F")){
				c[1][i]=(float)(1/(float) Math.pow(a, b));
				System.out.println((float) Math.pow(a, b));
				c[0][i]=(String) temp1[0];
				System.out.println(c[1][i]+">>"+c[0][i]);
				}
			else if(temp2[3].equals("T") && temp2[4].equals("F")){
				c[1][i] =(float) ((float) 1/((Math.pow(a, b)-(fact(a,b)))));
				c[0][i]=(String) temp1[0];
				System.out.println(((Math.pow(a, b)-(fact(a,b)))));
				System.out.println(c[1][i]+">>"+c[0][i]);
			}
			else if(temp2[3].equals("F") && temp2[4].equals("T")){
				c[1][i]=(float) ((float) 1/(Math.pow(a, b)-a));
				c[0][i]=(String) temp1[0];
				System.out.println(((Math.pow(a, b)-a)));
				System.out.println(c[1][i]+">>"+c[0][i]);
			}
			else if(temp2[3].equals("T") && temp2[4].equals("T")){
				c[1][i] =(float)(1/(Math.pow(a, b)-(fact(a,b)+a)));
				c[0][i]=(String) temp1[0];
				System.out.println((Math.pow(a, b)-(fact(a,b)+a)));
				System.out.println(c[1][i]+">>"+c[0][i]);
				}
			i++;
		}
		for(int k = 0;k<c[1].length;k++)
		{
			p = c[1][k];
			q = c[0][k];
			for(int m = 0;m<c[1].length;m++)
			{
				
				if((float) p>(float) c[1][m])
				{
					c[1][k]=c[1][m];
					c[1][m]=p;
					p=c[1][k];
					c[0][k]=c[0][m];
					c[0][m]=q;
					q=c[0][k];
							
				}
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(int m = 0;m<c[1].length;m++){
			System.out.println(c[1][m]+">>"+c[0][m]);
		}
		String[] o = new String[c[0].length];
		int bb=0;
		for(Object s:c[0]){
			o[bb]=(String) s;
			bb++;
		}
		return  o;
	}
	float fact(float f,float c){
		float x = 0;
		float s = 0;
		float e=f-c+1;
		for(float j=c-1;j>0;j--){
			for(float i = e ;i>0;i--){
				x=x+i;
			}
			e--;
			s=s+x;
			x=0;
		}
		return s;
	}
	public static void main(String[] args)
	{
		String[] d;
		String[] arg1 ={"INDIGO: 93 8 T F",
				 "ORANGE: 29 8 F T",
				 "VIOLET: 76 6 F F",
				 "BLUE: 100 8 T T",
				 "RED: 99 8 T T",
				 "GREEN: 78 6 F T",
				 "YELLOW: 75 6 F F"};
		Lottery lt = new Lottery();
		d=lt.sortByOdds(arg1);
		for(String s:d){
			//System.out.println(s);
		}
	}
}
