import java.util.Scanner;

/**
 * @JUDGE_ID:  1000AA  706  Java
 * @author Marshall Farrier
 * @since 2014-02-18
 */
class Main {
	public static class LcdNumber {
		private int s;
		private int height;
		private String[][] digits;
		
		public LcdNumber(int s) {
			this.s = s;
			height = 2 * s + 3;
			digits = new String[10][height];
			init();
		}
		
		public void write(String numberSeq) {
			int i, j;
			String[] lines = new String[height];
			StringBuilder sb;
			for (i = 0; i < height; i++) {
				sb = new StringBuilder(digits[numberSeq.charAt(0) - '0'][i]);
				for (j = 1; j < numberSeq.length(); j++) {
					sb.append(" ").append(digits[numberSeq.charAt(j) - '0'][i]);
				}
				lines[i] = sb.toString();
			}
			for (i = 0; i < height; i++) {
				System.out.println(lines[i]);
			}
		}
		
		private void init() {
			StringBuilder sb = new StringBuilder("");
			String tmp;
			int i;
			
			// build first row for each digit
			sb.append(" ");
			for (i = 0; i < s; i++) {
				sb.append("-");
			}
			sb.append(" ");
			tmp = sb.toString();
			// pattern " -- "
			for (i = 0; i < 10; i++) {
				if (i != 1 && i != 4) digits[i][0] = tmp;
				if (i != 1 && i != 7 && i != 0) digits[i][s + 1] = tmp;
				if (i != 1 && i != 4 && i != 7) digits[i][2 * s + 2] = tmp;
			}
			// pattern "   " (all spaces)
			sb = new StringBuilder("  ");
			for (i = 0; i < s; i++) {
				sb.append(" ");
			}
			tmp = sb.toString();
			digits[0][s + 1] = tmp;
			digits[1][0] = tmp;
			digits[1][s + 1] = tmp;
			digits[1][2 * s + 2] = tmp;
			digits[4][0] = tmp;
			digits[4][2 * s + 2] = tmp;
			digits[7][s + 1] = tmp;
			digits[7][2 * s + 2] = tmp;
			// pattern "|   |"
			sb = new StringBuilder("|");
			for (i = 0; i < s; i++) {
				sb.append(" ");
			}
			tmp = sb.append("|").toString();
			for (i = 1; i <= s; i++) {
				digits[0][i] = tmp;
				digits[0][i + s + 1] = tmp;
				digits[4][i] = tmp;
				digits[6][i + s + 1] = tmp;
				digits[8][i] = tmp;
				digits[8][i + s + 1] = tmp;
				digits[9][i] = tmp;
			}
			// pattern "   |"
			sb = new StringBuilder(" ");
			for (i = 0; i < s; i++) {
				sb.append(" ");
			}
			tmp = sb.append("|").toString();
			for (i = 1; i <= s; i++) {
				digits[1][i] = tmp;
				digits[1][i + s + 1] = tmp;
				digits[2][i] = tmp;
				digits[3][i] = tmp;
				digits[3][i + s + 1] = tmp;
				digits[4][i + s + 1] = tmp;
				digits[5][i + s + 1] = tmp;
				digits[7][i] = tmp;
				digits[7][i + s + 1] = tmp;
				digits[9][i + s + 1] = tmp;
			}
			// pattern "|   "
			sb = new StringBuilder("|");
			for (i = 0; i < s; i++) {
				sb.append(" ");
			}
			tmp = sb.append(" ").toString();
			for (i = 1; i <= s; i++) {
				digits[2][i + s + 1] = tmp;
				digits[5][i] = tmp;
				digits[6][i] = tmp;
			}
			
		}
	}
	
	static void run() {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		String digits;
		while (s > 0) {
			digits = in.next();
			new LcdNumber(s).write(digits);
			System.out.println();
			s = in.nextInt();
		}
		in.close();
	}
	
	public static void main(String[] args) {
		run();
	}

}
