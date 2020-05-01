import java.util.Scanner;

public class ESAbATAd {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		int b = sc.nextInt();
		for(int a = 1; a <= tests; a++) {
			int[] bits = new int[b];
			int idx_left = 0, idx_right = b-1;
			boolean first_round = true;
			while(true) {
				if(idx_left > idx_right) { //found all
					if(guess(bits, sc)) break;
					else System.exit(0);
				}
				if(first_round) { //ask 10 positions
					for(int i = 0; i < 5; i++) {
						System.out.println(idx_left+1);
						bits[idx_left] = sc.nextInt();
						idx_left++;
					}
					for(int i = 0; i < 5; i++) {
						System.out.println(idx_right+1);
						bits[idx_right] = sc.nextInt();
						idx_right--;
					}
					first_round = false;
				} else {
					boolean invert = false, swap = false;
					int tmp, asked = 0;;
					int[] pos = findSuitable(bits, idx_left, idx_right);
					if(pos[0] != -1) {
						System.out.println(pos[0]+1);
						tmp = sc.nextInt();
						asked++;
						if(tmp != bits[pos[0]]) invert = true;
					}
					if(pos[1] != -1) {
						System.out.println(pos[1]+1);
						tmp = sc.nextInt();
						asked++;
						if(invert && tmp == bits[pos[1]] || !invert && tmp != bits[pos[1]]) swap = true;
					}
					bits = modify(bits, invert, swap);
					if(asked%2 == 0) {
						for(int i = 0; i < 5-asked/2; i++) {
							System.out.println(idx_left+1);
							bits[idx_left] = sc.nextInt();
							idx_left++;
						}
						for(int i = 0; i < 5-asked/2; i++) {
							System.out.println(idx_right+1);
							bits[idx_right] = sc.nextInt();
							idx_right--;
						}
					} else {
						for(int i = 0; i < 4; i++) {
							System.out.println(idx_left+1);
							bits[idx_left] = sc.nextInt();
							idx_left++;
						}
						for(int i = 0; i < 5; i++) {
							System.out.println(idx_right+1);
							bits[idx_right] = sc.nextInt();
							idx_right--;
						}
					}
				}
			}
		}
	}

	private static boolean guess(int[] b, Scanner sc) {
		for(int i = 0; i < b.length; i++) System.out.print(b[i]);
		System.out.println();
		String result = sc.next();
		if(result.equals("N")) return false;
		return true;
	}

	private static int[] modify(int[] b, boolean invert, boolean swap) {
		int[] a = new int[b.length];
		if(swap) {
			if(invert) for(int i = 0; i < b.length; i++) a[i] = b[b.length-1-i] == 1 ? 0 : 1;
			else for(int i = 0; i < b.length; i++) a[i] = b[b.length-1-i];
		} else {
			if(invert) for(int i = 0; i < b.length; i++) a[i] = b[i] == 1 ? 0 : 1;
			else for(int i = 0; i < b.length; i++) a[i] = b[i];
		}
		return a;
	}

	private static int[] findSuitable(int[] b, int idx_left, int idx_right) {
		int same = -1, diff = -1;
		for(int i = 0; i < idx_left; i++) {
			if(same == -1 && b[i] == b[b.length-1-i]) same = i;
			if(diff == -1 && (b[i] > b[b.length-1-i] || b[i] < b[b.length-1-i])) diff = i;
			if(diff != -1 && same != -1) break;
		}
		return new int[] {same, diff};
	}
}