package mil.nga.gars;

import mil.nga.grid.GridConstants;

/**
 * Global Area Reference System Constants
 *
 * @author osbornb
 */
public class GARSConstants {

	/**
	 * Minimum longitude
	 */
	public static final double MIN_LON = GridConstants.MIN_LON;

	/**
	 * Maximum longitude
	 */
	public static final double MAX_LON = GridConstants.MAX_LON;

	/**
	 * Minimum latitude
	 */
	public static final double MIN_LAT = GridConstants.MIN_LAT;

	/**
	 * Maximum latitude
	 */
	public static final double MAX_LAT = GridConstants.MAX_LAT;

	/**
	 * Minimum grid longitude band number
	 */
	public static final int MIN_BAND_NUMBER = 1;

	/**
	 * Maximum grid longitude band number
	 */
	public static final int MAX_BAND_NUMBER = 720;

	/**
	 * Minimum grid latitude band letters
	 */
	public static final String MIN_BAND_LETTERS = "AA";

	/**
	 * Maximum grid latitude band letters
	 */
	public static final String MAX_BAND_LETTERS = "QZ";

	/**
	 * Minimum grid latitude single band letter
	 */
	public static final char MIN_BAND_LETTER = 'A';

	/**
	 * Maximum grid latitude single band letter
	 */
	public static final char MAX_BAND_LETTER = 'Z';

	/**
	 * Minimum grid latitude band letters number equivalent
	 */
	public static final int MIN_BAND_LETTERS_NUMBER = 1;

	/**
	 * Maximum grid latitude band letters number equivalent
	 */
	public static final int MAX_BAND_LETTERS_NUMBER = 360;

	/**
	 * Default quadrant (southwest corner)
	 */
	public static final int DEFAULT_QUADRANT = 3;

	/**
	 * Default keypad (southwest corner)
	 */
	public static final int DEFAULT_KEYPAD = 7;

}
