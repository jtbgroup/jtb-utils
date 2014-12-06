package be.smd.i18n;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Default implementation to manage resource bundles that contain locale-specific objects.
 * 
 * @author Pierre Jeanmenne
 * @version 05-11-08
 */
public class I18nResourceManager implements ResourceManager {
	
	/** A set with the locales managed by the resource manager */
	protected Set<Locale> managedLocales;
	
	/** The default locale that will be used if no locale is specified on resource query */
	protected Locale defaultLocale = new Locale("en","EN");
	
	/** Indicates if the resource manager has to return <code>null</code> for missing resources */
	protected boolean nullable;
	
	/** The optional context handler used to limit the set of bundles for searching resources */
	private ContextHandler contextHandler;
	
	/** The optional error handler used when a resource cannot be found */
	private ErrorHandler errorHandler;
	
	/** The data structure that hold the {@link ResourceHandler} */
	private Map<String, ResourceHandler> resourceHandlers;
	
	/** The unique instance of this Resource Manager */
	private static I18nResourceManager sharedInstance;

	/** Creates a {@link I18nResourceManager} */
	private I18nResourceManager(){
		resourceHandlers = new HashMap<String, ResourceHandler>();
		contextHandler = new DefaultContextHandler();
		errorHandler = new DefaultErrorHandler();
	}
	
	/**
	 * <p>
	 * Adds a resource bundle to be managed by the resource manager.<br/>
	 * The resource bundle with the specified base name and for the given locale will be added using the caller's class loader.
	 * </p>
	 * 
	 * <p>
	 * Following the I18n standard implementation, the resource can be stored in a text file
	 * or in a java class that extends {@link ResourceBundle}. <br/>
	 * If the resources are stored in a text file, the bundle name parameter have to be
	 * the name of the file without its <code>".properties"</code> extension, and without its locale suffix.
	 *   <blockquote>
	 *   ex: for a resource bundle named <code>"globalResources_en_EN.properties"</code> and stored in the directory <code>"resources"</code>,</br>
	 *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the bundle name parameter value should be <code>"bundles.globalResources"</code> 
	 *   </blockquote>
	 * If the resources are stored in a subclass of <code>ResourceBundle</code>, the bundle name parameter have to be
	 * the fully qualified name without its <code>".class"</code> extension, and without its locale suffix.
	 *   <blockquote>
	 *   ex: for a resource bundle class named <code>"globalResources_en_EN.java"</code> and stored in the package <code>"resources"</code>,</br>
	 *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the bundle name parameter value should be <code>"bundles.globalResources"</code> 
	 *   </blockquote>
	 * </p>
	 * 
	 * @param bundleBaseName - the base name of the bundle to add.
	 * @param managedLocales - an array with the locales to consider for the bundle to add 
	 */
	public void addBundle(String bundleBaseName, Locale...managedLocales){
		addBundle(bundleBaseName, new DefaultResourceHandler(), managedLocales);
	}
	
	/**
	 * <p>
	 * Adds a resource bundle to be managed by the resource manager.<br/>
	 * The resource bundle with the specified base name and for the given locale will be added using the caller's class loader.
	 * </p>
	 * 
	 * <p>
	 * Following the I18n standard implementation, the resource can be stored in a text file
	 * or in a java class that extends {@link ResourceBundle}. <br/>
	 * If the resources are stored in a text file, the bundle name parameter have to be
	 * the name of the file without its <code>".properties"</code> extension, and without its locale suffix.
	 *   <blockquote>
	 *   ex: for a resource bundle named <code>"globalResources_en_EN.properties"</code> and stored in the directory <code>"resources"</code>,</br>
	 *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the bundle name parameter value should be <code>"bundles.globalResources"</code> 
	 *   </blockquote>
	 * If the resources are stored in a subclass of <code>ResourceBundle</code>, the bundle name parameter have to be
	 * the fully qualified name without its <code>".class"</code> extension, and without its locale suffix.
	 *   <blockquote>
	 *   ex: for a resource bundle class named <code>"globalResources_en_EN.java"</code> and stored in the package <code>"resources"</code>,</br>
	 *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the bundle name parameter value should be <code>"bundles.globalResources"</code> 
	 *   </blockquote>
	 * </p>
	 * 
	 * @param bundleBaseName - the base name of the bundle to add.
	 * @param handler		 - the handler used to access the resources of the specified bundle
	 * @param managedLocales - an array with the locales to consider for the bundle to add 
	 */
	public void addBundle(String bundleBaseName, ResourceHandler handler, Locale...args){
		handler.addBundle(bundleBaseName, args);
		resourceHandlers.put(bundleBaseName, handler);
	}
	
	/**
	 * <p>Sets the context handler that should be used to limit the set of bundles where to search the resources.<p/>
	 * 
	 * <p>If the specified context handler is <code>null</code>, then a default context handler will be set which<br/>
	 * <code>getBundlesForContext()</code> method returns an array with all the base names of the bundles managed by this resource handler.</p>
	 * @param handler - the context handler to set
	 */
	public void setContextHandler(ContextHandler handler){
		contextHandler = handler != null ? handler : new DefaultContextHandler();
	}
	
	/**
	 * <p>Sets the error handler that should be used when a resource cannot be found</p>
	 * 
	 * <p>If the specified error handler is <code>null</code>, then a default error handler will be set which
	 * <code>resourceNotFound(String, Locale)</code> does nothing.</p>
	 * 
	 * @param handler - the error handler to set
	 */
	public void setErrorHandler(ErrorHandler handler){
		errorHandler = handler != null ? handler : new DefaultErrorHandler();
	}
	
	/** @see be.smd.i18n.ResourceManager#getString(String) */
	public String getString(String bundleKey){
		return getString(bundleKey, defaultLocale);
	}
   
	/** @see be.smd.i18n.ResourceManager#getString(String, Locale) */
	public String getString(String bundleKey, Locale locale){
		String value;
		ResourceHandler handler = null;
		for (String bundleName : contextHandler.getBundlesForContext()) {
			if(resourceHandlers.containsKey(bundleName)){
				handler = resourceHandlers.get(bundleName);
				if((value = handler.getString(bundleKey, locale)) != null)
					return value;
			}
		}
		return getValueForMissingResource(bundleKey, locale);
	}
	
	/** @see be.smd.i18n.ResourceManager#getString(String, String) */
	public String getString(String bundleKey, String bundleName){
		return getString(bundleKey, bundleName, defaultLocale);
	}
	   
	/** @see be.smd.i18n.ResourceManager#getString(String, String, Locale) */
	public String getString(String bundleKey, String bundleName, Locale locale){
		String value;
		if(resourceHandlers.containsKey(bundleName)){
			ResourceHandler handler = resourceHandlers.get(bundleName);
			if((value = handler.getString(bundleKey, locale)) != null)
				return value;
		}
		return getValueForMissingResource(bundleKey, locale);
	}
	   
	/** @see be.smd.i18n.ResourceManager#getMessage(String, Object) */
	public String getMessage(String bundleKey, Object... args){
		return getMessage(bundleKey, defaultLocale, args);
	}
	  
	/** @see be.smd.i18n.ResourceManager#getMessage(String, Locale, Object) */
	public String getMessage(String bundleKey, Locale locale, Object... args){
		String message;
		ResourceHandler handler = null;
		for (String bundleName : contextHandler.getBundlesForContext()) {
			if(resourceHandlers.containsKey(bundleName)){
				handler = resourceHandlers.get(bundleName);
				if((message = handler.getMessage(bundleKey, locale, args)) != null)
					return message;
			}
		}
		return getValueForMissingResource(bundleKey, locale);
	}
	
	/** @see be.smd.i18n.ResourceManager#getMessage(String, String, Object) */
	public String getMessage(String bundleKey, String bundleName, Object... args){
		return getMessage(bundleKey, bundleName, defaultLocale, args);
	}
	  
	/** @see be.smd.i18n.ResourceManager#getMessage(String, String, Locale, Object) */
	public String getMessage(String bundleKey, String bundleName, Locale locale, Object... args){
		String message;
		if(resourceHandlers.containsKey(bundleName)){
			ResourceHandler handler = resourceHandlers.get(bundleName);
			if((message = handler.getMessage(bundleKey, locale, args)) != null)
				return message;
		}
		return getValueForMissingResource(bundleKey, locale);
	}
	
	/** @see be.smd.i18n.ResourceManager#getObject(String) */
    public Object getObject(String bundleKey) {
		return getObject(bundleKey, defaultLocale);
	}

    /** @see be.smd.i18n.ResourceManager#getObject(String, Locale) */
    public Object getObject(String bundleKey, Locale locale) {
    	Object value;
		ResourceHandler handler = null;
		for (String bundleName : contextHandler.getBundlesForContext()) {
			if(resourceHandlers.containsKey(bundleName)){
				handler = resourceHandlers.get(bundleName);
				if((value = handler.getObject(bundleKey, locale)) != null)
					return value;
			}
		}
		return getValueForMissingResource(bundleKey, locale);
	}
    
    /** @see be.smd.i18n.ResourceManager#getObject(String, String) */
    public Object getObject(String bundleKey, String bundleBaseName) {
		return getObject(bundleKey, bundleBaseName, defaultLocale);
	}

    /** @see be.smd.i18n.ResourceManager#getObject(String, String, Locale) */
    public Object getObject(String bundleKey, String bundleBaseName, Locale locale) {
    	Object value;
		if(resourceHandlers.containsKey(bundleBaseName)){
			ResourceHandler handler = resourceHandlers.get(bundleBaseName);
			if((value = handler.getObject(bundleKey, locale)) != null)
				return value;
		}
		return getValueForMissingResource(bundleKey, locale);
	}

	/**
	 * Gets the value to return when a resource is missing.<br/>
	 * If <code>null</code> cannot be returned, the bundle key surround by question marks will be returned
	 *  
	 * @param bundleKey - the bundle key for which no value was found
	 * @param locale 	- the requested locale for the resource to find
	 * @return <code>null</code> or a substitution text if null values are not allowed
	 */
	private String getValueForMissingResource(String bundleKey, Locale locale){
		errorHandler.resourceNotFound(bundleKey, locale);
		return isNullable() ? null : "??? " + bundleKey + " ???";
	}
	
	/** @see be.smd.i18n.ResourceManager#getManagedLocales() */
	public Set<Locale> getManagedLocales(){
		if(managedLocales == null){
			managedLocales = new HashSet<Locale>();
			for (ResourceHandler handler : resourceHandlers.values()) {
				managedLocales.addAll(handler.getManagedLocales());
			}
		}
		return managedLocales;
	}
	
	/** @see be.smd.i18n.ResourceManager#getLocale(String) */
    public Locale getLocale(String language){
    	for (Locale locale : getManagedLocales()) {
    		if(language.equalsIgnoreCase(locale.getLanguage()))
    			return locale;
    	}
    	return defaultLocale;
    }
    
    public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	/** @see be.smd.i18n.ResourceManager#getDefaultLocale() */
    public Locale getDefaultLocale(){
    	return defaultLocale;
    }

    /**
     * Sets the current locale for the user interface
     * @param locale {@link Locale} the locale to set
     */
    public void setDefaultLocale(Locale locale){
    	this.defaultLocale = locale;
    }

    /**
     * Returns the unique instance of {@link I18nResourceManager} and creates it as needed
     * @return {@link I18nResourceManager} the unique instance of {@link I18nResourceManager}
     */
    public static I18nResourceManager sharedInstance(){
    	return sharedInstance != null ? sharedInstance : (sharedInstance = new I18nResourceManager());
    }
    
    /**
     * Default context handler for this manager.
     * By default, all the bundles managed by the resource manager will be returned.
     * 
     * @author Pierre Jeanmenne
     * @version 05-12-08
     */
    private final class DefaultContextHandler implements ContextHandler{

    	/** An array with all the bundles managed by the i18n manager */
    	private String[] bundles;
		
    	/** @see ContextHandler#getBundlesForContext() */
    	public String[] getBundlesForContext() {
			
    		if(bundles == null){
    			List<String> bundlesList = new ArrayList<String>();
    			bundles = new String[resourceHandlers.size()];
    			for (String bundleBaseName : resourceHandlers.keySet()) {
					bundlesList.add(bundleBaseName);
				}
    			bundles = bundlesList.toArray(new String[bundlesList.size()]);
    		}
			return bundles;
		}
    }
    
    /**
     * Default error handler for the manager
     * By default it has no behavior
     * 
     * @author Pierre Jeanmenne
     * @version 05-12-08
     */
    private final class DefaultErrorHandler implements ErrorHandler{

		public void resourceNotFound(String bundleKey, Locale locale) {	}
    }
}
