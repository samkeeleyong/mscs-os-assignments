package samkeeleyong.mscs.week2;

/*
 * 
 * Practice Class to try to generate instruction sets for comparers
 */
public class Scrap {
	
	static int[] arr = new int[]{0, 1, 3, 5, 9, 8, 6, 4, 2};
//									{0, 1, 4, 6, 7, 9, 11, 12, 16,
//			  						15, 14, 13, 10, 8, 5, 3, 2};
	static int N = arr.length - 1;
	
	static int comparerCounter = 0;
	
	public static void main(String[] args){
		int x = N;
		printOutArr();
		while(x != 1){
			int sectionCount = N/x,
				startSentinel = 0;
			int startSentinelIncrement = N/sectionCount;
			for(int i = 1; i <= sectionCount; i++){
				bitonicSwapsMove(x, 1 + startSentinel);
				startSentinel += startSentinelIncrement;
			}
			System.out.println();
			comparerCounter = 0;
			x /= 2;
			
//			printOutArr();
		}
	}
	
	public static void swapAndCompare(int xpos, int ypos){
		if(arr[xpos] > arr[ypos]){
			int temp = arr[xpos];
			arr[xpos] = arr[ypos];
			arr[ypos] = temp;	
		}
	}
	
	public static void bitonicSwapsMove(int length, int start){
		int half = length/2;
		for(int i = start, k = 1; k <= half; i++, k++){
			swapAndCompare(i, i + half);
			
			System.out.println("(" + i + " , " +  (i + half) + ") - " + comparerCounter);
			comparerCounter++;
		}
	}
	
	public static void printOutArr(){
		
		for(int i = 1; i < arr.length; i++){
			System.out.print(arr[i] + ",");
		}
		System.out.println("\n");
	}
}
