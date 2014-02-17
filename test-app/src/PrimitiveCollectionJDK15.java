import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
 
public class PrimitiveCollectionJDK15 {
   public static void main(String[] args) {
      List<Integer> lst = new ArrayList<Integer>();
 
      // Add 10 random primitive int into the List
      Random random = new Random();
      for (int i = 1; i <= 10; ++i) {
         lst.add(random.nextInt(10)); // autobox to Integer, upcast to Object, type-safe
      }
      System.out.println(lst);
 
      // Transverse via iterator
      Iterator<Integer> iter = lst.iterator();
      while (iter.hasNext()) {
         int i = iter.next();  // downcast to Integer, auto-unbox to int, type-safe
         System.out.println(i);
      }
 
      // Transverse via enhance for-loop
      for (int i : lst) {      // downcast to Integer, auto-unbox to int, type-safe
         System.out.println(i);
      }
 
      // Retrieve via for-loop with List's index
      for (int i = 0; i < lst.size(); ++i) {
         int j = lst.get(i);   // downcast to Integer, auto-unbox to int, type-safe
         System.out.println(j);
      }
   }
}

