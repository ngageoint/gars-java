package mil.nga.gars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import mil.nga.gars.features.Point;

/**
 * GARS Test
 * 
 * @author osbornb
 */
public class GARSTest {

	/**
	 * Test parsing a GARS string value
	 * 
	 * @throws ParseException
	 *             upon failure to parse
	 */
	@Test
	public void testParse() throws ParseException {

		String garsValue = "001AA";
		assertTrue(GARS.isGARS(garsValue));
		GARS gars = GARS.parse(garsValue);
		assertEquals(1, gars.getLongitude());
		assertEquals("AA", gars.getLatitude());
		assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		Point point = gars.toPoint();
		assertEquals(-180.0, point.getLongitude(), 0);
		assertEquals(-90.0, point.getLatitude(), 0);
		GARS gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_QUADRANT
				+ GARSConstants.DEFAULT_KEYPAD, gars2.coordinate());

		garsValue = "001AW";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(1, gars.getLongitude());
		assertEquals("AW", gars.getLatitude());
		assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(-180.0, point.getLongitude(), 0);
		assertEquals(-80.0, point.getLatitude(), 0);
		gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_QUADRANT
				+ GARSConstants.DEFAULT_KEYPAD, gars2.coordinate());

		garsValue = "001QZ";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(1, gars.getLongitude());
		assertEquals("QZ", gars.getLatitude());
		assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(-180.0, point.getLongitude(), 0);
		assertEquals(89.5, point.getLatitude(), 0);
		gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_QUADRANT
				+ GARSConstants.DEFAULT_KEYPAD, gars2.coordinate());

		garsValue = "001QD";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(1, gars.getLongitude());
		assertEquals("QD", gars.getLatitude());
		assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(-180.0, point.getLongitude(), 0);
		assertEquals(79.5, point.getLatitude(), 0);
		gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_QUADRANT
				+ GARSConstants.DEFAULT_KEYPAD, gars2.coordinate());

		garsValue = "720AA";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(720, gars.getLongitude());
		assertEquals("AA", gars.getLatitude());
		assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(179.5, point.getLongitude(), 0);
		assertEquals(-90.0, point.getLatitude(), 0);
		gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_QUADRANT
				+ GARSConstants.DEFAULT_KEYPAD, gars2.coordinate());

		garsValue = "720AW";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(720, gars.getLongitude());
		assertEquals("AW", gars.getLatitude());
		assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(179.5, point.getLongitude(), 0);
		assertEquals(-80.0, point.getLatitude(), 0);
		gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_QUADRANT
				+ GARSConstants.DEFAULT_KEYPAD, gars2.coordinate());

		garsValue = "720QZ";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(720, gars.getLongitude());
		assertEquals("QZ", gars.getLatitude());
		assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(179.5, point.getLongitude(), 0);
		assertEquals(89.5, point.getLatitude(), 0);
		gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_QUADRANT
				+ GARSConstants.DEFAULT_KEYPAD, gars2.coordinate());

		garsValue = "720QD";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(720, gars.getLongitude());
		assertEquals("QD", gars.getLatitude());
		assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(179.5, point.getLongitude(), 0);
		assertEquals(79.5, point.getLatitude(), 0);
		gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_QUADRANT
				+ GARSConstants.DEFAULT_KEYPAD, gars2.coordinate());

		garsValue = "006AG";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(6, gars.getLongitude());
		assertEquals("AG", gars.getLatitude());
		assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(-177.5, point.getLongitude(), 0);
		assertEquals(-87.0, point.getLatitude(), 0);
		gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_QUADRANT
				+ GARSConstants.DEFAULT_KEYPAD, gars2.coordinate());

		garsValue = "006AG3";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(6, gars.getLongitude());
		assertEquals("AG", gars.getLatitude());
		assertEquals(3, gars.getQuadrant());
		assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(-177.5, point.getLongitude(), 0);
		assertEquals(-87.0, point.getLatitude(), 0);
		gars2 = GARS.from(point);
		assertEquals(gars, gars2);
		assertEquals(garsValue + GARSConstants.DEFAULT_KEYPAD,
				gars2.coordinate());

		garsValue = "006AG39";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(6, gars.getLongitude());
		assertEquals("AG", gars.getLatitude());
		assertEquals(3, gars.getQuadrant());
		assertEquals(9, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(-177.3333333, point.getLongitude(), 0.000001);
		assertEquals(-87.0, point.getLatitude(), 0);
		gars2 = GARS.from(Point.create(point.getLongitude() + 0.000001,
				point.getLatitude()));
		assertEquals(gars, gars2);
		assertEquals(garsValue, gars2.coordinate());

		garsValue = "006AG25";
		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);
		assertEquals(6, gars.getLongitude());
		assertEquals("AG", gars.getLatitude());
		assertEquals(2, gars.getQuadrant());
		assertEquals(5, gars.getKeypad());
		point = gars.toPoint();
		assertEquals(-177.1666667, point.getLongitude(), 0.000001);
		assertEquals(-86.6666667, point.getLatitude(), 0.000001);
		gars2 = GARS.from(Point.create(point.getLongitude(),
				point.getLatitude() + 0.000001));
		assertEquals(gars, gars2);
		assertEquals(garsValue, gars2.coordinate());

	}

	/**
	 * Test parsing an invalid GARS string value
	 */
	@Test
	public void testParseInvalid() {

		assertFalse(GARS.isGARS("1AA"));
		assertFalse(GARS.isGARS("01AA"));
		assertFalse(GARS.isGARS("001A"));
		assertFalse(GARS.isGARS("000AA"));
		assertFalse(GARS.isGARS("721AA"));
		assertFalse(GARS.isGARS("001RA"));
		assertFalse(GARS.isGARS("720ZZ"));
		assertFalse(GARS.isGARS("000AG3"));
		assertFalse(GARS.isGARS("721AG3"));
		assertFalse(GARS.isGARS("006RA3"));
		assertFalse(GARS.isGARS("006RA3"));
		assertFalse(GARS.isGARS("000AG39"));
		assertFalse(GARS.isGARS("721AG39"));
		assertFalse(GARS.isGARS("006RA39"));
		assertFalse(GARS.isGARS("006RA39"));
		assertFalse(GARS.isGARS("006AG09"));
		assertFalse(GARS.isGARS("006AG59"));
		assertFalse(GARS.isGARS("006AG30"));
		assertFalse(GARS.isGARS("006AG310"));

	}

}
