package be.smd.i18n.demo.ui.controls;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.actions.TestAction;
import be.smd.i18n.demo.ui.util.IconManager;
import be.smd.i18n.swing.I18nCheckBox;
import be.smd.i18n.swing.border.I18nTitledBorder;

public class CheckBoxesControlPanel extends JPanel{

	/** The Serial Version UID for CheckBoxesControlPanel */
	private static final long serialVersionUID = 9187582526628308591L;
	
	private JPanel boxesPanel;
	
	public CheckBoxesControlPanel(){
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		initComponents();
		layoutComponents();
	}

	private void initComponents() {
		createDemoBoxes();
	}

	private void layoutComponents(){
		setLayout(new BorderLayout(10,10));
		add(boxesPanel, BorderLayout.CENTER);
	}

	private void createDemoBoxes() {
		
		JCheckBox c1 = new I18nCheckBox(IconManager.FAVORITES_DISABLED);
		c1.setSelectedIcon(IconManager.FAVORITES);
		formatBox(c1);
		
		JCheckBox c2 = new I18nCheckBox("component.simple.label");
		formatBox(c2);
		
		I18nCheckBox c3 = new I18nCheckBox("component.simple.label", IconManager.FAVORITES_DISABLED);
		c3.setSelectedIcon(IconManager.FAVORITES);
		c3.setTooltipTextBundleKey("component.simple.title");
		formatBox(c3);
		
		I18nCheckBox c4 = new I18nCheckBox("component.complex.label");
		c4.setTextParameters(I18nResourceManager.sharedInstance().getString("checkBox.class.label"), 2);
		c4.setTooltipTextBundleKey("component.complex.title");
		c4.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("checkBox.class.label"),2);
		formatBox(c4);
		
		Action a = new TestAction("component.complex.label", "component.complex.title", null);
		I18nCheckBox c5 = new I18nCheckBox(a);
		c5.setTextParameters(I18nResourceManager.sharedInstance().getString("checkBox.class.label"), 2);
		c5.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("checkBox.class.label"),2);
		formatBox(c5);
		
		boxesPanel = new JPanel();
		boxesPanel.setOpaque(false);

		I18nTitledBorder border = new I18nTitledBorder("titleBorder.complex.label");
		border.setTitleTextParameters(I18nResourceManager.sharedInstance().getString("checkBox.class.label"));
		boxesPanel.setBorder(border);
		
		boxesPanel.setLayout(new BoxLayout(boxesPanel, BoxLayout.Y_AXIS));
		
		boxesPanel.add(c1);
		boxesPanel.add(c2);
		boxesPanel.add(c3);
		boxesPanel.add(c4);
		boxesPanel.add(c5);
	}
	
	private void formatBox(JCheckBox boxToFormat){
		
		boxToFormat.setBackground(Color.white);
		boxToFormat.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		boxToFormat.setOpaque(true);
	}
}
