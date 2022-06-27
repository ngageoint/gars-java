package mil.nga.gars.grid;

import java.util.Iterator;

import mil.nga.gars.GARS;
import mil.nga.grid.features.Bounds;

/**
 * Grid Range
 * 
 * @author osbornb
 */
public class GridRange implements Iterable<GARS> {

	/**
	 * Band Number Range
	 */
	private BandNumberRange bandNumberRange;

	/**
	 * Band Letters Range
	 */
	private BandLettersRange bandLettersRange;

	/**
	 * Constructor, full range
	 */
	public GridRange() {
		this.bandNumberRange = new BandNumberRange();
		this.bandLettersRange = new BandLettersRange();
	}

	/**
	 * Constructor
	 * 
	 * @param bandNumberRange
	 *            band number range
	 * @param bandLettersRange
	 *            band letters range
	 */
	public GridRange(BandNumberRange bandNumberRange,
			BandLettersRange bandLettersRange) {
		this.bandNumberRange = bandNumberRange;
		this.bandLettersRange = bandLettersRange;
	}

	/**
	 * Get the band number range
	 * 
	 * @return band number range
	 */
	public BandNumberRange getBandNumberRange() {
		return bandNumberRange;
	}

	/**
	 * Set the band number range
	 * 
	 * @param bandNumberRange
	 *            band number range
	 */
	public void setBandNumberRange(BandNumberRange bandNumberRange) {
		this.bandNumberRange = bandNumberRange;
	}

	/**
	 * Get the band letters range
	 * 
	 * @return band letters range
	 */
	public BandLettersRange getBandLettersRange() {
		return bandLettersRange;
	}

	/**
	 * Set the band letters range
	 * 
	 * @param bandLettersRange
	 *            band letters range
	 */
	public void setBandLettersRange(BandLettersRange bandLettersRange) {
		this.bandLettersRange = bandLettersRange;
	}

	/**
	 * Get the grid range bounds
	 * 
	 * @return bounds
	 */
	public Bounds getBounds() {

		double west = bandNumberRange.getWestLongitude();
		double south = bandLettersRange.getSouthLatitude();
		double east = bandNumberRange.getEastLongitude();
		double north = bandLettersRange.getNorthLatitude();

		return Bounds.degrees(west, south, east, north);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<GARS> iterator() {
		return new Iterator<GARS>() {

			/**
			 * Band numbers
			 */
			private final Iterator<Integer> bandNumbers = bandNumberRange
					.iterator();

			/**
			 * Band letters
			 */
			private Iterator<String> bandLetters = bandLettersRange.iterator();

			/**
			 * Current band number
			 */
			private Integer bandNumber = bandNumbers.hasNext()
					? bandNumbers.next()
					: null;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return bandNumber != null && bandLetters.hasNext();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public GARS next() {
				String letters = bandLetters.next();
				GARS gars = GARS.create(bandNumber, letters);
				if (!bandLetters.hasNext()) {
					if (bandNumbers.hasNext()) {
						bandNumber = bandNumbers.next();
						bandLetters = bandLettersRange.iterator();
					} else {
						bandNumber = null;
					}
				}
				return gars;
			}

		};
	}

}
