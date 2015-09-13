package samkeeleyong.mscs.week2;

import static samkeeleyong.mscs.week2.BitonicAssignment.NUMBERS;

import java.util.ArrayList;
import java.util.List;

public class Comparer extends Thread{

	public Comparer(String name, BitonicAssignment LOCK){
		this.name = name;
		this.LOCK = LOCK;
	}
	
	public void buildInstructionSet() {
		
		String[] instructionsOfEachBlocks = this.instructions.toString().split(",");
		for(String strPair: instructionsOfEachBlocks){
			String[] pair = strPair.split("-");
			
			toSwaps.add(Integer.parseInt(pair[0]));
			toSwaps.add(Integer.parseInt(pair[1]));
		}
	}

	String name;
	StringBuilder instructions = new StringBuilder();
	final BitonicAssignment LOCK;
	List<Integer> toSwaps = new ArrayList<Integer>();
	int myCounter = 0;

	@Override
	public void run(){
		
		while(!BitonicAssignment.IS_FINISHED){
			synchronized(LOCK){
				compare();
				try {
					LOCK.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void compare(){
		int xpos = 0;
		int ypos = 0;
		try{
			xpos = toSwaps.get(myCounter);
			ypos = toSwaps.get(++myCounter);
		} catch(IndexOutOfBoundsException e){
			System.out.println(myCounter);
		}
		if (NUMBERS[xpos] > NUMBERS[ypos]) {
			int temp = NUMBERS[xpos];
			NUMBERS[xpos] = NUMBERS[ypos];
			NUMBERS[ypos] = temp;
		}
		myCounter++;
		BitonicAssignment.ROUND_COUNTER.incrementAndGet();
	}
}
