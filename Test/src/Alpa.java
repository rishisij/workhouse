public class Alpa {  
       
      public static void main(String[] args) {  
        char startUpper = 'A';      
        int numOfLoops = 26;  
       
        for (int i = 0; i < numOfLoops; i++) {  
          System.out.print(startUpper++ + " ");  
        }  
       
        System.out.println();  
      }  
    }  