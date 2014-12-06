package be.smd.i18n.swing.border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.swing.util.I18nUIProperties;

/**
 * A {@link TitledBorder} with localizable text
 * 
 * @author Pierre Jeanmenne
 * @version 12-03-2009
 */
public class I18nTitledBorder extends TitledBorder{

	/** The Serial Version UID for I18nTitleBorder */
	private static final long serialVersionUID = 8103214717848199967L;
	
	/** The key used by the resource manager to find the text to display on the border */
	protected String titleTextBundleKey;
	
	/**
	 * An array with substitution values for the text to display on the component,
	 * that can be used if text bundle key points to a message with place holders 
	 */
	protected Object[] titleTextParameters;
	
	/** Indicates if the internal state of the component has changed */
	protected boolean stateChanged;
	
	/**
	 * Creates an I18nTitledBorder with the specified border and an empty title.
	 *  
	 * @param border - the border
	 */
	public I18nTitledBorder(Border border){
		super(border);
	}
	
	/**
	 * Creates an I18nTitledBorder with a localized title.
	 * 
	 * @param titleTextBundleKey - the key used by the resource manager to find
	 * 							   the title the border should display
	 */
	public I18nTitledBorder(String titleTextBundleKey){
		super((String)null);
		this.titleTextBundleKey = titleTextBundleKey;
	}
	
	/**
	 * Creates an I18nTitledBorder with the specified border, and a localized title.
	 * 
	 * @param border - the border
	 * @param titleTextBundleKey - the key used by the resource manager to find
	 * 							   the title the border should display
	 */
	public I18nTitledBorder(Border border, String titleTextBundleKey){
		super(border);
		this.titleTextBundleKey = titleTextBundleKey;
	}

	/**
	 * Creates an I18nTitledBorder with the specified border,
	 * a localized title, a title-justification, and a title-position.
	 * 
	 * @param border - the border
	 * @param titleTextBundleKey - the key used by the resource manager to find
	 * 							   the title the border should display
	 * @param titleJustification - the justification for the title
	 * @param titlePosition 	 - the position for the title
	 */
	public I18nTitledBorder(Border border,
							String titleTextBundleKey,
							int titleJustification,
							int titlePosition){
		super(border, null, titleJustification, titlePosition);
		this.titleTextBundleKey = titleTextBundleKey;
	}
	
	/**
	 * Creates an I18nTitledBorder with the specified border, a localized title,
	 * a title-justification, a title-position, and a title font.
	 * 
	 * @param border - the border
	 * @param titleTextBundleKey - the key used by the resource manager to find
	 * 							   the title the border should display
	 * @param titleJustification - the justification for the title
	 * @param titlePosition 	 - the position for the title
	 * @param titleFont			 - the font for the title text
	 */
	public I18nTitledBorder(Border border,
							String titleTextBundleKey,
							int titleJustification,
							int titlePosition,
							Font titleFont){
		super(border, null, titleJustification, titlePosition, titleFont);
		this.titleTextBundleKey = titleTextBundleKey;
	}
	
	public I18nTitledBorder(Border border,
							String titleTextBundleKey,
							int titleJustification,
							int titlePosition,
							Font titleFont,
							Color titleColor){
		super(border, null, titleJustification, titlePosition, titleFont, titleColor);
		this.titleTextBundleKey = titleTextBundleKey;
	}
	
	/**
     * <p>Gets the text displayed on the component.<p/>
     * 
     * Since the component is localized, and to increase the performances, the text to return <br/>
     * will be searched in the resource bundle only when the internal state of the component has changed.<br/>
     * It means when the text bundle key has changed, or when the optional parameters for the text has changed.
     */
    @Override
    public String getTitle() {
    	if(stateChanged)
    		return titleTextParameters == null	
					? I18nResourceManager.sharedInstance().getString(titleTextBundleKey)
					: I18nResourceManager.sharedInstance().getMessage(titleTextBundleKey, titleTextParameters);
    	return super.getTitle();	
    }
    
    /**
     * <p>Sets the key that should be used to find the text to display on the component in a resource bundle.</p>
     * @param titleTextBundleKey - the bundle key to set
     */
	public void setTitleTextBundleKey(String titleTextBundleKey) {
		this.titleTextBundleKey = titleTextBundleKey;
		stateChanged = true;
	}

	/**
	 * <p>Sets the substitution values for the localized text placeholders.</p>
	 * <p>This method should be used only if the text bundle key points to a message with placeholders</p>
	 * @param titleTextParameters -  an array with substitution values for the text placeholders
	 */
	public void setTitleTextParameters(Object... titleTextParameters) {
		this.titleTextParameters = titleTextParameters;
		stateChanged = true;
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		
		Graphics2D g2d = (Graphics2D)g;
		if(I18nUIProperties.isTextAntiAliasingEnabled())
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		super.paintBorder(c, g2d, x, y, width, height);
	}
}
