// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Marshall Farrier
 * @since 2014-02-15
 * 
 * Programming Challenges, p. 15
 */
class Main {
	static class Solver {
		private Map<Integer, Integer> solutions;
		
		Solver() {
			solutions = new HashMap<Integer, Integer>();
			solutions.put(1, 1);
		}
		
		int solve(int val) {
			if (solutions.get(val) != null) {
				return solutions.get(val);
			}
			int answer;
			if (val % 2 == 0) {
				answer = solve(val / 2) + 1;
			}
			else {
				answer = solve(3 * val + 1) + 1;
			}
			solutions.put(val, answer);
			return answer;
		}
		
		int getMaxCycleLength(int min, int max) {
			int answer = solve(min);
			int tmp;
			for (int i = min + 1; i <= max; i++) {
				tmp = solve(i);
				if (tmp > answer) {
					answer = tmp;
				}
			}
			return answer;
		}
	}
	
	static void run() {
		List<List<Integer>> inputs = new LinkedList<List<Integer>>();
		StringTokenizer tokenizer;
		List<Integer> list;
		//String testCase;
		
		Scanner keyboard = new Scanner(System.in);
		while (keyboard.hasNext()) {
			tokenizer = new StringTokenizer(keyboard.nextLine());
			list = new ArrayList<Integer>(2);
			while (tokenizer.hasMoreTokens()) {
				list.add(Integer.parseInt(tokenizer.nextToken()));
			}
			inputs.add(list);
		}
		keyboard.close();
		/*
		while ((testCase = ReadLn (255)) != null) {
			tokenizer = new StringTokenizer(testCase);
			list = new ArrayList<Integer>(2);
			while (tokenizer.hasMoreTokens()) {
				list.add(Integer.parseInt(tokenizer.nextToken()));
			}
			inputs.add(list);
		}
		*/
		ListIterator<List<Integer>> it = inputs.listIterator();
		Solver solver = new Solver();
		while (it.hasNext()) {
			list = it.next();
			System.out.print(list.get(0) + " " + list.get(1) + " ");
			if (list.get(0) <= list.get(1)) {
				System.out.println(solver.getMaxCycleLength(list.get(0), list.get(1)));
			}
			else {
				System.out.println(solver.getMaxCycleLength(list.get(1), list.get(0)));
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		run();
	}
	/*
	static String ReadLn(int maxLength) {  // utility function to read from stdin,
        // Provided by Programming-challenges, edit for style only
		byte line[] = new byte [maxLength];
		int length = 0;
		int input = -1;
		try {
			while (length < maxLength) {//Read untill maxlength
				input = System.in.read();
				if ((input < 0) || (input == '\n')) break; //or untill end of line ninput
				line [length++] += input;
			}
			
			if ((input < 0) && (length == 0)) return null;  // eof
			return new String(line, 0, length);
		} catch (IOException e){
			return null;
		}
	}
	*/
}
