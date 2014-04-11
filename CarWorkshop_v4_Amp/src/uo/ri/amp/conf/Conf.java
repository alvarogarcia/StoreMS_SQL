package uo.ri.amp.conf;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Álvaro García
 * 
 *         Clase que recupera las consultas del archivo configuration.properties
 * 
 */
public class Conf {

	private static final String CONF_FILE = "configuration.properties";

	private static Conf instance;
	private Properties properties;

	/**
	 * Constructor
	 */
	private Conf() {
		properties = new Properties();
		try {
			properties.load(Conf.class.getClassLoader().getResourceAsStream(
					CONF_FILE));
		} catch (IOException e) {
			throw new RuntimeException("Properties file can not be loaded", e);
		}
	}

	/**
	 * Recupera la consulta SQL almacenada en el fichero
	 * configuration.properties
	 * 
	 * @param key
	 *            Key de la consulta SQL guardada
	 * @return Consulta SQL necesitada.
	 */
	public static String get(String key) {
		return getInstance().getProperty(key);
	}

	public String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property not found in config file");
		}
		return value;
	}

	/**
	 * @return Nueva instancia del la clase Conf.
	 */
	private static Conf getInstance() {
		instance = new Conf();
		return instance;
	}
}