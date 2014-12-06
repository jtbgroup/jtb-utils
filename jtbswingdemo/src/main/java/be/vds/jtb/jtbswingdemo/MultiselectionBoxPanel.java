package be.vds.jtb.jtbswingdemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import be.vds.jtb.swing.component.MultiSelectionComboBox;
import be.vds.jtb.swing.component.SearchBox;
import be.vds.jtb.swing.layout.GridBagLayoutManager;

public class MultiselectionBoxPanel extends JPanel {

	public MultiselectionBoxPanel() {
		init();
	}

	private void init() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 0);
		GridBagLayoutManager.addComponent(this, createLine1(), c, 0, 0, 1, 1,
				1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		GridBagLayoutManager.addComponent(this, Box.createVerticalGlue(), c, 0,
				1, 1, 1, 1, 1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER);

	}

	private Component createLine1() {
		List<Object> data = new ArrayList<Object>();
		data.add("One");
		data.add("Two");
		data.add("Three");
		
		MultiSelectionComboBox box = new MultiSelectionComboBox();
		box.setData(data);
		box.setBorder(new LineBorder(Color.RED));
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JLabel("A Box Stretchable"), BorderLayout.WEST);
		p.add(box, BorderLayout.CENTER);
		return p;
	}

}
