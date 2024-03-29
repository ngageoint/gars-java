package mil.nga.gars;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mil.nga.gars.grid.GridType;
import mil.nga.grid.features.Point;
import mil.nga.sf.util.GeometryUtils;

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
			"^(\\d{3})([A-HJ-NP-Z]{2})(?:([1-4])([1-9])?)?$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Longitudinal band number
	 */
	private final int longitude;

	/**
	 * Latitudinal band letters
	 */
	private final String latitude;

	/**
	 * 15 minute quadrant
	 */
	private final int quadrant;

	/**
	 * 5 minute keypad
	 */
	private final int keypad;

	/**
	 * Create, default southwest corner quadrant
	 * ({@link GARSConstants#DEFAULT_QUADRANT}) and keypad
	 * ({@link GARSConstants#DEFAULT_KEYPAD})
	 * 
	 * @param longitude
	 *            longitudinal band number
	 * @param latitude
	 *            latitudinal band letters
	 * @return GARS
	 */
	public static GARS create(int longitude, String latitude) {
		return new GARS(longitude, latitude);
	}

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
	 * Constructor, default southwest corner quadrant
	 * ({@link GARSConstants#DEFAULT_QUADRANT}) and keypad
	 * ({@link GARSConstants#DEFAULT_KEYPAD})
	 * 
	 * @param longitude
	 *            longitudinal band number
	 * @param latitude
	 *            latitudinal band letters
	 */
	public GARS(int longitude, String latitude) {
		this(longitude, latitude, GARSConstants.DEFAULT_QUADRANT,
				GARSConstants.DEFAULT_KEYPAD);
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
			type = GridType.FIVE_MINUTE;
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

		double lon = GARSUtils.getLongitude(longitude);
		double lat = GARSUtils.getLatitude(latitude);

		lon += GARSUtils.quadrantColumn(quadrant)
				* GridType.FIFTEEN_MINUTE.getPrecision();
		lat += GARSUtils.quadrantRow(quadrant)
				* GridType.FIFTEEN_MINUTE.getPrecision();

		lon += GARSUtils.keypadColumn(keypad)
				* GridType.FIVE_MINUTE.getPrecision();
		lat += GARSUtils.keypadRow(keypad)
				* GridType.FIVE_MINUTE.getPrecision();

		return Point.degrees(lon, lat);
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
				matches = latitudeValue >= GARSConstants.MIN_BAND_LETTERS_NUMBER
						&& latitudeValue <= GARSConstants.MAX_BAND_LETTERS_NUMBER;
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

		point = point.copy().toDegrees();

		// Bound the latitude if needed
		if (point.getLatitude() < GARSConstants.MIN_LAT) {
			point.setLatitude(GARSConstants.MIN_LAT);
		} else if (point.getLatitude() > GARSConstants.MAX_LAT) {
			point.setLatitude(GARSConstants.MAX_LAT);
		}

		// Normalize the longitude if needed
		GeometryUtils.normalizeWGS84(point);

		double lon = GARSUtils.getLongitudeDecimalBand(point.getLongitude());
		double lat = GARSUtils.getLatitudeDecimalBandValue(point.getLatitude());

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
	 * Convert the coordinate to GARS
	 *
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @return GARS
	 */
	public static GARS from(double longitude, double latitude) {
		return from(Point.degrees(longitude, latitude));
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
		if (latitudeValue < GARSConstants.MIN_BAND_LETTERS_NUMBER
				|| latitudeValue > GARSConstants.MAX_BAND_LETTERS_NUMBER) {
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
