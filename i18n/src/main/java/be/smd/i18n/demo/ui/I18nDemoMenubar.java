package be.smd.i18n.demo.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.actions.ExitAction;
import be.smd.i18n.demo.ui.actions.LanguageAction;
import be.smd.i18n.demo.ui.actions.TestAction;
import be.smd.i18n.demo.ui.util.ActionsManager;
import be.smd.i18n.demo.ui.util.IconManager;
import be.smd.i18n.swing.I18nMenu;
import be.smd.i18n.swing.I18nMenuItem;

/**
 * The menu bar for I18n framework demo
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 23-02-2009
 */
public class I18nDemoMenubar extends JMenuBar {
	
	/** The Serial Version UID for {@link I18nDemoMenubar} */
	private static final long serialVersionUID = -8052755450005147632L;

	/** The default background color for the menu bar */
	protected static final Color DEFAULT_BACKGROUND_COLOR = new Color(245,245,245);
	
	/** The menus of the I18n demo menu bar */
	protected JMenu fileMenu, languageMenu, demoMenu;
	
	/** The destination frame for the menu bar */
	protected DemoFrame frame;
	
	/**
	 * Creates an {@link I18nDemoMenubar}
	 * @param frame {@link DemoFrame} the destination frame for menu bar
	 */
	public I18nDemoMenubar(DemoFrame frame){
		this.frame = frame;
		initComponents();
	}
	
	/**
	 * Initializes the menu bar elements creation
	 */
	protected void initComponents() {
		
		setPreferredSize(new Dimension(frame.getWidth(), 30));
		createFileMenu();
		createLanguageMenu();
		createDemoMenu();
	}
	
	/** Creates <code>File</code> menu which allows general actions */
	private void createFileMenu() {
		
		fileMenu = new I18nMenu("file");
		
		ActionsManager am = ActionsManager.sharedInstance();
		
		Action exitAction;
		if((exitAction = am.getAction("exit")) == null){
			exitAction = new ExitAction("exit",IconManager.EXIT);
			exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
			am.addAction("exit", exitAction);
		}

		fileMenu.add(exitAction);
		
		add(fileMenu);
	}
	
	/** Creates the menu for language switching */
	private void createLanguageMenu() {
		
		LanguageAction languageAction = new LanguageAction(frame);
		
		languageMenu = new I18nMenu("languages");
		
		JMenuItem english = new I18nMenuItem("english");
		english.setAction(languageAction);
		english.setActionCommand("en");
		english.setIcon(IconManager.ENGLISH_FLAG);
		languageMenu.add(english);
				
		JMenuItem french = new I18nMenuItem("french");
		french.setAction(languageAction);
		french.setActionCommand("fr");
		french.setIcon(IconManager.FRENCH_FLAG);
		languageMenu.add(french);
		
		JMenuItem dutch = new I18nMenuItem("dutch");
		dutch.setAction(languageAction);
		dutch.setActionCommand("nl");
		dutch.setIcon(IconManager.DUTCH_FLAG);
		languageMenu.add(dutch);
		
		add(languageMenu);
	}
	
	private void createDemoMenu(){
		
		TestAction demoAction1 = new TestAction(
				"demo.label",
				"component.simple.title",
				KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_MASK),
				IconManager.FAVORITES);
		
		demoMenu = new I18nMenu(demoAction1);
		demoMenu.add(demoAction1);
		
		demoMenu.add(new I18nMenuItem(demoAction1));
		
		I18nMenu demo2 = new I18nMenu("component.complex.label");
		demo2.setIcon(IconManager.FAVORITES);
		demo2.setTextParameters(I18nResourceManager.sharedInstance().getString("menuItem.class.label"), 2);
		demo2.setTooltipTextBundleKey("component.complex.title");
		demo2.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("menuItem.class.label"),2);
		demo2.add(new I18nMenuItem(demoAction1));
		
		demoMenu.add(demo2);
		add(demoMenu);
	}
}
