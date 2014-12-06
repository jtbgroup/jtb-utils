package be.smd.i18n.demo.ui.util;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Class which holds all the application's images and icons
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 23-02-2009
 */
public final class IconManager {
	
	/** The base directory for images */
	private static final String RESOURCES_BASE = "be/smd/i18n/demo/images/";
	
	/** The unique instance of IconManager */
	private static IconManager sharedInstance;

	/** Creates an IconManager */
	private IconManager() {}

	public static final Icon EXIT = loadIcon("exit16.png");
	public static final Icon FAVORITES = loadIcon("favorites16.png");
	public static final Icon FAVORITES_DISABLED = loadIcon("favorites_disabled16.png");
	public static final Icon ENGLISH_FLAG = loadIcon("english16.gif");
	public static final Icon FRENCH_FLAG = loadIcon("french16.gif");
	public static final Icon DUTCH_FLAG = loadIcon("dutch16.gif");
	public static final Icon CONTROL = loadIcon("bullet_go.png");
	
		
	/**
	 * Loads the icon for the given path
	 * @param iconPath {@link String} the path of the icon to load, relative to the
	 *            		icons base directory {@link IconManager#RESOURCES_BASE}
	 * @return {@link Icon} the requested icon
	 */
	private static Icon loadIcon(String iconPath) {
		return new ImageIcon(IconManager.class.getClassLoader().getResource(RESOURCES_BASE + iconPath));
	}

//	/**
//	 * Loads the image for the given path
//	 * @param imagePath {@link String} the path of the image to load, relative to the
//	 *            		icons base directory {@link IconManager#RESOURCES_BASE}
//	 * @return {@link Image} the requested image
//	 */
//	private static Image loadImage(String imagePath) {
//		return ((ImageIcon)loadIcon(imagePath)).getImage();
//	}

	/**
	 * Returns the unique instance of IconManager and creates it as needed
	 * 
	 * @return {@link IconManager} the unique instance of IconManager
	 */
	public static synchronized IconManager sharedInstance() {
		return sharedInstance == null ? sharedInstance = new IconManager()
				: sharedInstance;
	}
}
