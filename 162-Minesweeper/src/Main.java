import java.util.Scanner;

/**
 * @JUDGE_ID:  1000AA  10189  Java
 * @author Marshall Farrier
 * @since 2014-02-15
 * 
 * Programming Challenges, p. 16
 */

class Main {
	static class MineField {
		private char[][] mines;
		
		public MineField(char[][] mines) {
			this.mines = mines;
		}
		
		public MineField solve() {
			int i, j;
			for (i = 0; i < mines.length; i++) {
				for (j = 0; j < mines[i].length; j++) {
					if (mines[i][j] == '*') continue;
					setFieldValue(i, j);
				}
			}
			return this;
		}
		
		public MineField print() {
			int i, j;
			for (i = 0; i < mines.length; i++) {
				for (j = 0; j < mines[i].length; j++) {
					System.out.print(mines[i][j]);
				}
				System.out.println();
			}
			return this;
		}
		
		private void setFieldValue(int row, int col) {
			int mineCount = 0, rowMin, rowMax, colMin, colMax, i, j;
			rowMin = row - 1;
			if (rowMin < 0) rowMin = 0;
			rowMax = row + 1;
			if (rowMax >= mines.length) rowMax = mines.length - 1;
			colMin = col - 1;
			if (colMin < 0) colMin = 0;
			colMax = col + 1;
			if (colMax >= mines[row].length) colMax = mines[row].length - 1;
			for (i = rowMin; i <= rowMax; i++) {
				for (j = colMin; j <= colMax; j++) {
					if (mines[i][j] == '*') mineCount++;
				}
			}
			mines[row][col] = (char) ('0' + mineCount);
		}
	}

	static void run() {
		char[][] mines;
		Scanner in = new Scanner(System.in);
		int rows = in.nextInt();
		int cols = in.nextInt();
		int i, counter = 1;
		boolean firstTime = true;
		
		while (rows > 0 && cols > 0) {
			in.nextLine();
			mines = new char[rows][cols];
			for (i = 0; i < rows; i++) {
				mines[i] = in.nextLine().toCharArray();
			}
			if (!firstTime) System.out.println();
			firstTime = false;
			System.out.println("Field #" + counter++ + ":");
			new MineField(mines).solve().print();
			rows = in.nextInt();
			cols = in.nextInt();
		}
		in.close();
	}
	
	public static void main(String[] args) {
		run();
	}

}
