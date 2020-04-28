import java.util.Scanner;

public class Vestigium {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for(int a = 1; a <= tests; a++) {
			int size = sc.nextInt();
			int[][] array = new int[size][size];
			int[][][] duplicates = new int[2][size][size]; //0..rows, 1...cols
			int trace = 0, dup_cols = 0, dup_rows = 0;
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					array[i][j] = sc.nextInt();
					if(i == j) trace += array[i][j];
					duplicates[0][i][array[i][j]-1]++;
					duplicates[1][j][array[i][j]-1]++;
				}
			}
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(duplicates[0][i][j] > 1) {
						dup_rows++;
						break;
					}
				}
				for(int j = 0; j < size; j++) {
					if(duplicates[1][i][j] > 1) {
						dup_cols++;
						break;
					}
				}
			}
			System.out.printf("Case #%d: %d %d %d\n", a, trace, dup_rows, dup_cols);
		}
	}
}