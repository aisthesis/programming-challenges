import java.util.Arrays;
import java.util.Scanner;

/**
 * @JUDGE_ID:  1000AA  10137  Java
 * @author Marshall Farrier
 * @since 2014-02-15
 * 
 * Programming Challenges, p. 16
 * 
 * Test cases:
 * 3
 * 10.00	+10.00
 * 20.00	+0
 * 30.00	-10.00
 * sum: 60.00
 * ave: 20.00
 * answer: $10.00
 * 
 * 4
 * 3.00		+6.00	9.00
 * 3.01		+5.99	9.00
 * 15.00	-5.99	9.01
 * 15.01	-6.00	9.01
 * sum: 36.02
 * ave: 9.00 r0.02
 * answer: $11.99
 * 
 * 4
 * 1.00		+8.00	9.00
 * 11.01	-2.01	9.00
 * 12.00	-3.99	9.01
 * 12.01	-3.00	9.01
 * sum: 36.02
 * ave: 9.00 r0.02
 * answer: $8.00
 * 
 * 4
 * 3.00		+6.00	9.00
 * 3.01		+5.99	9.00
 * 3.01		+6.00	9.01
 * 27.00	-17.99	9.01
 * sum: 36.02
 * ave: 9.00 r0.02
 * answer: $17.99
 * 
 * 4
 * 3.01		+5.99	9.00
 * 3.01		+6.00	9.01
 * 15.00	-5.99	9.01
 * 15.01	-6.00	9.01
 * sum: 36.03
 * ave: 9.00 r0.03
 * answer: $11.99
 */

class Main {
	
	static class Trip {
		private int[] expenses;
		private int students;
		private int sum;
		private int truncatedAve;
		private int remainder;
		
		public Trip (int students, int[] expenses) {
			this. students = students;
			this.expenses = expenses;
			Arrays.sort(this.expenses);
			sum = 0;
			for (int expense : expenses) {
				sum += expense;
			}
			truncatedAve = sum / this.students;
			remainder = sum % this.students;
		}
		
		public int minCents() {
			int toPayIn = 0;
			int maxIndex = expenses.length - 1;
			for (int expense : expenses) {
				if (expense < truncatedAve) toPayIn += truncatedAve - expense;
			}
			for (int i = 0; i < remainder; i++) {
				if (expenses[maxIndex - i] <= truncatedAve) toPayIn++;
			}
			return toPayIn;
		}
	}

	static void run() {
		Scanner in = new Scanner(System.in);
		int students = in.nextInt();
		double epsilon = 0.001;
		int[] expenses;
		int i, minCents;
		while (students > 0) {
			in.nextLine();
			expenses = new int[students];
			for (i = 0; i < students; i++) {
				expenses[i] = (int) ((Double.parseDouble(in.nextLine()) + epsilon) * 100);
			}
			minCents = new Trip(students, expenses).minCents();
			System.out.printf("$%1d.%02d%n", minCents / 100, minCents % 100);
			students = in.nextInt();
		}
		in.close();
	}
	
	public static void main(String[] args) {
		run();
	}

}
