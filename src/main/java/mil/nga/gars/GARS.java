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
			"^(\\d{3})([A-HJ-NP-Z]{2})(?:([1-4])([1-9])?)?",
			Pattern.CASE_INSENSITIVE);

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
	 * 5 minute keypad
	 */
	private int keypad;

	/**
	 * Create
	 * 
	 * @param longitude
	 *            longitudinal band number
	 * @param latitude
	 *            latitudinal band letters
	 * @param quadrant
	 *            15 minute quadrant
	 * @param keypad
	 *            5 minute keypad
	 * @return GARS
	 */
	public static GARS create(int longitude, String latitude, int quadrant,
			int keypad) {
		return new GARS(longitude, latitude, quadrant, keypad);
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
	 * @param keypad
	 *            5 minute keypad
	 */
	public GARS(int longitude, String latitude, int quadrant, int keypad) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.quadrant = quadrant;
		this.keypad = keypad;
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
	 * Get the 5 minute keypad
	 * 
	 * @return keypad
	 */
	public int getKeypad() {
		return keypad;
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

				gars.append(keypad);

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

		double lon = GARSConstants.MIN_LON
				+ ((this.longitude - 1) * GARSConstants.BAND_DEGREES);

		int latBand = GARSUtils.bandValue(latitude);
		double lat = GARSConstants.MIN_LAT
				+ ((latBand - 1) * GARSConstants.BAND_DEGREES);

		lon += GARSUtils.quadrantColumn(quadrant)
				* GARSConstants.QUADRANT_DEGREES;
		lat += GARSUtils.quadrantRow(quadrant) * GARSConstants.QUADRANT_DEGREES;

		lon += GARSUtils.keypadColumn(keypad) * GARSConstants.KEYPAD_DEGREES;
		lat += GARSUtils.keypadRow(keypad) * GARSConstants.KEYPAD_DEGREES;

		return new Point(lon, lat);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return coordinate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + keypad;
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + longitude;
		result = prime * result + quadrant;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GARS other = (GARS) obj;
		if (keypad != other.keypad)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude != other.longitude)
			return false;
		if (quadrant != other.quadrant)
			return false;
		return true;
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
		Matcher matcher = garsPattern.matcher(gars);
		boolean matches = matcher.matches();
		if (matches) {
			int longitude = Integer.parseInt(matcher.group(1));
			matches = longitude >= GARSConstants.MIN_BAND_NUMBER
					&& longitude <= GARSConstants.MAX_BAND_NUMBER;
			if (matches) {
				String latitude = matcher.group(2).toUpperCase();
				int latitudeValue = GARSUtils.bandValue(latitude);
				matches = latitudeValue >= GARSConstants.MIN_BAND_NUMBER
						&& latitudeValue <= GARSConstants.MAX_BAND_NUMBER / 2.0;
			}
		}
		return matches;
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

		double lon = ((longitude - GARSConstants.MIN_LON)
				/ GARSConstants.BAND_DEGREES) + 1.0;
		double lat = ((latitude - GARSConstants.MIN_LAT)
				/ GARSConstants.BAND_DEGREES) + 1.0;

		int lonInt = (int) lon;
		int latInt = (int) lat;

		String latBand = GARSUtils.bandLetters(latInt);

		double lonDecimal = lon - lonInt;
		double latDecimal = lat - latInt;

		double quadrantColumn = lonDecimal * 2.0;
		double quadrantRow = latDecimal * 2.0;

		int quadrantColumnInt = (int) quadrantColumn;
		int quadrantRowInt = (int) quadrantRow;

		int quadrant = GARSUtils.quadrant(quadrantColumnInt, quadrantRowInt);

		lonDecimal = quadrantColumn - quadrantColumnInt;
		latDecimal = quadrantRow - quadrantRowInt;

		int keypadColumn = (int) (lonDecimal * 3.0);
		int keypadRow = (int) (latDecimal * 3.0);

		int keypad = GARSUtils.keypad(keypadColumn, keypadRow);

		return GARS.create(lonInt, latBand, quadrant, keypad);
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
		if (longitude < GARSConstants.MIN_BAND_NUMBER
				|| longitude > GARSConstants.MAX_BAND_NUMBER) {
			throw new ParseException("Invalid GARS longitude: "
					+ matcher.group(1) + ", GARS: " + gars, 0);
		}

		String latitude = matcher.group(2).toUpperCase();
		int latitudeValue = GARSUtils.bandValue(latitude);
		if (latitudeValue < GARSConstants.MIN_BAND_NUMBER
				|| latitudeValue > GARSConstants.MAX_BAND_NUMBER / 2.0) {
			throw new ParseException("Invalid GARS latitude: "
					+ matcher.group(2) + ", GARS: " + gars, 3);
		}

		int quadrant = GARSConstants.DEFAULT_QUADRANT;
		int keypad = GARSConstants.DEFAULT_KEYPAD;

		String quadrantValue = matcher.group(3);
		if (quadrantValue != null) {

			quadrant = Integer.parseInt(quadrantValue);

			String keypadValue = matcher.group(4);
			if (keypadValue != null) {

				keypad = Integer.parseInt(keypadValue);

			}

		}

		return GARS.create(longitude, latitude, quadrant, keypad);
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
