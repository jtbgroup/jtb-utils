package be.smd.i18n.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JDialog;
import javax.swing.JFrame;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.swing.util.I18nUIProperties;

/**
 * A localized JDialog
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 25-03-2009
 */
public class I18nDialog extends JDialog{

	/** The Serial Version UID for I18nDialog */
	private static final long serialVersionUID = 1183419405805978617L;

	/** The key used by the resource manager to find the text to display as title */
	protected String titleTextBundleKey;	
	
	/**
	 * An array with substitution values for the text to display as title,
	 * that can be used if text bundle key points to a message with place holders 
	 */
	protected Object[] titleTextParameters;
	
	/** Indicates if the internal state of the component has changed */
	protected boolean stateChanged = true;
	
	/**
	 * Creates an I18nDialog with a parent container,
	 * a key used to find the title in a resource bundle, and a modal state
	 * 
	 * @param owner 			 - the parent frame for the dialog
	 * @param titleTextBundleKey - the key used by the resource manager to find
	 * 							   the title of the dialog in a resource bundle
	 * @param modal <code>true</code> if the dialog must be modal, <code>false</code> otherwise.
	 */
	public I18nDialog(JFrame owner, String titleTextBundleKey, boolean modal){
		super(owner, modal);
		this.titleTextBundleKey = titleTextBundleKey;
	}
	
	/**
     * Gets the key used by the resource manager to find
     * the title of this dialog in a resource bundle
     * @return - the requested bundle key
     */
	public String getTitleTextBundleKey() {
		return titleTextBundleKey;
	}

	/**
     * <p>Sets the key that should be used to find the title
     * fir this dialog in a resource bundle.</p>
     * @param titleTextBundleKey - the bundle key to set
     */
	public void setTitleTextBundleKey(String titleTextBundleKey) {
		this.titleTextBundleKey = titleTextBundleKey;
		stateChanged = true;
	}

	/**
	 * <p>Sets the substitution values for the localized text placeholders.</p>
	 * <p>This method should be used only if the title bundle key points to a message with placeholders</p>
	 * @param textParameters -  an array with substitution values for the title placeholders
	 */
	public void setTitleTextParameters(Object... titleTextParameters) {
		this.titleTextParameters = titleTextParameters;
		stateChanged = true;
	}
	
	@Override
	public void paint(Graphics g) {
    
		Graphics2D g2d = (Graphics2D)g;
		if(I18nUIProperties.isTextAntiAliasingEnabled())
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		if(titleTextBundleKey != null 
				&& (stateChanged || I18nResourceManager.sharedInstance().getDefaultLocale() != getLocale())){
			setLocalizedTitleText();
    		setLocale(I18nResourceManager.sharedInstance().getDefaultLocale());
		}
		stateChanged = false;
    	super.paint(g2d);
	}
	
	protected void setLocalizedTitleText(){
		setTitle(titleTextParameters == null	
				? I18nResourceManager.sharedInstance().getString(titleTextBundleKey)
				: I18nResourceManager.sharedInstance().getMessage(titleTextBundleKey, titleTextParameters));
	}
}
