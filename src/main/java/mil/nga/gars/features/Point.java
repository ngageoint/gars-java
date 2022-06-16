package mil.nga.gars.features;

import java.text.ParseException;

import mil.nga.gars.GARS;
import mil.nga.gars.GARSUtils;
import mil.nga.gars.tile.GARSTile;
import mil.nga.gars.tile.Pixel;

/**
 * Point
 * 
 * @author osbornb
 */
public class Point {

	/**
	 * Latitude
	 */
	private double latitude;

	/**
	 * Longitude
	 */
	private double longitude;

	/**
	 * Unit
	 */
	private Unit unit;

	/**
	 * Create a point with default degree unit
	 * 
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @return point
	 */
	public static Point create(double longitude, double latitude) {
		return degrees(longitude, latitude);
	}

	/**
	 * Create a point
	 * 
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @param unit
	 *            unit
	 * @return point
	 */
	public static Point create(double longitude, double latitude, Unit unit) {
		return new Point(longitude, latitude, unit);
	}

	/**
	 * Create a point in degrees
	 * 
	 * @param longitude
	 *            longitude in degrees
	 * @param latitude
	 *            latitude in degrees
	 * @return point in degrees
	 */
	public static Point degrees(double longitude, double latitude) {
		return create(longitude, latitude, Unit.DEGREE);
	}

	/**
	 * Create a point in meters
	 * 
	 * @param longitude
	 *            longitude in meters
	 * @param latitude
	 *            latitude in meters
	 * @return point in meters
	 */
	public static Point meters(double longitude, double latitude) {
		return create(longitude, latitude, Unit.METER);
	}

	/**
	 * Create a point from a coordinate in a unit to another unit
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
		return GARSUtils.toUnit(fromUnit, longitude, latitude, toUnit);
	}

	/**
	 * Create a point from a coordinate in an opposite unit to another unit
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
		return GARSUtils.toUnit(longitude, latitude, unit);
	}

	/**
	 * Create a point converting the degrees coordinate to meters
	 * 
	 * @param longitude
	 *            longitude in degrees
	 * @param latitude
	 *            latitude in degrees
	 * @return point in meters
	 */
	public static Point degreesToMeters(double longitude, double latitude) {
		return toUnit(Unit.DEGREE, longitude, latitude, Unit.METER);
	}

	/**
	 * Create a point converting the meters coordinate to degrees
	 * 
	 * @param longitude
	 *            longitude in meters
	 * @param latitude
	 *            latitude in meters
	 * @return point in degrees
	 */
	public static Point metersToDegrees(double longitude, double latitude) {
		return toUnit(Unit.METER, longitude, latitude, Unit.DEGREE);
	}

	/**
	 * Constructor, in {@link Unit#DEGREE} units
	 * 
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 */
	public Point(double longitude, double latitude) {
		this(longitude, latitude, Unit.DEGREE);
	}

	/**
	 * Constructor
	 * 
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @param unit
	 *            unit
	 */
	public Point(double longitude, double latitude, Unit unit) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.unit = unit;
	}

	/**
	 * Get the latitude
	 * 
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Set the latitude
	 * 
	 * @param latitude
	 *            latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Get the longitude
	 * 
	 * @return longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Set the longitude
	 * 
	 * @param longitude
	 *            longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get the unit
	 * 
	 * @return unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * Set the unit
	 * 
	 * @param unit
	 *            unit
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * Is in the provided unit type
	 * 
	 * @param unit
	 *            unit
	 * @return true if in the unit
	 */
	public boolean isUnit(Unit unit) {
		return this.unit == unit;
	}

	/**
	 * Is this point in degrees
	 * 
	 * @return true if degrees
	 */
	public boolean isDegrees() {
		return isUnit(Unit.DEGREE);
	}

	/**
	 * Is this point in meters
	 * 
	 * @return true if meters
	 */
	public boolean isMeters() {
		return isUnit(Unit.METER);
	}

	/**
	 * Convert to the unit
	 * 
	 * @param unit
	 *            unit
	 * @return point in units, same point if equal units
	 */
	public Point toUnit(Unit unit) {
		Point point = null;
		if (isUnit(unit)) {
			point = this;
		} else {
			point = GARSUtils.toUnit(this.unit, longitude, latitude, unit);
		}
		return point;
	}

	/**
	 * Convert to degrees
	 * 
	 * @return point in degrees, same point if already in degrees
	 */
	public Point toDegrees() {
		return toUnit(Unit.DEGREE);
	}

	/**
	 * Convert to meters
	 * 
	 * @return point in meters, same point if already in meters
	 */
	public Point toMeters() {
		return toUnit(Unit.METER);
	}

	/**
	 * Convert to a GARS coordinate
	 * 
	 * @return GARS
	 */
	public GARS toGARS() {
		return GARS.from(this);
	}

	/**
	 * Get the pixel where the point fits into tile
	 * 
	 * @param tile
	 *            tile
	 * @return pixel
	 */
	public Pixel getPixel(GARSTile tile) {
		return getPixel(tile.getWidth(), tile.getHeight(), tile.getBounds());
	}

	/**
	 * Get the pixel where the point fits into the bounds
	 * 
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @param bounds
	 *            bounds
	 * @return pixel
	 */
	public Pixel getPixel(int width, int height, Bounds bounds) {
		return GARSUtils.getPixel(width, height, bounds, this);
	}

	/**
	 * Parse a GARS string value to a coordinate
	 * 
	 * @param gars
	 *            GARS string value
	 * @return coordinate
	 * @throws ParseException
	 *             upon failure to parse the GARS value
	 */
	public static Point parse(String gars) throws ParseException {
		return GARS.parse(gars).toPoint();
	}

}
