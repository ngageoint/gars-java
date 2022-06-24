package mil.nga.gars;

import mil.nga.gars.features.Bounds;
import mil.nga.gars.features.Point;
import mil.nga.gars.features.Unit;
import mil.nga.gars.grid.BandLettersRange;
import mil.nga.gars.grid.BandNumberRange;
import mil.nga.gars.grid.GridRange;
import mil.nga.gars.grid.GridType;
import mil.nga.gars.tile.Pixel;

/**
 * Global Area Reference System utilities
 * 
 * @author osbornb
 */
public class GARSUtils {

	/**
	 * Get the pixel where the point fits into the bounds
	 * 
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @param bounds
	 *            bounds
	 * @param point
	 *            point
	 * @return pixel
	 */
	public static Pixel getPixel(int width, int height, Bounds bounds,
			Point point) {

		point = point.toMeters();
		bounds = bounds.toMeters();

		float x = getXPixel(width, bounds, point.getLongitude());
		float y = getYPixel(height, bounds, point.getLatitude());
		return new Pixel(x, y);
	}

	/**
	 * Get the X pixel for where the longitude in meters fits into the bounds
	 *
	 * @param width
	 *            width
	 * @param bounds
	 *            bounds
	 * @param longitude
	 *            longitude in meters
	 * @return x pixel
	 */
	public static float getXPixel(int width, Bounds bounds, double longitude) {

		bounds = bounds.toMeters();

		double boxWidth = bounds.getMaxLongitude() - bounds.getMinLongitude();
		double offset = longitude - bounds.getMinLongitude();
		double percentage = offset / boxWidth;
		float pixel = (float) (percentage * width);

		return pixel;
	}

	/**
	 * Get the Y pixel for where the latitude in meters fits into the bounds
	 *
	 * @param height
	 *            height
	 * @param bounds
	 *            bounds
	 * @param latitude
	 *            latitude
	 * @return y pixel
	 */
	public static float getYPixel(int height, Bounds bounds, double latitude) {

		bounds = bounds.toMeters();

		double boxHeight = bounds.getMaxLatitude() - bounds.getMinLatitude();
		double offset = bounds.getMaxLatitude() - latitude;
		double percentage = offset / boxHeight;
		float pixel = (float) (percentage * height);

		return pixel;
	}

	/**
	 * Get the tile bounds from the XYZ tile coordinates and zoom level
	 *
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param zoom
	 *            zoom level
	 * @return bounds
	 */
	public static Bounds getBounds(int x, int y, int zoom) {

		int tilesPerSide = tilesPerSide(zoom);
		double tileSize = tileSize(tilesPerSide);

		double minLon = (-1 * GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH)
				+ (x * tileSize);
		double minLat = GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH
				- ((y + 1) * tileSize);
		double maxLon = (-1 * GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH)
				+ ((x + 1) * tileSize);
		double maxLat = GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH
				- (y * tileSize);

		return Bounds.meters(minLon, minLat, maxLon, maxLat);
	}

	/**
	 * Get the tiles per side, width and height, at the zoom level
	 *
	 * @param zoom
	 *            zoom level
	 * @return tiles per side
	 */
	public static int tilesPerSide(int zoom) {
		return (int) Math.pow(2, zoom);
	}

	/**
	 * Get the tile size in meters
	 *
	 * @param tilesPerSide
	 *            tiles per side
	 * @return tile size
	 */
	public static double tileSize(int tilesPerSide) {
		return (2 * GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH) / tilesPerSide;
	}

	/**
	 * Get the zoom level of the bounds using the shortest bounds side length
	 * 
	 * @param bounds
	 *            bounds
	 * @return zoom level
	 */
	public static double getZoomLevel(Bounds bounds) {
		bounds = bounds.toMeters();
		double tileSize = Math.min(bounds.getWidth(), bounds.getHeight());
		double tilesPerSide = 2 * GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH
				/ tileSize;
		return Math.log(tilesPerSide) / Math.log(2);
	}

	/**
	 * Convert a coordinate from a unit to another unit
	 * 
	 * @param fromUnit
	 *            unit of provided coordinate
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @param toUnit
	 *            desired unit
	 * @return point in unit
	 */
	public static Point toUnit(Unit fromUnit, double longitude, double latitude,
			Unit toUnit) {
		Point point = null;
		if (fromUnit == toUnit) {
			point = Point.create(longitude, latitude, toUnit);
		} else {
			point = toUnit(longitude, latitude, toUnit);
		}
		return point;
	}

	/**
	 * Convert a coordinate to the unit, assumes the coordinate is in the
	 * opposite unit
	 * 
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @param unit
	 *            desired unit
	 * @return point in unit
	 */
	public static Point toUnit(double longitude, double latitude, Unit unit) {
		Point point = null;
		switch (unit) {
		case DEGREE:
			point = toDegrees(longitude, latitude);
			break;
		case METER:
			point = toMeters(longitude, latitude);
			break;
		default:
			throw new IllegalArgumentException("Unsupported unit: " + unit);
		}
		return point;
	}

	/**
	 * Convert a WGS84 coordinate to a point in meters
	 * 
	 * @param longitude
	 *            WGS84 longitude
	 * @param latitude
	 *            WGS84 latitude
	 * @return point in meters
	 */
	public static Point toMeters(double longitude, double latitude) {
		double lon = longitude * GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH
				/ 180;
		double lat = Math.log(Math.tan((90 + latitude) * Math.PI / 360))
				/ (Math.PI / 180);
		lat = lat * GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH / 180;
		return Point.meters(lon, lat);
	}

	/**
	 * Convert a coordinate in meters to a WGS84 point
	 * 
	 * @param longitude
	 *            longitude in meters
	 * @param latitude
	 *            latitude in meters
	 * @return WGS84 coordinate
	 */
	public static Point toDegrees(double longitude, double latitude) {
		double lon = longitude * 180
				/ GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH;
		double lat = latitude * 180
				/ GARSConstants.WEB_MERCATOR_HALF_WORLD_WIDTH;
		lat = Math.atan(Math.exp(lat * (Math.PI / 180))) / Math.PI * 360 - 90;
		return Point.degrees(lon, lat);
	}

	/**
	 * Get the longitude from the longitude band
	 * 
	 * @param band
	 *            longitudinal band number
	 * @return longitude
	 */
	public static double getLongitude(int band) {
		return GARSConstants.MIN_LON
				+ ((band - 1) * GridType.THIRTY_MINUTE.getPrecision());
	}

	/**
	 * Get the latitude from the latitude band letters
	 * 
	 * @param band
	 *            latitudinal band letters
	 * @return latitude
	 */
	public static double getLatitude(String band) {
		return getLatitude(bandValue(band));
	}

	/**
	 * Get the latitude from the latitude band letters number equivalent
	 * 
	 * @param band
	 *            latitudinal band number
	 * @return latitude
	 */
	public static double getLatitude(int band) {
		return GARSConstants.MIN_LAT
				+ ((band - 1) * GridType.THIRTY_MINUTE.getPrecision());
	}

	/**
	 * Get the longitude band from the longitude
	 * 
	 * @param longitude
	 *            longitude
	 * @return longitude band
	 */
	public static int getLongitudeBand(double longitude) {
		return (int) getLongitudeDecimalBand(longitude);
	}

	/**
	 * Get the longitude decimal band from the longitude
	 * 
	 * @param longitude
	 *            longitude
	 * @return longitude decimal band
	 */
	public static double getLongitudeDecimalBand(double longitude) {
		return ((longitude - GARSConstants.MIN_LON)
				/ GridType.THIRTY_MINUTE.getPrecision()) + 1.0;
	}

	/**
	 * Get the latitude band letters from the latitude
	 * 
	 * @param latitude
	 *            latitude
	 * @return latitude band letters
	 */
	public static String getLatitudeBand(double latitude) {
		return bandLetters(getLatitudeBandValue(latitude));
	}

	/**
	 * Get the latitude band value from the latitude
	 * 
	 * @param latitude
	 *            latitude
	 * @return latitude band value
	 */
	public static int getLatitudeBandValue(double latitude) {
		return (int) getLatitudeDecimalBandValue(latitude);
	}

	/**
	 * Get the latitude decimal band value from the latitude
	 * 
	 * @param latitude
	 *            latitude
	 * @return latitude decimal band value
	 */
	public static double getLatitudeDecimalBandValue(double latitude) {
		return ((latitude - GARSConstants.MIN_LAT)
				/ GridType.THIRTY_MINUTE.getPrecision()) + 1.0;
	}

	/**
	 * The the band letter an omitted letter
	 * {@link GARSConstants#BAND_LETTER_OMIT_I} or
	 * {@link GARSConstants#BAND_LETTER_OMIT_O}
	 * 
	 * @param letter
	 *            band letter
	 * @return true if omitted
	 */
	public static boolean isOmittedBandLetter(char letter) {
		return letter == GARSConstants.BAND_LETTER_OMIT_I
				|| letter == GARSConstants.BAND_LETTER_OMIT_O;
	}

	/**
	 * Get the latitude band number equivalent to the longitude band (where AA
	 * is 1 and QZ is 360)
	 * 
	 * @param latitudeBand
	 *            two character latitude band
	 * @return number band value
	 */
	public static int bandValue(String latitudeBand) {
		String latitude = latitudeBand.toUpperCase();
		int latitude1 = bandValue(latitude.charAt(0));
		int latitude2 = bandValue(latitude.charAt(1));
		return 24 * (latitude1 - 1) + latitude2;
	}

	/**
	 * Get the latitude character band number equivalent (where A is 1 and Z is
	 * 24)
	 * 
	 * @param latitudeBand
	 *            single character from latitude band
	 * @return number band value
	 */
	public static int bandValue(char latitudeBand) {
		int value = latitudeBand - GARSConstants.MIN_BAND_LETTER + 1;
		if (latitudeBand > GARSConstants.BAND_LETTER_OMIT_I) {
			value--;
			if (latitudeBand > GARSConstants.BAND_LETTER_OMIT_O) {
				value--;
			}
		}
		return value;
	}

	/**
	 * Get the latitude band from the band number (where 1 is AA and 360 is QZ)
	 * 
	 * @param bandValue
	 *            number band value
	 * @return two character latitude band
	 */
	public static String bandLetters(int bandValue) {
		bandValue -= 1;
		int latitude1 = bandValue / 24;
		int latitude2 = bandValue % 24;
		return String.valueOf(bandLetter(latitude1 + 1))
				+ bandLetter(latitude2 + 1);
	}

	/**
	 * Get the latitude character equivalent from the band number (where 1 is A
	 * and 24 is Z)
	 * 
	 * @param bandValue
	 *            number band value
	 * @return single character of latitude band
	 */
	public static char bandLetter(int bandValue) {
		char letter = GARSConstants.MIN_BAND_LETTER;
		letter += bandValue - 1;
		if (letter >= GARSConstants.BAND_LETTER_OMIT_I) {
			letter++;
			if (letter >= GARSConstants.BAND_LETTER_OMIT_O) {
				letter++;
			}
		}
		return letter;
	}

	/**
	 * Get the quadrant southwest origin 0 indexed column
	 * 
	 * @param quadrant
	 *            quadrant number
	 * @return 0 for quadrants 1|3, 1 for quadrants 2|4
	 */
	public static int quadrantColumn(int quadrant) {
		return quadrant % 2 == 0 ? 1 : 0;
	}

	/**
	 * Get the quadrant southwest origin 0 indexed row
	 * 
	 * @param quadrant
	 *            quadrant number
	 * @return 0 for quadrants 3|4, 1 for quadrants 1|2
	 */
	public static int quadrantRow(int quadrant) {
		return quadrant >= 3 ? 0 : 1;
	}

	/**
	 * Get the keypad southwest origin 0 indexed column
	 * 
	 * @param keypad
	 *            keypad number
	 * @return 0 for keypads 1|4|7, 1 for keypads 2|5|8, 2 for keypads 3|6|9
	 */
	public static int keypadColumn(int keypad) {
		int column = 0;
		if (keypad % 3 == 0) {
			column = 2;
		} else if ((keypad + 1) % 3 == 0) {
			column = 1;
		}
		return column;
	}

	/**
	 * Get the keypad southwest origin 0 indexed row
	 * 
	 * @param keypad
	 *            keypad number
	 * @return 0 for keypads 7|8|9, 1 for keypads 4|5|6, 2 for keypads 1|2|3
	 */
	public static int keypadRow(int keypad) {
		int row = 0;
		if (keypad <= 3) {
			row = 2;
		} else if (keypad <= 6) {
			row = 1;
		}
		return row;
	}

	/**
	 * Get the quadrant from the southwest origin 0 indexed column and row
	 * 
	 * @param column
	 *            0 indexed column
	 * @param row
	 *            0 indexed row
	 * @return quadrant
	 */
	public static int quadrant(int column, int row) {
		return (1 - row) * 2 + column + 1;
	}

	/**
	 * Get the keypad from the southwest origin 0 indexed column and row
	 * 
	 * @param column
	 *            0 indexed column
	 * @param row
	 *            0 indexed row
	 * @return keypad
	 */
	public static int keypad(int column, int row) {
		return (2 - row) * 3 + column + 1;
	}

	/**
	 * Get a grid range from the bounds
	 * 
	 * @param bounds
	 *            bounds
	 * @return grid range
	 */
	public static GridRange getGridRange(Bounds bounds) {
		bounds = bounds.toDegrees();
		BandNumberRange bandNumberRange = getBandNumberRange(bounds);
		BandLettersRange bandLettersRange = getBandLettersRange(bounds);
		return new GridRange(bandNumberRange, bandLettersRange);
	}

	/**
	 * Get a band number range between the western and eastern bounds
	 * 
	 * @param bounds
	 *            bounds
	 * @return band number range
	 */
	public static BandNumberRange getBandNumberRange(Bounds bounds) {
		bounds = bounds.toDegrees();
		return getBandNumberRange(bounds.getMinLongitude(),
				bounds.getMaxLongitude());
	}

	/**
	 * Get a band number range between the western and eastern longitudes
	 * 
	 * @param west
	 *            western longitude in degrees
	 * @param east
	 *            eastern longitude in degrees
	 * @return band number range
	 */
	public static BandNumberRange getBandNumberRange(double west, double east) {
		int westBand = getLongitudeBand(west);
		int eastBand = getLongitudeBand(east);
		return new BandNumberRange(westBand, eastBand);
	}

	/**
	 * Get a band letters range between the southern and northern bounds
	 * 
	 * @param bounds
	 *            bounds
	 * @return band letters range
	 */
	public static BandLettersRange getBandLettersRange(Bounds bounds) {
		bounds = bounds.toDegrees();
		return getBandLettersRange(bounds.getMinLatitude(),
				bounds.getMaxLatitude());
	}

	/**
	 * Get a band letters range between the southern and northern latitudes in
	 * degrees
	 * 
	 * @param south
	 *            southern latitude in degrees
	 * @param north
	 *            northern latitude in degrees
	 * @return band letters range
	 */
	public static BandLettersRange getBandLettersRange(double south,
			double north) {
		String southBand = getLatitudeBand(south);
		String northBand = getLatitudeBand(north);
		return new BandLettersRange(southBand, northBand);
	}

	/**
	 * Get the precision degrees value before the degrees value
	 * 
	 * @param value
	 *            value
	 * @param precision
	 *            precision in degrees
	 * @return degrees precision value
	 */
	public static double precisionBefore(double value, double precision) {
		return value - ((value % precision + precision) % precision);
	}

	/**
	 * Get the precision degrees value after the degrees value
	 * 
	 * @param value
	 *            value
	 * @param precision
	 *            precision in degrees
	 * @return degrees precision value
	 */
	public static double precisionAfter(double value, double precision) {
		return precisionBefore(value + precision, precision);
	}

	/**
	 * Create a degree grid label
	 * 
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @return degree label
	 */
	public static String degreeLabel(double longitude, double latitude) {
		StringBuilder label = new StringBuilder();
		label.append(Math.abs((int) longitude));
		label.append(longitude < 0 ? "W" : "E");
		label.append(Math.abs((int) latitude));
		label.append(latitude < 0 ? "S" : "N");
		return label.toString();
	}

}
