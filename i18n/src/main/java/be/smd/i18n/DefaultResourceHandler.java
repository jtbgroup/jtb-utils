package be.smd.i18n;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Default implementation of the {@link ResourceHandler} interface.
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 05-11-2008
 */
public class DefaultResourceHandler implements ResourceHandler{

	/** The data structure to hold the resource bundles */
	protected Map<Locale, ResourceBundle> bundles;
	
	/**
	 * Creates a {@link DefaultResourceHandler}
	 * The constructor is set as <code>protected</code> to allow it to be specialized
	 */
	protected DefaultResourceHandler(){
		bundles = new HashMap<Locale, ResourceBundle>();
	}
	
	/** @see ResourceHandler#getString(String, Locale) */
	public String getString(String bundleKey, Locale locale) {
		
		Object objectValue = getObject(bundleKey, locale);
		if(objectValue != null)
			return objectValue.toString();
		return null;
	}
	
	/** @see ResourceHandler#getMessage(String, Locale, Object...) */
    public String getMessage(String messageKey, Locale locale, Object... args){
    	
    	Object message = getObject(messageKey, locale);
    	if(message != null)
    		return getFormattedMessage(message.toString(), args); 
    	return null;
    }

    /**
     * Formats the given message by replacing its place holders by the given substitution values
     * @param message - the message to format
     * @param args 	  - an array with the substitution values for the message placeholders
     * @return the formatted message
     */
	private String getFormattedMessage(String message, Object... args) {
		
		message = message.replaceAll("'", "''");
		if(args != null)
			return new MessageFormat(message).format(args);
		else{
			MessageFormat mf = new MessageFormat(message);
			String[] dummyArgs = new String[mf.getFormats().length];
			for (int i = 0; i < dummyArgs.length; i++)
				dummyArgs[i] = "???";
			return mf.format(dummyArgs);
		}
	}

	/**
	 * Installs the given bundles for the given locales
	 * @param bundleBaseName - the base name of the bundle to install
	 * @param locales 		 - an array with the locales for the bundles to load
	 * @throws NullPointerException if the base name or the locale is <code>null</code>
	 * @throws MissingResourceException if no bundle for the specified base name can be found
	 */
	public void addBundle(String bundleBaseName, Locale... locales){
		
		for (Locale locale : locales) 
			bundles.put(locale, ResourceBundle.getBundle(bundleBaseName, locale));
	}

	/** @see ResourceHandler#getAvailableLocales() */
	public Set<Locale> getManagedLocales() {
		return bundles.keySet();
	}

	public Object getObject(String bundleKey, Locale locale) {
		
		if(bundles.containsKey(locale)){
			try {
    			ResourceBundle bundle = bundles.get(locale);
    			return bundle.getObject(bundleKey);
    		} catch (NullPointerException e) {
    			return null;
    		} catch (MissingResourceException e) {
    			return null;
    		}
		}
    	return null;
	}
}
