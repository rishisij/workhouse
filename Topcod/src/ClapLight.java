import java.util.*;
public class ClapLight {
  public int threshold(int[] background) {
    outer:
    for(int i = 0; i <= 1001; i++){
      int lows = 0;
      int suc = 0;
      for(int j = 0; j < background.length; j++){
        if(background[j] < i){
          lows++;
          if(suc==3) continue outer;
          else suc = 1;
        }else{
          if(suc!=0)suc++;
        }
      }
      if(lows > background.length/2) return i;
    }
    return -1;
  }
 
/** begin cut - don't modify this line*/
  public static void main(String[] a) {
    new ClapLight().runTestCase(0);
    new ClapLight().runTestCase(1);
    new ClapLight().runTestCase(2);
    new ClapLight().runTestCase(3);
  }
 
  public void runTestCase(int nbr) {
    switch(nbr) {
      case 0 : {
        checkOutput(threshold(new int[] {6,6,6,6,6}), 7, 0); break;
      }
      case 1 : {
        checkOutput(threshold(new int[] { 5,8,7,6,12,8,4,3,2,6 } ), 9, 1); break;
      }
      case 2 : {
        checkOutput(threshold(new int[] {8,8,8,1,1,1,1,1,1,1,1,1,1,1,2,1}), 2, 2); break;
      }
      case 3 : {
        checkOutput(threshold(new int[] {921,1,5,900,8,813,3,3,3,3,3,3,3,813,813}), 4, 3); break;
      }
    }
  }
  final void checkOutput(int mine, int them, int nbr) {
    boolean success = (mine==them);
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append(mine);
      out.append(", Expected: ");
      out.append(them);
    }
    System.out.println(out);
  }
  final void checkOutput(long mine, long them, int nbr) {
    boolean success = (mine==them);
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append(mine);
      out.append(", Expected: ");
      out.append(them);
    }
    System.out.println(out);
  }
  final void checkOutput(double mine, double them, int nbr) {
    boolean success = doubleCompare(mine, them);
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append(mine);
      out.append(", Expected: ");
      out.append(them);
    }
    System.out.println(out);
  }
  private static boolean doubleCompare(double expected, double result){
    double MAX_DOUBLE_ERROR = 1E-9;
    if(Double.isNaN(expected)){
      return Double.isNaN(result);
    }else if(Double.isInfinite(expected)){
      if(expected > 0){
        return result > 0 && Double.isInfinite(result);
      }else{
        return result < 0 && Double.isInfinite(result);
      }
    }else if(Double.isNaN(result) || Double.isInfinite(result)){
      return false;
    }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){
      return true;
    }else{
      double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR),
        expected * (1.0 + MAX_DOUBLE_ERROR));
      double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR),
          expected * (1.0 + MAX_DOUBLE_ERROR));
      return result > min && result < max;
    }
  }
  final void checkOutput(char mine, char them, int nbr) {
    boolean success = (mine==them);
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append("'");
      out.append(mine);
      out.append("'");
      out.append(", Expected: ");
      out.append("'");
      out.append(them);
      out.append("'");
    }
    System.out.println(out);
  }
  final void checkOutput(String mine, String them, int nbr) {
    boolean success = (mine.equals(them));
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append("\"");
      out.append(mine);
      out.append("\"");
      out.append(", Expected: ");
      out.append("\"");
      out.append(them);
      out.append("\"");
    }
    System.out.println(out);
  }
  final void checkOutput(long[] mine, long[] them, int nbr) {
    boolean success = (Arrays.equals(mine, them));
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append("{");
      for(int x=0;x<mine.length;x++) {
        out.append(mine[x]);
        if(x<mine.length-1) out.append(", ");
      }
      out.append("}");
      out.append(", Expected: ");
      out.append("{");
      for(int x=0;x<them.length;x++) {
        out.append(them[x]);
        if(x<them.length-1) out.append(", ");
      }
      out.append("}");
    }
    System.out.println(out);
  }
  final void checkOutput(char[] mine, char[] them, int nbr) {
    boolean success = (Arrays.equals(mine, them));
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append("{");
      for(int x=0;x<mine.length;x++) {
        out.append(mine[x]);
        if(x<mine.length-1) out.append(", ");
      }
      out.append("}");
      out.append(", Expected: ");
      out.append("{");
      for(int x=0;x<them.length;x++) {
        out.append(them[x]);
        if(x<them.length-1) out.append(", ");
      }
      out.append("}");
    }
    System.out.println(out);
  }
  final void checkOutput(double[] mine, double[] them, int nbr) {
    boolean success = (Arrays.equals(mine, them));
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append("{");
      for(int x=0;x<mine.length;x++) {
        out.append(mine[x]);
        if(x<mine.length-1) out.append(", ");
      }
      out.append("}");
      out.append(", Expected: ");
      out.append("{");
      for(int x=0;x<them.length;x++) {
        out.append(them[x]);
        if(x<them.length-1) out.append(", ");
      }
      out.append("}");
    }
    System.out.println(out);
  }
  final void checkOutput(int[] mine, int[] them, int nbr) {
    boolean success = (Arrays.equals(mine, them));
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append("{");
      for(int x=0;x<mine.length;x++) {
        out.append(mine[x]);
        if(x<mine.length-1) out.append(", ");
      }
      out.append("}");
      out.append(", Expected: ");
      out.append("{");
      for(int x=0;x<them.length;x++) {
        out.append(them[x]);
        if(x<them.length-1) out.append(", ");
      }
      out.append("}");
    }
    System.out.println(out);
  }
  final void checkOutput(String[] mine, String[] them, int nbr) {
    boolean success = (Arrays.equals(mine, them));
    StringBuffer out = new StringBuffer();
    out.append("Example ");
    out.append((nbr+1));
    out.append(" - ");
    out.append(success ? "success" : "failure   ");
    if(!success) {
      out.append("Got: ");
      out.append("{");
      for(int x=0;x<mine.length;x++) {
        out.append(mine[x]);
        if(x<mine.length-1) out.append(", ");
      }
      out.append("}");
      out.append(", Expected: ");
      out.append("{");
      for(int x=0;x<them.length;x++) {
        out.append(them[x]);
        if(x<them.length-1) out.append(", ");
      }
      out.append("}");
    }
    System.out.println(out);
  }
 
/** end cut - don't modify this line*/
}
 
// Powered by PopsEdit
// Powered by CodeProcessor