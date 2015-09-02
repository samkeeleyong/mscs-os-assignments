package samkeeleyong.mscs.week1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

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

	// [[3 - 229], [229 - 456], [457 - 683]]
	@Test
	public void testDelegateThreads() {
		list = PrimeAssignment.delegateThreads(465321, 3);

		ThreadMeta t1 = new ThreadMeta();
		t1.start = 3;
		t1.end = 229;
		ThreadMeta t2 = new ThreadMeta();
		t2.start = 229;
		t2.end = 456;
		ThreadMeta t3 = new ThreadMeta();
		t3.start = 457;
		t3.end = 683;
		ThreadMeta[] metaArr = new ThreadMeta[] { t1, t2, t3 };

		assertArrayEquals(metaArr, list.toArray());
	}

	// [[3 - 19], [19 - 36], [37 - 53], [53 - 68]]
	@Test
	public void testDelegateThreads2() {
		list = PrimeAssignment.delegateThreads(4561, 4);

		ThreadMeta t1 = new ThreadMeta();
			t1.start = 3;
			t1.end = 19;
		ThreadMeta t2 = new ThreadMeta();
			t2.start = 19;
			t2.end = 36;
		ThreadMeta t3 = new ThreadMeta();
			t3.start = 37;
			t3.end = 53;
		ThreadMeta t4 = new ThreadMeta();
			t4.start = 53;
			t4.end = 68;
		ThreadMeta[] metaArr = new ThreadMeta[] { t1, t2, t3, t4 };

		assertArrayEquals(metaArr, list.toArray());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testTooSmallInput() {
		list = PrimeAssignment.delegateThreads(11, 4);

		fail("should not come here");
	}
}
