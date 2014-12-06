package be.smd.i18n.swing;

/**
 * Expected behavior for any localized component
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 09-03-2009
 */
public interface I18nComponent {
	
	/** The key used in the Swing action to set a disabled icon on the action receiver */
	public static final String SMALL_ICON_DISABLED = "SMALL_ICON_DISABLED"; 
	
	/**
     * <p>Sets the key that should be used to find the text to display on the component in a resource bundle.</p>
     * @param textBundleKey - the bundle key to set
     */
	void setTextBundleKey(String textBundleKey);
	
	/**
     * Gets the key used by the resource manager to find the text to display on the component in a resource bundle
     * @return - the requested bundle key
     */
	String getTextBundleKey();
	
	/**
     * <p>Sets the key that should be used to find the tooltip text to display on the component in a resource bundle.</p>
     * @param tooltipTextBundleKey - the bundle key to set
     */
	void setTooltipTextBundleKey(String tooltipTextBundleKey);
	
	/**
     * Gets the key used by the resource manager to find the tooltip text to display on the component in a resource bundle
     * @return - the requested bundle key
     */
	String getTooltipTextBundleKey();
	
	/**
	 * <p>Sets the substitution values for the localized text placeholders.</p>
	 * <p>This method should be used only if the text bundle key points to a message with placeholders</p>
	 * @param textParameters -  an array with substitution values for the text placeholders
	 */
	void setTextParameters(Object... textParameters);
	
	/**
	 * <p>Sets the substitution values for the localized tooltip text placeholders.</p>
	 * <p>This method should be used only if the tooltip text key points to a message with placeholders</p>
	 * @param tooltipTextParameters -  an array with substitution values for the tooltip text placeholders
	 */
	void setTooltipTextParameters(Object... tooltipTextParameters);
}
