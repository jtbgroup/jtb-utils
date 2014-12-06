package be.vds.jtb.jtbswingdemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import be.vds.jtb.swing.component.DurationComponent;
import be.vds.jtb.swing.component.SearchBox;
import be.vds.jtb.swing.layout.GridBagLayoutManager;

public class DurationPanel extends JPanel {

	private JLabel timeLabel;

	public DurationPanel() {
		init();
	}

	private void init() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 0);
		GridBagLayoutManager.addComponent(this, createLine1(), c, 0, 0, 1, 1,
				1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

		GridBagLayoutManager.addComponent(this, createLine2(), c, 0, 1, 1, 1,
				0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

		GridBagLayoutManager.addComponent(this, Box.createVerticalGlue(), c, 0,
				2, 1, 1, 1, 1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER);

	}

	private Component createLine2() {
		timeLabel = new JLabel("time");
		timeLabel.setBorder(new LineBorder(Color.RED));
		return timeLabel;
	}

	private Component createLine1() {
		DurationComponent p = new DurationComponent();
		p.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(
						DurationComponent.TIME_PROPERTY)) {
					timeLabel.setText("Time : "
							+ String.valueOf(evt.getNewValue()));
				}
			}
		});
		return p;
	}

}
