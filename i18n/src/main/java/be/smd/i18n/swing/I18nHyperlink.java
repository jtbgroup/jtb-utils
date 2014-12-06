package be.smd.i18n.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JLabel;

import be.smd.i18n.I18nResourceManager;

/**
 * A localizable {@link JLabel} which behavior and presentation are similar to an HTML hyperlink
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 10-03-2009
 */
public class I18nHyperlink extends I18nButton implements MouseListener{

	/** The Serial Version UID for I18nHyperlink */
	private static final long serialVersionUID = 8688568500679258105L;

	/** The default color used to render the link text when the mouse cursor crosses the text area */
	public static final Color DEFAULT_HOVER_COLOR = Color.blue;
	
	/** The default color used to render the link text */
	public static final Color DEFAULT_TEXT_COLOR = Color.black;
	
	/** The default color used to render the disabled link text */
	private static final Color DEFAULT_DIASBLED_COLOR = new Color(150,150,150);
	
	/** The default color for visited link */
	public static final Color DEFAULT_VISITED_COLOR = new Color(85, 26, 169);
	
	/** The default color for active link */
	private static final Color DEFAULT_ACTVE_COLOR = Color.blue;
	
	/** The default cursor displayed when the mouse pointer crosses the link area */
	public static final Cursor DEFAULT_HOVER_CURSOR = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	
	/**
	 * The user defined color to render the link text when the mouse cursor crosses the text area
	 * If no custom color is provided, the color will be {@link I18nHyperlink#DEFAULT_HOVER_COLOR}
	 */
	private Color hoverColor = DEFAULT_HOVER_COLOR;
	
	/**
	 * The user defined color used to render the link text
	 * If no custom color is provided, {@link I18nHyperlink#DEFAULT_TEXT_COLOR} will be used
	 */
	private Color textColor = DEFAULT_TEXT_COLOR;
	
	/**
	 * The user defined color used to render the disabled link text
	 * If no custom color is provided, {@link I18nHyperlink#DEFAULT_DIASBLED_COLOR} will be used
	 */
	private Color disabledTextColor = DEFAULT_DIASBLED_COLOR;
	
	/**
	 * The user defined color used to render the visited link text
	 * If no custom color is provided, {@link I18nHyperlink#DEFAULT_VISITED_COLOR} will be used
	 */
	private Color visitedColor = DEFAULT_VISITED_COLOR;
	
	/**
	 * The user defined color used to render an active link
	 * If no custom color is provided, {@link I18nHyperlink#DEFAULT_ACTVE_COLOR} will be used
	 */
	private Color activeColor = DEFAULT_ACTVE_COLOR;
	
	/**
	 * The user defined cursor displayed when the mouse pointer crosses the link area.
	 * If no custom cursor is provided, {@link I18nHyperlink#DEFAULT_HOVER_CURSOR} will be used
	 */
	private Cursor hoverCursor = DEFAULT_HOVER_CURSOR;
	
	/** Indicates if the link has been visited */
	private boolean isVisited;
	
	/** Indicates if the link text should always be underlined */
	private boolean isAlwaysUnderlined = true;
	
	/** The text to display on the component */
	private String text;
	
	/** Indicates if the mouse pointer is crossing the link area */
	private boolean mouseOver;
	
	/** Indicates if the link is active or not */
	private boolean active;
	
	/**
	 * Creates an I18nHyperlink with localized text.
	 * @param textBundleKey - the key used by the resource manager to find the text
	 * 						  to display on the component in a resource bundle.
	 */
	public I18nHyperlink(String textBundleKey) {
		this(textBundleKey, null, JLabel.LEADING);
	}

	/**
	 * an I18nHyperlink with localized text and a specific alignment for the text.
	 * 
	 * @param textBundleKey 	  - the key used by the resource manager to find the text
	 * 						  		to display on the component in a resource bundle.
	 * @param horizontalAlignment - the horizontal alignment for the text
	 */
	public I18nHyperlink(String textBundleKey, int horizontalAlignment) {
		this(textBundleKey, null, horizontalAlignment);
	}
	
	/**
	 * an I18nHyperlink with an icon.
	 * @param icon - the icon to display on the component
	 */
	public I18nHyperlink(Icon icon) {
		this(null, icon, CENTER);
	}
	
	/**
	 * Creates an I18nHyperlink with an icon and a specific horizontal alignment for the icon.
	 * @param icon - the icon to display on the component
	 * @param horizontalAlignment - the horizontal alignment for the icon
	 */
	public I18nHyperlink(Icon icon, int horizontalAlignment){
		this(null, icon, horizontalAlignment);
	}

	/**
	 * an I18nHyperlink with localized text, an icon, and a specific alignment for the text.
	 * 
	 * @param textBundleKey 	  - the key used by the resource manager to find the text
	 * 						  		to display on the component in a resource bundle.
	 * @param icon 				  - the icon to display on the component
	 * @param horizontalAlignment - the horizontal alignment for the text
	 */
	public I18nHyperlink(String textBundleKey, Icon icon, int horizontalAlignment) {
		super(textBundleKey, icon);
		setHorizontalAlignment(horizontalAlignment);
		initLinkProperties();
	}
	
	/**
	 * an I18nHyperlink where properties are taken from the Action supplied.
	 * @param action - the action for the link
	 */
	public I18nHyperlink(Action action){
		super(action);
		initLinkProperties();
	}
	
	/**
	 * Configures the rendering of the link
	 * and adds the mouse listener to allow the normal behavior of any hyperlink
	 */
	private void initLinkProperties() {
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setMargin(new Insets(0,0,0,0));
		setIconTextGap(5);
		addMouseListener(this);
	}
    
    /**
	 * Changes the cursor and the foreground of the link when the mouse enters in the text area
	 */
	public void mouseEntered(MouseEvent e) {
		if(isEnabled()){
			mouseOver = true;
			setCursor(hoverCursor);
			setForeground(isVisited ? visitedColor : hoverColor);
			setText("<html><u>" + text);
			repaint();
		}
	}

	/**
	 * Changes the cursor and the foreground of the link when the mouse exits in the text area
	 */
	public void mouseExited(MouseEvent e) {
		if(isEnabled()){
			mouseOver = false;
			setCursor(Cursor.getDefaultCursor());
			if(active)
				setForeground(activeColor);
			else
				setForeground(isVisited ? visitedColor : textColor);
			setText(isAlwaysUnderlined ? "<html><u>" + text : text);
			repaint();
		}
	}
	
	public void mouseClicked(MouseEvent e) 	{ }
	public void mousePressed(MouseEvent e) 	{ }
	public void mouseReleased(MouseEvent e) { }
	
	@Override
	protected void setLocalizedText() {
		text = textParameters == null 	? I18nResourceManager.sharedInstance().getString(textBundleKey)
										: I18nResourceManager.sharedInstance().getMessage(textBundleKey, textParameters);
		setText(isAlwaysUnderlined ? "<html><u>" + text : text);
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
    	if(text != null){
    		return isAlwaysUnderlined || mouseOver ? "<html><u>" + text : super.getText();
    	}
    	return null;
    }
   
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		setForeground(!enabled ? disabledTextColor : textColor);
	}

	/**
	 * Indicates if the text of the link is always underlined
	 * @return <code>true</code> if the text is always underlined, <code>false</code> otherwise
	 */
	public boolean isAlwaysUnderlined() {
		return isAlwaysUnderlined;
	}

	/**
	 * Defines if the text of the link should always be underlined
	 * @param underlined <code>true</code> if the text must always be underlined,
	 * 					 <code>false</code> otherwise
	 */
	public void setAlwaysUnderlined(boolean alwaysUnderlined) {
		this.isAlwaysUnderlined = alwaysUnderlined;
	}

	/**
	 * Gets the color used to paint the localized text when the mouse pointer crosses the link.
	 * @return - the color used to paint the text when the mouse pointer crosses the link.
	 */
	public Color getHoverColor() {
		return hoverColor;
	}

	/**
	 * Sets the color to use to paint the text when the mouse pointer crosses the link.
	 * @param hoverColor - the color to set
	 */
	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}

	/**
	 * Gets the color used to paint the localized text
	 * @return - the color used to paint the localized text
	 */
	public Color getTextColor() {
		return textColor;
	}
	
	/**
	 * Gets the color used to paint the localized text when the link is disabled
	 * @return - the color used to paint the localized text when the link is disabled
	 */
	public Color getDisabledTextColor() {
		return disabledTextColor;
	}

	/**
	 * Sets the color to use to paint the localized text of the link
	 * @param textColor - the color to set
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	
	/**
	 * Sets the color to use to paint the localized text of the link if it is disabled
	 * @param disabledTextColor - the color to set
	 */
	public void setDisabledTextColor(Color disabledTextColor) {
		this.disabledTextColor = disabledTextColor;
	}

	/**
	 * Indicates if the link has been visited
	 * @return <code>true</code> if the link has been visited, <code>false</code> otherwise
	 */
	public boolean isVisited() {
		return isVisited;
	}

	/**
	 * Indicates if the link should be displayed as visited.
	 * @param isVisited - <code>true</code> if the the link must be presented as visited
	 * 					  and its text be painted with the <code>"visited color"</code>,
	 * 					  <code>false</code> otherwise
	 */
	public void setVisited(boolean isVisited) {
		if(isEnabled()){
			this.isVisited = isVisited;
			setForeground(isVisited ? visitedColor : textColor);
		}
	}

	/**
	 * Gets the color used to paint the text of the visited link.
	 * @return - the color used to paint the text of the visited link
	 */
	public Color getVisitedColor() {
		return visitedColor;
	}

	/**
	 * Sets the color to use to paint the text of the visited link
	 * @param visitedColor - the color to set
	 */
	public void setVisitedColor(Color visitedColor) {
		this.visitedColor = visitedColor;
	}
	
	/**
	 * Gets the cursor displayed when the mouse pointer crosses the link area.
	 * @return - the cursor displayed when the mouse pointer crosses the link area
	 */
	public Cursor getHoverCursor() {
		return hoverCursor;
	}

	/**
	 * Sets the cursor that must be displayed when the mouse pointer crosses the link area.
	 * @param hoverCursor - the cursor to set
	 */
	public void setHoverCursor(Cursor hoverCursor) {
		this.hoverCursor = hoverCursor;
	}

	/**
	 * Gets the color used to paint the text of the active link.
	 * @return - the color used to paint the text of the active link
	 */
	public Color getActiveColor() {
		return activeColor;
	}

	/**
	 * Sets the color to use to paint the text of the active link
	 * @param activeColor - the color to set
	 */
	public void setActiveColor(Color activeColor) {
		this.activeColor = activeColor;
	}

	/**
	 * Indicates if the link is active or not
	 * @return <code>true</code> if the link is active, <code>false</code> otherwise.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Indicates if the link must be marked as active
	 * @param active <code>true</code> if the link must be marked as active, <code>false</code> otherwise
	 */
	public void setActive(boolean active) {
		if(isEnabled()){
			this.active = active;
			setForeground(active ? activeColor : textColor);
		}
	}
	
	
}
