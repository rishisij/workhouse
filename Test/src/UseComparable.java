import java.util.*;
 
public class UseComparable {
  public static void main(String[] args)
                     throws java.io.IOException {
    String[] names = { "Sue", "Bill", "Tom", "Dave", "Andy",
                       "Mary", "Beth", "Bill", "Mike" };
    TreeSet sl = new TreeSet(Arrays.asList(names));
    Iterator it = sl.iterator();
    while (it.hasNext()) {
        System.out.println(it.next());
    }
  }
}