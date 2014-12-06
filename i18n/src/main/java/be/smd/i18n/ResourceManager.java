package be.smd.i18n;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Resource Manager defines the expected behavior of any utility class used
 * to manage resource bundles that contain locale-specific objects.
 *  
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 6 mars 2009
 */
public interface ResourceManager {

	/**
	 * Adds a resource bundle to be managed by the resource manager.
	 * 
	 * @param bundleBaseName - the base name of the bundle to add.
	 * @param locales        - an array with the locales to consider for the bundle to add 
	 */
	void addBundle(String bundleBaseName, Locale... locales);
	
	/**
	 * Gets the localized text for the specified bundle key, and for the resource manager default locale
	 * 
	 * @param bundleKey	- the key of the requested text in the resource bundle
	 * @return the requested localized text
	 */
	String getString(String bundleKey);

	/**
	 * Gets the localized text for the specified bundle key and for the specified locale.
	 * 
	 * @param bundleKey	- the key of the requested text in the resource bundle
	 * @param locale	- the locale of the resource bundle
	 * @return the requested localized text
	 */
	String getString(String bundleKey, Locale locale);

	/**
	 * Gets the localized text for the specified bundle key,
	 * in the resource bundle with the given base name,  and for the resource manager default locale
	 * 
	 * @param bundleKey		 - the key of the requested text in the resource bundle
	 * @param bundleBaseName - the name of the bundle where the localized text should be found
	 * @return the requested localized text
	 */
	String getString(String bundleKey, String bundleBaseName);

	/**
	 * Gets the localized text for the specified bundle key,
	 * from the resource bundle with the given base name, and for the specified locale.
	 * 
	 * @param bundleKey		 - the key of the requested text in the resource bundle
	 * @param bundleBaseName - the name of the bundle where the localized text should be found
	 * @param locale 		 - the locale of the resource bundle
	 * @return the requested localized text
	 */
	String getString(String bundleKey, String bundleBaseName, Locale locale);

	/**
	 * Gets the localized text message for the specified bundle key, and for the resource manager default locale.<br/>
	 * If the requested message contains place holders, they will be substituted by the given arguments
	 * 
	 * @param bundleKey - the key of the requested message in the resource bundle
	 * @param args 		- an object array with substitution parameters for the message
	 * @return the requested localized text message
	 */
	String getMessage(String bundleKey, Object... args);

	/**
	 * Gets the localized text message for the specified bundle key, and for the specified locale.<br/>
	 * If the requested message contains place holders, they will be substituted by the given arguments
	 * 
	 * @param bundleKey - the key of the requested message in the resource bundle
	 * @param args 		- an object array with substitution parameters for the message
	 * @return the requested localized text message
	 */
	String getMessage(String bundleKey, Locale locale, Object... args);

	/**
	 * Gets the localized text message for the specified bundle key, and for the resource manager default locale.<br/>
	 * If the requested message contains place holders, they will be substituted by the given arguments
	 * 
	 * @param bundleKey  	 - the key of the requested message in the resource bundle
	 * @param bundleBaseName - the name of the bundle where the value should be found
	 * @param locale		 - the locale of the resource bundle
	 * @param args 			 - an object array with substitution parameters for the message
	 * @return the requested localized text message
	 */
	String getMessage(String bundleKey, String bundleBaseName, Object... args);

	/**
	 * Gets the localized text message for the specified bundle key,
	 * from the resource bundle with the given base name, and for the specified locale.<br/>
	 * If the requested message contains place holders, they will be substituted by the given arguments
	 * 
	 * @param bundleKey 	 - the key of the requested message in the resource bundle
	 * @param bundleBaseName - the name of the bundle where the localized text message should be found
	 * @param locale		 - the locale of the resource bundle
	 * @param args 			 - the array with substitution parameters for the message
	 * @return the requested localized text message
	 */
	String getMessage(String bundleKey, String bundleName, Locale locale, Object... args);
	
	/**
	 * Gets the localized object for the specified bundle key, and for the resource manager default locale<br/>
	 * This method should be used only if resources are stored in a subclass of {@link ResourceBundle}
	 * 
	 * @param bundleKey - the key of the requested localized object in the resource bundle
	 * @return the requested localized object
	 */
	Object getObject(String bundleKey);
	
	/**
	 * Gets the localized object for the specified bundle key, and for specified locale<br/>
	 * This method should be used only if resources are stored in a subclass of {@link ResourceBundle}
	 * 
	 * @param bundleKey - the key of the requested localized object in the resource bundle
	 * @param locale	- the locale of the resource bundle
	 * @return the requested localized object
	 */
	Object getObject(String bundleKey, Locale locale);
	
	/**
	 * Gets the localized object for the specified bundle key,
	 * from the resource bundle with the given name, and for the resource manager default locale<br/>
	 * This method should be used only if resources are stored in a subclass of {@link ResourceBundle}
	 * 
	 * @param bundleKey 	 - the key of the requested localized object in the resource bundle
	 * @param bundleBaseName - the name of the bundle where the localized object should be found
	 * @return the requested localized object
	 */
	Object getObject(String bundleKey, String bundleBaseName);
	
	/**
	 * Gets the localized object for the specified bundle key,
	 * from the resource bundle with the given name, and for specified locale<br/>
	 * This method should be used only if resources are stored in a subclass of {@link ResourceBundle}
	 * 
	 * @param bundleKey 	 - the key of the requested localized object in the resource bundle
	 * @param bundleBaseName - the name of the bundle where the localized object should be found
	 * @param locale	 	 - the locale of the resource bundle
	 * @return the requested localized object
	 */
	Object getObject(String bundleKey, String bundleBaseName, Locale locale);
	
	/**
	 * Indicates if the resource manager has to return <code>null</code> if a resource is missing
	 * @return <code>true</code> if the resource manager has to return <code>null</code> for missing
	 * resources, <code>false</code> otherwise
	 */
	boolean isNullable();
	
	/**
	 * Indicates if the resource manager has to return <code>null</code> if a resource is missing
	 * @param nullable - <code>true</code> if the resource manager has to return <code>null</code>
	 * 					 for missing resource, <code>false</code> otherwise
	 */
	void setNullable(boolean nullable);

	/**
	 * Gets a set with the locales managed by the resource manager
	 * @return a set of the locales managed by the resource manager
	 */
	Set<Locale> getManagedLocales();

	/**
	 * Gets the locale currently used as default by the resource manager
	 * @return the locale currently used as default by the resource manager
	 */
	Locale getDefaultLocale();
}