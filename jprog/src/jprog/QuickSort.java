package jprog;

import java.io.*;
import java.util.*;

public class QuickSort
{
   public static void swap (int A[], int x, int y)
   {
      int temp = A[x];
      A[x] = A[y];
      A[y] = temp;
   }

   // Reorganizes the given list so all elements less than the first are 
   // before it and all greater elements are after it.                   
   public static int partition(int A[], int f, int l)
   {
      int pivot = A[f];
      while (f < l)
      {
         while (A[f] < pivot) f++;
         while (A[l] > pivot) l--;
         swap (A, f, l);
      }
      return f;
   }

   public static void Quicksort(int A[], int f, int l)
   {
      if (f >= l) return;
      int pivot_index = partition(A, f, l);
      Quicksort(A, f, pivot_index);
      Quicksort(A, pivot_index+1, l);
   }

   // Usage: java QuickSort [integer] ...
   // All integers must be distinct
   public static void main(String argv[])
   {
      int A[] = {4,24,45,531,5521,14515,3,42,323,4231,231,-1,-4,-43,0,54,201,214};
      //for (int i=0 ; i < argv.length ; i++)
        // A[i] = Integer.parseInt(argv[i]);

      Quicksort(A, 0, A.length-1);

      for (int i=0 ; i < A.length ; i++) System.out.print(A[i] + " ");
      System.out.println();
   }
}
