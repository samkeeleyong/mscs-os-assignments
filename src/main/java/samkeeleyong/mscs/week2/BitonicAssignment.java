package samkeeleyong.mscs.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BitonicAssignment {
	
	
	public static int[] NUMBERS = 
								   {0, 1, 4, 6, 4, 9, 11, 12, 16,
								   15, 14, 13, 12, 8, 5, 3, 2};
	public static int N = NUMBERS.length - 1;
	
	// This variable must equal to N so that
	// threads must continue
	public static AtomicInteger ROUND_COUNTER = new AtomicInteger(0);
	public static boolean IS_FINISHED = false;
	
	public static List<Comparer> COMPARER_LIST = new ArrayList<Comparer>();
	
	// k such that 2^k = N
	public static final int K = (int) (Math.log(N) / Math.log(2));
	public static final int TOTAL_BLOCKS_PART1 = sumOfOneToN(K - 1);
	public static final int TOTAL_BLOCKS_PART2 = K;
	public static final int TOTAL_BLOCKS = TOTAL_BLOCKS_PART1 + TOTAL_BLOCKS_PART2;;
	
	public static final boolean SHOULD_LOG = true; 
	
	public static void main(String[] args) throws InterruptedException{
		new BitonicAssignment().process();
	}
	
	public void process() throws InterruptedException{
		
		System.out.println("Starting: ");
		System.out.println("Number of Integers: " + N);
		

		/*
		 * 
		 *  Setup
		 */
		
		// create the comparers(threads/workers)
		for(int i = 1; i <= N/2; i++){
			Comparer comparer = new Comparer("Thread" + i, this);
			COMPARER_LIST.add(comparer);
		}
		
		// generate the instruction sets or basically draw the wires
		InstructionSetsGenerator.generateAndAssignInstructions();

		for(Comparer c: COMPARER_LIST){
			c.buildInstructionSet();	
		}
		

		/*
		 * 
		 * Magic 
		*/
		printNumbers("Initial Numbers are");
		
		for(Comparer c: COMPARER_LIST){
			System.out.println(c.getName() + ": " + c.instructions.toString());
			c.start();
		}
		System.out.println("ALL THREADS just started!\n");
		
		for(int i = 1; i <= TOTAL_BLOCKS; i++){
			
			while(BitonicAssignment.ROUND_COUNTER.get() < N/2){
				// the other threads are still working			
			}
			
			// wait for 
			synchronized(this){
				System.out.println("Threads are all stopped. Time to start them again, ROUND COUNTER IS " + ROUND_COUNTER.get());
				BitonicAssignment.ROUND_COUNTER.set(0);
				this.notifyAll();	
				printNumbers("");
			}
			System.out.println("Next Round");
		}
		BitonicAssignment.IS_FINISHED = true;
		Thread.sleep(1000);
		System.out.println("FINISHED");
		
		printNumbers("\nFinal Numbers are:");
		System.out.println("Are numbers sorted? " + isNumbersSorted());
	}

	
	/*
	 * 
	* Utility functions
	*/
	private boolean isNumbersSorted(){
		int max = 0;
		
		for(int i: NUMBERS){
			if(i < max){
				return false;
			}
			max = i;
		}
		return true;
	}
	
	private void printNumbers(String header) {
		if (!SHOULD_LOG){
			return;
		}
		if(!header.isEmpty()){
			System.out.println(header);
		}
		for(int i: NUMBERS){
			System.out.print(i + ",");
			if(i % 50 == 0 && i!= 0){
				System.out.println();
			}
		}
		System.out.println("");
	}
	
	private static int sumOfOneToN(int n) {
		int total = 0;
		for(int i = 1; i <= n; i++){
			total+=i;
		}
		return total;
	}
}
