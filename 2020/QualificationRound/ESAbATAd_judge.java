import java.util.Scanner;

public class ESAbATAd_judge {

	static int index = -1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = (int) Math.round(Math.random()*99+1);
		int b = (int) Math.round(Math.random()*9+1)*10;
		System.out.println(t);
		System.out.println(b);
		for(int a = 1; a <= t; a++) {
			boolean[] bits = newRandomBits(b);
			int asked_questions = 0;
			index = -1;
			while(asked_questions < 150) {
				String s = sc.next();
				if(s.length() == b) {
					for(int i = 0; i < b; i++) {
						if(bits[i] && s.charAt(i) == '0' || !bits[i] && s.charAt(i) == '1') {
							System.out.println('N');
							System.err.println("["+a+"/"+t+"] Submited");
							System.err.println(s);
							System.err.println("["+a+"/"+t+"] Real");
							System.err.println(toString(bits));
							System.exit(1);
						}
					}
					System.out.println('Y');
					break;
				} else {
					asked_questions++;
					if(asked_questions%10 == 1) {
						bits = randomPermutation(bits);
					}
					int idx = Integer.parseInt(s);
					System.out.println(bits[idx-1] ? 1 : 0);
				}
			}
		}
	}

	private static String toString(boolean[] bits) {
		String s = "";
		for(boolean b : bits) {
			s += b ? '1' : '0';
		}
		return s;
	}

	private static boolean[] randomPermutation(boolean[] b) {
		boolean[] a = new boolean[b.length];
		boolean swap = Math.random() > 0.5;
		boolean invert = Math.random() > 0.5;
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

	private static boolean[] newRandomBits(int b) {
		boolean[] a = new boolean[b];
		for(int i = 0; i < b; i++) a[i] = Math.random() > 0.5;
		return a;
	}
}
