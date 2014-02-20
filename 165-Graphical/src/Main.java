import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @JUDGE_ID:  1000AA  10267  Java
 * @author Marshall Farrier
 * @since 2014-02-19
 * 
 * Programming Challenges, pp. 19f.
 * Graphical Editor
 */
class Main {
	public static class Editor {
		private char[][] image;
		
		public Editor(int rows, int cols) {
			image = new char[rows][cols];
			clear();
		}
		
		public void show() {
			for (int i = 0; i < image.length; i++) {
				System.out.println(image[i]);
			}
		}
		
		public void clear() {
			int i, j;
			for (i = 0; i < image.length; i++) {
				for (j = 0; j < image[i].length; j++) {
					image[i][j] = 'O';
				}
			}
		}
		
		public void setPixel(int i, int j, char color) {
			image[i][j] = color;
		}
		
		public void setRow(int row, int colMin, int colMax, char color) {
			int tmp;
			if (colMax < colMin) {
				tmp = colMin;
				colMin = colMax;
				colMax = tmp;
			}
			for (int j = colMin; j <= colMax; j++) {
				image[row][j] = color;
			}
		}
		
		public void setCol(int col, int rowMin, int rowMax, char color) {
			int tmp;
			if (rowMax < rowMin) {
				tmp = rowMin;
				rowMin = rowMax;
				rowMax = tmp;
			}
			for (int i = rowMin; i <= rowMax; i++) {
				image[i][col] = color;
			}
		}
		
		public void setRect(int rowMin, int colMin, int rowMax, int colMax, char color) {
			int i, j;
			for (i = rowMin; i <= rowMax; i++) {
				for (j = colMin; j <= colMax; j++) {
					image[i][j] = color;
				}
			}
		}
		
		public void fill(int row, int col, char color) {
			if (image[row][col] == color) return;
			char colorToMatch = image[row][col];
			image[row][col] = color;
			if (row - 1 >= 0 && image[row - 1][col] == colorToMatch) {
				fill(row - 1, col, color);
			}
			if (col - 1 >= 0 && image[row][col - 1] == colorToMatch) {
				fill(row, col - 1, color);
			}
			if (col + 1 < image[row].length && image[row][col + 1] == colorToMatch) {
				fill(row, col + 1, color);
			}
			if (row + 1 < image.length && image[row + 1][col] == colorToMatch) {
				fill(row + 1, col, color);
			}
		}
	}
	
	static Editor processLine(char command, StringTokenizer tok, Editor editor) {
		int rows, cols, i, j, rowMin, rowMax, colMin, colMax;
		switch (command) {
		case 'I':
			cols = Integer.parseInt(tok.nextToken());
			rows = Integer.parseInt(tok.nextToken());
			editor = new Editor(rows, cols);
			break;
		case 'C':
			editor.clear();
			break;
		case 'L':
			j = Integer.parseInt(tok.nextToken()) - 1;
			i = Integer.parseInt(tok.nextToken()) - 1;
			editor.setPixel(i, j, tok.nextToken().charAt(0));
			break;
		case 'V':
			j = Integer.parseInt(tok.nextToken()) - 1;
			rowMin = Integer.parseInt(tok.nextToken()) - 1;
			rowMax = Integer.parseInt(tok.nextToken()) - 1;
			editor.setCol(j, rowMin, rowMax, tok.nextToken().charAt(0));
			break;
		case 'H':
			colMin = Integer.parseInt(tok.nextToken()) - 1;
			colMax = Integer.parseInt(tok.nextToken()) - 1;
			i = Integer.parseInt(tok.nextToken()) - 1;
			editor.setRow(i, colMin, colMax, tok.nextToken().charAt(0));
			break;
		case 'K':
			colMin = Integer.parseInt(tok.nextToken()) - 1;
			rowMin = Integer.parseInt(tok.nextToken()) - 1;
			colMax = Integer.parseInt(tok.nextToken()) - 1;
			rowMax = Integer.parseInt(tok.nextToken()) - 1;
			editor.setRect(rowMin, colMin, rowMax, colMax, tok.nextToken().charAt(0));
			break;
		case 'F':
			j = Integer.parseInt(tok.nextToken()) - 1;
			i = Integer.parseInt(tok.nextToken()) - 1;
			editor.fill(i, j, tok.nextToken().charAt(0));
			break;
		case 'S':
			System.out.println(tok.nextToken());
			editor.show();
			break;
		}
		return editor;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Editor editor = null;
		StringTokenizer tok = new StringTokenizer(in.nextLine());
		while (!tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.nextLine());
		}
		char command = tok.nextToken().charAt(0);
		while (command != 'X') {
			editor = processLine(command, tok, editor);
			tok = new StringTokenizer(in.nextLine());
			while (!tok.hasMoreTokens()) {
				tok = new StringTokenizer(in.nextLine());
			}
			command = tok.nextToken().charAt(0);
		}
		in.close();
	}

}
