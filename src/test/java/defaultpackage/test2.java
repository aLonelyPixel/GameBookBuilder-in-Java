package defaultpackage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class test2 {

	@Test
	public void firstTest() {
		assertTrue(true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testMockito1() {
		// mock creation
		List mockedList = mock(List.class);
		// using mock object - it does not throw any "unexpected interaction" exception
		mockedList.add("one");
		mockedList.clear();
		// selective, explicit, highly readable verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testMockito2() {
		// you can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);
		// stubbing appears before the actual execution
		when(mockedList.get(0)).thenReturn("first");
		// the following prints "first"
		System.out.println(mockedList.get(0));
		// the following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));
	}
}
