package be.vds.jtb.swing.component;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.vds.jtb.swing.layout.GridBagLayoutManager;

/**
 * 
 * @author Gautier Vanderslyen
 */
public abstract class SearchBox extends JPanel {

	private static final long serialVersionUID = 3213341868520274101L;
	private Object selectedObject;
	private JTextField searchTextField;
	private JButton searchButton;

	public SearchBox() {
		this(null);
	}

	public SearchBox(Icon searchIcon) {
		init(searchIcon);
	}

	private void init(Icon searchIcon) {
		this.setOpaque(true);

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 3);
		GridBagLayoutManager.addComponent(this, createTextField(), c, 0, 0, 1,
				1, 1, 0, GridBagConstraints.HORIZONTAL,
				GridBagConstraints.CENTER);
		c.insets = new Insets(0, 0, 0, 0);
		GridBagLayoutManager.addComponent(this, createSearchButton(searchIcon),
				c, 1, 0, 1, 1, 0, 0, GridBagConstraints.NONE,
				GridBagConstraints.CENTER);
	}

	private Component createTextField() {
		searchTextField = new JTextField();
		searchTextField.setOpaque(false);

		searchTextField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					lookup();
				}
			}
		});

		return searchTextField;
	}

	private Component createSearchButton(Icon searchIcon) {
		searchButton = new JButton(new AbstractAction() {

			private static final long serialVersionUID = 8984853176707556631L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				lookup();
			}
		});

		searchButton.setBorderPainted(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setFocusPainted(false);

		if (searchIcon == null) {
			searchButton.setText("...");
		} else {
			searchButton.setIcon(searchIcon);
		}

		return searchButton;
	}

	public String getSearchText() {
		return searchTextField.getText();
	}

	public void setButtonIcon(Icon icon) {
		searchButton.setIcon(icon);
	}

	public void setButtonText(String text) {
		searchButton.setText(text);
	}

	public Object getSelectedObject() {
		return selectedObject;
	}

	protected abstract String formatSelectedObject(Object object);

	protected abstract void lookup();

	public void setColumn(int i) {
		searchTextField.setColumns(i);
	}
}
