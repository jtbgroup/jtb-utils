package be.smd.i18n.demo.ui.controls;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.actions.TestAction;
import be.smd.i18n.demo.ui.util.IconManager;
import be.smd.i18n.swing.I18nRadioButton;
import be.smd.i18n.swing.border.I18nTitledBorder;

public class RadioButtonsControlPanel extends JPanel{

	/** The Serial Version UID for CheckBoxesControlPanel */
	private static final long serialVersionUID = 9187582526628308591L;
	
	private JPanel buttonsPanel;
	
	public RadioButtonsControlPanel(){
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
		
		JRadioButton b1 = new I18nRadioButton(IconManager.FAVORITES_DISABLED);
		b1.setSelectedIcon(IconManager.FAVORITES);
		formatButtons(b1);
		
		JRadioButton b2 = new I18nRadioButton("component.simple.label");
		formatButtons(b2);
		
		I18nRadioButton b3 = new I18nRadioButton("component.simple.label", IconManager.FAVORITES_DISABLED);
		b3.setSelectedIcon(IconManager.FAVORITES);
		b3.setTooltipTextBundleKey("component.simple.title");
		formatButtons(b3);
		
		I18nRadioButton b4 = new I18nRadioButton("component.complex.label");
		b4.setTextParameters(I18nResourceManager.sharedInstance().getString("radioButton.class.label"), 2);
		b4.setTooltipTextBundleKey("component.complex.title");
		b4.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("radioButton.class.label"),2);
		formatButtons(b4);
		
		Action a = new TestAction("component.complex.label", "component.complex.title", null);
		I18nRadioButton b5 = new I18nRadioButton(a);
		b5.setTextParameters(I18nResourceManager.sharedInstance().getString("radioButton.class.label"), 2);
		b5.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("radioButton.class.label"),2);
		formatButtons(b5);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);

		I18nTitledBorder border = new I18nTitledBorder("titleBorder.complex.label");
		border.setTitleTextParameters(I18nResourceManager.sharedInstance().getString("radioButton.class.label"));
		buttonsPanel.setBorder(border);
		
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		
		buttonsPanel.add(b1);
		buttonsPanel.add(b2);
		buttonsPanel.add(b3);
		buttonsPanel.add(b4);
		buttonsPanel.add(b5);
	}
	
	private void formatButtons(JRadioButton boxToFormat){
		
		boxToFormat.setBackground(Color.white);
		boxToFormat.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		boxToFormat.setOpaque(true);
	}
}
