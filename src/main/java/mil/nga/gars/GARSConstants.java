package mil.nga.gars;

/**
 * Global Area Reference System Constants
 *
 * @author osbornb
 */
public class GARSConstants {

	/**
	 * Minimum longitude
	 */
	public static final double MIN_LON = -180.0;

	/**
	 * Maximum longitude
	 */
	public static final double MAX_LON = 180.0;

	/**
	 * Minimum latitude
	 */
	public static final double MIN_LAT = -90.0;

	/**
	 * Maximum latitude
	 */
	public static final double MAX_LAT = 90.0;

	/**
	 * Half the world distance in either direction
	 */
	public static final double WEB_MERCATOR_HALF_WORLD_WIDTH = 20037508.342789244;

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

	/**
	 * Omitted band letter 'I'
	 */
	public static final char BAND_LETTER_OMIT_I = 'I';

	/**
	 * Omitted band letter 'O'
	 */
	public static final char BAND_LETTER_OMIT_O = 'O';

	/**
	 * Max map zoom level
	 */
	public static final int MAX_MAP_ZOOM_LEVEL = 21;

}
