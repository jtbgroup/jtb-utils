package be.smd.i18n.demo.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.KeyStroke;

/**
 * The action to exit the application.
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 23-02-2009
 */
public class ExitAction extends AbstractAction{

    /** The Serial Version UID for {@link ExitAction} */
	private static final long serialVersionUID = -4047366740594305075L;

	/**
	 * Creates an {@link ExitAction}
	 * 
	 * @param label	{@link String}	the label to display on the component that use this action
	 * @param icon	{@link Icon}	the icon to display on the component that use this action
	*/
	public ExitAction(String label, Icon icon){
		super(label, icon);
		putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		
	}
	
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
