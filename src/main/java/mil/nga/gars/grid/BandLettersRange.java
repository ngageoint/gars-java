package mil.nga.gars.grid;

import java.util.Iterator;

import mil.nga.gars.GARSConstants;
import mil.nga.gars.GARSUtils;

/**
 * Latitude Band Letters Range
 * 
 * @author osbornb
 */
public class BandLettersRange implements Iterable<String> {

	/**
	 * Southern band letters
	 */
	private String south;

	/**
	 * Northern band letters
	 */
	private String north;

	/**
	 * Constructor, full range
	 */
	public BandLettersRange() {
		this(GARSConstants.MIN_BAND_LETTERS, GARSConstants.MAX_BAND_LETTERS);
	}

	/**
	 * Constructor
	 * 
	 * @param south
	 *            southern band letters
	 * @param north
	 *            northern band letters
	 */
	public BandLettersRange(String south, String north) {
		this.south = south;
		this.north = north;
	}

	/**
	 * Get the southern band letters
	 * 
	 * @return southern band letters
	 */
	public String getSouth() {
		return south;
	}

	/**
	 * Set the southern band letters
	 * 
	 * @param south
	 *            southern band letters
	 */
	public void setSouth(String south) {
		this.south = south;
	}

	/**
	 * Get the northern band letters
	 * 
	 * @return northern band letters
	 */
	public String getNorth() {
		return north;
	}

	/**
	 * Set the northern band letters
	 * 
	 * @param north
	 *            northern band letters
	 */
	public void setNorth(String north) {
		this.north = north;
	}

	/**
	 * Get the southern band letters equivalent number value
	 * 
	 * @return southern band letters value
	 */
	public int getSouthValue() {
		return GARSUtils.bandValue(south);
	}

	/**
	 * Get the norther band letters equivalent number value
	 * 
	 * @return norther band letters value
	 */
	public int getNorthValue() {
		return GARSUtils.bandValue(north);
	}

	/**
	 * Get the southern latitude
	 * 
	 * @return latitude
	 */
	public double getSouthLatitude() {
		return GARSUtils.getLatitude(south);
	}

	/**
	 * Get the northern latitude
	 * 
	 * @return latitude
	 */
	public double getNorthLatitude() {
		return GARSUtils.getLatitude(north);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {

			/**
			 * Band letters value
			 */
			private int value = getSouthValue();

			/**
			 * Band letters max value (north)
			 */
			private final int maxValue = getNorthValue();

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return value <= maxValue;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public String next() {
				return GARSUtils.bandLetters(value++);
			}

		};
	}

}
