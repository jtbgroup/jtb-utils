package be.smd.i18n.demo.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public class TestAction extends AbstractAction{

	/** The Serial Version UID for TestAction */
	private static final long serialVersionUID = 5749585092749870423L;

	public TestAction(	String textBundleKey,
						String tooltipTextBundleKey,
						Icon icon){
		this(textBundleKey, tooltipTextBundleKey, null, icon);
	}
	
	public TestAction(	String textBundleKey, Icon icon){
		this(textBundleKey, null, null, icon);
	}
	
	public TestAction(	String textBundleKey,
						String tooltipTextBundleKey,
						KeyStroke keyStroke,
						Icon icon){
		super(textBundleKey, icon);
		putValue(Action.SHORT_DESCRIPTION, tooltipTextBundleKey);
		putValue(Action.ACCELERATOR_KEY, keyStroke);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("[Test action: " + 
							e.getSource().getClass().getName() +
							" - " + 
							e.getActionCommand()
							+ "]");
	}

}
