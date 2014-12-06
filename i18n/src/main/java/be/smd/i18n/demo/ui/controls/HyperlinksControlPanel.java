package be.smd.i18n.demo.ui.controls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.actions.TestAction;
import be.smd.i18n.demo.ui.util.IconManager;
import be.smd.i18n.swing.I18nHyperlink;
import be.smd.i18n.swing.border.I18nTitledBorder;

public class HyperlinksControlPanel extends JPanel{

	/** The Serial Version UID for ButtonsControlPanel */
	private static final long serialVersionUID = 9187582526628308591L;
	
	private JPanel hyperlinksPanel;
	
	public HyperlinksControlPanel(){
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		initComponents();
		layoutComponents();
	}

	private void initComponents() {
		createDemoLinks();
	}

	private void layoutComponents(){
		setLayout(new BorderLayout(10,10));
		add(hyperlinksPanel, BorderLayout.CENTER);
	}

	private void createDemoLinks() {
		
		I18nHyperlink l1 = new I18nHyperlink(IconManager.FAVORITES);
		formatLink(l1);
		
		I18nHyperlink l2 = new I18nHyperlink("component.simple.label");
		formatLink(l2);
		
		I18nHyperlink l3 = new I18nHyperlink("component.simple.label", IconManager.FAVORITES, JLabel.LEFT);
		l3.setTooltipTextBundleKey("component.simple.title");
		formatLink(l3);
		
		I18nHyperlink l4 = new I18nHyperlink("component.complex.label"){

			/** The Serial Version UID for this component */
			private static final long serialVersionUID = -9160010299565253544L;

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				setVisited(true);
			}
			
		};
		l4.setIcon(IconManager.FAVORITES);
		l4.setTextParameters(I18nResourceManager.sharedInstance().getString("link.class.label"),2);
		l4.setTooltipTextBundleKey("component.complex.title");
		l4.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("link.class.label"),2);
		l4.setAlwaysUnderlined(false);
		formatLink(l4);
		
		I18nHyperlink l5 = new I18nHyperlink("component.complex.label"){

			/** The Serial Version UID for this component */
			private static final long serialVersionUID = -9160010299565253544L;

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				setVisited(true);
			}
			
		};
		l5.setTextParameters(I18nResourceManager.sharedInstance().getString("link.class.label"),2);
		l5.setTooltipTextBundleKey("component.complex.title");
		l5.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("link.class.label"),2);
		formatLink(l5);
		l5.setEnabled(false);
		l5.setAlwaysUnderlined(false);
		
		Action a = new TestAction("component.complex.label", "component.complex.title", IconManager.FAVORITES);
		I18nHyperlink l6 = new I18nHyperlink(a);
		l6.setTextParameters(I18nResourceManager.sharedInstance().getString("link.class.label"),2);
		l6.setTooltipTextParameters(I18nResourceManager.sharedInstance().getString("link.class.label"),2);
		l6.setActionCommand("link from action");
		formatLink(l6);
		l6.setHorizontalAlignment(I18nHyperlink.LEFT);
		
		hyperlinksPanel = new JPanel();
		hyperlinksPanel.setOpaque(false);

		I18nTitledBorder border = new I18nTitledBorder("titleBorder.complex.label");
		border.setTitleTextParameters(I18nResourceManager.sharedInstance().getString("link.class.label"));
		hyperlinksPanel.setBorder(border);
		hyperlinksPanel.setLayout(new BoxLayout(hyperlinksPanel, BoxLayout.Y_AXIS));
		
		hyperlinksPanel.add(l1);
		hyperlinksPanel.add(l2);
		hyperlinksPanel.add(l3);
		hyperlinksPanel.add(l4);
		hyperlinksPanel.add(l5);
		hyperlinksPanel.add(l6);
	}
	
	private void formatLink(I18nHyperlink linkToFormat){
		
		linkToFormat.setBackground(Color.white);
		linkToFormat.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		linkToFormat.setOpaque(true);
	}
	
	
}
