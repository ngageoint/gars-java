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
	 * One Degree
	 */
	ONE_DEGREE(1.0),

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

	/**
	 * Get the precision of the value in degrees
	 * 
	 * @param value
	 *            value in degrees
	 * @return precision grid type
	 */
	public static GridType getPrecision(double value) {
		GridType precision = null;
		if (value % TWENTY_DEGREE.precision == 0) {
			precision = TWENTY_DEGREE;
		} else if (value % TEN_DEGREE.precision == 0) {
			precision = TEN_DEGREE;
		} else if (value % FIVE_DEGREE.precision == 0) {
			precision = FIVE_DEGREE;
		} else if (value % ONE_DEGREE.precision == 0) {
			precision = ONE_DEGREE;
		} else if (value % THIRTY_MINUTE.precision == 0) {
			precision = THIRTY_MINUTE;
		} else if (value % FIFTEEN_MINUTE.precision == 0) {
			precision = FIFTEEN_MINUTE;
		} else {
			precision = FIVE_MINUTE;
		}
		return precision;
	}

}
