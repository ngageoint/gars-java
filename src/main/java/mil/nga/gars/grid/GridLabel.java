package mil.nga.gars.grid;

import mil.nga.gars.GARS;
import mil.nga.grid.Label;
import mil.nga.grid.features.Bounds;
import mil.nga.grid.features.Point;

/**
 * GARS Grid Label
 * 
 * @author osbornb
 */
public class GridLabel extends Label {

	/**
	 * Grid type
	 */
	private GridType gridType;

	/**
	 * GARS coordinate
	 */
	private GARS coordinate;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            name
	 * @param center
	 *            center point
	 * @param bounds
	 *            bounds
	 * @param gridType
	 *            grid type
	 * @param coordinate
	 *            GARS coordinate
	 */
	public GridLabel(String name, Point center, Bounds bounds,
			GridType gridType, GARS coordinate) {
		super(name, center, bounds);
		this.gridType = gridType;
		this.coordinate = coordinate;
	}

	/**
	 * Get the grid type
	 * 
	 * @return grid type
	 */
	public GridType getGridType() {
		return gridType;
	}

	/**
	 * Set the grid type
	 * 
	 * @param gridType
	 *            grid type
	 */
	public void setGridType(GridType gridType) {
		this.gridType = gridType;
	}

	/**
	 * Get the GARS coordinate
	 * 
	 * @return GARS coordinate
	 */
	public GARS getCoordinate() {
		return coordinate;
	}

	/**
	 * Set the GARS coordinate
	 * 
	 * @param coordinate
	 *            GARS coordinate
	 */
	public void setCoordinate(GARS coordinate) {
		this.coordinate = coordinate;
	}

}
