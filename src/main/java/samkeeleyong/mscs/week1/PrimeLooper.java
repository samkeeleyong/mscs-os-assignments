package samkeeleyong.mscs.week1;

import static samkeeleyong.mscs.week1.PrimeAssignment.*;

import java.math.BigInteger;
class PrimeLooper implements Runnable{

    public void run(){
    	meta.lastProcessedNumber = ZERO;
    	if(ThreadMeta.number.mod(TWO).equals(ZERO)){
    		meta.lastProcessedNumber = TWO;
    		System.out.printf("%s is divisible by %s\n", ThreadMeta.number.toString(), TWO.toString());
            PrimeAssignment.IS_COMPOSITE = true;
    	}
    	
        // loop through [3 - 1000] or [4564 - 6547]    	
    	for(BigInteger i = meta.start; i.compareTo(meta.end) == -1 && !PrimeAssignment.IS_COMPOSITE; i = i.add(TWO) ){
    		meta.lastProcessedNumber = i;
    		
    		if(ThreadMeta.number.mod(i).equals(ZERO)){
                System.out.printf("%s is divisible by %s\n", ThreadMeta.number.toString(), i.toString());
                PrimeAssignment.IS_COMPOSITE = true;    			
    		}
    	}
    }

    ThreadMeta meta;

    PrimeLooper(ThreadMeta meta){
        this.meta = meta;
    }
}