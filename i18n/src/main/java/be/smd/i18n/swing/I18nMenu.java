package be.smd.i18n.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.swing.util.I18nUIProperties;

/**
 * A locale dependent {@link JMenu}
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 09-03-2009
 */
public class I18nMenu extends JMenu implements I18nComponent{

	/** The Serial Version UID for I18nMenu */
	private static final long serialVersionUID = -2108755354246279545L;
	
	/** The key used by the resource manager to find the text to display on the menu */
	private String textBundleKey;
	
	/** The key used by the resource manager to find the tooltip text to display on menu */
	private String tooltipTextBundleKey;
	
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
	
	/** A flag that indicates if the internal state of the component has changed */
	protected boolean stateChanged = true;
	
	/**
     * Creates an I18nMenu with a key used by the resource manager to find the localized text to display on the menu
     * @param textBundleKey - the key in a resource bundle where to find the menu localized text.
     */
	public I18nMenu(String textBundleKey){
		setTextBundleKey(textBundleKey);
	}
	
	/**
	 * Creates an I18nMenu with an action
	 * @param action - the action to set on the menu
	 */
	public I18nMenu(Action action){
		super(action);
	}
	
	@Override
	protected JMenuItem createActionComponent(Action a) {
		
		Icon itemIcon = (Icon)a.getValue(Action.SMALL_ICON); 
		String itemBundleKey = (String)a.getValue(Action.NAME);
		String itemTooltipBundleKey = (String)a.getValue(Action.SHORT_DESCRIPTION);
		
		I18nMenuItem item = new I18nMenuItem(itemBundleKey, itemIcon){
				
			private static final long serialVersionUID = -7916978868441781887L;

			protected PropertyChangeListener createActionPropertyChangeListener(Action a) {
				PropertyChangeListener pcl = createActionChangeListener(this);
				if (pcl == null) {
					pcl = super.createActionPropertyChangeListener(a);
				}
				return pcl;
			}
        };
        item.setTooltipTextBundleKey(itemTooltipBundleKey);   
        item.setHorizontalTextPosition(JButton.TRAILING);
        item.setVerticalTextPosition(JButton.CENTER);
        item.setEnabled(a.isEnabled());
           
        return item;
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
    	
    	if(textBundleKey != null){
			if(!isDisplayable() || stateChanged)
				return textParameters == null	
						? I18nResourceManager.sharedInstance().getString(textBundleKey)
						: I18nResourceManager.sharedInstance().getMessage(textBundleKey, textParameters);
			return super.getText();
    	}
    	return null;
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
     * <p>Updates the text to display on the menu item.</p>
     * <p>The text will be updated only if at least one of the internal state property of the component has changed:</p>
     * <ul>
     * 	<li>the locale of the component</li>
     * 	<li>the text bundle key</li>
     * 	<li>the tooltip text bundle key</li>
     * </ul>
     */
    @Override
	protected void paintComponent(Graphics g) {
    	
    	Graphics2D g2d = (Graphics2D)g;
    	if(I18nUIProperties.isTextAntiAliasingEnabled())
    		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
    	if(stateChanged || I18nResourceManager.sharedInstance().getDefaultLocale() != getLocale()){
    		setLocale(I18nResourceManager.sharedInstance().getDefaultLocale());
    		if(textBundleKey != null)
    			setText( textParameters == null	
    					? I18nResourceManager.sharedInstance().getString(textBundleKey)
    					: I18nResourceManager.sharedInstance().getMessage(textBundleKey, textParameters));
    		if(tooltipTextBundleKey != null)
    			setToolTipText(tooltipTextParameters == null 
    					? I18nResourceManager.sharedInstance().getString(tooltipTextBundleKey)
    					: I18nResourceManager.sharedInstance().getMessage(tooltipTextBundleKey, tooltipTextParameters));
    		stateChanged = false;
    	}
        
        super.paintComponent(g2d);
	}
    
    /**
     * Gets the key used by the resource manager to find the text to display on the component in a resource bundle
     * @return - the requested bundle key
     */
	public String getTextBundleKey() {
		return textBundleKey;
	}

	/**
     * <p>Sets the key that should be used to find the text to display on the component in a resource bundle.</p>
     * @param textBundleKey - the bundle key to set
     */
	public void setTextBundleKey(String bundleKey) {
		this.textBundleKey = bundleKey;
		stateChanged = true;
		
	}

	/**
     * Gets the key used by the resource manager to find the tooltip text to display on the component in a resource bundle
     * @return - the requested bundle key
     */
	public String getTooltipTextBundleKey() {
		return tooltipTextBundleKey;
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
