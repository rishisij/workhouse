package jprog;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.CancellationException;


class quick
{
	int x;
	ArrayList a = new ArrayList();
	quick right;
	quick left;
	public quick(int i,ArrayList a )
	{
		this.x=i;
		this.a=a;
	}
	public String toString()
	{
		return x + " has an array " + a;
	}
}
public class progquick {
	quick root;
	quick focusnode;
	quick parent;
	int f=0;
	int c=0;
	int d=0;
	int check=0;
	Stack sk = new Stack();

	public void qsort(int b,ArrayList al)
	{
		ArrayList left = new ArrayList();
		ArrayList right = new ArrayList();
		int z=al.size()-2;
		int p=z+1;
		int q=0;
		int r=0;
		int rc=0;
		parent=focusnode;
		while(z>=0)
		{
			if(al.get(p).hashCode() >  al.get(z).hashCode())
			{
				left.add(q,al.get(z));
				q++;
			}
			if(al.get(p).hashCode() <  al.get(z).hashCode())
			{
				right.add(r, al.get(z));
				r++;
			}
			if(al.get(p) ==  al.get(z))
			{
				System.out.println("I told You give us a Distinct number :( ! Ab sort Nahi Chalega Don't be So Smart Ox");
				throw new CancellationException();
			}
			z--;
		}
		if(al.size()==1)
		{   
			if(!sk.isEmpty())
				focusnode =(quick) sk.pop();
			while(focusnode.right==null)
			{
				if(!sk.isEmpty())
					focusnode=(quick) sk.pop();
				
			}
			if(focusnode==root)
    		{
				check=check+1;
    			if(check==2){
    				throw new CancellationException();
    			}
				sk.clear();
    			sk.push(root);
    			if(root.right!=null)
    			{
    				focusnode=focusnode.right;
    				rc=(int) focusnode.a.get(focusnode.a.size()-1);
    				qsort(rc,focusnode.a);
    			}
    			
    		}
			rc=(int) focusnode.right.a.get(focusnode.right.a.size()-1);
			focusnode=focusnode.right;
			qsort(rc,focusnode.a);
		}
		
		if(left.size()>=1 && right.size()>=1)
		{
			int d=(int) left.get(left.size()-1);
			quick node2 =new quick(d,left);
			focusnode.left=node2;
			int c=(int) right.get(right.size()-1);
			quick node1 =new quick(c,right);
			focusnode.right=node1;
			sk.push(focusnode);
			parent=focusnode;
			focusnode=focusnode.left;
			qsort(d,left);
		}
		
		else if(left.isEmpty() && !right.isEmpty())
		{
			
			int c=(int) right.get(right.size()-1);
			quick node1 =new quick(c,right);
			focusnode.right=node1;
			focusnode=focusnode.right;
			qsort(c,right);
		}
		else if(!left.isEmpty() && right.isEmpty())
		{
			int c=(int) left.get(left.size()-1);
			quick node1 =new quick(c,left);
			focusnode.left=node1;
			focusnode=focusnode.left;
			qsort(c,left);
		}
	}
	public static void main(String[] args) {
		int[] a = {453,65432,41353,42,-455,0,543,54,45432,-45,432,323,4231,231,324,345,1345,2131,3113};
		if(a.length==0)	return;
		ArrayList al = new ArrayList();
		for(int s:a)
		{
			al.add(s);
		}
		progquick  pg = new progquick();
		quick node=new quick((int) al.get(al.size()-1), al);
		pg.root=node;
		pg.focusnode=pg.root;
		try{
			pg.qsort((int) al.get(al.size()-1), al);
		}
		catch(CancellationException expected)
		{
		}
		System.out.println("done");
		pg.inorder(pg.root);
	}
	 public void inorder(quick inord)
	    {
	    	if(inord.left!=null)inorder(inord.left);
	    	System.out.println(inord);
	    	if(inord.right!=null)inorder(inord.right);
	    }
}
