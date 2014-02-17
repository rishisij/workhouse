import java.util.*;
 
public class UseComparator {
   
  public static void main(String[] args) throws java.io.IOException {
    String[] names = { "Sue", "Bill", "Tom", "Dave", "Andy",
                       "Mary", "Beth", "Bill", "Mike" };
 
    TreeSet s2 = new TreeSet(new ReverseComparator());
    s2.addAll(Arrays.asList(names));
     
    Iterator it = s2.iterator();
    while (it.hasNext()) {
        System.out.println(it.next());
    }
  }
}
 
class ReverseComparator implements Comparator {
  public int compare(Object o1, Object o2) {
    if (o1 instanceof String && o2 instanceof String)
      return -((String)o1).compareTo((String)o2);
    else throw new ClassCastException("Objects are not Strings");
  }
}