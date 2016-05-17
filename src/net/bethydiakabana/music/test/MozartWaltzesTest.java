package net.bethydiakabana.music.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

import net.bethydiakabana.music.MozartWaltzes;

/**
 * Test for {@link MozartWaltzes}
 * 
 * @author Bethy Diakabana
 *
 */
public final class MozartWaltzesTest {
	private static final int TEST_ITERATIONS = 200;

	/**
	 * Tests the results of the private method <tt>throwDice()</tt> by rolling
	 * two fair dice with two-hundred iterations. Returns true each iteration if
	 * the result is between 2 and 12. The total will be the sum of the two
	 * dice.
	 * <p>
	 * <b>NOTE</b>: this private method is tested to obtain maximum code
	 * coverage for this project
	 * 
	 * @throws IllegalAccessException
	 *             if test does not have access to the private method
	 * @throws IllegalArgumentException
	 *             if illegal arguements are passed to the reflecting method
	 * @throws InvocationTargetException
	 *             wraps around exceptions thrown by reflecting method
	 * @throws NoSuchMethodException
	 *             if method can't be found
	 * @throws SecurityException
	 *             if there's a breach in method security
	 */
	@Test
	public void testRollDiceForMinuet() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		@SuppressWarnings("rawtypes")
		Class[] args = {Integer.TYPE};
		Method method = MozartWaltzes.class.getDeclaredMethod("throwDice", args);
		method.setAccessible(true);
		int smallestDieNumber = 2;
		int largestDieNumber = 12;
		int smallestInvalidDieNumber = 1;
		int largestInvalidDieNumber = 13;
		int numberOfDice = 2;
		int diceRoll = 0;
		for (int i = 0; i < TEST_ITERATIONS; i++) {
			diceRoll = (int) method.invoke(new MozartWaltzes(), numberOfDice);
			assertTrue(smallestDieNumber <= diceRoll && diceRoll <= largestDieNumber);
			assertFalse(diceRoll <= smallestInvalidDieNumber && diceRoll >= largestInvalidDieNumber);
		} // end for
	} // end testRollDiceForMinuet

	/**
	 * Tests the results of the private method <tt>throwDice()</tt> by rolling
	 * one fair die with two-hundred iterations. Returns true each iteration if
	 * the result is between 1 and 6.
	 * <p>
	 * <b>NOTE</b>: this private method is tested to obtain maximum code
	 * coverage for this project
	 * 
	 * @throws IllegalAccessException
	 *             if test does not have access to the private method
	 * @throws IllegalArgumentException
	 *             if illegal arguements are passed to the reflecting method
	 * @throws InvocationTargetException
	 *             wraps around exceptions thrown by reflecting method
	 * @throws NoSuchMethodException
	 *             if method can't be found
	 * @throws SecurityException
	 *             if there's a breach in method security
	 */
	@Test
	public void testThrowDieForTrio() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		@SuppressWarnings("rawtypes")
		Class[] args = {Integer.TYPE};
		Method method = MozartWaltzes.class.getDeclaredMethod("throwDice", args);
		method.setAccessible(true);
		int smallestDieNumber = 1;
		int largestDieNumber = 6;
		int smallestInvalidDieNumber = 0;
		int largestInvalidDieNumber = 7;
		int numberOfDice = 1;
		int diceRoll = 0;
		for (int i = 0; i < TEST_ITERATIONS; i++) {
			diceRoll = (int) method.invoke(new MozartWaltzes(), numberOfDice);
			assertTrue(smallestDieNumber <= diceRoll && diceRoll <= largestDieNumber);
			assertFalse(diceRoll <= smallestInvalidDieNumber && diceRoll >= largestInvalidDieNumber);
		} // end for
	} // end testRollDiceForMinuet

	/**
	 * Tests the results of the private method <tt>throwDice()</tt> by passing
	 * 0 to the method parameter. Returns true if the return value is less than or 
	 * equal to 0, or if the dice roll is not equal to zero when a positive number
	 * integer passed as a parameter
	 * <p>
	 * <b>NOTE</b>: this private method is tested to obtain maximum code
	 * coverage for this project
	 * 
	 * @throws IllegalAccessException
	 *             if test does not have access to the private method
	 * @throws IllegalArgumentException
	 *             if illegal arguements are passed to the reflecting method
	 * @throws InvocationTargetException
	 *             wraps around exceptions thrown by reflecting method
	 * @throws NoSuchMethodException
	 *             if method can't be found
	 * @throws SecurityException
	 *             if there's a breach in method security
	 */
	@Test
	public void testThrowDieForZeroAndBelow() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		@SuppressWarnings("rawtypes")
		Class[] args = {Integer.TYPE};
		Method method = MozartWaltzes.class.getDeclaredMethod("throwDice", args);
		method.setAccessible(true);
		int numberOfDice = 0;
		int expectedRolls = 0;
		int actualRolls = 0;

		actualRolls = (int) method.invoke(new MozartWaltzes(), numberOfDice);
		assertEquals(expectedRolls, actualRolls);

		numberOfDice = -1; 
		actualRolls = (int) method.invoke(new MozartWaltzes(), numberOfDice);
		assertEquals(expectedRolls, actualRolls);
		
		numberOfDice = 2;
		actualRolls = (int) method.invoke(new MozartWaltzes(), numberOfDice);
		assertFalse(expectedRolls == actualRolls);
	} // end testThrowDieWithZeroDice
	
	/**
	 * Tests the private method <tt>selectMeasures()</tt> by checking for
	 * invalid file names in the resulting <tt>List</tt> of psudeorandomly
	 * selected minuet and trio measures. Returns true if the list does not
	 * contain invalid measures
	 * <p>
	 * <b>NOTE</b>: this private method is tested to obtain maximum code
	 * coverage for this project
	 * 
	 * @throws IllegalAccessException
	 *             if test does not have access to the private method
	 * @throws IllegalArgumentException
	 *             if illegal arguements are passed to the reflecting method
	 * @throws InvocationTargetException
	 *             wraps around exceptions thrown by reflecting method
	 * @throws NoSuchMethodException
	 *             if method can't be found
	 * @throws SecurityException
	 *             if there's a breach in method security
	 */
	@Test
	public void testSelectMeasures() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method method = MozartWaltzes.class.getDeclaredMethod("selectMeasures");
		method.setAccessible(true);
		int measures = 32;
		for (int i = 0; i < TEST_ITERATIONS; i++) {
			@SuppressWarnings("unchecked")
			List<String> waltzMeasures = (List<String>) method.invoke(new MozartWaltzes(), new Object[0]);
			int arraySize = waltzMeasures.size();
			assertTrue(arraySize == measures);
			assertFalse(arraySize < measures || arraySize > measures);
			assertFalse(waltzMeasures.contains("M0") || waltzMeasures.contains("T0"));
			assertFalse(waltzMeasures.contains("M178") || waltzMeasures.contains("T97"));
		} // end for
	} // end method testComposeWaltzes

	/**
	 * Returns when the method is able to find every measure file and play it
	 * without interruption
	 */
	@Test
	public void testComposeWalts() {
		assertTrue(new MozartWaltzes().composeWaltz());
	} // end method testComposeWaltz

		@Test
	public void testSaveWaltz() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		Method method = MozartWaltzes.class.getDeclaredMethod("selectMeasures");
		method.setAccessible(true);
		@SuppressWarnings("unchecked")
		List<String> waltzMeasures = (List<String>) method.invoke(new MozartWaltzes(), new Object[0]);
		List<File> audioFiles = new ArrayList<File>();
		for (String measure: waltzMeasures)
			audioFiles.add(new File("src/resources/" + measure + ".wav"));

		Date creationDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("_yyyy_MM_dd_H_mm_ss");
		String fileName = "Waltz" + formatter.format(creationDate) + ".wav";
		assertTrue(new MozartWaltzes().saveWaltz(audioFiles, fileName));

	}
}
