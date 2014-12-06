package be.smd.i18n.demo.ui.actions;

import java.awt.event.ActionEvent;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.DemoFrame;

/**
 * The action used to switch user interface language at runtime
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 23-02-2009
 */
public class LanguageAction extends AbstractAction{

	/** The Serial Version UID for {@link LanguageAction} */
	private static final long serialVersionUID = -4993674793724250660L;

	/** The top level container of the External Gateway Manager application */
	private DemoFrame frame;
	
	/**
	 * Creates a {@link LanguageAction}
	 * @param frame {@link DemoFrame} top level container of the Total Health Manager application
	 */
	public LanguageAction(DemoFrame frame){
		this.frame = frame;
	}
	
	/**
	 * Checks if the request language differs from the current language of the user interface,
	 * and updates the internationalized components if needed.
	 */
	public void actionPerformed(ActionEvent ae){
		
		Locale currentLocale = I18nResourceManager.sharedInstance().getDefaultLocale();
		Locale requestedLocale = I18nResourceManager.sharedInstance().getLocale(ae.getActionCommand().toLowerCase());
		
		if(!requestedLocale.equals(currentLocale)){
			I18nResourceManager.sharedInstance().setDefaultLocale(requestedLocale);
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
}
