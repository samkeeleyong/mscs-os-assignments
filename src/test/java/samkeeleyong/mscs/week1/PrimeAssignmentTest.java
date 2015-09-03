package samkeeleyong.mscs.week1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PrimeAssignmentTest {

	List<ThreadMeta> list;

	@Before
	public void setup() {
		list = new ArrayList<>();
	}

	// [[3 - 155109], [155109 - 310216], [310217 - 465321]]
	@Test
	public void testDelegateThreads() {
		list = PrimeAssignment.delegateThreads(new BigInteger("465321"), new BigInteger("3"));

		ThreadMeta t1 = new ThreadMeta();
		t1.start = new BigInteger("3");
		t1.end = new BigInteger("155109");
		ThreadMeta t2 = new ThreadMeta();
		t2.start = new BigInteger("155109");
		t2.end = new BigInteger("310216");
		ThreadMeta t3 = new ThreadMeta();
		t3.start = new BigInteger("310217");
		t3.end = new BigInteger("465321");
		ThreadMeta[] metaArr = new ThreadMeta[] { t1, t2, t3 };

		assertArrayEquals(metaArr, list.toArray());
	}

	// [[3 - 1142], [1143 - 2282], [2283 - 3422], [3423 - 4561]]
	@Test
	public void testDelegateThreads2() {
		list = PrimeAssignment.delegateThreads(new BigInteger("4561"), new BigInteger("4"));

		ThreadMeta t1 = new ThreadMeta();
			t1.start = new BigInteger("3");
			t1.end = new BigInteger("1142");
		ThreadMeta t2 = new ThreadMeta();
			t2.start = new BigInteger("1143");
			t2.end = new BigInteger("2282");
		ThreadMeta t3 = new ThreadMeta();
			t3.start = new BigInteger("2283");
			t3.end = new BigInteger("3422");
		ThreadMeta t4 = new ThreadMeta();
			t4.start = new BigInteger("3423");
			t4.end = new BigInteger("4561");
		ThreadMeta[] metaArr = new ThreadMeta[] { t1, t2, t3, t4 };

		assertArrayEquals(metaArr, list.toArray());
	}
}
