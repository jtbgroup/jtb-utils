package be.vds.jtb.jtbswingdemo;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;

import be.vds.jtb.swing.component.reflection.ReflectionPane;
import be.vds.jtb.swing.utils.GraphicsUtilities;

public class ReflectionPanel extends JPanel {

	public ReflectionPanel() {
		init();
	}

	private void init() {
		try {
			ReflectionPane p = new ReflectionPane(
					GraphicsUtilities.loadCompatibleImage(getClass()
							.getResource("/images/about_200.png")));
			p.setPreferredSize(new Dimension(300, 300));
			this.add(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ReflectionPane p2 = new ReflectionPane();
			p2.setBufferedImage(GraphicsUtilities
					.loadCompatibleImage(getClass().getResource(
							"/images/about_200.png")));
			p2.setPreferredSize(new Dimension(300, 300));
			this.add(p2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
