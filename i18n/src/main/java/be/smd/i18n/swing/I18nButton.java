package be.smd.i18n.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.swing.util.I18nUIProperties;

/**
 * A locale dependent {@link JButton}
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 05-11-08
 */
public class I18nButton extends JButton implements I18nComponent{
    
    /** The Serial Version UID for {@link I18nButton} */
	private static final long serialVersionUID = -1431091350035259118L;

	/** The key used by the resource manager to find the text to display on the button */
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
	
	/** Indicates if the internal state of the component has changed */
	protected boolean stateChanged = true;
	
	/** Creates an I18nButton */
    public I18nButton(){
    	super();
    }
    
    /**
     * Creates an I18nButton with a localized text
     * @param textBundleKey	- the key used by the resource manager to find the button text in a resource bundle
     */
    public I18nButton(String textBundleKey){
        this(textBundleKey, null);
    }
    
    /**
     * Creates an I18nButton with an icon
     * @param icon - the icon to display on the button
     */
    public I18nButton(Icon icon){
        this(null, icon);
    }
    
    /**
     * Creates an I18nButton with a localized text and an icon
     * @param textBundleKey	- the key used by the resource manager to find the button text in a resource bundle
     * @param icon 			- the icon to display on the button
     */
    public I18nButton(String textBundleKey, Icon icon){
        super(icon);
        this.textBundleKey = textBundleKey;
    }
    
    /**
     * Creates an I18nButton with an action
     * @param action - the action for the button
     */
    public I18nButton(Action action){
        super(action);
    }
    
    /**
     * <p>Configures the menu with the properties of the specified action.</p>
     * <ul>
     * 	<li>the mnemonic key</li>
     * 	<li>the text to display on the menu</li>
     * 	<li>the the tooltip text to display on the menu</li>
     * 	<li>the icon to display on the left of the menu</li>
     * </ul>
     */
    @Override
    protected void configurePropertiesFromAction(Action a) {
    
    	String[] types = { 	Action.MNEMONIC_KEY,
    						Action.NAME,
    						Action.ACCELERATOR_KEY,
    						Action.SHORT_DESCRIPTION,
    						Action.SMALL_ICON,
    						SMALL_ICON_DISABLED,
    						Action.ACTION_COMMAND_KEY,
    						"enabled" };
        
        for (String type : types) {
        	
        	if (type == null) continue;

            if (type.equals(Action.MNEMONIC_KEY)) {
                Integer n = (a==null) ? null : (Integer)a.getValue(type);
                setMnemonic(n==null ? '\0' : n.intValue());
            } 
            else if (type.equals(Action.NAME)) {
            	Boolean hide = (Boolean)getClientProperty("hideActionText");
            	String value = (String)a.getValue(Action.NAME);
            	if(value != null)
            		setTextBundleKey(a != null && hide != Boolean.TRUE ? value : null);
            } 
            else if (type.equals(Action.SHORT_DESCRIPTION)) {
            	setTooltipTextBundleKey(a!=null ? (String)a.getValue(type) : null);
            } else if (type.equals(Action.SMALL_ICON)) {
                setIcon(a!=null ? (Icon)a.getValue(type) : null);
            } else if (type.equals(SMALL_ICON_DISABLED)) {
                setDisabledIcon(a!=null ? (Icon)a.getValue(type) : null);
            } else if (type.equals(Action.ACTION_COMMAND_KEY)) {
                setActionCommand(a!=null? (String)a.getValue(type) : null);
            } else if (type.equals("enabled")) {
                setEnabled(a!=null ? a.isEnabled() : true);
            }
		}
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
}
