package samkeeleyong.mscs.week2;

import static samkeeleyong.mscs.week2.BitonicAssignment.COMPARER_LIST;
import static samkeeleyong.mscs.week2.BitonicAssignment.N;
import static samkeeleyong.mscs.week2.BitonicAssignment.NUMBERS;


public class InstructionSetsGenerator {
	
	static int comparerCounter = 0;
	
	public static void generateAndAssignInstructions() {
		int x = BitonicAssignment.N;

		while (x != 1) {
			int sectionCount = N / x, startSentinel = 0;
			int startSentinelIncrement = N / sectionCount;
			for (int i = 1; i <= sectionCount; i++) {
				bitonicSwapsMove(x, 1 + startSentinel);
				startSentinel += startSentinelIncrement;
			}
			comparerCounter = 0;
			x /= 2;
		}
	}

	private static void bitonicSwapsMove(int length, int start) {
		
		int half = length / 2;	//core
		for (int i = start, k = 1; k <= half; i++, k++) {
//			swapAndCompare(i, i + half);	//core
			
			// add a pair (instruction) to the comparator
			COMPARER_LIST.get(comparerCounter).instructions.append(i + "-" + (i+half)+",");
			comparerCounter++;
		}
	}

	
	/*
	 * 
	 *  Not used functions
	 */
	private static void swapAndCompare(int xpos, int ypos) {
		if (NUMBERS[xpos] > NUMBERS[ypos]) {
			int temp = NUMBERS[xpos];
			NUMBERS[xpos] = NUMBERS[ypos];
			NUMBERS[ypos] = temp;
		}
	}

	private static void printOutArr() {

		for (int i = 1; i < NUMBERS.length; i++) {
			System.out.print(NUMBERS[i] + ",");
		}
		System.out.println("\n");
	}
}
