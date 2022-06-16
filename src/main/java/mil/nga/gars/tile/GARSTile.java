package mil.nga.gars.tile;

import mil.nga.gars.GARSUtils;
import mil.nga.gars.features.Bounds;
import mil.nga.gars.features.Point;

/**
 * Global Area Reference System Tile
 * 
 * @author osbornb
 */
public class GARSTile {

	/**
	 * Tile width
	 */
	private int width;

	/**
	 * Tile height
	 */
	private int height;

	/**
	 * Zoom level
	 */
	private int zoom;

	/**
	 * Bounds
	 */
	private Bounds bounds;

	/**
	 * Create a tile
	 * 
	 * @param width
	 *            tile width
	 * @param height
	 *            tile height
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param zoom
	 *            zoom level
	 * @return tile
	 */
	public static GARSTile create(int width, int height, int x, int y,
			int zoom) {
		return new GARSTile(width, height, x, y, zoom);
	}

	/**
	 * Create a tile
	 * 
	 * @param width
	 *            tile width
	 * @param height
	 *            tile height
	 * @param bounds
	 *            tile bounds
	 * @return tile
	 */
	public static GARSTile create(int width, int height, Bounds bounds) {
		return new GARSTile(width, height, bounds);
	}

	/**
	 * Constructor
	 * 
	 * @param width
	 *            tile width
	 * @param height
	 *            tile height
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param zoom
	 *            zoom level
	 */
	public GARSTile(int width, int height, int x, int y, int zoom) {
		this.width = width;
		this.height = height;
		this.zoom = zoom;
		this.bounds = GARSUtils.getBounds(x, y, zoom);
	}

	/**
	 * Constructor
	 * 
	 * @param width
	 *            tile width
	 * @param height
	 *            tile height
	 * @param bounds
	 *            tile bounds
	 */
	public GARSTile(int width, int height, Bounds bounds) {
		this.width = width;
		this.height = height;
		this.bounds = bounds;
		this.zoom = (int) Math.round(GARSUtils.getZoomLevel(bounds));
	}

	/**
	 * Get the tile width
	 * 
	 * @return tile width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the tile height
	 * 
	 * @return tile height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get the zoom level
	 * 
	 * @return zoom level
	 */
	public int getZoom() {
		return zoom;
	}

	/**
	 * Get the tile bounds
	 * 
	 * @return bounds
	 */
	public Bounds getBounds() {
		return bounds;
	}

	/**
	 * Get the point pixel location in the tile
	 * 
	 * @param point
	 *            point
	 * @return pixel
	 */
	public Pixel getPixel(Point point) {
		return GARSUtils.getPixel(width, height, bounds, point);
	}

	/**
	 * Get the longitude in meters x pixel location in the tile
	 * 
	 * @param longitude
	 *            longitude in meters
	 * @return x pixel
	 */
	public float getXPixel(double longitude) {
		return GARSUtils.getXPixel(width, bounds, longitude);
	}

	/**
	 * Get the latitude (in meters) y pixel location in the tile
	 * 
	 * @param latitude
	 *            latitude in meters
	 * @return y pixel
	 */
	public float getYPixel(double latitude) {
		return GARSUtils.getYPixel(height, bounds, latitude);
	}

}
