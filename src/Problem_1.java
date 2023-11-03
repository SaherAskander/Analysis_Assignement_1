import java.util.ArrayList;
import java.util.Scanner;

public class Problem_1 {

	public static long naivepower(int a, int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= a;
        }
        return result ;
	}
        public static long divideandconquerpower(int a, int n) {
        if (n == 0) {
            return 1;
        } else if (n % 2 == 0) {
            long tmp = divideandconquerpower(a, n / 2);
            return tmp * tmp;
        } else {
            long tmp = divideandconquerpower(a, n / 2);
            return a * tmp * tmp;
        }
    }
        public static void main(String[] args) {
        	 Scanner scanner = new Scanner(System.in);
             System.out.print("Please Enter a base number: ");
             int a = scanner.nextInt();
             ArrayList<Integer> exponents=new ArrayList<Integer>();
             for(int i=1;i<=1000000;i*=10) {
            	 exponents.add(i);
             }
       
             
             // Measuring time for the naive iterative method
            for(Integer exp:exponents) {
             long startNaive = System.nanoTime();
	         long resultNaive = naivepower(a,exp);
	         long endNaive = System.nanoTime();
	         long timeNaive = endNaive - startNaive;
            
             // Measuring time for the divide-and-conquer method
             long startDivideConquer = System.nanoTime();
             long resultDivideConquer = divideandconquerpower(a,exp);
             long endDivideConquer = System.nanoTime();
             long timeDivideConquer = endDivideConquer - startDivideConquer;
    	    
            
             System.out.println("Naive Iterative Method Result: " + resultNaive);
             System.out.println("Time taken by Naive Iterative Method: " + timeNaive + " nanoseconds");

             System.out.println("Divide-and-Conquer Method Result: " + resultDivideConquer);
             System.out.println("Time taken by Divide-and-Conquer Method: " + timeDivideConquer + " nanoseconds");
    		}
        }
	
}
