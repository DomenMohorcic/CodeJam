import java.util.Scanner;

public class Indicium {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		byte tests = sc.nextByte();
		for(byte t = 1; t <= tests; t++) {
			byte n = sc.nextByte();
			byte k = sc.nextByte();
			byte[][] matrix = new byte[n][n];
			if(solve(matrix, 0, 0, k)) {
				System.out.printf("Case #%d: POSSIBLE\n", t);
				for(byte i = 0; i < n; i++) {
					for(byte j = 0; j < n; j++) {
						if(j == 0) System.out.print(matrix[i][j]);
						else System.out.printf(" %d", matrix[i][j]);
					}
					System.out.println();
				}
			} else {
				System.out.printf("Case #%d: IMPOSSIBLE\n", t);
			}
		}
	}

	private static boolean solve(byte[][] matrix, int i, int j, int trace) {
		if(j == matrix.length) {
			j = 0;
			i++;
		}
		if(i == matrix.length) {
			short sum = 0;
			for(int l = 0; l < matrix.length; l++) sum += matrix[l][l];
			if(sum == trace) return true;
			else return false;
		}
		for(byte k = 1; k <= matrix.length; k++) {
			if(canBePlaced(matrix, i, j, k)) {
				matrix[i][j] = k;
				if(solve(matrix, i, j+1, trace)) {
					return true;
				}
			}
		}
		matrix[i][j] = 0;
		return false;
	}

	private static boolean canBePlaced(byte[][] matrix, int i, int j, byte k) {
		if(inRow(matrix, i, k)) return false;
		else if(inColumn(matrix, j, k)) return false;
		return true;
	}

	private static boolean inColumn(byte[][] matrix, int j, byte k) {
		for(int i = 0; i < matrix.length; i++) {
			if(matrix[i][j] == k) return true;
		}
		return false;
	}

	private static boolean inRow(byte[][] matrix, int i, byte k) {
		for(int j = 0; j < matrix.length; j++) {
			if(matrix[i][j] == k) return true;
		}
		return false;
	}
}
