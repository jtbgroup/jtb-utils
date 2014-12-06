package be.smd.i18n.demo.ui.controls;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.util.IconManager;
import be.smd.i18n.swing.I18nLabel;
import be.smd.i18n.swing.border.I18nTitledBorder;

public class LabelsControlPanel extends JPanel{

	/** The Serial Version UID for ButtonsControlPanel */
	private static final long serialVersionUID = 9187582526628308591L;
	
	private JPanel labelsPanel;
	
	public LabelsControlPanel(){
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		initComponents();
		layoutComponents();
	}

	private void initComponents() {
		createDemoLabels();
	}

	private void layoutComponents(){
		setLayout(new BorderLayout(10,10));
		add(labelsPanel, BorderLayout.CENTER);
	}

	private void createDemoLabels() {
		
		JLabel l1 = new I18nLabel(IconManager.FAVORITES);
		formatLabel(l1);
		
		JLabel l2 = new I18nLabel("component.simple.label");
		formatLabel(l2);
		
		I18nLabel l3 = new I18nLabel("component.simple.label", IconManager.FAVORITES, I18nLabel.LEFT);
		l3.setTooltipTextBundleKey("component.simple.title");
		formatLabel(l3);
		
		I18nLabel l4 = new I18nLabel("component.complex.label");
		l4.setTextParameters(I18nResourceManager.sharedInstance().getString("label.class.label"), 2);
		l4.setTooltipTextBundleKey("component.complex.title");
		l4.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("label.class.label"),2);
		formatLabel(l4);
		
		labelsPanel = new JPanel();
		labelsPanel.setOpaque(false);

		I18nTitledBorder border = new I18nTitledBorder("titleBorder.complex.label");
		border.setTitleTextParameters(I18nResourceManager.sharedInstance().getString("label.class.label"));
		labelsPanel.setBorder(border);
		
		labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
		
		labelsPanel.add(l1);
		labelsPanel.add(l2);
		labelsPanel.add(l3);
		labelsPanel.add(l4);
	}
	
	private void formatLabel(JLabel labelToFormat){
		
		labelToFormat.setBackground(Color.white);
		labelToFormat.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		labelToFormat.setOpaque(true);
	}
}
