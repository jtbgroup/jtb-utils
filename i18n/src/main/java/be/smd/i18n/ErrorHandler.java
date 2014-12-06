package be.smd.i18n;

import java.util.Locale;

/**
 * Used to perform some processing when a resource cannot be found.<br/>
 * 
 * Its unique method should be called before returning <code>null</code>,
 * or the substitution value when a resource cannot be found.
 * 
 * @author Pierre Jeanmenne
 * @version 05-11-08
 */
public interface ErrorHandler {

	/**
	 * If an error handler is set to the {@link ResourceManager}, 
	 * this method should be called before returning <code>null</code>,
	 * or the substitution value when a resource cannot be found.
	 * 
	 * @param bundleKey - the bundle key for which no localized resource has been found
	 * @param locale 	- the requested locale for the resource to find
	 */
	void resourceNotFound(String bundleKey, Locale locale);
}
