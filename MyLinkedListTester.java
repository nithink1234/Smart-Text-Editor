/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
		shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

	}

	/**
	 * Test if the get method is working correctly.
	 */
	/*
	 * You should not need to add much to this method. We provide it as an
	 * example of a thorough test.
	 */
	@Test
	public void testGet() {
		// test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		// System.out.println(shortList.get(0));
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

	}

	/**
	 * Test adding an element into the end of the list, specifically public
	 * boolean add(E element)
	 */
	@Test
	public void testAddEnd() {
		// TODO: implement this test

		shortList.add("C");
		assertEquals("Check Last", "C", shortList.get(shortList.size - 1));
		assertEquals("Check Size", 3, shortList.size);

		shortList.add(shortList.size - 1, "D");
		assertEquals("Check Size", 4, shortList.size);

		try {
			shortList.add(null);
		} catch (NullPointerException e) {

		}

		try {
			shortList.add(shortList.size - 1, null);
		} catch (NullPointerException e) {

		}
	}

	/** Test the size of the list */
	@Test
	public void testSize() {
		// TODO: implement this test
		
		try {
			int size = emptyList.size;
		} catch (NullPointerException e) {

		}
		
		assertEquals("Check Size",2 ,shortList.size );
	}

	/**
	 * Test adding an element into the list at a specified index, specifically:
	 * public void add(int index, E element)
	 */
	@Test
	public void testAddAtIndex() {
		// TODO: implement this test

		list1.add(0,86);
		assertEquals("Add: check a is correct ", (Integer)86, list1.get(0));
		assertEquals("Add: check size is correct ", 4, list1.size());

		// Adding Last element

		longerList.add(longerList.size - 1,10);
		assertEquals("Add: check element 10 is correct ", (Integer) 10, longerList.get(longerList.size - 2));
		assertEquals("Add: check size is correct ", 11, longerList.size());

		// Adding element inbtw

		longerList.add(4,5);
		assertEquals("Add: check element 3 is correct ", (Integer) 3, longerList.get(3));
		assertEquals("Add: check element 4 is correct ", (Integer) 5, longerList.get(4));
		assertEquals("Add: check element 5 is correct ", (Integer) 4, longerList.get(5));
		assertEquals("Add: check size is correct ", 12, longerList.size());

		// Removing out of bound
		try {
			longerList.add(longerList.size(), 10);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			longerList.add(-1,10);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
	}

	/** Test setting an element in the list */
	@Test
	public void testSet() {
		// TODO: implement this test

		// TODO: implement this test

		list1.set(0,13);
		assertEquals("Set: check a is correct ", (Integer)13, list1.get(0));

		// Adding Last element

		longerList.set(longerList.size - 1,34);
		assertEquals("Set: check element 10 is correct ", (Integer) 34, longerList.get(longerList.size - 1));

		// Adding element inbtw

		longerList.set(4,23);
		assertEquals("Set: check element 3 is correct ", (Integer) 3, longerList.get(3));
		assertEquals("Set: check element 4 is correct ", (Integer) 23, longerList.get(4));
		assertEquals("Set: check element 5 is correct ", (Integer) 5, longerList.get(5));

		// Removing out of bound
		try {
			longerList.set(longerList.size(), 10);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			longerList.set(-1,10);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
	
		
	}

	/**
	 * Test removing an element from the list. We've included the example from
	 * the concept challenge. You will want to add more tests.
	 */
	@Test
	public void testRemove() {
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

		// TODO: Add more tests here

		// Removing Last element

		int b = longerList.remove(longerList.size - 1);
		assertEquals("Remove: check b is correct ", 9, b);
		assertEquals("Remove: check element 8 is correct ", (Integer) 8, longerList.get(longerList.size - 1));
		assertEquals("Remove: check size is correct ", 9, longerList.size());

		// Removing element inbtw

		int c = longerList.remove(4);
		assertEquals("Remove: check b is correct ", 4, c);
		assertEquals("Remove: check element 3 is correct ", (Integer) 3, longerList.get(3));
		assertEquals("Remove: check element 4 is correct ", (Integer) 5, longerList.get(4));
		assertEquals("Remove: check size is correct ", 8, longerList.size());

		// Removing out of bound
		try {
			longerList.remove(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			longerList.remove(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
	}

	// TODO: Optionally add more test methods.

}
