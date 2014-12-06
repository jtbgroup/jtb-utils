package be.smd.i18n;

import java.util.Locale;
import java.util.Set;

/**
 * Handles a resource bundle for all its localized forms.
 *
 * @author Pierre Jeanmenne
 * @version 05-11-08
 */
public interface ResourceHandler {
	
	/**
     * Gets the localized text stored in a resource bundle,
     * for the specified key, and for the specified locale.
     * 
     * @param bundleKey	- the key of the requested text in the resource bundle
     * @param locale 	- the locale of the resource bundle
     * @return the requested localized text
     */
    String getString(String bundleKey, Locale locale);
    
    /**
     * Gets the localized object stored in a resource bundle,
     * for the specified key, and for a specified locale.
     * 
     * @param bundleKey	- the key of the requested localized object in the resource bundle
     * @param locale 	- the locale of the resource bundle
     * @return the requested localized object
     */
    Object getObject(String bundleKey, Locale locale);
    
	/**
     * Gets the localized message for the specified bundle key, and for the specified locale.
     * If the requested message contains place holders, they will be substituted by the given arguments
     * 
     * @param messageKey - the key in the resource bundle for the message to find
     * @param args 		 - the array with substitution parameters for the message
     * @return the requested localized message
     */
    String getMessage(String messageKey, Locale locale, Object... args);

    /**
     * Gets the set with the locales managed by the resource handler
     * @return the set with the locales managed by the resource handler
     */
	Set<Locale> getManagedLocales();
	
	/**
	 * Loads the resource bundle with the given base name, and for the specified locales 
	 * @param bundleBaseName - the name of the resource bundle to load
	 * @param locales 		 - an array with the locales to consider for the bundle to load
	 */
	void addBundle(String bundleBaseName, Locale...locales);
}
