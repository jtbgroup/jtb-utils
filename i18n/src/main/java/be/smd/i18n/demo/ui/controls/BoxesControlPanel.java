package be.smd.i18n.demo.ui.controls;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.actions.TestAction;
import be.smd.i18n.demo.ui.util.IconManager;
import be.smd.i18n.swing.I18nBox;
import be.smd.i18n.swing.border.I18nTitledBorder;

public class BoxesControlPanel extends JPanel{

	/** The Serial Version UID for ButtonsControlPanel */
	private static final long serialVersionUID = 9187582526628308591L;
	
	private JPanel boxesPanel;
	
	public BoxesControlPanel(){
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		initComponents();
		layoutComponents();
	}

	private void initComponents() {
		createBoxes();
	}

	private void layoutComponents(){
		setLayout(new BorderLayout(10,10));
		add(boxesPanel, BorderLayout.CENTER);
	}

	private void createBoxes() {
		
		I18nBox box_1 = new I18nBox("component.simple.label", IconManager.FAVORITES);
		
		I18nBox box_2 = new I18nBox("component.complex.label", IconManager.FAVORITES, I18nBox.RIGHT_TO_LEFT);
		box_2.setTextParameters(I18nResourceManager.sharedInstance().getString("button.class.label"), 2);
		box_2.setTooltipTextBundleKey("component.complex.title");
		box_2.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("button.class.label"),2);
		
		Action a = new TestAction("component.simple.label", "component.simple.title", IconManager.FAVORITES);
		box_2.addAction(a);
		
		boxesPanel = new JPanel();
		boxesPanel.setLayout(new BoxLayout(boxesPanel, BoxLayout.Y_AXIS));
		
		I18nTitledBorder border = new I18nTitledBorder("titleBorder.complex.label");
		border.setTitleTextParameters(I18nResourceManager.sharedInstance().getString("box.class.label"));
		boxesPanel.setBorder(border);
		
		
		
		boxesPanel.setOpaque(false);
		boxesPanel.add(box_1);
		boxesPanel.add(Box.createVerticalStrut(5));
		boxesPanel.add(box_2);
	}
}
