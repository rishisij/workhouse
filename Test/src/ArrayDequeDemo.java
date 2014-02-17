import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeDemo {
   public static void main(String[] args) {
      
      // create an empty array deque with an initial capacity
      Deque<Integer> deque = new ArrayDeque<Integer>(8);

      // use add() method to add elements in the deque
      deque.add(15);
      deque.add(30);
      deque.add(20);
      deque.add(18);        
           
      // let us print all the elements available in deque
      for (Integer number : deque) {
         System.out.println("Number = " + number);
      }
      
      // getFirst() will retrieve element at first(head) position
      int retval = deque.getFirst();
      System.out.println("Retrieved Element is = " + retval);
   }
}