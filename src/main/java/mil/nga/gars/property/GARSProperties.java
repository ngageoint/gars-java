package mil.nga.gars.property;

import mil.nga.grid.property.GridProperties;

/**
 * GARS property loader
 *
 * @author osbornb
 */
public class GARSProperties extends GridProperties {

	/**
	 * Property file name
	 */
	public static final String PROPERTIES_FILE = "gars.properties";

	/**
	 * Singleton instance
	 */
	public static GARSProperties instance = new GARSProperties();

	/**
	 * Get the singleton instance
	 * 
	 * @return instance
	 */
	public static GARSProperties getInstance() {
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFile() {
		return PROPERTIES_FILE;
	}

}
