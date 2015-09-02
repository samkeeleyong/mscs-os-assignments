package samkeeleyong.mscs.week1;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PrimeAssignmentTabulationTest {
	private int number;
	private int numberOfThreads;
	
	public PrimeAssignmentTabulationTest(int number, int numberOfThreads){
		this.number = number;
		this.numberOfThreads = numberOfThreads;
	}
	@Parameterized.Parameters
	public static Collection testDate(){	
		return Arrays.asList(new Object[][]{
				{2147483601, 1},
				{2147483601, 2},
				{2147483601, 3},
				{2147483601, 4},
				{2147483601, 5},
				{2147483601, 6}
		});
	}
	
	@Test
	public void something() throws InterruptedException{
		
		String[] args = new String[]{
									 String.valueOf(number),
									 String.valueOf(numberOfThreads)};
		PrimeAssignment.main(args);
		assert true;
	}
}
