package be.smd.i18n.demo.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import be.smd.i18n.demo.ui.ControlTree.ControlTreeNode;
import be.smd.i18n.demo.ui.controls.LabelsControlPanel;

public class DemoPanel extends JPanel implements MouseListener{

	/** The Serial Version UID for DemoPanel */
	private static final long serialVersionUID = -2286109442321306384L;
	
	private ControlTree controlTree;
	
	private JPanel contentPanel;
	
	public DemoPanel(){
		initComponents();
		layoutComponents();
	}

	private void layoutComponents() {
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JScrollPane controlTreePane = new JScrollPane(controlTree);
		controlTreePane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		splitPane.setLeftComponent(controlTreePane);
		splitPane.setRightComponent(contentPanel);
		splitPane.setDividerLocation(200);
		add(splitPane, BorderLayout.CENTER);
	}

	private void initComponents() {
		createControlTree();
		createContentPanel();
	}

	private void createContentPanel() {
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBackground(Color.white);
		contentPanel.add(new LabelsControlPanel(), BorderLayout.CENTER);
	}

	private void createControlTree() {
		controlTree = new ControlTree();
		controlTree.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		
		JPanel controlPanel = null;
		TreePath selectedPath = controlTree.getPathForLocation(e.getX(), e.getY());
		if(selectedPath != null){
			DefaultMutableTreeNode selectedNode  = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
			if(selectedNode instanceof ControlTreeNode){
				if((controlPanel = ((ControlTreeNode)selectedNode).getControlPanel()) != null){
					contentPanel.removeAll();
					contentPanel.add(controlPanel, BorderLayout.CENTER);
					contentPanel.repaint();
					contentPanel.revalidate();
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e)	{}

	public void mouseExited(MouseEvent e) 	{}

	public void mousePressed(MouseEvent e) 	{}

	public void mouseReleased(MouseEvent e) {}
}
