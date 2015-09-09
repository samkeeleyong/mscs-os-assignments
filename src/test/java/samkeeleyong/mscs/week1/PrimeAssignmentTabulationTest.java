package samkeeleyong.mscs.week1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PrimeAssignmentTabulationTest {
	private String number;
	private String numberOfThreads;
	
	public PrimeAssignmentTabulationTest(String  number, String numberOfThreads){
		this.number = number;
		this.numberOfThreads = numberOfThreads;
	}
	@Parameterized.Parameters
	public static Collection testDate(){	
		return Arrays.asList(new Object[][]{
				{"999933377799933377", "25"}
//				{"564212381", "5"}
//				{"564212381", "10"}
//				{"564212381", "20"}
//				{"564212381", "25"}
//				{"564212381", "50"}
//				{"564212381", "100"}
//				"48112959837082048697"), new BigInteger("20")},
//				"48112959837082048697"), new BigInteger("30")},
//				"48112959837082048697"), new BigInteger("40")},
//				{new BigInteger("48112959837082048697"), new BigInteger("50")},
//				{new BigInteger("48112959837082048697"), new BigInteger("60")},
		});
	}
	
	@Test
	public void something() throws InterruptedException{
		
		String[] args = new String[]{
									 number,
									 numberOfThreads};
		PrimeAssignment.main(args);
		assert true;
	}
}
