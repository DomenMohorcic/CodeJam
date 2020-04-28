import java.util.Scanner;
import java.util.ArrayList;

public class ParentingPartneringReturns {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int a = 1; a <= t; a++) {
			int n = sc.nextInt();
			ArrayList<Integer>[] conflicts = (ArrayList<Integer>[]) new ArrayList[n];
			int[][] table = new int[n][2]; //0...start, 1...end
			for(int i = 0; i < n; i++) {
				conflicts[i] = new ArrayList<Integer>();
				table[i][0] = sc.nextInt();
				table[i][1] = sc.nextInt();
				for(int j = 0; j < i; j++) {
					if(table[i][1] > table[j][0] && table[i][0] < table[j][1]) {
						conflicts[i].add(j);
						conflicts[j].add(i);
					}
				}
			}
			char[] tasks = new char[n];
			int c;
			char neighbours = '0';
			ArrayList<Integer> idx = new ArrayList<Integer>();
			boolean isPossible = true;
			while(!isColored(tasks) && isPossible) {
				idx.add(findNextUncolored(tasks));
				while(idx.size() > 0 && isPossible) {
					c = (int) idx.get(0);
					idx.remove(0);
					neighbours = '0';
					for(int neighbour : conflicts[c]) {
						if(!isPossible) break;
						if(tasks[neighbour] != '\u0000') {
							if(neighbours == '0') neighbours = tasks[neighbour];
							else if(neighbours != tasks[neighbour]) isPossible = false;
						} else idx.add(neighbour);
					}
					if(neighbours == '0') tasks[c] = 'C';
					else if(neighbours == 'C') tasks[c] = 'J';
					else tasks[c] = 'C';
				}
			}
			if(!isPossible) System.out.printf("Case #%d: IMPOSSIBLE\n", a);
			else System.out.printf("Case #%d: %s\n", a, toString(tasks));
		}
	}

	public static boolean isColored(char[] c) {
		for(int i = 0; i < c.length; i++) if(c[i] == '\u0000') return false;
		return true;
	}

	public static int findNextUncolored(char[] c) {
		for(int i = 0; i < c.length; i++) if(c[i] == '\u0000') return i;
		return -1;
	}

	public static String toString(char[] c) {
		String s = "";
		for(char a : c) s += a;
		return s;
	}
}