package mil.nga.gars.grid;

import java.util.ArrayList;
import java.util.List;

import mil.nga.color.Color;
import mil.nga.gars.GARS;
import mil.nga.gars.GARSUtils;
import mil.nga.grid.features.Bounds;
import mil.nga.grid.features.Point;

/**
 * GARS grid labeler
 * 
 * @author osbornb
 */
public class GARSLabeler extends GridLabeler {

	/**
	 * Default Constructor
	 */
	public GARSLabeler() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param minZoom
	 *            minimum zoom
	 * @param color
	 *            label color
	 */
	public GARSLabeler(int minZoom, Color color) {
		super(minZoom, color);
	}

	/**
	 * Constructor
	 * 
	 * @param minZoom
	 *            minimum zoom
	 * @param color
	 *            label color
	 * @param textSize
	 *            label text size
	 */
	public GARSLabeler(int minZoom, Color color, double textSize) {
		super(minZoom, color, textSize);
	}

	/**
	 * Constructor
	 * 
	 * @param minZoom
	 *            minimum zoom
	 * @param color
	 *            label color
	 * @param textSize
	 *            label text size
	 * @param buffer
	 *            grid edge buffer (greater than or equal to 0.0 and less than
	 *            0.5)
	 */
	public GARSLabeler(int minZoom, Color color, double textSize,
			double buffer) {
		super(minZoom, color, textSize, buffer);
	}

	/**
	 * Constructor
	 * 
	 * @param minZoom
	 *            minimum zoom
	 * @param maxZoom
	 *            maximum zoom
	 * @param color
	 *            label color
	 */
	public GARSLabeler(int minZoom, Integer maxZoom, Color color) {
		super(minZoom, maxZoom, color);
	}

	/**
	 * Constructor
	 * 
	 * @param minZoom
	 *            minimum zoom
	 * @param maxZoom
	 *            maximum zoom
	 * @param color
	 *            label color
	 * @param textSize
	 *            label text size
	 */
	public GARSLabeler(int minZoom, Integer maxZoom, Color color,
			double textSize) {
		super(minZoom, maxZoom, color, textSize);
	}

	/**
	 * Constructor
	 * 
	 * @param minZoom
	 *            minimum zoom
	 * @param maxZoom
	 *            maximum zoom
	 * @param color
	 *            label color
	 * @param textSize
	 *            label text size
	 * @param buffer
	 *            grid edge buffer (greater than or equal to 0.0 and less than
	 *            0.5)
	 */
	public GARSLabeler(int minZoom, Integer maxZoom, Color color,
			double textSize, double buffer) {
		super(minZoom, maxZoom, color, textSize, buffer);
	}

	/**
	 * Constructor
	 * 
	 * @param enabled
	 *            enabled labeler
	 * @param minZoom
	 *            minimum zoom
	 * @param maxZoom
	 *            maximum zoom
	 * @param color
	 *            label color
	 */
	public GARSLabeler(boolean enabled, int minZoom, Integer maxZoom,
			Color color) {
		super(enabled, minZoom, maxZoom, color);
	}

	/**
	 * Constructor
	 * 
	 * @param enabled
	 *            enabled labeler
	 * @param minZoom
	 *            minimum zoom
	 * @param maxZoom
	 *            maximum zoom
	 * @param color
	 *            label color
	 * @param textSize
	 *            label text size
	 */
	public GARSLabeler(boolean enabled, int minZoom, Integer maxZoom,
			Color color, double textSize) {
		super(enabled, minZoom, maxZoom, color, textSize);
	}

	/**
	 * Constructor
	 * 
	 * @param enabled
	 *            enabled labeler
	 * @param minZoom
	 *            minimum zoom
	 * @param maxZoom
	 *            maximum zoom
	 * @param color
	 *            label color
	 * @param textSize
	 *            label text size
	 * @param buffer
	 *            grid edge buffer (greater than or equal to 0.0 and less than
	 *            0.5)
	 */
	public GARSLabeler(boolean enabled, int minZoom, Integer maxZoom,
			Color color, double textSize, double buffer) {
		super(enabled, minZoom, maxZoom, color, textSize, buffer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GridLabel> getLabels(Bounds tileBounds, GridType gridType) {

		List<GridLabel> labels = new ArrayList<>();

		double precision = gridType.getPrecision();

		tileBounds = tileBounds.toPrecision(precision);

		for (double lon = tileBounds.getMinLongitude(); lon <= tileBounds
				.getMaxLongitude(); lon = GARSUtils.nextPrecision(lon,
						precision)) {

			for (double lat = tileBounds.getMinLatitude(); lat <= tileBounds
					.getMaxLatitude(); lat = GARSUtils.nextPrecision(lat,
							precision)) {

				Bounds bounds = Bounds.degrees(lon, lat, lon + precision,
						lat + precision);
				Point center = bounds.getCentroid();
				GARS coordinate = GARS.from(center);

				String name = null;

				switch (gridType) {
				case TWENTY_DEGREE:
				case TEN_DEGREE:
				case FIVE_DEGREE:
				case ONE_DEGREE:
					name = GARSUtils.getDegreeLabel(lon, lat);
					break;
				default:
					name = coordinate.coordinate(gridType);
				}

				labels.add(new GridLabel(name, center, bounds, gridType,
						coordinate));

			}
		}

		return labels;
	}

}
