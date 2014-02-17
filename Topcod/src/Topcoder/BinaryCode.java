package Topcoder;
public class BinaryCode
	{
		public String[]	decode(String arg)
		{	
			String[] k ={"",""};
			if(arg.equals(null)){
				
				 k[1]="NONE";
			 	 k[0]="NONE";
			 	return k;
			}
			String  b[] = arg.split("");
			if(b.length==2 ){
				
				 if(b.equals(1) || b.equals(0))
				 	{
						 k[1] = b[0];
						 k[0] = b[0];
				 	}
					else
					 {
						 k[1]="NONE";
					 	 k[0]="NONE";
				     }		
				return k;
			}
			int[] d = new int[b.length];
			for(int i=1;i<b.length;i++)
			{
				d[i-1]=Integer.parseInt(b[i]);
			}
			int[] m=prev(d,0);
			int[] n=prev(d,1);
			for(int i=0;i<b.length-1;i++){
				if(m[i]==1 || m[i]==0)
				{
					 k[0] = k[0]+m[i];
			 	}
			 	else
			 	{
					 k[0]="NONE";
				 	 break;
			 	}		
			}
			for(int i=0;i<b.length-1;i++){
				 if(n[i]==1 || n[i]==0)
			 	{
					 k[1] = k[1]+n[i];
			 	}
				 else
				 {
					 k[1]="NONE";
				 	 break;
			     }		
			}
		
		return k;
		}
	 int[] prev(int[] d,int l)
		{
			int p[] = new int[d.length-1];
			p[0]=l;
			p[1]=d[0]-p[0];
			for(int i=1;i<d.length-3;i++)
			{
				p[i+1]=d[i]-p[i]-p[i-1];
			}
			p[d.length-2]=d[d.length-2]-p[d.length-3];
			return p;
		}	
	 
	}