package mil.nga.gars.grid;

/**
 * Grid type enumeration
 * 
 * @author osbornb
 */
public enum GridType {

	/**
	 * Twenty Degree
	 */
	TWENTY_DEGREE(20.0),

	/**
	 * Ten Degree
	 */
	TEN_DEGREE(10.0),

	/**
	 * Five Degree
	 */
	FIVE_DEGREE(5.0),

	/**
	 * Thirty Minute
	 */
	THIRTY_MINUTE(0.5),

	/**
	 * Fifteen Minute
	 */
	FIFTEEN_MINUTE(0.25),

	/**
	 * Five Minute
	 */
	FIVE_MINUTE(0.25 / 3.0);

	/**
	 * Grid precision in degrees
	 */
	private double precision;

	/**
	 * Constructor
	 * 
	 * @param precision
	 *            precision in degrees
	 */
	private GridType(double precision) {
		this.precision = precision;
	}

	/**
	 * Get the precision in degrees
	 * 
	 * @return precision degrees
	 */
	public double getPrecision() {
		return precision;
	}

}
