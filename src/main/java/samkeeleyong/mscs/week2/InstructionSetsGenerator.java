package samkeeleyong.mscs.week2;

import static samkeeleyong.mscs.week2.BitonicAssignment.N;
import static samkeeleyong.mscs.week2.BitonicAssignment.NUMBERS;


public class InstructionSetsGenerator {
	
	static int comparerCounter = 0;
	
	public static void generateAndAssignInstructions() {
//		part1();
		part2();
	}
	
	public static void part1(){
		int x = 2;
		int numberOfSections = 1; 
		int direction = 1;
		int sectionCounter = 2;
		
		while(x != BitonicAssignment.N){	// BLOCK LOOP 
			System.out.println("\n\n\nThis Block: x is :" + x);
			System.out.println("Number of Sections is :" + numberOfSections);
			
			sectionCounter = x;
			for(int i = 1 ; i <= numberOfSections; i++){	// SECTION LOOP
				System.out.println("This is a Section");
				System.out.println("Section Counter is :" + sectionCounter);
				
				for(int k = 1; k <= BitonicAssignment.N; k += sectionCounter){ //	Do Bitonic Merges
					bitonicMerge(sectionCounter, k, direction);
					direction = flipDirections(direction);
				}
				sectionCounter /= 2;
			}

			numberOfSections+=1;
			x*=2;
		}
	}
	private static void part2(){
		int x = BitonicAssignment.N;
 
		while (x != 1) {
			int sectionCount = N / x, startSentinel = 0;
			int startSentinelIncrement = N / sectionCount;
			for (int i = 1; i <= sectionCount; i++) {
				bitonicMerge(x, 1 + startSentinel, 1);
				startSentinel += startSentinelIncrement;
			}
			comparerCounter = 0;
			x /= 2;
		}		
	}

	private static void bitonicMerge(int length, int start, int direction) {
		
		int half = length / 2;	//core
		for (int i = start, k = 1; k <= half; i++, k++) {
//			swapAndCompare(i, i + half);	//core
			
			// add a pair (instruction) to the comparator
			System.out.println("\t" + i + "-" + (i+half)+"-" + direction + ",");
//			COMPARER_LIST.get(comparerCounter).instructions.append(i + "-" + (i+half)+"-" + direction + ",");
			comparerCounter++;
		}
	}

	/*
	 *  Father, forgive me for this function
	 */
	private static int flipDirections(int direction){
		return direction == 1 ? 0: 1;
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