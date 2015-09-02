package samkeeleyong.mscs.week1;


import java.util.*;
import java.lang.IllegalStateException;

/**
 * @author Sam Ong
 *
 */

public class PrimeAssignment {
    public static boolean IS_COMPOSITE = false;
    

    /*
     * 
     *  @desc The input is given via the args parameter
     *	@param args[0] is the number to check if prime
     *  		 	   e.g. is 123123 a prime?
     *  	   args[1] is the number of threads to spawn. 
     *  			   Having this argument as 1 would be equivalent to 
     *  			   being a "single-threaded app"
     */
    public static void main(String[] args) throws InterruptedException{
//        int number = 465321,
//            numberOfThreads = 3;
    	int number = Integer.parseInt(args[0]),
    		numberOfThreads = Integer.parseInt(args[1]);
    		
        
        List<ThreadMeta> metaList = delegateThreads(number, numberOfThreads);        
        ThreadMeta.number = number;

        for(ThreadMeta meta: metaList){
            Thread thread = new Thread(new PrimeLooper(meta));
            meta.thread = thread;
            thread.start();
        }

        for(ThreadMeta meta: metaList){
            meta.thread.join();
        }

        // After all the threads finish
        System.out.printf("%d is %s\n", number, IS_COMPOSITE ? "composite":
                                                               "prime");

        for(ThreadMeta meta: metaList){
            System.out.printf("\n%s's last processed number is %d", meta.thread.getName(), meta.lastProcessedNumber);
        }
    }

    /*
     *  @desc divides the number by numberOfThreads
     *  e.g. 17 maybe divided into 3 with
	 *  [[3 - 229], [229 - 456], [457 - 683]]
	 *  
	 *  @param number - The number to check if prime
	 *  @param number - number of threads
     */
    public static List<ThreadMeta> delegateThreads(int number, int numberOfThreads){
        int start = 3,
            end = (int)(Math.ceil(Math.sqrt(number)));
        List<ThreadMeta> metaList = new ArrayList<>();
        
        if(numberOfThreads >= end){
            throw new IllegalStateException("Number of Threads too big!");
        }

        int total_length = end - start;
        int subrange_length = (int) (Math.ceil((total_length / numberOfThreads)));
        int current_start = start;

        for (int i = 1; i <= numberOfThreads; ++i) {

            ThreadMeta meta = new ThreadMeta();
            meta.start = current_start % 2 == 0 ? current_start - 1:
                                                  current_start;
            meta.end = (current_start + subrange_length);
            metaList.add(meta);

            current_start += subrange_length + 1;
        }

        // hack because i've spent too much time already.
        metaList.get(metaList.size() - 1).end = end;

        System.out.println("Ranges of the threads:" + metaList);
		return metaList;
    }
}