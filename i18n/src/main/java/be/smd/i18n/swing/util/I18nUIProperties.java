package be.smd.i18n.swing.util;

import java.util.HashMap;
import java.util.Map;

/**
 * The class that stores the UI properties for the components of the I18n framework
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 19-12-2008
 */
public final class I18nUIProperties {
	
	public static final String TEXT_ANTIALIASING = "text.antialiasing";

	/** Map which stores the UI properties for the application */
	private static Map<String, Object> uiProperties;
	
	static{
		initUIProperties();
	}

	/** Creates a {@link I18nUIProperties} */
	private I18nUIProperties(){}
	
	/** Initializes the default UI properties */
	private static void initUIProperties() {
		uiProperties = new HashMap<String, Object>();
		putUIProperty(TEXT_ANTIALIASING, true);
	}
	
	public static void enableTextAntiAliasing(boolean enabled){
		putUIProperty(TEXT_ANTIALIASING, enabled );
	}
	
	public static boolean isTextAntiAliasingEnabled(){
		return (Boolean) getUIProperty(TEXT_ANTIALIASING);
	}
	
	/**
	 * Adds or replaces a UI property for the application
	 * 
	 * @param propertyKey	{@link String} the key of the property
	 * @param PropertyValue	{@link Object} the value of the property
	 */
	public static void putUIProperty(String propertyKey, Object PropertyValue){
		uiProperties.put(propertyKey, PropertyValue);
	}
	
	/**
	 * Returns an UI property value for a given key
	 * @param propertyKey	{@link String}	the key of the UI property
	 * @return the UI property value or <code>null</code> if no such key is found
	 */
	public static Object getUIProperty(String propertyKey){
		return uiProperties.get(propertyKey);
	}
}
