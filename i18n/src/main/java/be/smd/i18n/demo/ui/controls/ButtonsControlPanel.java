package be.smd.i18n.demo.ui.controls;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.actions.TestAction;
import be.smd.i18n.demo.ui.util.IconManager;
import be.smd.i18n.swing.I18nButton;
import be.smd.i18n.swing.border.I18nTitledBorder;

public class ButtonsControlPanel extends JPanel{

	/** The Serial Version UID for ButtonsControlPanel */
	private static final long serialVersionUID = 9187582526628308591L;
	
	private JPanel buttonsPanel;
	
	public ButtonsControlPanel(){
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		initComponents();
		layoutComponents();
	}

	private void initComponents() {
		createDemoButtons();
	}

	private void layoutComponents(){
		setLayout(new BorderLayout(10,10));
		add(buttonsPanel, BorderLayout.CENTER);
	}

	private void createDemoButtons() {
		
		JButton b1 = new I18nButton(IconManager.FAVORITES);
		JButton b2 = new I18nButton("component.simple.label");
		JButton b3 = new I18nButton("component.simple.label", IconManager.FAVORITES);
		
		Action a = new TestAction("component.simple.label", "component.simple.title", IconManager.FAVORITES);
		JButton b4 = new I18nButton(a);
		
		I18nButton b5 = new I18nButton("component.complex.label");
		b5.setTextParameters(I18nResourceManager.sharedInstance().getString("button.class.label"), 2);
		b5.setTooltipTextBundleKey("component.complex.title");
		b5.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("button.class.label"),2);
		b5.setIcon(IconManager.FAVORITES);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		
		I18nTitledBorder border = new I18nTitledBorder("titleBorder.complex.label");
		border.setTitleTextParameters(I18nResourceManager.sharedInstance().getString("button.class.label"));
		buttonsPanel.setBorder(border);
		
		buttonsPanel.setOpaque(false);
		
		buttonsPanel.add(b1);
		buttonsPanel.add(b2);
		buttonsPanel.add(b3);
		buttonsPanel.add(b4);
		buttonsPanel.add(b5);
	}
}
