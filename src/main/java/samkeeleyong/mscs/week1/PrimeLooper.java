package samkeeleyong.mscs.week1;

class PrimeLooper implements Runnable{

    public void run(){
        if(ThreadMeta.number % 2 == 0){
            meta.lastProcessedNumber = 2;
            System.out.printf("%d is divisible by %d\n", ThreadMeta.number, 2);
            PrimeAssignment.IS_COMPOSITE = true;
        }

        // loop through [3 - 1000] or [4564 - 6547]
        for(int i = meta.start; i <= meta.end && !PrimeAssignment.IS_COMPOSITE; i+=2){
            meta.lastProcessedNumber = i;

            if(ThreadMeta.number % i == 0){
                System.out.printf("%d is divisible by %d\n", ThreadMeta.number, i);
                PrimeAssignment.IS_COMPOSITE = true;
            }
        }
    }

    ThreadMeta meta;

    PrimeLooper(ThreadMeta meta){
        this.meta = meta;
    }
}