package samkeeleyong.mscs.week3;

import static samkeeleyong.mscs.week2.BitonicSorter.N;

public class InstructionSetsGenerator {

	static int comparerCounter = 0;

	public static void generateAndAssignInstructions() {
		ADD_INSTRUCTIONS_TO_CONVERT_TO_A_BITONIC_SEQUENCE();
		ADD_INSTRUCTIONS_TO_BITONIC_MERGE();
	}

	/*
	 * creates swap instructions to create a bitonic SEQUENCE do not open method
	 * fold if you don't want to cry
	 */
	public static void ADD_INSTRUCTIONS_TO_CONVERT_TO_A_BITONIC_SEQUENCE() {
		int x = 2;
		int numberOfSections = 1;
		int direction = 1;
		int sectionCounter = 2;
		int directionCounterChecker = 1, directionCounter = 1;

		while (x != BitonicSorter.N) { // BLOCK LOOP

			sectionCounter = x;
			directionCounterChecker = 1;

			for (int sectionLoopIndex = 1; sectionLoopIndex <= numberOfSections; sectionLoopIndex++) { // SECTION
																										// LOOP
				for (int linePartLoopIndex = 1; linePartLoopIndex <= BitonicSorter.N; linePartLoopIndex += sectionCounter) { // Do
																																// Bitonic
																																// Merges
					bitonicMerge(sectionCounter, linePartLoopIndex, direction);

					if (directionCounterChecker == directionCounter) {
						direction = flipDirections(direction);
						directionCounter = 1;
					} else {
						directionCounter++;
					}
				}
				directionCounterChecker *= 2;
				sectionCounter /= 2;
				comparerCounter = 0;
			}

			numberOfSections += 1;
			x *= 2;
		}
	}

	/*
	 * creates swap instructions to create to do a bitonic MERGE do not open
	 * method fold if you don't want to cry
	 */
	private static void ADD_INSTRUCTIONS_TO_BITONIC_MERGE() {
		int x = BitonicSorter.N;
		try{
		while (x != 1) {
			int sectionCount = N / x, 
				startSentinel = 0;
			int startSentinelIncrement = N / sectionCount;
			for (int i = 1; i <= sectionCount; i++) {
				bitonicMerge(x, 1 + startSentinel, 1);
				startSentinel += startSentinelIncrement;
			}
			comparerCounter = 0;
			x /= 2;
		}
		} catch(Exception e){
			
		}
//		System.out.println(comparerCounter);
	}

	private static void bitonicMerge(int length, int start, int direction) {

		int half = length / 2; // core
		for (int i = start, k = 1; k <= half; i++, k++) {

			BitonicSorter.COMPARER_LIST.get(comparerCounter).instructions
					.append(i + "-" + (i + half) + "-" + direction + ",");
			comparerCounter++;
		}
	}

	/*
	 * Father, forgive me for this function probably should have been a string
	 * or AN ENUMMMM ?????
	 * 
	 * definitely an enum
	 */
	private static int flipDirections(int direction) {
		return direction == 1 ? 0 : 1;
	}
}