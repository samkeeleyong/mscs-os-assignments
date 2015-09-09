package samkeeleyong.mscs.week2;

public class Comparer extends Thread{

	public Comparer(String name, BitonicAssignment master){
		this.name = name;
		this.master = master;
	}
	
	String name;
	String instructions;
	final BitonicAssignment master;
	int myCounter = 0;

	@Override
	public void run(){
		
		while(!BitonicAssignment.IS_FINISHED){
			synchronized(master){
				compare();
				System.out.println(myCounter);
				try {
					master.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void compare(){
		myCounter++;
		BitonicAssignment.ROUND_COUNTER.incrementAndGet();
	}
}
