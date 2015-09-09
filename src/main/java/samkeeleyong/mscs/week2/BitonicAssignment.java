package samkeeleyong.mscs.week2;

import java.util.concurrent.atomic.AtomicInteger;

public class BitonicAssignment {
	
	public static int N = 16;
	public static int[] LIST = new int[N+1];

	
	// This variable must equal to N so that
	// threads must continue
	public static AtomicInteger ROUND_COUNTER = new AtomicInteger(0);
	public static boolean ROUND_CONTINUE = false;
	public static boolean IS_FINISHED = false;
	
	public static void main(String[] args) throws InterruptedException{
		new BitonicAssignment().process();
	}
	
	public void process() throws InterruptedException{
		
		for(int i = 1; i <= N; i++){
			Comparer comparer = new Comparer("Thread" + i, this);
			comparer.start();
		}

		for(int i = 1; i <= N; i++){
			
			while(BitonicAssignment.ROUND_COUNTER.get() < N){
				// the other threads are still working			
			}
			
			synchronized(this){
				System.out.println("Threads are all stopped. Time to start them again, ROUND COUNTER IS " + ROUND_COUNTER.get());
				BitonicAssignment.ROUND_COUNTER.set(0);
				this.notifyAll();	
			}
		}
		BitonicAssignment.IS_FINISHED = true;
		Thread.sleep(1000);
		System.out.println("FINISHED");
	}
}
