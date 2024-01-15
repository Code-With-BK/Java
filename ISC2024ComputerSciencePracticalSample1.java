import java.util.Scanner;

/**
 * 
 * @author Balkrishna Srivastava
 * Copyright © CodeWithBK 2024. All rights reserved.
 * 
 * Java program to input N and print the lucky numbers less than N
 * 1 < N < 100000
 * 
 * Example 1:
 * N = 25
 * Output:
 * 1 3 7 9 13 15 21 
 * 
 * Example 2:
 * N = 100
 * Output:
 * 1 3 7 9 13 15 21 25 31 33 37 43 49 51 63 67 69 73 75 79 87 93 99
 */

/*
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 
 * 1   3   5   7   9    11    13    15    17    19    21    23   
 * 1   3       7   9          13    15          19    21   
 * 1   3       7   9          13    15                21   
 * 
 */

public class ISC2024ComputerSciencePracticalSample1 {
	
	static void printArray(int N, int arr[]) {
		System.out.print("[ ");
		for( int i=1; i<N; i++ ) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("]");
	}
	
	/*
	 * Find all lucky numbers less than N
	 */
	static void findLuckyNumbers(int N) {
		
		// create an array of size N
		int[] arr=new int[N];
		
		// set all indicies to value i
		for( int i=0; i<N; i++ ) {
			arr[i] = i;
		}
		
		// step 1 - remove all even numbers
		for( int i=0; i<N; i+=2 ) {
			arr[i] = 0;
		}
		printArray(N, arr);
		
		// step 2 - remove every jth number, starting from 3
		int j = 3;
		while( j<N ) {
			// remove every jth non-zero value in the array
			int m = 1;
			while( m<N ) {
				int count = 0;
				while( m<N ) {
					if( arr[m]!=0 ) {
						count++;
					}
					if( count==j ) {
						break;
					}
					m++;
				}
				if( count==j ) {
					arr[m] = 0;
				}
			}
			printArray(N, arr);
			
			// find the next j
			m = j + 1;
			while( m<N && arr[m]==0 ) {
				m++;
			}
			
			j = m;
		}
		
		// print the non zero values
		System.out.println("Lucky numbers less than " + N + " are: ");
		for( int i=0; i<N; i++ ) {
			if( arr[i]!=0 ) {
				System.out.print(arr[i] + " ");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter N: ");
		int N = scanner.nextInt();
		
		// call the function
		findLuckyNumbers(N);
		
		scanner.close();
	}
}






