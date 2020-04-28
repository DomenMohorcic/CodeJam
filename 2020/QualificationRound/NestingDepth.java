import java.util.Scanner;

public class NestingDepth {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		String s, sol = "";
		for(int a = 1; a <= t; a++) {
			s = sc.next();
			sol = "";
			int prev_num = 0, brackets = 0;
			for(int i = 0; i < s.length(); i++) {
				int c = Character.getNumericValue(s.charAt(i));
				if(prev_num > c) { //)
					for(int j = 0; j < prev_num-c; j++) {
						sol += ')';
						brackets--;
					}
				} else if(prev_num < c) { //(
					for(int j = 0; j < c-prev_num; j++) {
						sol += '(';
						brackets++;
					}
				}
				sol += s.charAt(i);
				prev_num = c;
			}
			for(int j = 0; j < brackets; j++) {
				sol += ')';
			}
			System.out.printf("Case #%d: %s\n", a, sol);
		}
	}
}