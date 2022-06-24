package mil.nga.gars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import mil.nga.gars.features.Point;
import mil.nga.gars.grid.GridRange;
import mil.nga.gars.grid.GridType;

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

	/**
	 * Test parsing a MGRS string value
	 * 
	 * @throws ParseException
	 *             upon failure to parse
	 */
	@Test
	public void testCoordinate() throws ParseException {

		String gars = "419NV11";
		testCoordinate(29.06757, 63.98863, gars);
		testCoordinateMeters(3235787.09, 9346877.48, gars);

		gars = "468JN14";
		testCoordinate(53.51, 12.40, gars);
		testCoordinateMeters(5956705.95, 1391265.16, gars);

		gars = "045KG17";
		testCoordinate(-157.916861, 21.309444, gars);
		testCoordinateMeters(-17579224.55, 2428814.96, gars);

		gars = "395JE45";
		testCoordinate(17.3714337, 8.1258235, gars);
		testCoordinateMeters(1933779.15, 907610.20, gars);

		gars = "204LQ37";
		testCoordinate(-78.5, 37.0, gars);
		testCoordinateMeters(-8738580.027271975, 4439106.787250587, gars);

		gars = "204LQ27";
		testCoordinate(Point.create(-78.25, 37.25), gars);
		testCoordinateMeters(-8710750.154573657, 4474011.088229478, gars);

		gars = "204LQ25";
		testCoordinate(-78.16666666, 37.33333334, gars);
		testCoordinateMeters(-8701473.529598756, 4485671.563830873, gars);

		gars = "204LQ23";
		testCoordinate(-78.08333333, 37.41666667, gars);
		testCoordinateMeters(-8692196.905737048, 4497344.980476594, gars);

	}

	/**
	 * Test parsing 30 minute full range
	 * 
	 * @throws ParseException
	 *             upon failure to parse
	 */
	@Test
	public void test30MinuteParse() throws ParseException {

		GridRange gridRange = new GridRange();

		int count = 0;

		int number = GARSConstants.MIN_BAND_NUMBER;
		int letters = GARSConstants.MIN_BAND_LETTERS_NUMBER;
		double lon = GARSConstants.MIN_LON;
		double lat = GARSConstants.MIN_LAT;

		for (GARS gars : gridRange) {

			int bandNumber = gars.getLongitude();
			String bandLetters = gars.getLatitude();

			assertEquals(number, bandNumber);
			assertEquals(GARSUtils.bandLetters(letters), bandLetters);
			assertEquals(GARSConstants.DEFAULT_QUADRANT, gars.getQuadrant());
			assertEquals(GARSConstants.DEFAULT_KEYPAD, gars.getKeypad());

			Point point = gars.toPoint();

			assertEquals(lon, point.getLongitude(), 0);
			assertEquals(lat, point.getLatitude(), 0);

			letters++;
			lat += GridType.THIRTY_MINUTE.getPrecision();
			if (letters > GARSConstants.MAX_BAND_LETTERS_NUMBER) {
				letters = GARSConstants.MIN_BAND_LETTERS_NUMBER;
				lat = GARSConstants.MIN_LAT;
				number++;
				lon += GridType.THIRTY_MINUTE.getPrecision();
			}

			count++;
		}

		assertEquals(GARSConstants.MAX_BAND_NUMBER
				* GARSConstants.MAX_BAND_LETTERS_NUMBER, count);
	}

	/**
	 * Test the WGS84 coordinate with expected GARS coordinate
	 * 
	 * @param longitude
	 *            longitude in degrees
	 * @param latitude
	 *            latitude in degrees
	 * @param value
	 *            GARS value
	 * @throws ParseException
	 *             upon failure to parse
	 */
	private void testCoordinate(double longitude, double latitude, String value)
			throws ParseException {
		Point point = Point.create(longitude, latitude);
		testCoordinate(point, value);
		testCoordinate(point.toMeters(), value);
	}

	/**
	 * Test the WGS84 coordinate with expected GARS coordinate
	 * 
	 * @param longitude
	 *            longitude in degrees
	 * @param latitude
	 *            latitude in degrees
	 * @param value
	 *            GARS value
	 * @throws ParseException
	 *             upon failure to parse
	 */
	private void testCoordinateMeters(double longitude, double latitude,
			String value) throws ParseException {
		Point point = Point.meters(longitude, latitude);
		testCoordinate(point, value);
		testCoordinate(point.toDegrees(), value);
	}

	/**
	 * Test the coordinate with expected GARS coordinate
	 * 
	 * @param point
	 *            point
	 * @param value
	 *            GARS value
	 * @throws ParseException
	 *             upon failure to parse
	 */
	private void testCoordinate(Point point, String value)
			throws ParseException {

		GARS gars = point.toGARS();
		assertEquals(value, gars.toString());
		assertEquals(value, gars.coordinate());
		assertEquals(value, gars.coordinate(GridType.FIVE_MINUTE));
		assertEquals(value, gars.coordinate(null));

		assertEquals(value.substring(0, 5),
				gars.coordinate(GridType.TWENTY_DEGREE));
		assertEquals(value.substring(0, 5),
				gars.coordinate(GridType.TEN_DEGREE));
		assertEquals(value.substring(0, 5),
				gars.coordinate(GridType.FIVE_DEGREE));
		assertEquals(value.substring(0, 5),
				gars.coordinate(GridType.ONE_DEGREE));
		assertEquals(value.substring(0, 5),
				gars.coordinate(GridType.THIRTY_MINUTE));
		assertEquals(value.substring(0, 6),
				gars.coordinate(GridType.FIFTEEN_MINUTE));

	}

}
