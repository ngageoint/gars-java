package mil.nga.gars.grid;

import java.util.Iterator;

import mil.nga.gars.GARSConstants;
import mil.nga.gars.GARSUtils;

/**
 * Longitude Band Number Range
 * 
 * @author osbornb
 */
public class BandNumberRange implements Iterable<Integer> {

	/**
	 * Western band number
	 */
	private int west;

	/**
	 * Eastern band number
	 */
	private int east;

	/**
	 * Constructor, full range
	 */
	public BandNumberRange() {
		this(GARSConstants.MIN_BAND_NUMBER, GARSConstants.MAX_BAND_NUMBER);
	}

	/**
	 * Constructor
	 * 
	 * @param west
	 *            western band number
	 * @param east
	 *            eastern band number
	 */
	public BandNumberRange(int west, int east) {
		this.west = west;
		this.east = east;
	}

	/**
	 * Get the western band number
	 * 
	 * @return western band number
	 */
	public int getWest() {
		return west;
	}

	/**
	 * Set the western band number
	 * 
	 * @param west
	 *            western band number
	 */
	public void setWest(int west) {
		this.west = west;
	}

	/**
	 * Get the eastern band number
	 * 
	 * @return eastern band number
	 */
	public int getEast() {
		return east;
	}

	/**
	 * Set the eastern band number
	 * 
	 * @param east
	 *            eastern band number
	 */
	public void setEast(int east) {
		this.east = east;
	}

	/**
	 * Get the western longitude
	 * 
	 * @return longitude
	 */
	public double getWestLongitude() {
		return GARSUtils.getLongitude(west);
	}

	/**
	 * Get the eastern longitude
	 * 
	 * @return longitude
	 */
	public double getEastLongitude() {
		return GARSUtils.getLongitude(east);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			/**
			 * Band number
			 */
			private int number = west;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return number <= east;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Integer next() {
				return number++;
			}

		};
	}

}
