package be.vds.jtb.swing.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MultiSelectionComboBox extends JPanel {

	private static final long serialVersionUID = 7631830815581070206L;
	private JScrollPane selectionScroll;
	private JButton button;
	private JTextField tf;
	private JPopupMenu menu;
	private List<Object> data;
	private JPanel list;
	private Map<JCheckBox, Object> checkBoxObjectMap = new HashMap<JCheckBox, Object>();
	private List<JCheckBox> checkBoxList = new ArrayList<JCheckBox>();
	private ActionListener selectionListener;
	public static final String SELECTION_CHANGED = "selection.changed";
	// private boolean popupShown;
	private MultiSelectionComboBoxFormatter formatter;
	private int visibleRowCount = 3;
	private boolean showPopup = true;
	private boolean isShowingPopup;
	private Color checkBgColor = Color.LIGHT_GRAY;

	public MultiSelectionComboBox() {
		init();
	}

	private void init() {
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.setLayout(new BorderLayout());
		this.add(createTextField(), BorderLayout.CENTER);
		this.add(createButton(), BorderLayout.EAST);

		createSelectionPanel();
		createCheckSelectionListener();
	}

	private void createCheckSelectionListener() {
		selectionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setCheckOpacity((JCheckBox) e.getSource());
				List<Object> sel = getSelectedItems();
				adaptTextField(sel);
				firePropertyChange(SELECTION_CHANGED, null, sel);
			}
		};
	}

	private void adaptTextField(List<Object> selection) {
		if (selection == null) {
			clearTf();
		} else {
			StringBuilder sb = new StringBuilder();
			int i = selection.size() - 1;
			for (Object object : selection) {
				sb.append(format(object));
				if (i > 0) {
					sb.append(", ");
				}
				i--;
			}
			tf.setText(sb.toString());
		}
	}

	private void clearTf() {
		tf.setText(null);
	}

	public List<Object> getSelectedItems() {
		List<Object> l = new ArrayList<Object>();
		for (JCheckBox cb : checkBoxList) {
			if (cb.isSelected()) {
				l.add(checkBoxObjectMap.get(cb));
			}
		}
		if (l.size() == 0)
			return null;
		return l;
	}

	private void createSelectionPanel() {
		list = new JPanel();
		list.setLayout(new GridBagLayout());
		list.setBackground(this.getBackground());

		selectionScroll = new JScrollPane(list);
		selectionScroll.setBorder(null);
		selectionScroll.getVerticalScrollBar().setUnitIncrement(10);

		menu = new JPopupMenu();
		menu.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				isShowingPopup = false;
			}

			@Override
			public void focusGained(FocusEvent e) {
				isShowingPopup = true;
			}
		});

		menu.add(selectionScroll);
	}

	private Component createTextField() {
		tf = new JTextField(10);
		tf.setBorder(null);
		tf.setOpaque(false);
		tf.setEditable(false);
		return tf;
	}

	private Component createButton() {
		button = new JButton(new ImageIcon(getClass().getClassLoader()
				.getResource("triangle_down_16.png")));

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (showPopup) {
					showMenu(e);
					menu.requestFocus();
				} else {
					showPopup = true;
				}

			}

			private void showMenu(ActionEvent e) {
				int size = checkBoxList.size();
				if (size > visibleRowCount) {
					size = (int) (checkBoxList.get(0).getPreferredSize()
							.getHeight() * visibleRowCount);
				} else {
					size = (int) (checkBoxList.get(0).getPreferredSize()
							.getHeight() * size);
				}

				menu.setPreferredSize(new Dimension(tf.getWidth()
						+ button.getWidth()+2, size));
				menu.show(MultiSelectionComboBox.this, 0, tf.getHeight() + 1);
			}
		});

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (isShowingPopup) {
					showPopup = false;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup = true;
			}
		});

		Dimension d = new Dimension(20, 20);
		button.setPreferredSize(d);
		return button;
	}

	public void setVisibleRowCount(int visibleRowCount) {
		this.visibleRowCount = visibleRowCount;
	}

	public void setFormatter(MultiSelectionComboBoxFormatter formatter) {
		this.formatter = formatter;
		refreshFormatting();
	}

	private void refreshFormatting() {
		for (JCheckBox cb : checkBoxList) {
			cb.setText(format(checkBoxObjectMap.get(cb)));
		}
		adaptTextField(getSelectedItems());
	}

	public void setData(List<Object> data) {
		this.data = data;
		reloadData();
	}

	private void reloadData() {
		list.removeAll();
		checkBoxObjectMap.clear();
		checkBoxList.clear();

		if (data != null) {
			GridBagConstraints gc = new GridBagConstraints();
			int y = 0;
			gc.gridx = 0;
			gc.weightx = 1;
			gc.weighty = 0;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.anchor = GridBagConstraints.WEST;

			for (Object obj : data) {
				JCheckBox cb = new JCheckBox(format(obj));
				cb.setBackground(checkBgColor);
				cb.setOpaque(false);
				cb.setFocusable(false);
				cb.addActionListener(selectionListener);
				checkBoxObjectMap.put(cb, obj);
				checkBoxList.add(cb);
				gc.gridy = y;
				list.add(cb, gc);
				y++;
			}
		}
	}

	private String format(Object obj) {
		if (formatter != null) {
			return formatter.format(obj);
		}
		return obj.toString();
	}

//	public static void main(String[] args) {
//		JFrame f = new JFrame();
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		List<Object> data = new ArrayList<Object>();
//		for (int i = 0; i < 10; i++) {
//			data.add(String.valueOf(i));
//		}
//
//		MultiSelectionComboBox multi = new MultiSelectionComboBox();
//		multi.setFormatter(new MultiSelectionComboBoxFormatter() {
//
//			@Override
//			public String format(Object object) {
//				return "*** " + object.toString();
//			}
//		});
//		multi.addPropertyChangeListener(new PropertyChangeListener() {
//
//			@Override
//			public void propertyChange(PropertyChangeEvent evt) {
//				if (evt.getPropertyName().equals(SELECTION_CHANGED)) {
//					System.out.println("������������" + evt.getNewValue());
//				}
//			}
//		});
//		multi.setData(data);
//		multi.setVisibleRowCount(5);
//
//		JComboBox combo = new JComboBox(
//				new String[] { "1", "2", "3", "4", "5" });
//
//		JPanel p = new JPanel();
//		p.add(multi, BorderLayout.NORTH);
//		p.add(combo, BorderLayout.SOUTH);
//
//		f.getContentPane().add(p);
//		f.setSize(300, 300);
//		f.setLocationRelativeTo(null);
//		f.setVisible(true);
//	}

	public void setSelectedItems(List<? extends Object> objects) {
		clear();

		if (objects != null && objects.size() > 0) {
			for (JCheckBox cb : checkBoxObjectMap.keySet()) {
				if (objects.contains(checkBoxObjectMap.get(cb))) {
					selectCheckBox(cb, true);
				}
			}
			adaptTextField(getSelectedItems());
		}
	}

	private void clear() {
		clearTf();
		for (JCheckBox cb : checkBoxList) {
			selectCheckBox(cb, false);
		}
	}

	private void selectCheckBox(JCheckBox cb, boolean b) {
		cb.setSelected(b);
		setCheckOpacity(cb);
	}

	private void setCheckOpacity(JCheckBox cb) {
		cb.setOpaque(cb.isSelected());
	}

	public void setCheckBgColor(Color checkBgColor) {
		this.checkBgColor = checkBgColor;
	}
}
