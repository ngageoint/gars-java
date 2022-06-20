package mil.nga.gars;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * GARS Utils Test
 * 
 * @author osbornb
 */
public class GARSUtilsTest {

	/**
	 * Test latitude band number values
	 */
	@Test
	public void testBandValue() {

		assertEquals(1, GARSUtils.bandValue('A'));
		assertEquals(24, GARSUtils.bandValue('Z'));
		assertEquals(1, GARSUtils.bandValue("AA"));
		assertEquals(360, GARSUtils.bandValue("QZ"));

	}

	/**
	 * Test latitude band letters
	 */
	@Test
	public void testBandLetters() {

		assertEquals('A', GARSUtils.bandLetter(1));
		assertEquals('Z', GARSUtils.bandLetter(24));
		assertEquals("AA", GARSUtils.bandLetters(1));
		assertEquals("AZ", GARSUtils.bandLetters(24));
		assertEquals("BA", GARSUtils.bandLetters(25));
		assertEquals("PZ", GARSUtils.bandLetters(336));
		assertEquals("QA", GARSUtils.bandLetters(337));
		assertEquals("QZ", GARSUtils.bandLetters(360));

	}

	/**
	 * Test quadrants
	 */
	@Test
	public void testQuadrant() {

		assertEquals(1, GARSUtils.quadrant(0, 1));
		assertEquals(2, GARSUtils.quadrant(1, 1));
		assertEquals(3, GARSUtils.quadrant(0, 0));
		assertEquals(4, GARSUtils.quadrant(1, 0));

	}

	/**
	 * Test keypad
	 */
	@Test
	public void testKeypad() {

		assertEquals(1, GARSUtils.keypad(0, 2));
		assertEquals(2, GARSUtils.keypad(1, 2));
		assertEquals(3, GARSUtils.keypad(2, 2));
		assertEquals(4, GARSUtils.keypad(0, 1));
		assertEquals(5, GARSUtils.keypad(1, 1));
		assertEquals(6, GARSUtils.keypad(2, 1));
		assertEquals(7, GARSUtils.keypad(0, 0));
		assertEquals(8, GARSUtils.keypad(1, 0));
		assertEquals(9, GARSUtils.keypad(2, 0));

	}

}
