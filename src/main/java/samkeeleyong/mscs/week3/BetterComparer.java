package samkeeleyong.mscs.week3;

import static samkeeleyong.mscs.week2.BitonicSorter.NUMBERS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class BetterComparer extends Thread {

	public BetterComparer(String name, CyclicBarrier barrierPoint) {
		this.name = name;
		this.barrierPoint = barrierPoint;
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
	CyclicBarrier barrierPoint;
	List<Integer> toSwaps = new ArrayList<Integer>();
	int myCounter = 0;

	@Override
	public void run() {
		for (int i = 0; i < toSwaps.size() / 3; i++) {
			compare();
			try {
				barrierPoint.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
//			 System.out.println(myCounter);
			// just ignore this part
		}
	}
}
