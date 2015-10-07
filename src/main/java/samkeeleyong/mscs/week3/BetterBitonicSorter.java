package samkeeleyong.mscs.week3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BetterBitonicSorter {
	public static double startTime;
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException{
		CyclicBarrier barrierPoint = new CyclicBarrier(4096, new Runnable() {
			@Override
			public void run() {
//				System.out.println("Finished this round:" + (System.nanoTime() - startTime)/1_000_000_000);
			}
		});

		for(int i = 0 ; i < 4096; i ++){
			PrintNumber pn = new PrintNumber(barrierPoint);
			pn.start();
		}
		
		startTime = System.nanoTime();
		
//		barrierPoint.await();
//		System.out.println("Finished");
	}	
}

class PrintNumber extends Thread{
	CyclicBarrier barrier;
	
	PrintNumber(CyclicBarrier barrier){
		this.barrier = barrier;
	}
	
	public void run(){
		int counter = 0;
		for(int i = 1; i <= 4; i++){
//			System.out.println(i);
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}