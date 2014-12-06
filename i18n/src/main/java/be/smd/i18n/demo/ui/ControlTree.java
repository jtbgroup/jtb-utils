package be.smd.i18n.demo.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.controls.BoxesControlPanel;
import be.smd.i18n.demo.ui.controls.ButtonsControlPanel;
import be.smd.i18n.demo.ui.controls.CheckBoxesControlPanel;
import be.smd.i18n.demo.ui.controls.HyperlinksControlPanel;
import be.smd.i18n.demo.ui.controls.LabelsControlPanel;
import be.smd.i18n.demo.ui.controls.RadioButtonsControlPanel;
import be.smd.i18n.demo.ui.util.IconManager;
import be.smd.i18n.swing.util.I18nUIProperties;

public class ControlTree extends JTree{

	/** The Serial Version UID for ControlTree */
	private static final long serialVersionUID = -3790225821024552438L;
	
	/** Creates a ControlTree */
	public ControlTree(){
		initTree();
		setRootVisible(false);
		setShowsRootHandles(true);
		setCellRenderer(new ControlTreeCellRenderer());
		expandTree();
	}

	/** Creates the nodes for the tree */
	private void initTree() {
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		
		DefaultMutableTreeNode parent = new ControlTreeNode("labels.label");
		parent.add(new ControlTreeNode("label.class.label", new LabelsControlPanel()));
		parent.add(new ControlTreeNode("link.class.label", new HyperlinksControlPanel()));
		root.add(parent);

		parent = new ControlTreeNode("buttons.label");
		parent.add(new ControlTreeNode("button.class.label", new ButtonsControlPanel()));
		parent.add(new ControlTreeNode("checkBox.class.label", new CheckBoxesControlPanel()));
		parent.add(new ControlTreeNode("radioButton.class.label", new RadioButtonsControlPanel()));
		root.add(parent);
		
		
		parent = new ControlTreeNode("boxes.label");
		parent.add(new ControlTreeNode("box.class.label", new BoxesControlPanel()));
		root.add(parent);
		
		((DefaultTreeModel)getModel()).setRoot(root);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if(I18nUIProperties.isTextAntiAliasingEnabled())
    		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		super.paintComponent(g2d);
	}
	
	/**
	 * Expands all the nodes of the tree
	 */
	public void expandTree() {
		int row = 0;
		while (row < getRowCount()) {
			expandRow(row);
			row++;
		}
	}
	
	public class ControlTreeNode extends DefaultMutableTreeNode{

		/** The Serial Version UID for ControlTreeNode */
		private static final long serialVersionUID = 520403607179175386L;
		
		private String nodeTextBundleKey;
		
		protected ControlTreeNode(String nodeTextBundleKey, JPanel controlPanel){
			this.nodeTextBundleKey = nodeTextBundleKey;
			setUserObject(controlPanel);
		}
		
		protected ControlTreeNode(String nodeTextBundleKey){
			this(nodeTextBundleKey, null);
		}
		
		public JPanel getControlPanel(){
			if(userObject instanceof JPanel)
				return (JPanel)userObject;
			return null;
		}
		
		@Override
		public String toString() {
			return I18nResourceManager.sharedInstance().getString(nodeTextBundleKey);
		}
	}
	
	private class ControlTreeCellRenderer extends DefaultTreeCellRenderer{

		/** The Serial Version UID for ControlTreeCellRenderer */
		private static final long serialVersionUID = -8677160057772602240L;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {
			
			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			
			if(value instanceof ControlTreeNode){
				ControlTreeNode node = (ControlTreeNode)value;
				if(node.getControlPanel() != null)
					setIcon(IconManager.CONTROL);
				else
					setIcon(null);
			}
			else
				setIcon(null);
			
			return this;
		}
		
		
	}
}
