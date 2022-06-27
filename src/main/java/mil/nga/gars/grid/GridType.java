package mil.nga.gars.grid;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

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

	/**
	 * Get the less precise (larger precision value) grid types
	 * 
	 * @param type
	 *            grid type
	 * @return grid types less precise
	 */
	public static Set<GridType> lessPrecise(GridType type) {
		GridType[] types = Arrays.copyOfRange(GridType.values(), 0,
				type.ordinal());
		return new LinkedHashSet<>(Arrays.asList(types));
	}

	/**
	 * Get the more precise (smaller precision value) grid types
	 * 
	 * @param type
	 *            grid type
	 * @return grid types more precise
	 */
	public static Set<GridType> morePrecise(GridType type) {
		GridType[] values = GridType.values();
		GridType[] types = Arrays.copyOfRange(values, type.ordinal() + 1,
				values.length);
		return new LinkedHashSet<>(Arrays.asList(types));
	}

}
