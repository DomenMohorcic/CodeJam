import java.util.Scanner;

public class ESAbATAd {

	static int index = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		int b = sc.nextInt();
		for(int a = 1; a <= tests; a++) {
			boolean[] bits = new boolean[b];
			int idx_left = 0, idx_right = b-1;
			boolean first_round = true;
			index = 0;
			while(true) {
				if(idx_left > idx_right) { // check every 10 questions if we found all
					if(guess(bits, sc)) break;
					else System.exit(0);
				}
				if(first_round) { // ask about 10 positions
					for(int i = 0; i < 5; i++) {
						System.out.println(idx_left+1);
						bits[idx_left] = sc.nextInt() == 1;
						idx_left++;
					}
					for(int i = 0; i < 5; i++) {
						System.out.println(idx_right+1);
						bits[idx_right] = sc.nextInt() == 1;
						idx_right--;
					}
					first_round = false;
				} else {
					// figure out what happened
					boolean invert = false, swap = false;
					boolean tmp;
					int asked = 0;
					int[] pos = findSuitableIdx(bits, idx_left);
					if(pos[0] != -1) { // same bits
						System.out.println(pos[0]+1);
						tmp = sc.nextInt() == 1;
						asked++;
						if(tmp != bits[pos[0]]) invert = true;
					}
					if(pos[1] != -1) { // different bits
						System.out.println(pos[1]+1);
						tmp = sc.nextInt() == 1;
						asked++;
						if((invert && tmp == bits[pos[1]]) || (!invert && tmp != bits[pos[1]])) swap = true;
					}
					if(asked == 1) { // blank reading
						System.out.println(1);
						int u = sc.nextInt();
					}
					bits = modify(bits, invert, swap);
					// ask remaining questions
					for(int i = 0; i < 4; i++) {
						System.out.println(idx_left+1);
						bits[idx_left] = sc.nextInt() == 1;
						idx_left++;
					}
					for(int i = 0; i < 4; i++) {
						System.out.println(idx_right+1);
						bits[idx_right] = sc.nextInt() == 1;
						idx_right--;
					}
				}
			}
		}
	}

	private static boolean guess(boolean[] b, Scanner sc) {
		for(int i = 0; i < b.length; i++) System.out.print(b[i] ? 1 : 0);
		System.out.println();
		String result = sc.next();
		if(result.equals("N")) return false;
		return true;
	}

	private static boolean[] modify(boolean[] b, boolean invert, boolean swap) {
		boolean[] a = new boolean[b.length];
		//System.err.println("["+(index++)+"] Swap: "+swap+", invert: "+invert);
		if(swap) {
			if(invert) for(int i = 0; i < b.length; i++) a[i] = !b[b.length-1-i];
			else for(int i = 0; i < b.length; i++) a[i] = b[b.length-1-i];
		} else {
			if(invert) for(int i = 0; i < b.length; i++) a[i] = !b[i];
			else return b;
		}
		return a;
	}

	private static int[] findSuitableIdx(boolean[] b, int idx_left) {
		int same = -1, diff = -1;
		for(int i = 0; i < idx_left; i++) {
			if(same == -1 && b[i] == b[b.length-1-i]) same = i;
			else if(diff == -1 && b[i] != b[b.length-1-i]) diff = i;
			if(diff != -1 && same != -1) break;
		}
		return new int[] {same, diff};
	}
}