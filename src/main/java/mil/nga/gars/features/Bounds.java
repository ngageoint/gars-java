package mil.nga.gars.features;

import java.util.ArrayList;
import java.util.List;

import mil.nga.gars.GARSUtils;
import mil.nga.gars.grid.BandLettersRange;
import mil.nga.gars.grid.BandNumberRange;
import mil.nga.gars.grid.GridRange;
import mil.nga.gars.tile.GARSTile;
import mil.nga.gars.tile.Pixel;
import mil.nga.gars.tile.PixelRange;

/**
 * Grid Bounds
 * 
 * @author osbornb
 */
public class Bounds {

	/**
	 * Min longitude
	 */
	private double minLongitude;

	/**
	 * Max longitude
	 */
	private double maxLongitude;

	/**
	 * Min latitude
	 */
	private double minLatitude;

	/**
	 * Max latitude
	 */
	private double maxLatitude;

	/**
	 * Unit
	 */
	private Unit unit;

	/**
	 * Create bounds
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 * @param unit
	 *            unit
	 * @return bounds
	 */
	public static Bounds bounds(double minLongitude, double minLatitude,
			double maxLongitude, double maxLatitude, Unit unit) {
		return new Bounds(minLongitude, minLatitude, maxLongitude, maxLatitude,
				unit);
	}

	/**
	 * Create bounds in degrees
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 * @return bounds
	 */
	public static Bounds degrees(double minLongitude, double minLatitude,
			double maxLongitude, double maxLatitude) {
		return bounds(minLongitude, minLatitude, maxLongitude, maxLatitude,
				Unit.DEGREE);
	}

	/**
	 * Create bounds in meters
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 * @return bounds
	 */
	public static Bounds meters(double minLongitude, double minLatitude,
			double maxLongitude, double maxLatitude) {
		return bounds(minLongitude, minLatitude, maxLongitude, maxLatitude,
				Unit.METER);
	}

	/**
	 * Create bounds
	 * 
	 * @param southwest
	 *            southwest corner
	 * @param northeast
	 *            northeast corner
	 * @return bounds
	 */
	public static Bounds bounds(Point southwest, Point northeast) {
		return new Bounds(southwest, northeast);
	}

	/**
	 * Constructor
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 */
	public Bounds(double minLongitude, double minLatitude, double maxLongitude,
			double maxLatitude) {
		this(minLongitude, minLatitude, maxLongitude, maxLatitude, Unit.DEGREE);
	}

	/**
	 * Constructor
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 * @param unit
	 *            unit
	 */
	public Bounds(double minLongitude, double minLatitude, double maxLongitude,
			double maxLatitude, Unit unit) {
		this.minLongitude = minLongitude;
		this.minLatitude = minLatitude;
		this.maxLongitude = maxLongitude;
		this.maxLatitude = maxLatitude;
		this.unit = unit;
	}

	/**
	 * Constructor
	 * 
	 * @param southwest
	 *            southwest corner
	 * @param northeast
	 *            northeast corner
	 */
	public Bounds(Point southwest, Point northeast) {
		this(southwest.getLongitude(), southwest.getLatitude(),
				northeast.getLongitude(), northeast.getLatitude(),
				southwest.getUnit());

		if (!isUnit(northeast.getUnit())) {
			throw new IllegalArgumentException(
					"Points are in different units. southwest: " + unit
							+ ", northeast: " + northeast.getUnit());
		}
	}

	/**
	 * Get the min longitude
	 * 
	 * @return min longitude
	 */
	public double getMinLongitude() {
		return minLongitude;
	}

	/**
	 * Set the min longitude
	 * 
	 * @param minLongitude
	 *            min longitude
	 */
	public void setMinLongitude(double minLongitude) {
		this.minLongitude = minLongitude;
	}

	/**
	 * Get the max longitude
	 * 
	 * @return max longitude
	 */
	public double getMaxLongitude() {
		return maxLongitude;
	}

	/**
	 * Set the max longitude
	 * 
	 * @param maxLongitude
	 *            max longitude
	 */
	public void setMaxLongitude(double maxLongitude) {
		this.maxLongitude = maxLongitude;
	}

	/**
	 * Get the min latitude
	 * 
	 * @return min latitude
	 */
	public double getMinLatitude() {
		return minLatitude;
	}

	/**
	 * Set the min latitude
	 * 
	 * @param minLatitude
	 *            min latitude
	 */
	public void setMinLatitude(double minLatitude) {
		this.minLatitude = minLatitude;
	}

	/**
	 * Get the max latitude
	 * 
	 * @return max latitude
	 */
	public double getMaxLatitude() {
		return maxLatitude;
	}

	/**
	 * Set the max latitude
	 * 
	 * @param maxLatitude
	 *            max latitude
	 */
	public void setMaxLatitude(double maxLatitude) {
		this.maxLatitude = maxLatitude;
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
	 * Are bounds in degrees
	 * 
	 * @return true if degrees
	 */
	public boolean isDegrees() {
		return isUnit(Unit.DEGREE);
	}

	/**
	 * Are bounds in meters
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
	 * @return bounds in units, same bounds if equal units
	 */
	public Bounds toUnit(Unit unit) {
		Bounds bounds = null;
		if (isUnit(unit)) {
			bounds = this;
		} else {
			Point southwest = getSouthwest().toUnit(unit);
			Point northeast = getNortheast().toUnit(unit);
			bounds = new Bounds(southwest, northeast);
		}
		return bounds;
	}

	/**
	 * Convert to degrees
	 * 
	 * @return bounds in degrees, same bounds if already in degrees
	 */
	public Bounds toDegrees() {
		return toUnit(Unit.DEGREE);
	}

	/**
	 * Convert to meters
	 * 
	 * @return bounds in meters, same bounds if already in meters
	 */
	public Bounds toMeters() {
		return toUnit(Unit.METER);
	}

	/**
	 * Get the center longitude
	 * 
	 * @return center longitude
	 */
	public double getCenterLongitude() {
		return (getWidth() / 2.0) + minLongitude;
	}

	/**
	 * Get the center latitude
	 * 
	 * @return center latitude
	 */
	public double getCenterLatitude() {
		return getCenter().getLatitude();
	}

	/**
	 * Get the center coordinate
	 * 
	 * @return center coordinate
	 */
	public Point getCenter() {

		double centerLongitude = getCenterLongitude();

		Point northPoint = null;
		Point southPoint = null;
		switch (unit) {
		case DEGREE:
			northPoint = Point.degreesToMeters(centerLongitude, maxLatitude);
			southPoint = Point.degreesToMeters(centerLongitude, minLatitude);
			break;
		case METER:
			northPoint = Point.meters(centerLongitude, maxLatitude);
			southPoint = Point.meters(centerLongitude, minLatitude);
			break;
		default:
			throw new IllegalArgumentException("Unsupported unit: " + unit);
		}

		double centerX = northPoint.getLongitude();
		double centerY = southPoint.getLatitude()
				+ (0.5 * (northPoint.getLatitude() - southPoint.getLatitude()));

		Point point = Point.meters(centerX, centerY);
		if (unit == Unit.DEGREE) {
			point = point.toDegrees();
		}

		return point;
	}

	/**
	 * Get the width
	 * 
	 * @return width
	 */
	public double getWidth() {
		return maxLongitude - minLongitude;
	}

	/**
	 * Get the height
	 * 
	 * @return height
	 */
	public double getHeight() {
		return maxLatitude - minLatitude;
	}

	/**
	 * Determine if the bounds are empty
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return getWidth() <= 0.0 || getHeight() <= 0.0;
	}

	/**
	 * Get the southwest coordinate
	 * 
	 * @return southwest coordinate
	 */
	public Point getSouthwest() {
		return Point.create(minLongitude, minLatitude, unit);
	}

	/**
	 * Get the northwest coordinate
	 * 
	 * @return northwest coordinate
	 */
	public Point getNorthwest() {
		return Point.create(minLongitude, maxLatitude, unit);
	}

	/**
	 * Get the southeast coordinate
	 * 
	 * @return southeast coordinate
	 */
	public Point getSoutheast() {
		return Point.create(maxLongitude, minLatitude, unit);
	}

	/**
	 * Get the northeast coordinate
	 * 
	 * @return northeast coordinate
	 */
	public Point getNortheast() {
		return Point.create(maxLongitude, maxLatitude, unit);
	}

	/**
	 * Create a new bounds as the union between this bounds and the provided
	 * 
	 * @param bounds
	 *            bounds
	 * @return union bounds
	 */
	public Bounds union(Bounds bounds) {

		bounds = bounds.toUnit(unit);

		double minLongitude = Math.min(getMinLongitude(),
				bounds.getMinLongitude());
		double minLatitude = Math.min(getMinLatitude(),
				bounds.getMinLatitude());
		double maxLongitude = Math.max(getMaxLongitude(),
				bounds.getMaxLongitude());
		double maxLatitude = Math.max(getMaxLatitude(),
				bounds.getMaxLatitude());

		return new Bounds(minLongitude, minLatitude, maxLongitude, maxLatitude,
				unit);
	}

	/**
	 * Create a new bounds as the overlapping between this bounds and the
	 * provided
	 * 
	 * @param bounds
	 *            bounds
	 * @return overlap bounds
	 */
	public Bounds overlap(Bounds bounds) {

		bounds = bounds.toUnit(unit);

		double minLongitude = Math.max(getMinLongitude(),
				bounds.getMinLongitude());
		double minLatitude = Math.max(getMinLatitude(),
				bounds.getMinLatitude());
		double maxLongitude = Math.min(getMaxLongitude(),
				bounds.getMaxLongitude());
		double maxLatitude = Math.min(getMaxLatitude(),
				bounds.getMaxLatitude());

		return new Bounds(minLongitude, minLatitude, maxLongitude, maxLatitude,
				unit);
	}

	/**
	 * Determine if contains the point
	 *
	 * @param point
	 *            point
	 * @return true if contains
	 */
	public boolean contains(Point point) {
		point = point.toUnit(unit);
		double longitude = point.getLongitude();
		double latitude = point.getLatitude();
		return longitude >= getMinLongitude() && longitude <= getMaxLongitude()
				&& latitude >= getMinLatitude() && latitude <= getMaxLatitude();
	}

	/**
	 * Get the western line
	 * 
	 * @return west line
	 */
	public Line getWestLine() {
		return Line.line(getNorthwest(), getSouthwest());
	}

	/**
	 * Get the southern line
	 * 
	 * @return south line
	 */
	public Line getSouthLine() {
		return Line.line(getSouthwest(), getSoutheast());
	}

	/**
	 * Get the eastern line
	 * 
	 * @return east line
	 */
	public Line getEastLine() {
		return Line.line(getSoutheast(), getNortheast());
	}

	/**
	 * Get the northern line
	 * 
	 * @return north line
	 */
	public Line getNorthLine() {
		return Line.line(getNortheast(), getNorthwest());
	}

	/**
	 * Convert the bounds to be precision accurate minimally containing the
	 * bounds. Each bound is equal to or larger by the precision degree amount.
	 * 
	 * @param precision
	 *            precision in degrees
	 * @return precision bounds
	 */
	public Bounds toPrecision(double precision) {

		Bounds bounds = toDegrees();

		double minLon = GARSUtils.precisionBefore(bounds.getMinLongitude(),
				precision);
		double minLat = GARSUtils.precisionBefore(bounds.getMinLatitude(),
				precision);
		double maxLon = GARSUtils.precisionAfter(bounds.getMaxLongitude(),
				precision);
		double maxLat = GARSUtils.precisionAfter(bounds.getMaxLatitude(),
				precision);

		return Bounds.degrees(minLon, minLat, maxLon, maxLat);
	}

	/**
	 * Get the pixel range where the bounds fit into the tile
	 * 
	 * @param tile
	 *            tile
	 * @return pixel range
	 */
	public PixelRange getPixelRange(GARSTile tile) {
		return getPixelRange(tile.getWidth(), tile.getHeight(),
				tile.getBounds());
	}

	/**
	 * Get the pixel range where the bounds fit into the provided bounds
	 * 
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @param bounds
	 *            bounds
	 * @return pixel range
	 */
	public PixelRange getPixelRange(int width, int height, Bounds bounds) {
		bounds = bounds.toMeters();
		Pixel topLeft = GARSUtils.getPixel(width, height, bounds,
				getNorthwest());
		Pixel bottomRight = GARSUtils.getPixel(width, height, bounds,
				getSoutheast());
		return new PixelRange(topLeft, bottomRight);
	}

	/**
	 * Get the four line bounds in meters
	 * 
	 * @return lines
	 */
	public List<Line> getLines() {

		Point southwest = getSouthwest();
		Point northwest = getNorthwest();
		Point northeast = getNortheast();
		Point southeast = getSoutheast();

		List<Line> lines = new ArrayList<>();
		lines.add(Line.line(southwest, northwest));
		lines.add(Line.line(northwest, northeast));
		lines.add(Line.line(northeast, southeast));
		lines.add(Line.line(southeast, southwest));

		return lines;
	}

	/**
	 * Get a grid range of the bounds
	 * 
	 * @return grid range
	 */
	public GridRange getGridRange() {
		return GARSUtils.getGridRange(this);
	}

	/**
	 * Get a band number range between the western and eastern longitudes
	 * 
	 * @return band number range
	 */
	public BandNumberRange getBandNumberRange() {
		return GARSUtils.getBandNumberRange(this);
	}

	/**
	 * Get a band letters range between the southern and northern latitudes
	 * 
	 * @return band letters range
	 */
	public BandLettersRange getBandLettersRange() {
		return GARSUtils.getBandLettersRange(this);
	}

}
