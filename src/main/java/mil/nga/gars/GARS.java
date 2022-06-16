package mil.nga.gars;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mil.nga.gars.features.Point;
import mil.nga.gars.grid.GridType;

/**
 * Global Area Reference System Coordinate
 * 
 * @author osbornb
 */
public class GARS {

	/**
	 * GARS string pattern
	 */
	private static final Pattern garsPattern = Pattern.compile(
			"^(\\d{3})([A-HJ-NP-Z]{2})(?:([1-4])([1-9]?))?",
			Pattern.CASE_INSENSITIVE);

	/**
	 * The array of GARs letters.
	 */
	private static char[] letter_array = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };

	/**
	 * The array of GARs grid numbers.
	 */
	private static int[][] five_minute_array = { { 7, 4, 1 }, { 8, 5, 2 },
			{ 9, 6, 3 } };

	/**
	 * Longitudinal band number
	 */
	private int longitude;

	/**
	 * Latitudinal band letters
	 */
	private String latitude;

	/**
	 * 15 minute quadrant
	 */
	private int quadrant;

	/**
	 * 5 minute ninth
	 */
	private int ninth;

	/**
	 * Create
	 * 
	 * @param longitude
	 *            longitudinal band number
	 * @param latitude
	 *            latitudinal band letters
	 * @param quadrant
	 *            15 minute quadrant
	 * @param ninth
	 *            5 minute ninth
	 * @return GARS
	 */
	public static GARS create(int longitude, String latitude, int quadrant,
			int ninth) {
		return new GARS(longitude, latitude, quadrant, ninth);
	}

	/**
	 * Constructor
	 * 
	 * @param longitude
	 *            longitudinal band number
	 * @param latitude
	 *            latitudinal band letters
	 * @param quadrant
	 *            15 minute quadrant
	 * @param ninth
	 *            5 minute ninth
	 */
	public GARS(int longitude, String latitude, int quadrant, int ninth) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.quadrant = quadrant;
		this.ninth = ninth;
	}

	/**
	 * Get the longitudinal band number
	 * 
	 * @return longitude band number
	 */
	public int getLongitude() {
		return longitude;
	}

	/**
	 * Get the latitudinal band letters
	 * 
	 * @return latitude band letters
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * Get the 15 minute quadrant
	 * 
	 * @return quadrant
	 */
	public int getQuadrant() {
		return quadrant;
	}

	/**
	 * Get the 5 minute ninth
	 * 
	 * @return ninth
	 */
	public int getNinth() {
		return ninth;
	}

	/**
	 * Get the GARS coordinate with five minute precision
	 * 
	 * @return GARS coordinate
	 */
	public String coordinate() {
		return coordinate(GridType.FIVE_MINUTE);
	}

	/**
	 * Get the GARS coordinate with specified grid precision
	 * 
	 * @param type
	 *            grid type precision
	 * @return GARS coordinate
	 */
	public String coordinate(GridType type) {

		StringBuilder gars = new StringBuilder();

		if (type == null) {
			type = GridType.FIFTEEN_MINUTE;
		}

		gars.append(String.format("%03d", longitude));
		gars.append(latitude);

		if (type == GridType.FIFTEEN_MINUTE || type == GridType.FIVE_MINUTE) {

			gars.append(quadrant);

			if (type == GridType.FIVE_MINUTE) {

				gars.append(ninth);

			}

		}

		return gars.toString();
	}

	/**
	 * Convert to a point
	 * 
	 * @return point
	 */
	public Point toPoint() {
		// TODO
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return coordinate();
	}

	/**
	 * Return whether the given string is valid GARS string
	 *
	 * @param gars
	 *            potential GARS string
	 * @return true if GARS string is valid, false otherwise
	 */
	public static boolean isGARS(String gars) {
		gars = removeSpaces(gars);
		return garsPattern.matcher(gars).matches();
	}

	/**
	 * Removed spaces from the value
	 * 
	 * @param value
	 *            value string
	 * @return value without spaces
	 */
	private static String removeSpaces(String value) {
		return value.replaceAll("\\s", "");
	}

	/**
	 * Encodes a point as a GARS string
	 *
	 * @param point
	 *            point
	 * @return GARS
	 */
	public static GARS from(Point point) {
		return from(point.getLongitude(), point.getLatitude());
	}

	/**
	 * Convert the coordinate to GARS
	 *
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @return GARS
	 */
	public static GARS from(double longitude, double latitude) {
		/* North pole is an exception, read over and down */
		if (latitude == 90.0) {
			latitude = 89.99999999999;
		}
		// Check for valid lat/lon range
		if (latitude < -90 || latitude > 90) {
			return null;
		}
		if (longitude < -180 || longitude > 180) {
			return null;
		}
		// Get the longitude band ==============================================
		double longBand = longitude + 180;
		// Normalize to 0.0 <= longBand < 360
		while (longBand < 0) {
			longBand = longBand + 360;
		}
		while (longBand > 360) {
			longBand = longBand - 360;
		}
		longBand = Math.floor(longBand * 2.0);
		int intLongBand = (int) (longBand + 1); // Start at 001, not 000

		// Get the latitude band ===============================================
		double offset = latitude + 90;
		// Normalize offset to 0 < offset <90
		while (offset < 0) {
			offset = offset + 180;
		}
		while (offset > 180) {
			offset = offset - 180;
		}
		offset = Math.floor(offset * 2.0);
		int firstOffest = (int) Math.floor(offset / letter_array.length);
		int secondOffest = (int) Math.floor(offset % letter_array.length);
		String strLatBand = String.valueOf(letter_array[firstOffest])
				+ String.valueOf(letter_array[secondOffest]);

		// Get the quadrant ====================================================
		double latBand = (Math.floor((latitude + 90.0) * 4.0) % 2.0);
		longBand = (Math.floor((longitude + 180.0) * 4.0) % 2.0);
		int quadrant = 0;
		// return "0" if error occurs
		if (latBand < 0 || latBand > 1) {
			return null;
		}
		if (longBand < 0 || longBand > 1) {
			return null;
		}
		// Otherwise get the quadrant
		if (latBand == 0.0 && longBand == 0.0) {
			quadrant = 3;
		} else if (latBand == 1.0 && longBand == 0.0) {
			quadrant = 1;
		} else if (latBand == 1.0 && longBand == 1.0) {
			quadrant = 2;
		} else if (latBand == 0.0 && longBand == 1.0) {
			quadrant = 4;
		}

		int keypad = five_minute_array[(int) Math
				.floor(((longitude + 180) * 60.0) % 30 % 15 / 5.0)][(int) Math
						.floor(((latitude + 90) * 60.0) % 30 % 15 / 5.0)];

		return GARS.create(intLongBand, strLatBand, quadrant, keypad);
	}

	/**
	 * Parse a GARS string
	 * 
	 * @param gars
	 *            GARS string
	 * @return GARS
	 * @throws ParseException
	 *             upon failure to parse the GARS string
	 */
	public static GARS parse(String gars) throws ParseException {
		Matcher matcher = garsPattern.matcher(removeSpaces(gars));
		if (!matcher.matches()) {
			throw new ParseException("Invalid GARS: " + gars, 0);
		}

		int longitude = Integer.parseInt(matcher.group(1));
		String latitude = matcher.group(2).toUpperCase();
		int quadrant = 1;
		int ninth = 1;

		String quadrantValue = matcher.group(3);
		if (quadrantValue != null) {

			quadrant = Integer.parseInt(quadrantValue);

			String ninthValue = matcher.group(4);
			if (ninthValue != null) {

				ninth = Integer.parseInt(ninthValue);

			}

		}

		return GARS.create(longitude, latitude, quadrant, ninth);
	}

	/**
	 * Parse the GARS string for the precision
	 * 
	 * @param gars
	 *            GARS string
	 * @return grid type precision
	 * @throws ParseException
	 *             upon failure to parse the GARS string
	 */
	public static GridType precision(String gars) throws ParseException {
		Matcher matcher = garsPattern.matcher(removeSpaces(gars));
		if (!matcher.matches()) {
			throw new ParseException("Invalid GARS: " + gars, 0);
		}

		GridType precision = null;

		if (matcher.group(4) != null) {
			precision = GridType.FIVE_MINUTE;
		} else if (matcher.group(3) != null) {
			precision = GridType.FIFTEEN_MINUTE;
		} else {
			precision = GridType.THIRTY_MINUTE;
		}

		return precision;
	}

}
