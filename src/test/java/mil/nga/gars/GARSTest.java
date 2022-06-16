package mil.nga.gars;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

/**
 * GARS Test
 * 
 * @author osbornb
 */
public class GARSTest {

	/**
	 * Test parsing a GARS string value
	 * 
	 * @throws ParseException
	 *             upon failure to parse
	 */
	@Test
	public void testParse() throws ParseException {

		String garsValue = "006AG";

		assertTrue(GARS.isGARS(garsValue));
		GARS gars = GARS.parse(garsValue);

		garsValue = "006AG3";

		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);

		garsValue = "006AG39";

		assertTrue(GARS.isGARS(garsValue));
		gars = GARS.parse(garsValue);

	}

}
