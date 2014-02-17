package jprog;

import java.util.Stack;

public class progtest {
     node root;
     node focusnode;
     node parent;
     int count=0;
     int leaf=0;
     int check=0;
     int g=0;
     
     public void addnode(int key,String name)
     {
    	 node newnode = new node(key,name);
    	 if(root==null)
    	 {
    		 root=newnode;
    		 
    	 }
    	 else
    	 {
    		 focusnode=root;
    		
    		 while(true)
    		 {
	    		 if(key>focusnode.key)
	    		 {
	    			if(focusnode.rchild==null)
	    			{
	    				focusnode.rchild=newnode;
	    				break;
	    			}
	    			else
	    			{	
	    				parent = focusnode;
	    				focusnode = focusnode.rchild;
	    			}
	    		 }
	    		 if(key<focusnode.key)
	    		 {
	    			 if(focusnode.lchild==null)
	    			 {
	    				 focusnode.lchild=newnode;
	    				 break;	
	    			 }
	    			 else
	    			 {
	    				 parent=focusnode;
	    				 focusnode=focusnode.lchild;
	    				 
	    			 }
	    		 }
	    		 
    		 }
    	 }
     }
    public node search(int key)
    {
    	node search;
    	if(root==null)return null;
    	focusnode=root;
    	while(true)
    	{
    		parent = focusnode;
	    	if(key>parent.key)
	    	{
	    		focusnode = parent.rchild;
	    	}
	    	else if(key<parent.key)
	    	{
	    		focusnode = parent.lchild;
	    	}
	    	else if(key==parent.key)
	    	{
	    		search=parent;
	    		return search;
	    	}
	    	else
	    	{
	    		return null;
	    	}
    	}
    	
    	
    }
    
    
    public int depth()
    {
    	focusnode=root;
    	if(root==null)return count;
    	while(true)
    	{
    		parent =  focusnode;
    		if(parent!=null)
    		{
    			if(parent.lchild==null)
	    		{	
	    			focusnode=parent.rchild;
	    		    count++;
	    		}
	    		else
	    		{
		    		focusnode =	parent.lchild;
		    		count++;
	    		}
	    		
    		}
    		else
    			return count;
    	}
    }
    public int leaves()
    {
    	Stack sk=new Stack();
    	focusnode=root;
    	if(root==null)return leaf;
    	parent=root;
    	while(true)
    	{  
    		
    		if(focusnode.lchild!=null)
    		{
    			
    			sk.push(focusnode);
    			focusnode=focusnode.lchild;
	    	}
	    	else if(focusnode.rchild!=null)
	    	{	
	    		focusnode=focusnode.rchild;
	    	}
    		
    		
	    	else if(focusnode.rchild==null && focusnode.lchild==null)
    		{
	    		leaf++;
	    		System.out.println(focusnode);
	    		parent=(node) sk.pop();
    			if(parent.rchild!=null)
    				focusnode=parent.rchild;
    			while(focusnode==null)
    			{   
    				if(parent==root)
	    			{
	    				return leaf;
	    			}
    				if(!sk.empty())
    				{
	    				parent=(node) sk.pop();
    					focusnode=parent.rchild;
    				}
    				else
    					return leaf;
	    			
    			}
    			if(focusnode.key==g)
    				;
    			if(parent==root)
        		{
        			sk.clear();
        			sk.push(parent);
        			check=check+1;
        			if(check==2)
        				return leaf;
        		}
    				
    		}
  		}
   	}
    
    public void inorder(node inord)
    {
    	if(inord.lchild!=null)inorder(inord.lchild);
    	System.out.println(inord);
    	if(inord.rchild!=null)inorder(inord.rchild);
    }
	
	public static void main(String[] args) {
		progtest theTree = new progtest();

		theTree.addnode(50, "Boss");

		theTree.addnode(25, "Vice President");

		theTree.addnode(15, "Office Manager");

		theTree.addnode(30, "Secretary");
		
		theTree.addnode(83, "Secretary");

		theTree.addnode(75, "Sales Manager");

		theTree.addnode(85, "Salesman 1");
		
		theTree.addnode(91, "Office Manager");
		theTree.addnode(1, "Office Manager");
		theTree.addnode(17, "Office Manager");
		theTree.addnode(16, "Office Manager");
		theTree.addnode(18, "Office Manager");
		theTree.addnode(31, "Office Manager");
			

		System.out.println("its done");
		//System.out.println(theTree.search(1));
		//System.out.println(theTree.root.lchild);
		//System.out.println("hi");
		theTree.inorder(theTree.root);
		
		//theTree.leaves();

	}

}
class node {
	int key;
	String name;
	node rchild;
	node lchild;
	public  node(int key,String name)
	{
		this.key=key;
		this.name=name;
	}
	public String toString()
	{
		return name +" has a key " + key;
	}
	
	
}
