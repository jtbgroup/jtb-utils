package be.vds.jtb.jtbswingdemo;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class DemoFrame extends JFrame {

	private JTree tree;
	private JPanel detailPanel;
	private CardLayout cardLayout;
	private DefaultMutableTreeNode root;
	private Map<String, JComponent> componentsMap = new HashMap<String, JComponent>();
	private DefaultTreeModel treeModel;

	public DemoFrame() {
		init();
	}

	private void init() {
		setTitle("Jt'B Swing Demo");
		this.getContentPane().add(createContentPane());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fillComponents();
	}

	private void fillComponents() {
		DefaultMutableTreeNode node = createAndRegisterNode(root,
				"Simple Component");
		createAndRegisterLeaf(node, "Search box", createSearchBoxPanel());
		createAndRegisterLeaf(node, "Multiselection box", createMultiselectionBoxPanel());
		createAndRegisterLeaf(node, "Reflection Panel", createReflectionPanel());
		createAndRegisterLeaf(node, "Duration Component", createDurationPanel());

		treeModel.nodeStructureChanged(root);
	}

	private JComponent createDurationPanel() {
		return new DurationPanel();
	}

	private JComponent createReflectionPanel() {
		return new ReflectionPanel();
	}

	private JComponent createMultiselectionBoxPanel() {
		return new MultiselectionBoxPanel();
	}

	private JComponent createSearchBoxPanel() {
		return new SearchBoxPanel();
	}

	private DefaultMutableTreeNode createAndRegisterLeaf(
			DefaultMutableTreeNode parentNode, String name, JComponent component) {
		DefaultMutableTreeNode node = createNode(name);
		parentNode.add(node);

		detailPanel.add(component, name);
		componentsMap.put(name, component);

		return node;
	}

	private DefaultMutableTreeNode createAndRegisterNode(
			DefaultMutableTreeNode parentNode, String name) {
		DefaultMutableTreeNode node = createNode(name);
		parentNode.add(node);
		return node;
	}

	private DefaultMutableTreeNode createNode(String name) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);
		return node;
	}

	private Component createContentPane() {
		JSplitPane contentPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		contentPane.setLeftComponent(createComponentsTree());
		contentPane.setRightComponent(createComponentDetail());
		contentPane.setDividerLocation(150);
		return contentPane;
	}

	private Component createComponentsTree() {
		root = new DefaultMutableTreeNode();
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		tree.setRootVisible(false);

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode path = (DefaultMutableTreeNode) ((JTree) e
						.getSource()).getSelectionPath().getLastPathComponent();
				String s = path.getUserObject().toString();

				if (null == componentsMap.get(s)) {
					cardLayout.show(detailPanel, "default");
				} else {
					cardLayout.show(detailPanel, s);
				}
			}
		});

		JScrollPane scroll = new JScrollPane(tree);
		return scroll;
	}

	private Component createComponentDetail() {
		cardLayout = new CardLayout();
		detailPanel = new JPanel(cardLayout);

		JPanel p = new JPanel();
		p.add(new JLabel("DEMO"));
		detailPanel.add(p, "default");

		return detailPanel;
	}

}
