package samkeeleyong.mscs.week1;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam Ong
 *
 */

public class PrimeAssignment {
    public static boolean IS_COMPOSITE = false;
    
    public static BigInteger ZERO = BigInteger.ZERO;
    public static BigInteger ONE = BigInteger.ONE;
    public static BigInteger TWO = new BigInteger("2");
    public static BigInteger THREE = new BigInteger("3");

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
    	BigInteger number = new BigInteger(args[0]),
    		       numberOfThreads = new BigInteger(args[1]);
    		
        
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
        System.out.printf("%s is %s\n", number.toString(), IS_COMPOSITE ? "composite":
                                                               "prime");

        for(ThreadMeta meta: metaList){
            System.out.printf("\n%s's last processed number is %s", meta.thread.getName(), meta.lastProcessedNumber.toString());
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
    public static List<ThreadMeta> delegateThreads(BigInteger number, BigInteger NUMBER_OF_THREADS){
        BigInteger start = THREE,
                   end = number; 
        
        List<ThreadMeta> metaList = new ArrayList<>();
        
        BigInteger total_length = end.subtract(start);
        BigInteger subrange_length = total_length.divide(NUMBER_OF_THREADS);
        BigInteger current_start = start;

        for(BigInteger i = ZERO; i.compareTo(NUMBER_OF_THREADS) == -1; i = i.add(ONE)){
        	ThreadMeta meta = new ThreadMeta();

        	meta.start = current_start.mod(TWO).equals(BigInteger.ZERO) ? current_start.subtract(ONE):
        																  current_start;
        	meta.end = current_start.add(subrange_length);
        	metaList.add(meta);
        	current_start = current_start.add(subrange_length).add(ONE);
        }

        // hack because i've spent too much time already.
        metaList.get(metaList.size() - 1).end = end;

        System.out.println("Ranges of the threads:");
        for(ThreadMeta meta: metaList){
        	System.out.println(meta);
        }
		return metaList;
    }
}