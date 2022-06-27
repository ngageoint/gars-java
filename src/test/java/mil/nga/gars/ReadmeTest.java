package mil.nga.gars;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import mil.nga.gars.features.GridLine;
import mil.nga.gars.grid.Grid;
import mil.nga.gars.grid.GridType;
import mil.nga.gars.grid.Grids;
import mil.nga.gars.grid.Label;
import mil.nga.gars.grid.ZoomGrids;
import mil.nga.grid.features.Point;
import mil.nga.grid.tile.GridTile;
import mil.nga.grid.tile.Pixel;
import mil.nga.grid.tile.PixelRange;

/**
 * README example tests
 *
 * @author osbornb
 */
public class ReadmeTest {

	/**
	 * Test GARS coordinates
	 * 
	 * @throws ParseException
	 *             upon failure to parse
	 */
	@Test
	public void testCoordinates() throws ParseException {

		GARS gars = GARS.parse("006AG39");
		Point point = gars.toPoint();
		Point pointMeters = point.toMeters();

		double latitude = 63.98862388;
		double longitude = 29.06755082;
		Point point2 = Point.create(longitude, latitude);
		GARS gars2 = GARS.from(point2);
		String garsCoordinate = gars2.toString();
		String gars30m = gars2.coordinate(GridType.THIRTY_MINUTE);
		String gars15m = gars2.coordinate(GridType.FIFTEEN_MINUTE);
		String gars5m = gars2.coordinate(GridType.FIVE_MINUTE);

	}

	/**
	 * Test draw tile template logic
	 */
	@Test
	public void testDrawTile() {
		testDrawTile(GridTile.create(512, 512, 8, 12, 5));
	}

	/**
	 * Test draw tile template logic
	 * 
	 * @param tile
	 *            GARS tile
	 */
	private static void testDrawTile(GridTile tile) {

		// GridTile tile = ...;

		Grids grids = Grids.create();

		ZoomGrids zoomGrids = grids.getGrids(tile.getZoom());
		if (zoomGrids.hasGrids()) {

			for (Grid grid : zoomGrids) {

				List<GridLine> lines = grid.getLines(tile);
				if (lines != null) {
					for (GridLine line : lines) {
						Pixel pixel1 = line.getPoint1().getPixel(tile);
						Pixel pixel2 = line.getPoint2().getPixel(tile);
						// Draw line
					}
				}

				List<Label> labels = grid.getLabels(tile);
				if (labels != null) {
					for (Label label : labels) {
						PixelRange pixelRange = label.getBounds()
								.getPixelRange(tile);
						Pixel centerPixel = label.getCenter().getPixel(tile);
						// Draw label
					}
				}

			}
		}

	}

}
