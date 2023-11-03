
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Problem_2 {
	
	public static void arrayprint(int[] a) {
		for (int x: a)
		System.out.println(x);
	}
	
	 public static boolean binarySearch(int[] arr, int key, int i, int j) {
	        if (j>= i) {
	            int mid = (i+j) / 2;
	            if (arr[mid] == key) {
	                return true;
	            }
	            if (arr[mid] > key) {
	                return binarySearch(arr,key, i, mid - 1);
	            }
	            return binarySearch(arr,key, mid + 1, j);
	        }
	        return false;
	    }
	public static LinkedList<int[]> findPairs(int[] arr, int sum) {
	    LinkedList<int[]> results = new LinkedList<int[]>();
	    int n= arr.length;
	    Arrays.sort(arr);
	    arrayprint(arr);
        //mergeSort(arr, 0, n - 1);
        for (int i = 0; i <n; i++) {
            int complement = sum - arr[i];
            if (binarySearch(arr, complement, i+ 1, n - 1)) {
            	int[] result=new int[2];
            	result[0]=arr[i];
            	result[1]=complement;
            	results.add(result);

            }
        }
        return results;
    }
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Please Enter The array size: ");
		int size =sc.nextInt();
		int[] arr=new int[size];
		for(int i=0;i<size;i++) {
			System.out.print("Please Enter a number to insert it in the array: ");
			arr[i] =sc.nextInt();
		}
		System.out.print("Please Enter a number to find all the pairs which computes it : ");
		int sum =sc.nextInt();
		LinkedList< int[]> linkedList= findPairs(arr, sum);
		int[] test=new int[2];
		int k=1;
		while (!linkedList.isEmpty()) {
			test=linkedList.remove();
	            System.out.println("pair number "+ (k++) +" is: " +test[0] +" and "+ test[1]);
	        }
		}
	}

   








