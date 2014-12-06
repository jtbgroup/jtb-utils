package be.smd.i18n.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JLabel;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.swing.util.I18nUIProperties;

/**
 * A locale dependent {@link JLabel}
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 04-11-2008
 */
public class I18nLabel extends JLabel implements I18nComponent{

	/** The Serial Version UID for I18nLabel */
	private static final long serialVersionUID = 16759193077513179L;
	
	/** The key used by the resource manager to find the text to display on the component */
	protected String textBundleKey;
	
	/** The key used by the resource manager to find the tooltip text to display on the button */
	protected String tooltipTextBundleKey;
	
	/**
	 * An array with substitution values for the text to display on the component,
	 * that can be used if text bundle key points to a message with place holders 
	 */
	protected Object[] textParameters;
	
	/**
	 * An array with substitution values for the tooltip text to display on the component,
	 * that can be used if tooltip text bundle key points to a message with place holders 
	 */
	protected Object[] tooltipTextParameters;
	
	/**Indicates if the internal state of the component has changed */
	protected boolean stateChanged;
	
	/** Creates an I18nLabel. */
	public I18nLabel(){
		super();
	}
	
	/**
	 * Creates an I18nLabel with an icon.
	 * @param icon - the icon to display on the label
	 */
	public I18nLabel(Icon icon){
		super(icon);
	}
	
	/**
	 * Creates an I18nLabel with an icon and a specific horizontal alignment for the icon.
	 * @param icon - the icon to display on the label
	 * @param horizontalAlignment - the horizontal alignment for the icon
	 */
	public I18nLabel(Icon icon, int horizontalAlignment){
		super(icon, horizontalAlignment);
	}
	
	/**
	 * Creates an I18nLabel with a localized text. 
	 * @param textBundleKey - the key used by the resource manager to find the text
	 * 						  to display on the component in a resource bundle
	 */
	public I18nLabel(String textBundleKey){
		this(textBundleKey, null, LEADING);
	}
	
	/**
	 * Creates an I18nLabel with a localized text and a specific alignment for the text
	 * @param textBundleKey 	  - the key used by the resource manager to find the text.
	 * 					  		 	to display on the component in a resource bundle
	 * @param horizontalAlignment - the horizontal alignment for the component text
	 */
	public I18nLabel(String textBundleKey, int horizontalAlignment){
		this(textBundleKey, null, horizontalAlignment);
	}
	
	/**
	 * Creates an I18nLabel with a localized text, an icon, and a specific alignment for the text.
	 * @param textBundleKey 	  - the key used by the resource manager to find the text.
	 * 					  		 	to display on the component in a resource bundle
	 * @param icon 				  - the icon to display on the label
	 * @param horizontalAlignment - the horizontal alignment for the component text
	 */
	public I18nLabel(String textBundleKey, Icon icon, int horizontalAlignment){
		this(icon, horizontalAlignment);
		this.textBundleKey = textBundleKey;
	}
	
	/**
	 * Handles the user interface locale changes by updating the text to display
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		if(I18nUIProperties.isTextAntiAliasingEnabled())
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		if(textBundleKey != null 
				&& (stateChanged || I18nResourceManager.sharedInstance().getDefaultLocale() != getLocale())){
			setLocalizedText();
    		setLocale(I18nResourceManager.sharedInstance().getDefaultLocale());
		}
    	if(tooltipTextBundleKey != null)
    			setToolTipText(tooltipTextParameters == null 
    					? I18nResourceManager.sharedInstance().getString(tooltipTextBundleKey)
    					: I18nResourceManager.sharedInstance().getMessage(tooltipTextBundleKey, tooltipTextParameters));
    	
    	stateChanged = false;
    	super.paintComponent(g2d);
	}
	
	protected void setLocalizedText(){
		setText(textParameters == null	
				? I18nResourceManager.sharedInstance().getString(textBundleKey)
				: I18nResourceManager.sharedInstance().getMessage(textBundleKey, textParameters));
	}
	
	/**
	 * <p>Sets the substitution values for the localized text placeholders.</p>
	 * <p>This method should be used only if the text bundle key points to a message with placeholders</p>
	 * @param textParameters -  an array with substitution values for the text placeholders
	 */
	public void setTextParameters(Object... textParameters) {
		this.textParameters = textParameters;
		stateChanged = true;
	}
	
	/**
	 * <p>Sets the substitution values for the localized tooltip text placeholders.</p>
	 * <p>This method should be used only if the tooltip text key points to a message with placeholders</p>
	 * @param tooltipTextParameters -  an array with substitution values for the tooltip text placeholders
	 */
	public void setTooltipTextParameters(Object... tooltipTextParameters) {
		this.tooltipTextParameters = tooltipTextParameters;
		stateChanged = true;
	}

	/**
	 * <p>Gets the substitution values of the localized text.</p>
	 * @return - an array with the localized text substitution values
	 * 			 or <code>null</code> if no parameters have been set
	 */
	public Object[] getTextParameters() {
		return textParameters;
	}
	
	/**
	 * <p>Gets the substitution values of the localized tooltip text.</p>
	 * @return - an array with the localized text substitution values
	 * 			 or <code>null</code> if no parameters have been set
	 */
	public Object[] getTooltipTextParameters() {
		return tooltipTextParameters;
	}

	/**
     * Gets the key used by the resource manager to find the text to display on the component in a resource bundle
     * @return - the requested bundle key
     */
	public String getTextBundleKey() {
		return textBundleKey;
	}

	/**
     * Gets the key used by the resource manager to find the tooltip text to display on the component in a resource bundle
     * @return - the requested bundle key
     */
	public String getTooltipTextBundleKey() {
		return tooltipTextBundleKey;
	}

	/**
     * <p>Sets the key that should be used to find the text to display on the component in a resource bundle.</p>
     * @param textBundleKey - the bundle key to set
     */
	public void setTextBundleKey(String textBundleKey) {
		this.textBundleKey = textBundleKey;
		stateChanged = true;
	}

	/**
     * <p>Sets the key that should be used to find the tooltip text to display on the component in a resource bundle.</p>
     * @param tooltipTextBundleKey - the bundle key to set
     */
	public void setTooltipTextBundleKey(String tooltipTextBundleKey) {
		this.tooltipTextBundleKey = tooltipTextBundleKey;
		stateChanged = true;
	}
	
	/**
     * <p>Gets the text displayed on the component.<p/>
     * 
     * Since the component is localized, and to increase the performances, the text to return <br/>
     * will be searched in the resource bundle only when the internal state of the component has changed.<br/>
     * It means when the text bundle key has changed, or when the optional parameters for the text has changed.
     */
    @Override
    public String getText() {
    	if(textBundleKey != null)
    	return textParameters == null	
					? I18nResourceManager.sharedInstance().getString(textBundleKey)
					: I18nResourceManager.sharedInstance().getMessage(textBundleKey, textParameters);
		return super.getText();			
    		
    }
}
