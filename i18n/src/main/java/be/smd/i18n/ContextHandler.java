package be.smd.i18n;


/**
 * Used to specified in which resource bundle(s) the Resource Manager<br/>
 * must search a requested localized resource, depending on some application context<br/>
 * Its unique method will be called before any resource request.
 *
 * @author Pierre Jeanmenne
 * @version 05-11-08
 */
public interface ContextHandler {
	
	/**
	 * Gets an array with the base names of the resource bundle(s) where the Resource Manager<br/>
	 * must search a local-specific resource.
	 * @return an array with the requested bundles names
	 */
	String[] getBundlesForContext();
}
