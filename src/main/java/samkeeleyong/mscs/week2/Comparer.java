package samkeeleyong.mscs.week2;

import static samkeeleyong.mscs.week2.BitonicSorter.NUMBERS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Comparer extends Thread {

	public Comparer(String name, Semaphore semaphore) {
		this.name = name;
		this.semaphore = semaphore;
	}

	public void buildInstructionSet() {

		String[] instructionsOfEachBlocks = this.instructions.toString().split(
				",");
		for (String strPair : instructionsOfEachBlocks) {
			String[] pair = strPair.split("-");

			toSwaps.add(Integer.parseInt(pair[0]));
			toSwaps.add(Integer.parseInt(pair[1]));
			toSwaps.add(Integer.parseInt(pair[2]));
		}
	}

	String name;
	StringBuilder instructions = new StringBuilder();
	Semaphore semaphore;
	List<Integer> toSwaps = new ArrayList<Integer>();
	int myCounter = 0;
	BitonicSorter LOCK;

	@Override
	public void run() {

		while (true) {
			
			semaphore.release();
			compare();	

			if(myCounter > toSwaps.size()){
				return;
			}
		}
	}

	public void compare() {
		int xpos = 0;
		int ypos = 0;
		int direction;
		try {
			xpos = toSwaps.get(myCounter);
			ypos = toSwaps.get(++myCounter);
			direction = toSwaps.get(++myCounter);

			if (direction == 1) {
				if (NUMBERS[xpos] > NUMBERS[ypos]) {
					int temp = NUMBERS[xpos];
					NUMBERS[xpos] = NUMBERS[ypos];
					NUMBERS[ypos] = temp;
				}
			}
			if (direction == 0) {
				if (NUMBERS[xpos] < NUMBERS[ypos]) {
					int temp = NUMBERS[xpos];
					NUMBERS[xpos] = NUMBERS[ypos];
					NUMBERS[ypos] = temp;
				}
			}

		} catch (IndexOutOfBoundsException e) {
			// System.out.println(myCounter);
			// just ignore this part
		}

		myCounter++;
		BitonicSorter.ROUND_COUNTER.incrementAndGet();
	}
}
